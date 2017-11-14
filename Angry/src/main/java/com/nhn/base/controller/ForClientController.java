package com.nhn.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.nhn.base.constant.AngryMappingJacksonJsonView;
import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.base.notice.service.NoticeService;
import com.nhn.common.util.RequestUtil;
import com.nhn.game.service.MasterService;
import com.nhn.www.event.bean.BannerEventItemAndImg;
import com.nhn.www.event.service.BannerEventService;

/**
 * 클라와 통신할때 쿠키를 사용하지 못하는경우도 있다.
 * 인터셉터 태우지 않음.
 * 파라미터로 필요한정보 그때그때 요청 받아 처리하자.
 * 물론 세션스코프빈도 사용금지.
 * @author WOW
 *
 */
@RequestMapping("/service")
@Controller
public class ForClientController {
    private static Logger LOG = LoggerFactory.getLogger(ForClientController.class);

    @Autowired
    NoticeService noticeService;

    @Autowired
    MasterService masterService;

    @Autowired
    BannerEventService bannerEventService;

    @SuppressWarnings("serial")
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="This country is not supported.")  // 400
    public class NotFoundCountryException extends RuntimeException {

    }
    /**
     * 클라에 팝업공지 내용 전달.
     * @param req
     * @param res
     * @param local
     * @param level
     * @param regist_time
     * @param sno
     * @return
     */
    @RequestMapping(value = "/popup")
    public ModelAndView popup(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value = "local", required = false, defaultValue = "") String local,
            @RequestParam(value = "level", required = false, defaultValue = "0") int level,
            @RequestParam(value = "regist_time", required = false, defaultValue = "0") long regist_time,  //unixtime
            @RequestParam(value = "sno", required = false, defaultValue = "0") long sno  //회원번호
            ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(new AngryMappingJacksonJsonView());
        local = local.toUpperCase().replace("US", "en");
        LanguageEnum languageEnum = LanguageEnum.GetSupportLanguage(local);

        List<HashMap<String, Object>> popupList = noticeService.getPopupNoticeList(languageEnum, level, regist_time);



        HashMap<String, String> headerMap = RequestUtil.HeaderToMap(req);
        if(headerMap.containsKey("x-unity-version") && level > 2 && sno > 0){    //배너 이벤트 참여 조건.
            boolean isPossibleApply = bannerEventService.isPossibleApply(sno, languageEnum);  //참여 가능 여부 확인
            if(isPossibleApply == true){
                LOG.debug("Banner Event On! - "+sno);
                BannerEventItemAndImg todaysItem = bannerEventService.bannerEventApply(sno, languageEnum);
                bannerEventService.bannerEventItemSend(sno, todaysItem);
                HashMap<String, Object> eventData = bannerEventService.createWebviewData(todaysItem.getUrl());
                List<HashMap<String, Object>> popupListExtends = new ArrayList<HashMap<String, Object>>();
                popupListExtends.add(eventData);
                popupListExtends.addAll(popupList);

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("items", popupListExtends);
                modelAndView.addObject("popup", map);
                return modelAndView;
            }
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("items", popupList);
        modelAndView.addObject("popup", map);

        return modelAndView;
    }

    /**
     * 클라에 로딩이미지 전달.
     * @param req
     * @param res
     * @param local
     * @return
     */
    @RequestMapping(value = "/loading")
    public ModelAndView icon_popup(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value = "local", required = false, defaultValue = "") String local
            ){
        ModelAndView modelAndView = new ModelAndView();

        local = local.toUpperCase().replace("US", "en");
        LanguageEnum languageEnum = LanguageEnum.GetSupportLanguage(local);

        List<HashMap<String, Object>> popupList = noticeService.getLoadingImgList(languageEnum);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("items", popupList);
        modelAndView.addObject("popup", map);
        modelAndView.setView(new AngryMappingJacksonJsonView());
        return modelAndView;
    }
    /**
     * 마스터데이터 확인용
     * @param req
     * @param res
     * @param key
     * @param ver
     * @return
     */
    /*@RequestMapping(value = "/masterdata")
    public ModelAndView masterdata(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value = "key", required = true) String key,
            @RequestParam(value = "ver", required = true) String ver
            ){
        ModelAndView modelAndView = new ModelAndView();

        if(RequestUtil.isAllowByIp(RequestUtil.PlayMusiumAddr, req.getRemoteAddr()) == false){
            throw CustomException.NOT_ALLOWED_USER;
        }

        modelAndView.setView(new AngryMappingJacksonJsonView());

        JSONObject result = masterService.get(key, ver);


        HashMap<String, Object> masterDataMap = null;
        try {
            masterDataMap = new ObjectMapper().readValue(result.toString(), HashMap.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        modelAndView.addObject("result", masterDataMap);
        return modelAndView;
    }*/

}
