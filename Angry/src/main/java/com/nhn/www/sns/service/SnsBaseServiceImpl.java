package com.nhn.www.sns.service;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.apache.tika.Tika;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.nhn.common.exception.CustomException;
import com.nhn.common.util.HttpWebURLConnection;
import com.nhn.common.util.HttpWebURLResponse;
import com.nhn.www.sns.AccountByTwitterFactory;
import com.nhn.www.sns.KakaoApiConstant;
import com.nhn.www.sns.KakaoApiConstant.KakaoApi;
import com.nhn.www.sns.KakaoApiConstant.KakaoResponseBody;


@Service
public class SnsBaseServiceImpl implements SnsBaseService{
    private static Logger LOG = LoggerFactory.getLogger(SnsBaseService.class);

    /**
     * 트위터
     */
    @Override
    public AccessToken getAccessToken(RequestToken requestToken, String oauth_verifier) throws TwitterException{
        AccountByTwitterFactory factory = new AccountByTwitterFactory("JP");
        Twitter twitter = factory.getFactory().getInstance();
        AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, oauth_verifier);
        return accessToken;
    }

    /**
     * 트윗 작성.
     */
    @Override
    public Status sendStatus(AccessToken accessToken, String msg) throws TwitterException {
        AccountByTwitterFactory factory = new AccountByTwitterFactory("JP");
        Twitter twitter = factory.getFactory().getInstance();
        twitter.setOAuthAccessToken(accessToken);
        Status status = twitter.updateStatus(String.format(msg));
        return status;
    }
    /**
     * 트윗 작성2.
     */
    @Override
    public Status sendStatusMedia(Twitter twitter, String msg){
        StatusUpdate statusUpdate = new StatusUpdate(String.format(msg));
        Status status = null;
        try {
            status = twitter.updateStatus(statusUpdate);
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return status;
    }
    /**
     * 트윗작성 및 이미지 업로드
     */
    @Override
    public Status sendStatusMedia(Twitter twitter, String msg, String fileUrl){
        Status status = null;
        try {
            StatusUpdate statusUpdate = new StatusUpdate(String.format(msg));
            URL url = new URL(fileUrl);
            URLConnection urlConnection = url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            String mimeType = getMimeTypeByInputStream(in);
            if(mimeType.contains("image") == true){
                statusUpdate.setMedia("image.png", in);
            }
            status = twitter.updateStatus(statusUpdate);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return status;
    }
    /**
     * tika api 이용하여 mime타입 확인
     * @param inputStream
     * @return
     */
    public String getMimeTypeByInputStream(InputStream inputStream){
        String mimeType = null;
        try {
            Tika tika = new Tika();
            mimeType = tika.detect(inputStream);
        }catch(Exception e){
            throw CustomException.MimeTypeDetectException;
        }
        return mimeType;
    }


    /**
     * 카카오 Accesstoken 획득
     */
    @Override
    public String getKakaoAccessToken(String code, String redirect_uri) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("grant_type", "authorization_code");
        param.put("client_id", KakaoApiConstant.KakaoAppkey);
        param.put("redirect_uri", redirect_uri);
        param.put("code", code);

        String dest = KakaoApi.GET_TOKEN.getOauthApi();
        String kakaoAccessToken = "";
        HttpWebURLConnection conn = new HttpWebURLConnection(dest, param);
        try {
            HttpWebURLResponse result =  conn.sendPost();
            if(200 == result.getResponseCode()){
                kakaoAccessToken = result.getResultJson().getString("access_token");
            }
        } catch (Exception e) {
            throw CustomException.HTTP_WEB_CALL_EXCEPTION;
        }
        return kakaoAccessToken;
    }

    /**
     * 카카오 스토리 유저 확인
     * @param kakaoAccessToken
     * @return
     */
    @Override
    public int kakaoStoryProfile(String kakaoAccessToken){
        HashMap<String, Object> param = new HashMap<String, Object>();
        HttpWebURLConnection conn = new HttpWebURLConnection(KakaoApi.STORY_PROFILE.getStoryApi(), param);
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Authorization", "Bearer "+kakaoAccessToken);
        conn.setHeader(headerMap);
        try {
            HttpWebURLResponse result = conn.sendPost();
            return result.getResponseCode();
        } catch (Exception e) {
            LOG.error("Kakao api exception!"+KakaoApi.STORY_PROFILE.getStoryApi()+", "+new JSONObject(headerMap)+", "+new JSONObject(param));
        }

        return KakaoResponseBody.Systemfail.getCode();
    }

    /**
     * 카카오 스토리 글 포스팅
     * @return
     */
    @Override
    public int kakaoStoryPosting(String kakaoAccessToken, String message){
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("content", message);

        HttpWebURLConnection conn = new HttpWebURLConnection(KakaoApi.STORY_POSTING_NOTE.getStoryApi(), param);
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Authorization", "Bearer "+kakaoAccessToken);
        conn.setHeader(headerMap);
        try {
            HttpWebURLResponse result = conn.sendPost();
            return result.getResponseCode();
        } catch (Exception e) {
            LOG.error("Kakao api exception!"+KakaoApi.STORY_POSTING_NOTE.getStoryApi()+", "+new JSONObject(headerMap)+", "+new JSONObject(param));
        }

        return KakaoResponseBody.Systemfail.getCode();


    }
}
