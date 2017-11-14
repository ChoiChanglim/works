package com.nhn.www.sns.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import twitter4j.Twitter;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.www.preregist.bean.SnsReply;
import com.nhn.www.sns.TwitterContents.SNSShareCodeEnum;


public interface SnsEventService {
    public String getUserkeyInCookie(HttpServletRequest req);
    public String kakaoProcessByCode(String userkey, SNSShareCodeEnum snsShareCodeEnum, String kakaoAccessToken, HashMap<String, Object> reqmap);
    public void shareComplete(String userkey, String snsname, SNSShareCodeEnum snsShareCodeEnum, LanguageEnum languageEnum);
    public boolean isShareComplete(String userkey, String snsname, SNSShareCodeEnum snsShareCodeEnum);
    public String twitterProcessByCode(Twitter twitter, String userkey, SNSShareCodeEnum snsShareCodeEnum, LanguageEnum languageEnum, HashMap<String, Object> reqmap);
    public void insertReply(String userkey, String snsname, LanguageEnum languageEnum, String message);
    public List<SnsReply> getReplyTop5(LanguageEnum languageEnum);
    public int deleteReply(long seq);
}
