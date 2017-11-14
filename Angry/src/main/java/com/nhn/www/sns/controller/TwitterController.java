package com.nhn.www.sns.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.base.constant.ServerKey.ServerKeyEnum;
import com.nhn.common.exception.CustomException;
import com.nhn.common.util.AesCipher;
import com.nhn.common.util.RequestUtil;
import com.nhn.www.sns.AccountByTwitterFactory;
import com.nhn.www.sns.TwitterContents.SNSShareCodeEnum;
import com.nhn.www.sns.service.SnsBaseService;
import com.nhn.www.sns.service.SnsEventService;

@Controller
@RequestMapping("/sns/twitter")
public class TwitterController {
    private static Logger LOG = LoggerFactory.getLogger(TwitterController.class);
    public static final String EventCallbackURI = "/sns/twitter/{code}/{local}/callback";
    public static final String TwitterAccessTokenCookieName = "tact";
    public static final String TwitterAccessTokenSecretCookieName = "tacts";
    public static final String TwitterQstrAtOauthCookieName = "toqs";

    @Autowired
    SnsBaseService snsBaseService;

    @Autowired
    SnsEventService snsEventService;

    @Value("#{propinfo['Zone']}")
    private String Zone;

    public HashMap<String, Object> getQstrMapAtOauth(HttpServletRequest req, AesCipher cipher){
        //인증요청할때의 파라미터들
        HashMap<String, Object> qstrMap = new HashMap<String, Object>();
        if(RequestUtil.getCookieMap(req).containsKey(TwitterQstrAtOauthCookieName)){
            String foqs = cipher.decryptString(RequestUtil.getCookieMap(req).get(TwitterQstrAtOauthCookieName));
            qstrMap = RequestUtil.qstrSplit(foqs);
        }
        return qstrMap;
    }

    public String getTwitterCallbackUrl(HttpServletRequest req, String code, String local){
        String protocol = "http";
        if(Zone.equals("Real")){
            protocol = "https";
        }
        String returnValue = protocol+"://"+req.getServerName()+EventCallbackURI.replace("{code}", code.toLowerCase()).replace("{local}", local.toLowerCase());
        return returnValue;
    }


    /**
     * 트위터 권한얻기
     * @param req
     * @param res
     * @param local
     * @return
     * @throws TwitterException
     */
    @RequestMapping(value = "/{local}/oauth")
    public ModelAndView oauth(HttpServletRequest req, HttpServletResponse res,
            @PathVariable String local,
            @RequestParam(value="code", required = false, defaultValue="") String code
            ) throws TwitterException {
        ModelAndView modelAndView = new ModelAndView();

        if(SNSShareCodeEnum.isValid(code) == false){
            LOG.error("Twitter Share Code invalid - "+code);
            throw CustomException.INVALID_TWITTER_SHARE_CODE;
        }
        AesCipher cipher  = new AesCipher(ServerKeyEnum.PRE_REGIST.getKey());
        //oauth 요청시의 파라미터 쿠키에 저장 callback에서의 처리를 위해
        String qstrAtOauth = RequestUtil.getQstr(req);
        RequestUtil.setCookieByMinute(res, TwitterQstrAtOauthCookieName, cipher.encryptString(qstrAtOauth), 30);

        AccountByTwitterFactory factory = new AccountByTwitterFactory(local);
        Twitter twitter = factory.getFactory().getInstance();
        //트위터 엑세스토큰 있으면 저장된 토큰 사용
        if(RequestUtil.getCookieMap(req).containsKey(TwitterAccessTokenCookieName) && RequestUtil.getCookieMap(req).containsKey(TwitterAccessTokenSecretCookieName)){
            String token = cipher.decryptString(RequestUtil.getCookieMap(req).get(TwitterAccessTokenCookieName));
            String tokenSecret = cipher.decryptString(RequestUtil.getCookieMap(req).get(TwitterAccessTokenSecretCookieName));
            AccessToken accessToken = new AccessToken(token, tokenSecret);
            twitter.setOAuthAccessToken(accessToken);
            modelAndView.addObject("hasToken", true);

            //코드(요청)별 처리
            SNSShareCodeEnum snsShareCodeEnum = SNSShareCodeEnum.GetSNSShareCodeEnum(code);
            String result = snsEventService.twitterProcessByCode(twitter, snsEventService.getUserkeyInCookie(req), snsShareCodeEnum, LanguageEnum.GetRegistPageLanguage(local), RequestUtil.reqParamToMap(req));
            modelAndView.addObject("result", result);
            return modelAndView;
        }

        try {
            RequestToken requestToken = twitter.getOAuthRequestToken(getTwitterCallbackUrl(req, code, local));

            String oauthUrl = requestToken.getAuthorizationURL();
            modelAndView.addObject("oauthUrl", oauthUrl);
            modelAndView.addObject("result", "ready");
            modelAndView.addObject("hasToken", false);
        } catch (TwitterException e) {
            modelAndView.addObject("result", "fail");
            e.printStackTrace();
        }

        return modelAndView;
    }

    /**
     * 트위터 콜백 처리
     */
    @RequestMapping(value = "/{code}/{local}/callback")
    public ModelAndView twitter_callback(HttpServletRequest req, HttpServletResponse res,
            @PathVariable String code,
            @PathVariable String local,
            @RequestParam(value="oauth_token", required = false, defaultValue="") String oauth_token,
            @RequestParam(value="oauth_verifier", required = false, defaultValue="") String oauth_verifier
            ) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("code", code);
        modelAndView.addObject("snsname", "twitter");
        AccountByTwitterFactory factory = new AccountByTwitterFactory(local);
        Twitter twitter = factory.getFactory().getInstance();
        try {
            RequestToken requestToken = new RequestToken(oauth_token, oauth_verifier);
            AccessToken accessToken = factory.getFactory().getInstance().getOAuthAccessToken(requestToken, oauth_verifier);
            twitter.setOAuthAccessToken(accessToken);

            //accessToken 저장
            AesCipher cipher  = new AesCipher(ServerKeyEnum.PRE_REGIST.getKey());
            RequestUtil.setCookieByMinute(res, TwitterAccessTokenCookieName, cipher.encryptString(accessToken.getToken()), 60);
            RequestUtil.setCookieByMinute(res, TwitterAccessTokenSecretCookieName, cipher.encryptString(accessToken.getTokenSecret()), 60);

            //oauth 요청당시의 파라미터 정보
            HashMap<String, Object> refererParamMap = getQstrMapAtOauth(req, cipher);
            boolean isShareCode = refererParamMap.containsKey("code");
            String shareCode = "";
            if(isShareCode){
                shareCode = (String)refererParamMap.get("code");
            }
            SNSShareCodeEnum snsShareCodeEnum = SNSShareCodeEnum.GetSNSShareCodeEnum(shareCode);
            if(SNSShareCodeEnum.isValid(shareCode) == false){
                LOG.error("Twitter Share Code invalid - "+shareCode);
                throw CustomException.INVALID_TWITTER_SHARE_CODE;
            }

            //코드(요청)별 처리
            String result = snsEventService.twitterProcessByCode(twitter, snsEventService.getUserkeyInCookie(req), snsShareCodeEnum, LanguageEnum.GetRegistPageLanguage(local), refererParamMap);
            modelAndView.addObject("result", result);

        }catch(Exception e){
            modelAndView.addObject("result", "fail");
        }
        modelAndView.setViewName("sns/callback");
        return modelAndView;
    }
}
