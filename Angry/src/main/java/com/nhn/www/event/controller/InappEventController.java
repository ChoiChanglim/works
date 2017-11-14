package com.nhn.www.event.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.base.constant.SessionScopeBean;
import com.nhn.common.exception.CustomException;
import com.nhn.common.security.RobotSubmitSecurity;
import com.nhn.common.util.DateUtil;
import com.nhn.common.util.NumberUtil;
import com.nhn.common.util.RequestUtil;
import com.nhn.game.constant.RewardType;
import com.nhn.www.event.bean.BannerEventItem;
import com.nhn.www.event.bean.BannerEventItemImg;
import com.nhn.www.event.bean.BannerEventItemImgKey;
import com.nhn.www.event.bean.BannerEventResultKey;
import com.nhn.www.event.constant.FlowerInfo.ResultMessage;
import com.nhn.www.event.constant.FlowerInfo.RewardItem;
import com.nhn.www.event.service.BannerEventService;
import com.nhn.www.event.service.FlowerEventService;

@RequestMapping("/event")
@Controller
public class InappEventController {
    private static Logger LOG = LoggerFactory.getLogger(InappEventController.class);
    public static int TestFlowerCount = 0;

    @Inject
    Provider<SessionScopeBean> sessionScopebeanFactory;

    @Autowired
    FlowerEventService flowerEventService;

    public static enum FlowerCssEnum{
        GLOBAL("/resources/css/event/global.css"),
        KOREA("/resources/css/event/korea.css");

        String fileName;
        private FlowerCssEnum(String fileName){
            this.fileName = fileName;
        }
        public String getFileName(){
            return fileName;
        }
    }
    @RequestMapping(value = "/flower")
    public ModelAndView flower(HttpServletRequest req, HttpServletResponse res
            ) {
        ModelAndView modelAndView = new ModelAndView();
        SessionScopeBean sessionScopeBean = this.sessionScopebeanFactory.get();
        long usn = sessionScopeBean.getSno();
        LanguageEnum languageEnum = LanguageEnum.GetRegistPageLanguage(sessionScopeBean.getLanguage());
        modelAndView.addObject("LanguageEnum", languageEnum);
        int flowerCount = flowerEventService.getTotalFlowerCount(usn) - flowerEventService.getUsedFlowerCount(usn)+TestFlowerCount;
        if(flowerCount < 0){
            flowerCount = 0;
        }

        modelAndView.addObject("flowerCount", flowerCount);
        modelAndView.addObject("usn", usn);

        if(languageEnum == LanguageEnum.KR){
            modelAndView.addObject("CSS_FILE_NAME", FlowerCssEnum.KOREA.getFileName());
        }else{
            modelAndView.addObject("CSS_FILE_NAME", FlowerCssEnum.GLOBAL.getFileName());
        }
        modelAndView.addObject("EntryItemList", RewardItem.GetEntryItemList(languageEnum));
        modelAndView.addObject("ReceiptItemList", RewardItem.GetReceiptItemList());

        //남은시간
        Date nextDate = DateUtil.convertStringToDate("2017-05-11 11:00:00", "yyyy-MM-dd HH:mm:ss");
        long now = Calendar.getInstance().getTimeInMillis();
        long remainSecond = nextDate.getTime() - now;
        modelAndView.addObject("now", Calendar.getInstance().getTimeInMillis());
        modelAndView.addObject("remainTime", remainSecond);
        modelAndView.addObject("remainDay", remainSecond /  1000 / 60 / 60 / 24 );

        HashMap<String, Integer> mywin = flowerEventService.myWinStatus(usn, languageEnum);
        modelAndView.addObject("mywin", mywin);
        return modelAndView;
    }

    @RequestMapping(value = "/flower_entry")
    public synchronized ModelAndView flower_entry(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value="local", required = false, defaultValue="") String local,
            @RequestParam(value="item_code", required = false, defaultValue="") String item_code,
            @RequestParam(value="usn", required = false, defaultValue="0") long usn
            ) {
        SessionScopeBean sessionScopeBean = this.sessionScopebeanFactory.get();
        ModelAndView modelAndView = new ModelAndView();

        if(0 == usn){
            throw CustomException.INVALID_USN;
        }

        RewardItem rewardItem = RewardItem.GetRewardItem(item_code);
        if(null == rewardItem){
            LOG.info("Invalid itemCode!");
            throw CustomException.INVALID_EVENT_INFO;
        }


        LanguageEnum languageEnum = LanguageEnum.GetRegistPageLanguage(sessionScopeBean.getLanguage());
        modelAndView.addObject("LanguageEnum", languageEnum);

        ResultMessage resultMessage = flowerEventService.entryItem(usn, rewardItem, languageEnum);
        modelAndView.addObject("result", resultMessage.name());

        int flowerCount = flowerEventService.getTotalFlowerCount(usn) - flowerEventService.getUsedFlowerCount(usn) + TestFlowerCount;
        if(flowerCount < 0){
            flowerCount = 0;
        }

        modelAndView.addObject("item_code", item_code);
        modelAndView.addObject("flowerCount", flowerCount);
        modelAndView.addObject("trendTime", NumberUtil.randomRange(800, 5000));
        HashMap<String, Integer> mywin = flowerEventService.myWinStatus(usn, languageEnum);
        modelAndView.addObject("mywin", mywin);
        modelAndView.addObject("myWinCount", mywin.keySet().size());


        return modelAndView;
    }

    /**
     * 배너 어드민 나중에 삭제
     */

    @Autowired
    BannerEventService bannerEventService;

    @RequestMapping(value = "/banner_admin")
    public ModelAndView banner_event_admin(HttpServletRequest req, HttpServletResponse res
            ) {
        ModelAndView modelAndView = new ModelAndView();

        if(RequestUtil.isAllowByIpExceptOffice(RobotSubmitSecurity.GetRemoteAddr(req)) == false){ //사무실이 아닌 경우에만 체크
            throw CustomException.NOT_ALLOWED_USER;
        }

        modelAndView.addObject("adminBannerList", bannerEventService.adminBannerList());
        modelAndView.addObject("adminBannerResultList", bannerEventService.adminBannerResultList());
        modelAndView.addObject("adminBannerItemImgList", bannerEventService.adminBannerItemImgList());
        modelAndView.addObject("RewardTypeList", RewardType.GetRewardTypeListForHTML());
        modelAndView.addObject("liveItemAndUrlList", bannerEventService.getLiveItemsAndLocalUrl());
        modelAndView.addObject("liveItemList", bannerEventService.getLiveItems());
        modelAndView.addObject("LanguageList", LanguageEnum.GetLanguageList());
        return modelAndView;
    }
    @RequestMapping(value = "/banner_item_insert")
    public ModelAndView banner_item_insert(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value="prob", required = false, defaultValue="0") double prob,
            @RequestParam(value="descript", required = false, defaultValue="") String descript,
            @RequestParam(value="itemSerial", required = false, defaultValue="0") int itemSerial,
            @RequestParam(value="itemType", required = false, defaultValue="0") int itemType,
            @RequestParam(value="itemCount", required = false, defaultValue="0") int itemCount,
            @RequestParam(value="used", required = false, defaultValue="") String used

            ) {
        ModelAndView modelAndView = new ModelAndView();
        BannerEventItem item = new BannerEventItem();
        item.setProb(prob);
        item.setDescript(descript);
        item.setItemSerial(itemSerial);
        item.setItemType(itemType);
        item.setItemCount(itemCount);
        item.setUsed(used);

        bannerEventService.insertBannerEventItem(item);
        return modelAndView;
    }
    @RequestMapping(value = "/banner_item_update")
    public ModelAndView banner_item_update(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value="itemKey", required = false, defaultValue="0") int itemKey,
            @RequestParam(value="prob", required = false, defaultValue="0") double prob,
            @RequestParam(value="descript", required = false, defaultValue="") String descript,
            @RequestParam(value="itemSerial", required = false, defaultValue="0") int itemSerial,
            @RequestParam(value="itemType", required = false, defaultValue="0") int itemType,
            @RequestParam(value="itemCount", required = false, defaultValue="0") int itemCount,
            @RequestParam(value="used", required = false, defaultValue="") String used

            ) {
        ModelAndView modelAndView = new ModelAndView();
        BannerEventItem item = new BannerEventItem();
        item.setItemKey(itemKey);
        item.setProb(prob);
        item.setDescript(descript);
        item.setItemSerial(itemSerial);
        item.setItemType(itemType);
        item.setItemCount(itemCount);
        item.setUsed(used);

        bannerEventService.updateBannerEventItem(item);
        return modelAndView;
    }

    @RequestMapping(value = "/banner_img_insert")
    public ModelAndView banner_img_insert(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value="itemKey", required = false, defaultValue="0") int itemKey,
            @RequestParam(value="local", required = false, defaultValue="KR") String local,
            @RequestParam(value="imgurl", required = false, defaultValue="") String imgurl

            ) {
        ModelAndView modelAndView = new ModelAndView();
        BannerEventItemImg img = new BannerEventItemImg();
        img.setItemKey(itemKey);
        img.setLocal(local);
        img.setUrl(imgurl);
        bannerEventService.insertBannerEventItemImg(img);
        return modelAndView;
    }

    @RequestMapping(value = "/banner_img_update")
    public ModelAndView banner_img_update(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value="itemKey", required = false, defaultValue="0") int itemKey,
            @RequestParam(value="local", required = false, defaultValue="KR") String local,
            @RequestParam(value="imgurl", required = false, defaultValue="") String imgurl

            ) {
        ModelAndView modelAndView = new ModelAndView();
        BannerEventItemImg img = new BannerEventItemImg();
        img.setItemKey(itemKey);
        img.setLocal(local);
        img.setUrl(imgurl);

        bannerEventService.updateBannerEventItemImg(img);
        return modelAndView;
    }

    @RequestMapping(value = "/deleteResult")
    public ModelAndView deleteResult(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value="sno", required = false, defaultValue="") long sno

            ) {
        ModelAndView modelAndView = new ModelAndView();
        BannerEventResultKey resultKey = new BannerEventResultKey();
        resultKey.setSno(sno);
        resultKey.setDay(new Date());

        bannerEventService.deleteBannerEventResult(resultKey);
        return modelAndView;
    }
    @RequestMapping(value = "/deleteImg")
    public ModelAndView deleteImg(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value="itemKey", required = false, defaultValue="0") int itemKey,
            @RequestParam(value="local", required = false, defaultValue="") String local

            ) {
        ModelAndView modelAndView = new ModelAndView();
        BannerEventItemImgKey imgKey = new BannerEventItemImgKey();
        imgKey.setItemKey(itemKey);
        imgKey.setLocal(local);

        bannerEventService.deleteBannerImg(imgKey);
        return modelAndView;
    }

}
