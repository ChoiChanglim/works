package com.nhn.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nhn.base.constant.AngryMappingJacksonJsonView;

@RequestMapping("/monitor")
@Controller
public class MonitorController {

    @RequestMapping("/l7check")
    public ModelAndView l7check(HttpServletRequest req, HttpServletResponse res){
        ModelAndView modelAndView = new ModelAndView();
        AngryMappingJacksonJsonView jsonView = new AngryMappingJacksonJsonView();
        modelAndView.setView(jsonView);
        return modelAndView;
    }
}
