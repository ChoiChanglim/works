package com.nhn.base.notice.controller;

import java.util.Calendar;

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

import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.base.constant.SessionScopeBean;
import com.nhn.base.notice.bean.Notice;
import com.nhn.base.notice.service.NoticeService;

@RequestMapping("/notice")
@Controller
public class NoticeController {
    private static Logger LOG = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    NoticeService noticeService;

    @Inject
    Provider<SessionScopeBean> sessionScopebeanFactory;

   /* //@TilesOn
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest req, HttpServletResponse res) {
        SessionScopeBean sessionScopeBean = this.sessionScopebeanFactory.get();
        ModelAndView modelAndView = new ModelAndView();

        OSInfo osinfo = sessionScopeBean.getOSInfo();
        LanguageEnum languageEnum = LanguageEnum.valueOf(sessionScopeBean.getLanguage());

        modelAndView.addObject("topList", noticeService.getNoticeList(WhereNoticeList.TOP, osinfo, languageEnum, sessionScopeBean.getSno()));
        modelAndView.addObject("bottomList", noticeService.getNoticeList(WhereNoticeList.BOTTOM, osinfo, languageEnum, sessionScopeBean.getSno()));
        return modelAndView;
    }*/

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value = "idx", required = false, defaultValue = "0") int idx) {
        SessionScopeBean sessionScopeBean = this.sessionScopebeanFactory.get();
        ModelAndView modelAndView = new ModelAndView();
        LanguageEnum languageEnum = LanguageEnum.valueOf(sessionScopeBean.getLanguage());

        Notice notice = noticeService.getNotice(idx, languageEnum);
        modelAndView.addObject("notice", notice);

        //남은시간 표시
        modelAndView.addObject("now", Calendar.getInstance().getTimeInMillis());

        if(1 == notice.getEventdel()){
            modelAndView.addObject("Status", RmainStatus.NOT_ALLOWED_VIEW);
            modelAndView.addObject("remainTime", 0);
            return modelAndView;
        }

        if(null == notice.getEventedate()){
            modelAndView.addObject("Status", RmainStatus.INVALID_PERIOD);
            modelAndView.addObject("remainTime", 0);
            return modelAndView;
        }

        Calendar tmp1 = Calendar.getInstance();
        Calendar event_end = Calendar.getInstance();
        event_end.setTime(notice.getEventedate());
        long remainTime = event_end.getTimeInMillis() - tmp1.getTimeInMillis();
        modelAndView.addObject("remainTime", remainTime);
        //이벤트 종료
        if(remainTime < 0){
            modelAndView.addObject("Status", RmainStatus.EVENT_END);
            modelAndView.addObject("remainTime", 0);
            return modelAndView;
        }

        //1년 이상 남음
        tmp1.add(Calendar.YEAR, 1);
        Calendar tmp2 = Calendar.getInstance();
        long calc_year = tmp1.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        if(remainTime > calc_year){
            modelAndView.addObject("Status", RmainStatus.REMAIN_INFINITE);
            return modelAndView;
        }
        //하루이상 남음
        Calendar tmp3 = Calendar.getInstance();
        tmp2.add(Calendar.DATE, 1);
        long calc_date = tmp2.getTimeInMillis() - tmp3.getTimeInMillis();
        if(remainTime > calc_date ){
            modelAndView.addObject("Status", RmainStatus.REMAIN_DATE);
            int remainDays = (int)(remainTime / (1000 * 60 * 60 * 24 * 1));
            int remainHour = (int)(remainTime / (1000 * 60 * 60) % 24);
            modelAndView.addObject("remainDays", remainDays);
            modelAndView.addObject("remainHour", remainHour);
        }else{
            modelAndView.addObject("Status", RmainStatus.REMAIN_TIME);

        }

        return modelAndView;
    }
    public static enum RmainStatus{
        EVENT_END("이벤트 기간 종료"),
        INVALID_PERIOD("이벤트 기간 설정이 잘못됨"),
        REMAIN_DATE("24시간 이상 남은상태"),
        REMAIN_TIME("24시간 이내 종료"),
        REMAIN_INFINITE("무기한 게시물"),
        NOT_ALLOWED_VIEW("남은시간 표기 노출 안함");

        private String desc;

        private RmainStatus(String desc){
            this.desc = desc;
        }
        public String getDesc(){
            return desc;
        }
    }

    public static enum DeviceInfo{
        NORMAL, MOBILE, TABLET

    }


}
