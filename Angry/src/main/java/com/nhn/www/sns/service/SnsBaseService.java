package com.nhn.www.sns.service;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;


public interface SnsBaseService {
    //트위터
    public AccessToken getAccessToken(RequestToken requestToken, String oauth_verifier) throws TwitterException;
    public Status sendStatus(AccessToken accessToken, String msg) throws TwitterException;
    public Status sendStatusMedia(Twitter twitter, String msg, String serverImgPath);
    public Status sendStatusMedia(Twitter twitter, String msg);

    //카카오
    public String getKakaoAccessToken(String code, String redirect_uri);
    public int kakaoStoryProfile(String kakaoAccessToken);
    public int kakaoStoryPosting(String kakaoAccessToken, String message);


}
