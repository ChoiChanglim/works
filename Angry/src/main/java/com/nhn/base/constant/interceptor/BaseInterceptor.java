package com.nhn.base.constant.interceptor;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.nhn.base.constant.SessionScopeBean;
import com.nhn.common.util.RequestUtil;

public class BaseInterceptor extends HandlerInterceptorAdapter{
    private static Logger LOG = LoggerFactory.getLogger(BaseInterceptor.class);
    @Inject
    Provider<SessionScopeBean> sessionScopebeanFactory;

    @Value("#{propinfo['Zone']}")
    private String Zone;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        SessionScopeBean sessionBean = this.sessionScopebeanFactory.get();
        sessionBean.BaseRequestInfoSetting(request);
        sessionBean.setZone(Zone);
        //회원번호 쿠키저장
        if(sessionBean.getSno() > 0){
            RequestUtil.setCookie(response, SessionScopeBean.SNO_COOKIE_KEY, String.valueOf(sessionBean.getSno()), SessionScopeBean.COOKIE_UNTILL_DATE);
        }


        //언어코드 로케일리졸버 세팅 쿠키에 저장도 된다.
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

        if (localeResolver == null){
            throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
        }
        localeResolver.setLocale(request, response, StringUtils.parseLocaleString(sessionBean.getLanguage()));

        //클라이언트에서 전해준 OS정보 쿠키에 세팅
        if(sessionBean.getParamMap().containsKey("os")){
            RequestUtil.setCookie(response, SessionScopeBean.OS_COOKIE_KEY, sessionBean.getOSInfo().name(), SessionScopeBean.COOKIE_UNTILL_DATE);
        }

        //특별한 확장자 서블릿 접근금지
        String[] arrs = request.getRequestURI().split("[/]");
        String lastCallName = arrs[arrs.length-1];
        if(lastCallName.split("[.]").length > 1){
            String extedsName = lastCallName.split("[.]")[1];
            String[] notAllowedExtends = {"ico"};
            for(int i=0;i<notAllowedExtends.length;i++){
                if(notAllowedExtends[i].contains(extedsName)){
                    LOG.info("extedsName:"+extedsName+" return false;");
                    return false;
                }
            }

        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        //기본정보 제공하지 않아야되는뷰네임
        String[] excludeInfoJsonViewName = {"service/popup", "service/icon_popup"};
        if(!Arrays.asList(excludeInfoJsonViewName).contains(modelAndView.getViewName())){
            modelAndView.addObject("info", this.sessionScopebeanFactory.get());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){

    }
}
