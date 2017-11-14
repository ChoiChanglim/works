package com.nhn.www.event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nhn.base.constant.HspService;
import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.common.exception.CustomException;
import com.nhn.game.api.AdminGetUserInfoRequest;
import com.nhn.game.service.AdminApiService;
import com.nhn.hsp.Games;
import com.nhncorp.hsp.connector.response.APlayedGameLog;
import com.nhncorp.hsp.connector.response.AProfile;

@RequestMapping("/page")
@Controller
public class PageController {
    private static Logger LOG = LoggerFactory.getLogger(PageController.class);

    @Autowired
    HspService hspService;

    @Autowired
    AdminApiService adminApiService;

    @RequestMapping("/hottracks")
    public ModelAndView hottracks(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value="local", required = false, defaultValue="") String local
            ){
        ModelAndView modelAndView = new ModelAndView();

        LanguageEnum languageEnum = LanguageEnum.GetRegistPageLanguage(local);
        modelAndView.addObject("LanguageEnum", languageEnum);
        Device currentDevice = DeviceUtils.getCurrentDevice(req);
        modelAndView.addObject("Device", GetDeviceType(currentDevice));

        return modelAndView;
    }
    public static String GetDeviceType(Device currentDevice) {
        if(currentDevice.isNormal()){
            return "NORMAL";
        }else{
            return "MOBILE";
        }

    }

    @RequestMapping("/hottracks2")
    public ModelAndView hottracks2(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value="local", required = false, defaultValue="") String local,
            @RequestParam(value="sno", required = false, defaultValue="0") long sno
            ){
        ModelAndView modelAndView = new ModelAndView();

        AProfile aprofile = hspService.getProfileDetail2(sno).getProfile();
        if(aprofile.getValidCode().equals("Y") == false){
            throw CustomException.INVALID_SNO;
        }
        AdminGetUserInfoRequest adminGetUserInfoRequest = adminApiService.getAdminGetUserInfoRequest(sno);
        int level = adminGetUserInfoRequest.getLevel();
        if(level < 5){
            LOG.info("Under lv5:"+sno);
            modelAndView.addObject("level", false);
        }

        APlayedGameLog playLog = hspService.getPlayedGameLog(Long.valueOf(sno)).getGameLogMap().get(Games.Angry);
        long firstloginTime = Long.valueOf(playLog.getFirstLoginTime());  //yyyyMMddHHmmss
        long firstloginTimePoint = 20170511000000L;
        if(firstloginTime < firstloginTimePoint){
            LOG.info("Before 2017-05-11 - FirstloginTime: "+firstloginTime);
            modelAndView.addObject("firstlogin", false);
        }



        LanguageEnum languageEnum = LanguageEnum.GetRegistPageLanguage(local);
        modelAndView.addObject("LanguageEnum", languageEnum);
        Device currentDevice = DeviceUtils.getCurrentDevice(req);
        modelAndView.addObject("Device", GetDeviceType(currentDevice));

        return modelAndView;
    }

}
