package com.nhn.www.sns.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nhn.base.constant.AngryMappingJacksonJsonView;
import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.base.constant.ServerKey.ServerKeyEnum;
import com.nhn.common.exception.CustomException;
import com.nhn.common.util.AesCipher;
import com.nhn.common.util.RequestUtil;
import com.nhn.www.preregist.controller.PreRegistController;
import com.nhn.www.sns.TwitterContents.SNSShareCodeEnum;
import com.nhn.www.sns.service.SnsEventService;


@Controller
@RequestMapping("/sns/event")
public class SnsEventController {
    private static Logger LOG = LoggerFactory.getLogger(SnsEventController.class);

    @Autowired
    SnsEventService snsEventService;

    /**
     * 공유완료
     * @return
     */
    @RequestMapping(value = "/{local}/complete_share")
    public ModelAndView complete_share(HttpServletRequest req, HttpServletResponse res,
            @PathVariable String local,
            @RequestParam(value="code", required = false, defaultValue="") String code,
            @RequestParam(value="userkey", required = false, defaultValue="") String userkey,
            @RequestParam(value="snsname", required = false, defaultValue="") String snsname
            ) {
        ModelAndView modelAndView = new ModelAndView();
        if(SNSShareCodeEnum.isValid(code) == false){
            LOG.error("Invalid Share Code!");
            throw CustomException.INVALID_CODE;
        }
        if("".equals(local)){
            LOG.error("Invalid Local!");
            throw CustomException.INVALID_LANGUAGE;
        }
        //userkey
        HashMap<String, String> cookieMap = RequestUtil.getCookieMap(req);
        if(cookieMap.containsKey(PreRegistController.UserKeyName) == false){
            LOG.error("Customers who have not pre-registered.");
            throw CustomException.IS_NOT_REGIST;
        }
        String enc_userkey = cookieMap.get(PreRegistController.UserKeyName);
        AesCipher cipher  = new AesCipher(ServerKeyEnum.PRE_REGIST.getKey());
        String dec_userkey = cipher.decryptString(enc_userkey);
        if(userkey.equals(dec_userkey) == false){
            LOG.error("userkey and cookie userkey wrong - "+"userkey:"+userkey+", dec_userkey:"+dec_userkey);
            throw CustomException.INVALID_USER;
        }

        SNSShareCodeEnum snsShareCodeEnum = SNSShareCodeEnum.getSNSShareCodeEnum(code);
        boolean isLike = snsEventService.isShareComplete(dec_userkey, snsname, snsShareCodeEnum);
        if(isLike == false){
            snsEventService.shareComplete(userkey, snsname, snsShareCodeEnum, LanguageEnum.GetRegistPageLanguage(local));
        }

        modelAndView.addObject("result", "SUCCESS");
        return modelAndView;
    }

    @RequestMapping(value = "/{local}/insert_reply")
    public ModelAndView pre_regist_reply_complete(HttpServletRequest req, HttpServletResponse res,
            @PathVariable String local,
            @RequestParam(value="code", required = false, defaultValue="") String code,
            @RequestParam(value="userkey", required = false, defaultValue="") String userkey,
            @RequestParam(value="snsname", required = false, defaultValue="") String snsname,
            @RequestParam(value="msg", required = false, defaultValue="") String msg
            ) {
        ModelAndView modelAndView = new ModelAndView();
        local = local.replace("kr", "nhn");
        if(SNSShareCodeEnum.isValid(code) == false){
            LOG.error("Invalid Share Code!");
            throw CustomException.INVALID_CODE;
        }
        if("".equals(local)){
            LOG.error("Invalid Local!");
            throw CustomException.INVALID_LANGUAGE;
        }
        //userkey
        HashMap<String, String> cookieMap = RequestUtil.getCookieMap(req);
        if(cookieMap.containsKey(PreRegistController.UserKeyName) == false){
            LOG.error("Customers who have not pre-registered.");
            throw CustomException.IS_NOT_REGIST;
        }
        String enc_userkey = cookieMap.get(PreRegistController.UserKeyName);
        AesCipher cipher  = new AesCipher(ServerKeyEnum.PRE_REGIST.getKey());
        String dec_userkey = cipher.decryptString(enc_userkey);
        if(userkey.equals(dec_userkey) == false){
            LOG.error("userkey and cookie userkey wrong - "+"userkey:"+userkey+", dec_userkey:"+dec_userkey);
            throw CustomException.INVALID_USER;
        }
        if("".equals(msg)){
            LOG.error("Empty msg parameter!");
            throw CustomException.EMPTY_PARAMETER;
        }

        snsEventService.insertReply(userkey, snsname, LanguageEnum.GetRegistPageLanguage(local), msg);
        modelAndView.addObject("result", "SUCCESS");
        return modelAndView;


    }
    @RequestMapping(value = "/{local}/reply_list")
    public ModelAndView pre_regist_reply_list(HttpServletRequest req, HttpServletResponse res,
            @PathVariable String local
            ) {
        ModelAndView modelAndView = new ModelAndView();
        local = local.replace("kr", "nhn");
        modelAndView.setView(new AngryMappingJacksonJsonView());
        if(LanguageEnum.isSuportRegistPageLanguage(local) == false){
            throw CustomException.INVALID_LANGUAGE;
        }
        modelAndView.addObject("list", snsEventService.getReplyTop5(LanguageEnum.GetRegistPageLanguage(local)));
        modelAndView.addObject("result", "SUCCESS");
        return modelAndView;
    }

    @RequestMapping(value = "/delete_reply")
    public ModelAndView delete_reply(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value="seq", required = false, defaultValue="") long seq
            ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(new AngryMappingJacksonJsonView());
        HashMap<String, String> headerMap = RequestUtil.HeaderToMap(req);
        String remoteAddr = req.getRemoteAddr();
        if(headerMap.containsKey("x-forwarded-for")){
            remoteAddr = headerMap.get("x-forwarded-for");
        }
        if(RequestUtil.isAllowByIpExceptOffice(remoteAddr) == false){ //사무실이 아닌 경우에만 체크
            throw CustomException.NOT_ALLOWED_USER;
        }
        int delete = snsEventService.deleteReply(seq);
        if(delete > 0){
            modelAndView.addObject("result", "success");
        }
        return modelAndView;
    }


}
