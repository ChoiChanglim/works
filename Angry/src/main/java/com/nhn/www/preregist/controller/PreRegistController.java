package com.nhn.www.preregist.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.nhn.base.constant.AppDownLinkEnum;
import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.base.constant.ServerKey.ServerKeyEnum;
import com.nhn.base.constant.SupportOSEnum;
import com.nhn.common.exception.CustomException;
import com.nhn.common.security.RobotSubmitSecurity;
import com.nhn.common.util.AesCipher;
import com.nhn.common.util.EmailValidUtil;
import com.nhn.common.util.RequestUtil;
import com.nhn.common.util.StringUtil;
import com.nhn.www.preregist.bean.PreRegist;
import com.nhn.www.preregist.bean.PreRegistStatus;
import com.nhn.www.preregist.service.PreRegistService;
import com.nhn.www.sns.FacebookContents;
import com.nhn.www.sns.TwitterContents;
import com.nhn.www.sns.service.SnsEventService;

@Controller
@RequestMapping("/pre-regist")
public class PreRegistController {
    private static Logger LOG = LoggerFactory.getLogger(PreRegistController.class);
    public static final int IpPerRegistCount = 50;
    public static final String RefererPattern = "angry.hangame.com";
    public static final long AllowTimeRange = 1000 * 30;     //로봇 방지 클라이언트와 오차 허용 시간(ms) 30초 세팅
    public static final String UserKeyName = "gbukey";


    @Autowired
    PreRegistService preRegistService;

    @Autowired
    SnsEventService snsEventService;


    public static enum CssEnum{
        GLOBAL_MOBILE("/resources/css/teaser/global/mobile.css"),
        GLOBAL_NORMAL("/resources/css/teaser/global/normal.css"),
        KOREA_MOBILE("/resources/css/teaser/kr/mobile.css"),
        KOREA_NORMAL("/resources/css/teaser/kr/normal.css");

        String fileName;
        private CssEnum(String fileName){
            this.fileName = fileName;
        }
        public String getFileName(){
            return fileName;
        }
        public static String GetGlobalCssFileName(Device currentDevice) {
            if(currentDevice.isNormal()){
                return CssEnum.GLOBAL_NORMAL.getFileName();
            }else{
                return CssEnum.GLOBAL_MOBILE.getFileName();
            }

        }

        public static String GetKoreaCssFileName(Device currentDevice) {
            if(currentDevice.isNormal()){
                return CssEnum.KOREA_NORMAL.getFileName();
            }else{
                return CssEnum.KOREA_MOBILE.getFileName();
            }

        }
    }

    public static String GetDeviceType(Device currentDevice) {
        if(currentDevice.isNormal()){
            return "NORMAL";
        }else{
            return "MOBILE";
        }

    }
    /**
     * 사전등록 사이트
     * @param req
     * @param res
     * @param local
     * @return
     */
    @RequestMapping(value = "/{local}/index")
    public ModelAndView pre_regist_global(HttpServletRequest req, HttpServletResponse res,
            @PathVariable String local
            ) {
        RobotSubmitSecurity.CheckPointInitialization(res);
        ModelAndView modelAndView = new ModelAndView();
        if(LanguageEnum.isSuportRegistPageLanguage(local) == false){
            throw CustomException.INVALID_LANGUAGE;
        }
        LanguageEnum languageEnum = LanguageEnum.GetRegistPageLanguage(local);
        //@PathVariable 파라미터는 인터셉터에서 파라미터로 인식하지 않아서 수동으로 쿠키에 로케일 세팅한다.
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(req);
        if (localeResolver == null){
            throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
        }
        localeResolver.setLocale(req, res, StringUtils.parseLocaleString(LanguageEnum.GetRegistPageLanguage(local).name()));

        Device currentDevice = DeviceUtils.getCurrentDevice(req);
        modelAndView.addObject("Device", GetDeviceType(currentDevice));

        modelAndView.addObject("LanguageEnumList", LanguageEnum.PreRegistGlobalLanguageList());
        modelAndView.addObject("LanguageEnum", languageEnum);
        if(languageEnum == LanguageEnum.KR){
            modelAndView.addObject("CSS_FILE_NAME", CssEnum.GetKoreaCssFileName(currentDevice));
            modelAndView.setViewName("teaser/korean");
        }else{
            modelAndView.addObject("CSS_FILE_NAME", CssEnum.GetGlobalCssFileName(currentDevice));
            modelAndView.setViewName("teaser/global");
        }
        //사전등록 완료기록이 쿠키에 있으면
        /*String regist_key = "";
        if(RequestUtil.getCookieMap(req).containsKey(UserKeyName)){
            String userkey_enc = RequestUtil.getCookieMap(req).get(UserKeyName);
            AesCipher cipher  = new AesCipher(ServerKeyEnum.PRE_REGIST.getKey());
            String userkey = cipher.decryptString(userkey_enc);
            if(preRegistService.isRegist(userkey) == true){
                PreRegist preRegist =  preRegistService.getUser(userkey);
                regist_key = preRegist.getUserkey();
            }
        }
        modelAndView.addObject("regist_key", regist_key);
        modelAndView.addObject("reply_list", snsEventService.getReplyTop5(LanguageEnum.GetRegistPageLanguage(local)));*/
        modelAndView.addObject("regist_key", "the_end");

        modelAndView.addObject("FBFanPage", FacebookContents.FacebookFanpageByLanguage.GetFacebookFanpageByLanguage(local));
        modelAndView.addObject("TWFanPage", TwitterContents.TwitterFanpageByLanguage.GetTwitterFanpageByLanguage(local));

        modelAndView.addObject("AosDownUrl", AppDownLinkEnum.GetUrl(languageEnum, SupportOSEnum.AOS));
        modelAndView.addObject("IosDownUrl", AppDownLinkEnum.GetUrl(languageEnum, SupportOSEnum.IOS));

        return modelAndView;
    }


    /**
     * 사전등록 처리
     * @param req
     * @param res
     * @param local
     * @param userkey
     * @param store
     * @return
     */
    @RequestMapping(value = "/{local}/regist")
    public ModelAndView pre_regist_global_proc(HttpServletRequest req, HttpServletResponse res,
            @PathVariable String local,
            @RequestParam(value="userkey", required = false, defaultValue="") String userkey,
            @RequestParam(value="store", required = false, defaultValue="") String store
            ) {
        ModelAndView modelAndView = new ModelAndView();
        /*userkey = userkey.trim();
        local = local.replace("kr", "nhn");
        if(LanguageEnum.isSuportRegistPageLanguage(local) == false){
            throw CustomException.INVALID_LANGUAGE;
        }

        if("".equals(store)){
            throw CustomException.INVALID_PARAMETER;
        }
        LanguageEnum languageEnum = LanguageEnum.GetRegistPageLanguage(local);
        if(languageEnum == LanguageEnum.KR){
            if(StringUtil.isDigit(userkey) == false || userkey.length() < 10){
                throw CustomException.INVALID_USER;
            }
        }else{
            if(EmailValidUtil.isValidEmailAddress(userkey) == false){
                throw CustomException.INVALID_EMAIL;
            }
        }

        if(preRegistService.isRegist(userkey) == true){
            throw CustomException.ALREADY_REGIST;
        }

        if(RequestUtil.isAllowByIpExceptOffice(RobotSubmitSecurity.GetRemoteAddr(req)) == false){ //사무실이 아닌 경우에만 체크
            if(preRegistService.getRegistTryCountByIp(RobotSubmitSecurity.GetRemoteAddr(req)) > IpPerRegistCount){
                LOG.error("IpPerRegistCount over"+RobotSubmitSecurity.GetRemoteAddr(req));
                throw CustomException.NOT_ALLOWED_USER;
            }
            preRegistService.registTry(RobotSubmitSecurity.GetRemoteAddr(req)); //시도 로그 기록

        }

        boolean isRobot = RobotSubmitSecurity.isRobotClient(req, AllowTimeRange, RefererPattern);
        if(isRobot == true){
            LOG.error("You are robot! - "+RobotSubmitSecurity.GetRemoteAddr(req));
            throw CustomException.NOT_ALLOWED_USER;
        }

        try{
            PreRegist preRegist = preRegistService.regist(store, languageEnum, userkey);
            modelAndView.addObject("preRegist", preRegist);

            AesCipher cipher  = new AesCipher(ServerKeyEnum.PRE_REGIST.getKey());
            String userkey_enc = cipher.encryptString(preRegist.getUserkey());
            RequestUtil.setCookie(res, UserKeyName, userkey_enc, 30); //이메일 쿠키에 저장
            modelAndView.addObject("result", "SUCCESS");
        }catch(Exception e){
            LOG.error(e.toString());
            throw CustomException.SYSTEM_FAILURE;
        }
*/
        return modelAndView;
    }

    /**
     * 사전등록 확인
     * @param req
     * @param res
     * @param local
     * @param userkey
     * @return
     */
    @RequestMapping(value = "/{local}/regist_confirm")
    public ModelAndView regist_confirm(HttpServletRequest req, HttpServletResponse res,
            @PathVariable String local,
            @RequestParam(value="userkey", required = false, defaultValue="") String userkey
            ) {
        ModelAndView modelAndView = new ModelAndView();
        userkey = userkey.trim();
        local = local.replace("kr", "nhn");
        if(LanguageEnum.isSuportRegistPageLanguage(local) == false){
            throw CustomException.INVALID_LANGUAGE;
        }
        LanguageEnum languageEnum = LanguageEnum.GetRegistPageLanguage(local);
        if(languageEnum == LanguageEnum.KR){
            if(StringUtil.isDigit(userkey) == false || userkey.length() < 10){
                throw CustomException.INVALID_USER;
            }
        }else{
            if(EmailValidUtil.isValidEmailAddress(userkey) == false){
                throw CustomException.INVALID_EMAIL;
            }
        }

        if(preRegistService.isRegist(userkey) == false){
            throw CustomException.IS_NOT_REGIST;
        }


        try{
            PreRegist preRegist = preRegistService.getUser(userkey);
            modelAndView.addObject("preRegist", preRegist);

            AesCipher cipher  = new AesCipher(ServerKeyEnum.PRE_REGIST.getKey());
            String userkey_enc = cipher.encryptString(preRegist.getUserkey());
            RequestUtil.setCookie(res, UserKeyName, userkey_enc, 30); //이메일 쿠키에 저장
            modelAndView.addObject("result", "SUCCESS");
        }catch(Exception e){
            LOG.error(e.toString());
            throw CustomException.SYSTEM_FAILURE;
        }

        return modelAndView;
    }

    @RequestMapping(value = "/status")
    public ModelAndView status(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value="userkey", required = false, defaultValue="") String userkey
            ) {
        ModelAndView modelAndView = new ModelAndView();

        if(RequestUtil.isAllowByIpExceptOffice(RobotSubmitSecurity.GetRemoteAddr(req)) == false){ //사무실이 아닌 경우에만 체크
            throw CustomException.NOT_ALLOWED_USER;
        }
        modelAndView.addObject("reply_list_kr", snsEventService.getReplyTop5(LanguageEnum.GetRegistPageLanguage("KR")));
        modelAndView.addObject("reply_list_en", snsEventService.getReplyTop5(LanguageEnum.GetRegistPageLanguage("EN")));
        List<PreRegistStatus> statusList = preRegistService.getRegistStatus();
        modelAndView.addObject("statusList", statusList);
        int registerCount = 0;
        Iterator<PreRegistStatus> iter = statusList.iterator();
        while(iter.hasNext()){
            PreRegistStatus status = iter.next();
            registerCount = registerCount + status.getCount();
        }
        modelAndView.addObject("registerCount", registerCount);
        return modelAndView;
    }

}
