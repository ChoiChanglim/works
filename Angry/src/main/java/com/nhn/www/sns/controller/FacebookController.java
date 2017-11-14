package com.nhn.www.sns.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.base.constant.ServerKey.ServerKeyEnum;
import com.nhn.common.exception.CustomException;
import com.nhn.common.util.AesCipher;
import com.nhn.common.util.RequestUtil;
import com.nhn.www.sns.FacebookContents;
import com.nhn.www.sns.TwitterContents.SNSShareCodeEnum;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;

@RequestMapping("/sns/facebook")
@Controller
public class FacebookController {
    private static Logger LOG = LoggerFactory.getLogger(FacebookController.class);

    @Value("#{propinfo['Zone']}")
    private String Zone;

    public String getRedirectUrl(HttpServletRequest req, LanguageEnum languageEnum){
        String uri = "/sns/facebook/"+languageEnum.name().toLowerCase()+"/callback";

        String protocol = "http";
        if(Zone.equals("Real")){
            protocol = "https";
        }


        return protocol+"://"+req.getServerName()+uri;
    }

    public HashMap<String, Object> getQstrMapAtOauth(HttpServletRequest req, AesCipher cipher){
        //인증요청할때의 파라미터들
        HashMap<String, Object> qstrMap = new HashMap<String, Object>();
        if(RequestUtil.getCookieMap(req).containsKey(FacebookContents.FacebookQstrAtOauthCookieName)){
            String foqs = cipher.decryptString(RequestUtil.getCookieMap(req).get(FacebookContents.FacebookQstrAtOauthCookieName));
            qstrMap = RequestUtil.qstrSplit(foqs);
        }
        return qstrMap;
    }

    /**
     * 페이스북 콜백
     * @param req
     * @param res
     * @param code
     * @param state
     * @return
     */
    @RequestMapping(value = "/{local}/callback")
    public ModelAndView facebook_callback(HttpServletRequest req, HttpServletResponse res,
            @PathVariable String local,
            @RequestParam(value="code", required = false, defaultValue="") String code
            ) {
        ModelAndView modelAndView = new ModelAndView();

        Facebook facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId(FacebookContents.FacebookAppId, FacebookContents.FacebookAppSecret);
        facebook.setOAuthPermissions("publish_actions");

        AesCipher cipher  = new AesCipher(ServerKeyEnum.PRE_REGIST.getKey());
        try {
            String fbCallbackUrl = getRedirectUrl(req, LanguageEnum.GetRegistPageLanguage(local));
            facebook.getOAuthAccessToken(code, fbCallbackUrl);
            AccessToken accessToken = facebook.getOAuthAccessToken();
            RequestUtil.setCookieByMinute(res, FacebookContents.FacebookAccessTokenCookieName, cipher.encryptString(accessToken.getToken()), 360);
            //System.err.println("callback accesstoken:"+accessToken.getToken());
            facebook.setOAuthAccessToken(accessToken);
        } catch (FacebookException e) {
            LOG.error("facebook get access token exception!");
            throw CustomException.INVALID_FACEBOOK_ACCESS_TOKEN;
        }

        HashMap<String, Object> refererParamMap = getQstrMapAtOauth(req, cipher);
        //System.err.println("callback qstr:"+new JSONObject(refererParamMap));

        boolean isShareCode = refererParamMap.containsKey("code");
        String shareCode = "";
        if(isShareCode){
            shareCode = (String)refererParamMap.get("code");
        }
        if(SNSShareCodeEnum.isValid(shareCode) == false){
            LOG.error("Share Code invalid - "+code);
            throw CustomException.INVALID_TWITTER_SHARE_CODE;
        }
        SNSShareCodeEnum snsShareCodeEnum = SNSShareCodeEnum.GetSNSShareCodeEnum(shareCode);

        String result = "success";
        modelAndView.addObject("result", result);
        modelAndView.addObject("snsname", "facebook");
        modelAndView.addObject("code", snsShareCodeEnum.name().toLowerCase());
        modelAndView.setViewName("sns/callback");
        //modelAndView.setView(new AngryMappingJacksonJsonView());

        return modelAndView;
    }

    /**
     * 페이스북 인증
     * @param req
     * @param res
     * @param local
     * @param code
     * @return
     */
    @RequestMapping(value = "/{local}/oauth")
    public ModelAndView kakao_oauth(HttpServletRequest req, HttpServletResponse res,
            @PathVariable String local,
            @RequestParam(value="code", required = false, defaultValue="") String code
            ) {
        ModelAndView modelAndView = new ModelAndView();

        if(SNSShareCodeEnum.isValid(code) == false){
            LOG.error("Share Code invalid - "+code);
            throw CustomException.INVALID_TWITTER_SHARE_CODE;
        }



        Facebook facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId(FacebookContents.FacebookAppId, FacebookContents.FacebookAppSecret);
        facebook.setOAuthPermissions("publish_actions");

        AesCipher cipher  = new AesCipher(ServerKeyEnum.PRE_REGIST.getKey());
        //oauth 요청시의 파라미터 쿠키에 저장 callback에서의 처리를 위해
        String qstrAtOauth = RequestUtil.getQstr(req);
        RequestUtil.setCookieByMinute(res, FacebookContents.FacebookQstrAtOauthCookieName, cipher.encryptString(qstrAtOauth), 30);

        //페이스북 엑세스토큰 있으면 저장된 토큰 사용
        if(RequestUtil.getCookieMap(req).containsKey(FacebookContents.FacebookAccessTokenCookieName)){
            String token = cipher.decryptString(RequestUtil.getCookieMap(req).get(FacebookContents.FacebookAccessTokenCookieName));
            modelAndView.addObject("hasToken", true);
            AccessToken accessToken = new AccessToken(token);
            facebook.setOAuthAccessToken(accessToken);
            System.err.println("oauth accesstoken:"+accessToken.getToken());
            //코드(요청)별 처리
            return modelAndView;
        }


        //콜백 url
        String fbCallbackUrl = getRedirectUrl(req, LanguageEnum.GetRegistPageLanguage(local));
        String oauthUrl = facebook.getOAuthAuthorizationURL(fbCallbackUrl);

        modelAndView.setViewName("redirect:"+oauthUrl+"&display=popup");
        modelAndView.addObject("oauthUrl", oauthUrl);
        modelAndView.addObject("result", "ready");
        modelAndView.addObject("hasToken", false);
        return modelAndView;
    }

    @RequestMapping(value = "/test")
    public ModelAndView facebook_test(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value="code", required = false, defaultValue="") String code
            ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("isSecure", req.isSecure());
        modelAndView.addObject("protocol", req.getProtocol());
        modelAndView.addObject("getRequestURL", req.getRequestURL());
        modelAndView.addObject("header", RequestUtil.HeaderToMap(req));
        modelAndView.setViewName("sns/test");
        return modelAndView;
    }
}
