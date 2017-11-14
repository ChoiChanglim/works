package com.nhn.base.coupon.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nhn.base.constant.AngryPaging;
import com.nhn.base.constant.HspService;
import com.nhn.base.constant.SessionScopeBean;
import com.nhn.base.constant.annotation.TilesOn;
import com.nhn.base.coupon.bean.CouponLogExtends;
import com.nhn.base.coupon.service.CouponService;
import com.nhn.common.exception.CustomException;
import com.nhncorp.hsp.connector.response.AProfile;


@RequestMapping("/coupon")
@Controller
public class CouponController {
    private static Logger LOG = LoggerFactory.getLogger(CouponController.class);

    @Autowired
    CouponService couponService;

    @Autowired
    HspService hspService;

    @Inject
    Provider<SessionScopeBean> sessionScopebeanFactory;

    @TilesOn
    @RequestMapping(value = "/regist", method = RequestMethod.GET)
    public ModelAndView regist(HttpServletRequest req, HttpServletResponse res){
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }

    @RequestMapping(value = "/couponItemSend", method = RequestMethod.POST)
    public ModelAndView couponItemSend(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value = "cnum", required = false, defaultValue = "") String cnum){
        ModelAndView modelAndView = new ModelAndView();

        SessionScopeBean sessionScopeBean = this.sessionScopebeanFactory.get();
        if(0 == sessionScopeBean.getSno()){
            throw CustomException.EMPTY_SNO;
        }

        long sno = sessionScopeBean.getSno();
        AProfile aprofile = hspService.getProfileDetail2(sno).getProfile();
        if(aprofile.getValidCode().equals("Y") == false){
            LOG.error(sno+ " hsp validation fail");
            throw CustomException.INVALID_SNO;
        }

        if(cnum.trim().length()==12){
            String hottracks_result = couponService.HottracksCouponCheckAndItemSend(cnum.toUpperCase(), String.valueOf(sno));  //핫트렉스 쿠폰
            if("0".equals(hottracks_result)){
                modelAndView.addObject("result", hottracks_result);
                return modelAndView;
            }

            String hgb_result = couponService.HgbCouponCheckAndItemSend(cnum.toUpperCase(), String.valueOf(sno));  //HGB 쿠폰
            if("0".equals(hgb_result)){
                modelAndView.addObject("result", hgb_result);
                return modelAndView;
            }

            modelAndView.addObject("result", hottracks_result); //핫트렉스도 아니고 hgb도 아닌경우 에러메시지는 핫트렉스로


        }else{
            String code_result = couponService.CodeCouponCheckAndItemSend(cnum.toUpperCase(), String.valueOf(sno));  //코드형
            modelAndView.addObject("result", code_result);
        }


        return modelAndView;
    }

    @TilesOn
    @RequestMapping(value = "/mylist", method = RequestMethod.GET)
    public ModelAndView mylist(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value = "page", required = false, defaultValue = "1") String page
            ){
        SessionScopeBean sessionScopeBean = this.sessionScopebeanFactory.get();
        ModelAndView modelAndView = new ModelAndView();
        String rowCount = "7";
        int myCouponUsedListCount = couponService.getMyCouponUsedListCount(sessionScopeBean.getSno());
        AngryPaging paging = new AngryPaging(myCouponUsedListCount, rowCount, page);

        List<CouponLogExtends> myCouponUsedList = couponService.getMyCouponUsedList(sessionScopeBean.getSno(), paging.getBegin(), paging.getRowCount());
        modelAndView.addObject("list", myCouponUsedList);
        modelAndView.addObject("paging", paging);
        return modelAndView;
    }

}
