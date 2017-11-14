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
import com.nhn.common.util.DateUtil;
import com.nhn.common.util.NumberUtil;
import com.nhn.game.service.AdminApiService;
import com.nhn.www.event.bean.BannerEventItem;
import com.nhn.www.event.bean.BannerEventItemAndImg;
import com.nhn.www.event.bean.BannerEventItemImg;
import com.nhn.www.event.bean.BannerEventItemImgExample;
import com.nhn.www.event.bean.BannerEventItemImgKey;
import com.nhn.www.event.bean.BannerEventResult;
import com.nhn.www.event.bean.BannerEventResultExample;
import com.nhn.www.event.bean.BannerEventResultKey;
import com.nhn.www.event.mapper.BannerEventItemImgMapper;
import com.nhn.www.event.mapper.BannerEventItemMapper;
import com.nhn.www.event.mapper.BannerEventResultMapper;

/**
 * 배너이벤트는 개발만 되었고 진행되지 않았음.
 * @author WOW
 *
 */
@Service
@Transactional(value="baseTransactionManager", readOnly=false)
public class BannerEventServiceImpl implements BannerEventService{
    private static Logger LOG = LoggerFactory.getLogger(BannerEventServiceImpl.class);

    @Autowired
    BannerEventItemMapper bannerEventItemMapper;

    @Autowired
    BannerEventItemImgMapper bannerEventItemImgMapper;

    @Autowired
    BannerEventResultMapper bannerEventResultMapper;

    @Autowired
    AdminApiService adminApiService;

    /**
     * 배너 이벤트 참여 가능여부 확인
     * true  = 참여 가능
     * false = 참여 불가능
     */
    @Override
    public boolean isPossibleApply(long sno, LanguageEnum languageEnum) {
        List<BannerEventItemAndImg> itemList = getBannerEventItemAndImg(languageEnum);
        if(itemList.size() == 0){
            LOG.debug("BannerEvent Item and Img not setting!");
            return false;
        }
        boolean isComplete = isBannerEventTodayComplete(sno);
        if(isComplete == true){
            LOG.debug("BannerEvent already complete! - sno:"+sno);
            return false;
        }

        return true;
    }

    /**
     * 배너 이벤트 상품 정보 및 이미지 정보 목록
     * @param languageEnum
     * @return
     */
    @Override
    public List<BannerEventItemAndImg> getBannerEventItemAndImg(LanguageEnum languageEnum){
        List<BannerEventItemAndImg> list = bannerEventItemMapper.selectBannerEventItemAndImg(languageEnum.name());
        return list;
    }

    /**
     * 배너 이벤트 오늘 참여했는지 여부
     */
    @Override
    public boolean isBannerEventTodayComplete(long sno) {
        BannerEventResultKey key = new BannerEventResultKey();
        key.setSno(sno);
        key.setDay(new Date());

        BannerEventResult result = bannerEventResultMapper.selectByPrimaryKey(key);
        if(null == result){
            return false;
        }
        return true;
    }

    /**
     * 배너 이벤트 오늘 참여 완료
     *
     */
    @Override
    public BannerEventItemAndImg bannerEventApply(long sno, LanguageEnum languageEnum) {
        List<BannerEventItemAndImg> itemList = getBannerEventItemAndImg(languageEnum);
        Iterator<BannerEventItemAndImg> iter = itemList.iterator();
        while(iter.hasNext()){
            BannerEventItemAndImg item = iter.next();
            if(NumberUtil.isInProbability(item.getProb()) == true){
                return item;
            }
        }

        String NoItemUrl = "https://images.toast.com/toast/s629/angry/web/event/banner/5.png";
        BannerEventItemAndImg item = new BannerEventItemAndImg();
        item.setItemKey(0);
        item.setDescript("꽝");
        item.setUrl(NoItemUrl);
        item.setLocal(languageEnum.name());

        return item;

    }

    /**
     * 보상 지급 및 로그
     * @param sno
     * @param item
     */
    @Override
    public void bannerEventItemSend(long sno, BannerEventItemAndImg item){
        BannerEventResult bannerEventResult = new BannerEventResult();
        bannerEventResult.setDay(new Date());
        bannerEventResult.setRegdate(new Date());
        bannerEventResult.setSno(sno);
        bannerEventResult.setItemKey(item.getItemKey());
        bannerEventResult.setLocal(item.getLocal());

        if(item.getItemKey() == 0){ //itemKey:0 = 꽝
            bannerEventResultMapper.insert(bannerEventResult);
        }else{
            boolean isSendMail = adminApiService.isSendItemMail(sno, item.getItemType(), item.getItemSerial(), item.getItemCount());
            if(isSendMail){
                bannerEventResultMapper.insert(bannerEventResult);
            }
        }

    }
    /**
     * 웹뷰 형식과 똑같은 데이타 생성.
     * @param imgUrl
     * @return
     */
    @Override
    public HashMap<String, Object> createWebviewData(String imgUrl){
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("key", 99999);
        data.put("img_addr", imgUrl);
        data.put("link_addr", "");
        data.put("event_view", 0);
        data.put("new_popup", false);
        Calendar start_c = Calendar.getInstance();
        start_c.set(Calendar.HOUR_OF_DAY, 0);
        start_c.set(Calendar.MINUTE, 0);
        start_c.set(Calendar.SECOND, 0);

        Calendar end_c = Calendar.getInstance();
        end_c.set(Calendar.HOUR_OF_DAY, 23);
        end_c.set(Calendar.MINUTE, 59);
        end_c.set(Calendar.SECOND, 59);

        data.put("event_start", (start_c.getTimeInMillis() / 1000) );
        data.put("event_end", (end_c.getTimeInMillis() / 1000) );
        data.put("param", false);
        return data;

    }

    //########################################################################################################################################
    //########################################################################################################################################
    //########################################################################################################################################
    //########################################################################################################################################
    //########################################################################################################################################
    //########################################################################################################################################
    //########################################################################################################################################
    //########################################################################################################################################

    @Override
    public List<BannerEventItem> adminBannerList(){
        List<BannerEventItem> list = bannerEventItemMapper.selectByExample(null);
        return list;
    }

    @Override
    public List<BannerEventResult> adminBannerResultList() {
        BannerEventResultExample example = new BannerEventResultExample();
        String day = DateUtil.convertDateToString(new Date(), "yyyy-MM-dd");
        example.createCriteria().andDayGreaterThanOrEqualTo(DateUtil.convertStringToDate(day, "yyyy-MM-dd"));
        List<BannerEventResult> list = bannerEventResultMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<BannerEventItemImg> adminBannerItemImgList() {
        List<Integer> itemKeyList = new ArrayList<Integer>();

        List<BannerEventItemAndImg> liveItem = getLiveItems();
        Iterator<BannerEventItemAndImg> iter = liveItem.iterator();
        while(iter.hasNext()){
            BannerEventItemAndImg itm = iter.next();
            itemKeyList.add(itm.getItemKey());
        }

        BannerEventItemImgExample example = new BannerEventItemImgExample();
        if(itemKeyList.size() > 0){
            example.createCriteria().andItemKeyIn(itemKeyList);
        }
        List<BannerEventItemImg> list = bannerEventItemImgMapper.selectByExample(example);
        return list;
    }

    @Override
    public void insertBannerEventItem(BannerEventItem item) {
        bannerEventItemMapper.insert(item);

    }

    @Override
    public void updateBannerEventItem(BannerEventItem item) {
        bannerEventItemMapper.updateByPrimaryKey(item);

    }

    @Override
    public void insertBannerEventItemImg(BannerEventItemImg img) {
        bannerEventItemImgMapper.insert(img);

    }

    @Override
    public void updateBannerEventItemImg(BannerEventItemImg img) {
        bannerEventItemImgMapper.updateByPrimaryKey(img);

    }

    @Override
    public void deleteBannerEventResult(BannerEventResultKey resultKey) {
        bannerEventResultMapper.deleteByPrimaryKey(resultKey);

    }

    @Override
    public List<BannerEventItemAndImg> getLiveItemsAndLocalUrl() {
        List<BannerEventItemAndImg> list = bannerEventItemMapper.getLiveItemsAndLocalUrl();
        return list;
    }
    @Override
    public List<BannerEventItemAndImg> getLiveItems() {
        List<BannerEventItemAndImg> list = bannerEventItemMapper.getLiveItems();
        return list;
    }

    @Override
    public void deleteBannerImg(BannerEventItemImgKey imgKey) {
        bannerEventItemImgMapper.deleteByPrimaryKey(imgKey);
    }



}
