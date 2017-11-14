package com.nhn.www.event.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.common.exception.CustomException;
import com.nhn.common.util.DateUtil;
import com.nhn.common.util.NumberUtil;
import com.nhn.elog.bean.AbsEventlog;
import com.nhn.elog.service.EventlogService;
import com.nhn.game.service.AdminApiService;
import com.nhn.www.event.bean.FlowerEventInfo;
import com.nhn.www.event.bean.FlowerEventInfoByDate;
import com.nhn.www.event.bean.FlowerEventInfoByDateExample;
import com.nhn.www.event.bean.FlowerEventInfoByDateKey;
import com.nhn.www.event.bean.FlowerEventLog;
import com.nhn.www.event.bean.FlowerEventLogExample;
import com.nhn.www.event.bean.MyWin;
import com.nhn.www.event.constant.FlowerInfo;
import com.nhn.www.event.constant.FlowerInfo.MissionLog;
import com.nhn.www.event.constant.FlowerInfo.ResultMessage;
import com.nhn.www.event.constant.FlowerInfo.RewardItem;
import com.nhn.www.event.controller.InappEventController;
import com.nhn.www.event.mapper.FlowerEventInfoByDateMapper;
import com.nhn.www.event.mapper.FlowerEventInfoMapper;
import com.nhn.www.event.mapper.FlowerEventLogMapper;

@Transactional(value="baseTransactionManager", readOnly=false)
@Service
public class FlowerEventServiceImpl implements FlowerEventService{
    private static Logger LOG = LoggerFactory.getLogger(FlowerEventServiceImpl.class);
    @Autowired
    EventlogService eventlogService;

    @Autowired
    FlowerEventLogMapper flowerEventLogMapper;

    @Autowired
    FlowerEventInfoMapper flowerEventInfoMapper;

    @Autowired
    FlowerEventInfoByDateMapper flowerEventInfoByDateMapper;

    @Autowired
    AdminApiService adminApiService;

    /**
     * 꽃잎 갯수
     */
    @Override
    public int getTotalFlowerCount(long usn) {
        int count = 0;
        List<AbsEventlog> list = eventlogService.getUserEventlogList(usn, FlowerInfo.MissionLog.GetMissionLogList());
        Iterator<AbsEventlog> iter = list.iterator();
        while(iter.hasNext()){
            AbsEventlog log = iter.next();
            count = count + MissionLog.GetMissionPoint(log.getLog());
        }
        return count;
    }

    /**
     * 사용한 꽃잎 갯수
     * @param usn
     * @return
     */
    @Override
    public int getUsedFlowerCount(long usn){
        int count = 0;
        FlowerEventLogExample flowerEventLogExample = new FlowerEventLogExample();
        flowerEventLogExample.createCriteria().andUsnEqualTo(usn);
        List<FlowerEventLog> list = flowerEventLogMapper.selectByExample(flowerEventLogExample);
        Iterator<FlowerEventLog> iter = list.iterator();
        while(iter.hasNext()){
            FlowerEventLog log = iter.next();
            count = count + RewardItem.GetUsedRewardPoint(log.getItemCode());
        }
        return count;
    }

    /**
     * 아이템 응모 및 지급
     */
    @Override
    public ResultMessage entryItem(long usn, RewardItem rewardItem, LanguageEnum languageEnum) {
        FlowerEventLog log = new FlowerEventLog();
        log.setItemCode(rewardItem.name());
        log.setLocal(languageEnum.name());
        log.setUsn(usn);
        log.setRegdate(new Date());

        FlowerEventInfo itemInfo = getFlowerEventInfo(rewardItem.name());


        int flowerCount = getTotalFlowerCount(usn) - getUsedFlowerCount(usn) + InappEventController.TestFlowerCount;
        if(rewardItem.getFlower() > flowerCount){  //꽃잎 유효성 검사
            return ResultMessage.NOT_ENOUGH_FLOWER;
        }
        if(rewardItem.getIsLimited()){
            int givenItemCount = getGivenItemCount(rewardItem.name());
            int allowedItemCount = itemInfo.getMaxCount() - givenItemCount;
            if(allowedItemCount < 1){  //전체 소진 확인
                LOG.info(rewardItem.name()+" SOLD OUT!, 지급 제한한 총 갯수:"+itemInfo.getMaxCount()+", 지급된 총 갯수:"+givenItemCount);
                log.setResult("F");
                flowerEventLogMapper.insert(log);
                return ResultMessage.SOLD_OUT;
            }

            /* 날짜별 수량제한 옵션 제거. 2017-04-17
            if(isLimitedByDate(rewardItem.name())){  //날짜별 수량 제한 아이템인 경우
                int todayMaxAllowedItemCount = getCountGiveAllowedByDate(rewardItem.name());
                int todayGivenItemCount = getTodayGivenItemCount(rewardItem.name());
                int todayAllowdCount = todayMaxAllowedItemCount - todayGivenItemCount;
                if(todayAllowdCount < 1){  //오늘만 소진 확인
                    LOG.info(rewardItem.name()+" TODAY SOLD OUT!, 오늘 지급 가능한 총 갯수:"+todayMaxAllowedItemCount+", 오늘 지급된 총 갯수:"+todayGivenItemCount);
                    log.setResult("F");
                    flowerEventLogMapper.insert(log);
                    return ResultMessage.TODAY_SOLD_OUT;
                }
            }*/
        }


        boolean isWinner = NumberUtil.isInProbability(itemInfo.getProb());
        log.setResult(String.valueOf(isWinner).toUpperCase().substring(0, 1));

        try{
            flowerEventLogMapper.insert(log);
            if(isWinner == false){
                return ResultMessage.LEAVING_OUT;
            }


            if(isWinner == true && itemInfo.getItemCount() > 0){
                boolean itemSend = adminApiService.isSendItemMail(usn, itemInfo.getItemType(), itemInfo.getItemSerial(), itemInfo.getItemCount());
                if(itemSend){
                    return ResultMessage.ITEM_SEND_SUCCESS;
                }
            }
        }catch(Exception e){
            LOG.error(e.toString());
            return ResultMessage.SYSTEM_ERROR;
        }


        return ResultMessage.ITEM_ENTRY_SUCCESS;
    }

    /**
     * 아이템 확률 정보 조회
     * @param itemCode
     * @return
     */
    public FlowerEventInfo getFlowerEventInfo(String itemCode){
        FlowerEventInfo flowerEventInfo = flowerEventInfoMapper.selectByPrimaryKey(itemCode);
        if(null == flowerEventInfo){
            LOG.error("Invalid item code!");
            throw CustomException.INVALID_EVENT_INFO;
        }
        return flowerEventInfo;
    }

    /**
     * 오늘의 지급가능한 상품 갯수
     * @param itemCode
     * @return
     */
    public int getCountGiveAllowedByDate(String itemCode){
        String todayStr = DateUtil.convertDateToString(new Date(), "yyyy-MM-dd");
        FlowerEventInfoByDateKey key = new FlowerEventInfoByDateKey();
        key.setByDate(todayStr);
        key.setItemCode(itemCode);

        FlowerEventInfoByDate flowerEventInfoByDate = flowerEventInfoByDateMapper.selectByPrimaryKey(key);
        if(null == flowerEventInfoByDate){
            return 0;
        }
        return flowerEventInfoByDate.getItemCount();
    }

    /**
     * 날짜별로 제한되어있는 아이템인지 확인
     * @param itemCode
     * @return
     */
    public boolean isLimitedByDate(String itemCode){
        FlowerEventInfoByDateExample example = new FlowerEventInfoByDateExample();
        example.createCriteria().andItemCodeEqualTo(itemCode);
        int count = flowerEventInfoByDateMapper.countByExample(example);
        if(count == 0){
            return false;
        }
        return true;
    }

    /**
     * 지금까지 지급된 아이템 갯수
     * @param itemCode
     * @return
     */
    public int getGivenItemCount(String itemCode){
        FlowerEventLogExample example = new FlowerEventLogExample();
        example.createCriteria().andItemCodeEqualTo(itemCode).andResultEqualTo("T");
        int count = flowerEventLogMapper.countByExample(example);
        return count;
    }
    /**
     * 오늘 지급된 아이템 갯수
     * @param itemCode
     * @return
     */
    public int getTodayGivenItemCount(String itemCode){
        FlowerEventLogExample example = new FlowerEventLogExample();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        example.createCriteria().andItemCodeEqualTo(itemCode).andResultEqualTo("T").andRegdateBetween(c.getTime(), new Date());
        int count = flowerEventLogMapper.countByExample(example);
        return count;
    }

    /**
     * 나의 당첨 목록
     */
    @Override
    public HashMap<String, Integer> myWinStatus(long usn, LanguageEnum languageEnum) {
        List<String> langList = new ArrayList<String>();
        langList.add("RECEIPT1");
        langList.add("RECEIPT2");
        langList.add("RECEIPT3");
        langList.add("RECEIPT4");
        langList.add("RECEIPT5");
        if(languageEnum == LanguageEnum.KR){
            langList.add("KOREAN_ENTRY1");
            langList.add("KOREAN_ENTRY2");
            langList.add("KOREAN_ENTRY3");
            langList.add("KOREAN_ENTRY4");
        }else{
            langList.add("GLOBAL_ENTRY1");
            langList.add("GLOBAL_ENTRY2");
            langList.add("GLOBAL_ENTRY3");
            langList.add("GLOBAL_ENTRY4");
        }

        FlowerEventLogExample example = new FlowerEventLogExample();
        example.createCriteria().andUsnEqualTo(usn).andItemCodeIn(langList).andResultEqualTo("T");


        HashMap<String, Integer> result = new HashMap<String, Integer>();
        List<MyWin> list = flowerEventLogMapper.myWinStatus(example);
        Iterator<MyWin> iter = list.iterator();
        while(iter.hasNext()){
            MyWin win = iter.next();
            result.put(win.getItemCode(), win.getCnt());
        }
        return result;
    }


}
