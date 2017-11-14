package com.nhn.www.sns.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nhn.base.constant.AngryMappingJacksonJsonView;
import com.nhn.base.constant.ServerKey.ServerKeyEnum;
import com.nhn.common.exception.CustomException;
import com.nhn.common.util.AesCipher;
import com.nhn.common.util.RequestUtil;
import com.nhn.www.sns.KakaoApiConstant;
import com.nhn.www.sns.KakaoApiConstant.KakaoApi;
import com.nhn.www.sns.TwitterContents.SNSShareCodeEnum;
import com.nhn.www.sns.service.SnsBaseService;
import com.nhn.www.sns.service.SnsEventService;

@Controller
@RequestMapping("/sns/kakao")
public class KakaoController {
    private static Logger LOG = LoggerFactory.getLogger(KakaoController.class);

    @Value("#{propinfo['Zone']}")
    private String Zone;

    @Autowired
    SnsBaseService snsBaseService;

    @Autowired
    SnsEventService snsEventService;



    public String getKakaoRedirectUri(HttpServletRequest req){
        if(Zone.equals("Real")){
            return "https://"+req.getServerName()+KakaoApiConstant.KakaoCallbackUri;
        }else{
            return "http://"+req.getServerName()+KakaoApiConstant.KakaoCallbackUri;
        }
    }

    public HashMap<String, Object> getQstrMapAtOauth(HttpServletRequest req, AesCipher cipher){
        //인증요청할때의 파라미터들
        HashMap<String, Object> qstrMap = new HashMap<String, Object>();
        if(RequestUtil.getCookieMap(req).containsKey(KakaoApiConstant.KakaoQstrAtOauthCookieName)){
            String foqs = cipher.decryptString(RequestUtil.getCookieMap(req).get(KakaoApiConstant.KakaoQstrAtOauthCookieName));
            qstrMap = RequestUtil.qstrSplit(foqs);
        }
        return qstrMap;
    }


    /**
     * 카카오 oauth callback
     * @param req
     * @param res
     */
    @RequestMapping(value = "/callback")
    public ModelAndView kakao_callback(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value="code", required = false, defaultValue="") String code,
            @RequestParam(value="state", required = false, defaultValue="") String state
            ) {
        ModelAndView modelAndView = new ModelAndView();
        if(SNSShareCodeEnum.isValid(state) == false){
            LOG.error("Share Code invalid - "+code);
            throw CustomException.INVALID_TWITTER_SHARE_CODE;
        }
        SNSShareCodeEnum snsShareCodeEnum = SNSShareCodeEnum.GetSNSShareCodeEnum(state);

        modelAndView.setView(new AngryMappingJacksonJsonView());
        String redirect_uri = getKakaoRedirectUri(req);
        String kakaoAccessToken = snsBaseService.getKakaoAccessToken(code, redirect_uri);

        AesCipher cipher  = new AesCipher(ServerKeyEnum.PRE_REGIST.getKey());
        RequestUtil.setCookieByMinute(res, KakaoApiConstant.KakaoAccessTokenCookieName, cipher.encryptString(kakaoAccessToken), 360);

        //oauth인증 시점의 파라미터들
        HashMap<String, Object> refererParamMap = getQstrMapAtOauth(req, cipher);

        String result = snsEventService.kakaoProcessByCode(snsEventService.getUserkeyInCookie(req), snsShareCodeEnum, kakaoAccessToken, refererParamMap);
        modelAndView.addObject("result", result);
        modelAndView.addObject("snsname", "kakao");
        modelAndView.addObject("code", snsShareCodeEnum.name().toLowerCase());
        modelAndView.setViewName("sns/callback");
        return modelAndView;
    }
    /**
     * 카카오 oauth 요청
     * @param req
     * @param res
     * @param code
     * @return
     */
    @RequestMapping(value = "/oauth")
    public ModelAndView kakao_oauth(HttpServletRequest req, HttpServletResponse res,
            @RequestParam(value="code", required = false, defaultValue="") String code
            ) {
        ModelAndView modelAndView = new ModelAndView();

        if(SNSShareCodeEnum.isValid(code) == false){
            LOG.error("Share Code invalid - "+code);
            throw CustomException.INVALID_TWITTER_SHARE_CODE;
        }
        AesCipher cipher  = new AesCipher(ServerKeyEnum.PRE_REGIST.getKey());
        //카카오 엑세스토큰 있으면 저장된 토큰 사용
        if(RequestUtil.getCookieMap(req).containsKey(KakaoApiConstant.KakaoAccessTokenCookieName)){
            String token = cipher.decryptString(RequestUtil.getCookieMap(req).get(KakaoApiConstant.KakaoAccessTokenCookieName));
            modelAndView.addObject("hasToken", true);

            //코드(요청)별 처리
            SNSShareCodeEnum snsShareCodeEnum = SNSShareCodeEnum.GetSNSShareCodeEnum(code);
            boolean isComplete = snsEventService.isShareComplete(snsEventService.getUserkeyInCookie(req), "kakao", snsShareCodeEnum);
            if(isComplete == true && snsShareCodeEnum == SNSShareCodeEnum.PRE_REGIST_LIKE){
                modelAndView.addObject("result", "success");
                return modelAndView;
            }
            String result = snsEventService.kakaoProcessByCode(snsEventService.getUserkeyInCookie(req), snsShareCodeEnum, token, RequestUtil.reqParamToMap(req));
            modelAndView.addObject("result", result);
            modelAndView.setView(new AngryMappingJacksonJsonView());
            return modelAndView;
        }


        //oauth 요청시의 파라미터 쿠키에 저장 callback에서의 처리를 위해
        RequestUtil.setCookieByMinute(res, KakaoApiConstant.KakaoQstrAtOauthCookieName, cipher.encryptString(RequestUtil.getQstr(req)), 30);


        HashMap<String, String> param = new HashMap<String, String>();
        param.put("client_id", KakaoApiConstant.KakaoAppkey);
        param.put("redirect_uri", getKakaoRedirectUri(req));
        param.put("response_type", "code");
        param.put("state", code);
        param.put("encode_state", "false");

        String oauthUrl = KakaoApi.GET_CODE.getOauthApi()+"?"+RequestUtil.getMapToQstr(param);
        modelAndView.addObject("oauthUrl", oauthUrl);
        modelAndView.addObject("result", "ready");
        modelAndView.addObject("hasToken", false);
        return modelAndView;
    }
}
