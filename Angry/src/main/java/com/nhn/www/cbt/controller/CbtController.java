package com.nhn.www.cbt.controller;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.base.constant.SessionScopeBean;
import com.nhn.common.exception.CustomException;
import com.nhn.www.cbt.service.CbtService;

@Controller
@RequestMapping("/cbt")
public class CbtController {

    @Inject
    Provider<SessionScopeBean> sessionScopebeanFactory;

    @Autowired
    CbtService cbtService;

    @RequestMapping(value = "/userinfo")
    public ModelAndView userinfo(HttpServletRequest req, HttpServletResponse res){
        ModelAndView modelAndView = new ModelAndView();
        SessionScopeBean sessionBean = this.sessionScopebeanFactory.get();
        if(sessionBean.getLanguageEnum() != LanguageEnum.KR){
            //throw CustomException.INVALID_COUNTRY;
        }

        modelAndView.addObject("sessionBean", new JSONObject(sessionBean));
        return modelAndView;
    }

    @RequestMapping(value = "/userinfo_save")
    public ModelAndView userinfo_save(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value = "userkey", required = false, defaultValue = "") String userkey
            ){
        ModelAndView modelAndView = new ModelAndView();
        SessionScopeBean sessionBean = this.sessionScopebeanFactory.get();
        if(sessionBean.getLanguageEnum() != LanguageEnum.KR){
            //throw CustomException.INVALID_COUNTRY;
        }

        boolean hasUsn = cbtService.hasUsn(sessionBean.getSno());
        if(hasUsn == true){
            modelAndView.addObject("result", "ALREADY_USN");
            modelAndView.addObject("key", cbtService.getUserKey(sessionBean.getSno()));
            return modelAndView;
        }
        if("".equals(userkey)){
            throw CustomException.EMPTY_USER;
        }
        boolean hasUserKey = cbtService.hasUserKey(userkey);
        if(hasUserKey == true){
            throw CustomException.ALREADY_PHONE;
        }

        cbtService.registUser(sessionBean.getSno(), userkey);
        modelAndView.addObject("result", "SUCCESS");

        return modelAndView;
    }
}
