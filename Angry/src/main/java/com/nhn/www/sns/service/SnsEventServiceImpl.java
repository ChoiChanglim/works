package com.nhn.www.sns.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import twitter4j.JSONObject;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.base.constant.ServerKey.ServerKeyEnum;
import com.nhn.common.exception.CustomException;
import com.nhn.common.util.AesCipher;
import com.nhn.common.util.RequestUtil;
import com.nhn.www.preregist.bean.SnsReply;
import com.nhn.www.preregist.bean.SnsReplyExample;
import com.nhn.www.preregist.bean.SnsShare;
import com.nhn.www.preregist.bean.SnsShareExample;
import com.nhn.www.preregist.controller.PreRegistController;
import com.nhn.www.sns.KakaoApiConstant.KakaoResponseBody;
import com.nhn.www.sns.TwitterContents;
import com.nhn.www.sns.TwitterContents.SNSShareCodeEnum;
import com.nhn.www.sns.TwitterContents.TwitterMessagePictureByLanguage;
import com.nhn.www.sns.mapper.SnsReplyMapper;
import com.nhn.www.sns.mapper.SnsShareMapper;

@Transactional(value="baseTransactionManager", readOnly=false)
@Service
public class SnsEventServiceImpl implements SnsEventService{
    private static Logger LOG = LoggerFactory.getLogger(SnsEventServiceImpl.class);

    @Autowired
    SnsBaseService snsBaseService;

    @Autowired
    SnsShareMapper snsShareMapper;

    @Autowired
    SnsReplyMapper snsReplyMapper;

    /**
     * 이벤트 참가 유저키 획득 없으면 언노운
     */
    @Override
    public String getUserkeyInCookie(HttpServletRequest req){
        AesCipher cipher  = new AesCipher(ServerKeyEnum.PRE_REGIST.getKey());
        String userkey = "unknown";
        if(RequestUtil.getCookieMap(req).containsKey(PreRegistController.UserKeyName)){
            String userkey_enc = RequestUtil.getCookieMap(req).get(PreRegistController.UserKeyName);
            userkey = cipher.decryptString(userkey_enc);
        }
        return userkey;
    }


    /**
     * 카카오 스토리 요청별 이벤트 처리
     */
    @Override
    public String kakaoProcessByCode(String userkey, SNSShareCodeEnum snsShareCodeEnum, String kakaoAccessToken, HashMap<String, Object> reqmap) {
        if(snsShareCodeEnum == SNSShareCodeEnum.LOGIN){
            return "success";

        }else if(snsShareCodeEnum == SNSShareCodeEnum.PRE_REGIST_REPLY){
          //유저키가 없으면 댓글 등록 참여 불가
            if("unknown".equals(userkey) == true){
                LOG.error("Not pre-regist user!");
                throw CustomException.EMPTY_USER;
            }
            if(reqmap.containsKey("msg") == false){
                LOG.error("Empty msg parameter!");
                throw CustomException.EMPTY_PARAMETER;
            }
            String msg = (String)reqmap.get("msg");

            int profileResponseCode =snsBaseService.kakaoStoryProfile(kakaoAccessToken);
            if(200 == profileResponseCode){
                int postResponseCode = snsBaseService.kakaoStoryPosting(kakaoAccessToken, msg);
                if(200 == postResponseCode){
                    insertReply(userkey, "kakao", LanguageEnum.KR, msg);
                    return "success";
                }else{
                    return KakaoResponseBody.GetKakaoResponseBody(profileResponseCode).name();
                }
            }else{
                return KakaoResponseBody.GetKakaoResponseBody(profileResponseCode).name();
            }

        }
        return "fail";
    }

    /**
     * Twitter code(요청)별 처리
     */
    @Override
    public String twitterProcessByCode(Twitter twitter, String userkey, SNSShareCodeEnum snsShareCodeEnum, LanguageEnum languageEnum, HashMap<String, Object> reqmap) {
        String result = "fail";
        if(snsShareCodeEnum == SNSShareCodeEnum.PRE_REGIST){  //사전등록 공유 이벤트
            //유저키가 없으면 공유시키지 말자
            if("unknown".equals(userkey) == true){
                LOG.error("Not pre-regist user!");
                throw CustomException.EMPTY_USER;
            }

            TwitterContents.TwitterMessagePictureByLanguage twitterContents = TwitterMessagePictureByLanguage.getTwitterMessagePictureByLanguage(languageEnum.name());
            String picture = twitterContents.getImgurl();
            String msg = twitterContents.getMessage();

            Status status = snsBaseService.sendStatusMedia(twitter, msg, picture);

            if(status.getId() > 0){
                result = "success";
                if(isShareComplete(userkey, "twitter", snsShareCodeEnum) == false){ //참여기록 한번만
                    shareComplete(userkey, "twitter", snsShareCodeEnum, languageEnum);
                }
            }
        }else if(snsShareCodeEnum == SNSShareCodeEnum.LOGIN){
            result = "success";

        }else if(snsShareCodeEnum == SNSShareCodeEnum.PRE_REGIST_REPLY){ //댓글 이벤트
            //유저키가 없으면 댓글 등록 참여 불가
            if("unknown".equals(userkey) == true){
                LOG.error("Not pre-regist user!");
                throw CustomException.EMPTY_USER;
            }
            if(reqmap.containsKey("msg") == false){
                LOG.error("Empty msg parameter!");
                throw CustomException.EMPTY_PARAMETER;
            }
            String msg = (String)reqmap.get("msg");
            Status status = snsBaseService.sendStatusMedia(twitter, msg);
            if(status.getId() > 0){
                result = "success";
                insertReply(userkey, "twitter", languageEnum, msg);
            }

        }else if(snsShareCodeEnum == SNSShareCodeEnum.PRE_REGIST_LIKE){ //좋아요 이벤트
            try {
                //IDs ids = twitter.getFollowersIDs("ABIslands", -1);
                //System.err.println("ids.getIDs().length:"+ids.getIDs().length);
                //if(ids.getIDs().length == 0){
                    User user = twitter.createFriendship("ABIslands", true);
                    LOG.debug("user:"+new JSONObject(user));
                //}
                if(isShareComplete(userkey, "twitter", snsShareCodeEnum) == false){  //참여기록 한번만
                    shareComplete(userkey, "twitter", SNSShareCodeEnum.PRE_REGIST_LIKE, languageEnum);
                }
                result = "success";
            } catch (TwitterException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 공유 이벤트 참여 저장
     */
    @Override
    public void shareComplete(String userkey, String snsname, SNSShareCodeEnum snsShareCodeEnum, LanguageEnum languageEnum) {
        SnsShare snsShare = new SnsShare();
        snsShare.setUserkey(userkey);
        snsShare.setSnsname(snsname);
        snsShare.setCode(snsShareCodeEnum.name());
        snsShare.setLocal(languageEnum.name());
        snsShare.setRegdate(new Date());
        snsShareMapper.insert(snsShare);

    }

    /**
     * 공유 이벤트 참여했는지확인
     * true: 이미참여
     * flase: 참여안함
     */
    @Override
    public boolean isShareComplete(String userkey, String snsname, SNSShareCodeEnum snsShareCodeEnum) {
        SnsShareExample example = new SnsShareExample();
        example.createCriteria().andUserkeyEqualTo(userkey).andCodeEqualTo(snsShareCodeEnum.name()).andSnsnameEqualTo(snsname);

        int cnt = snsShareMapper.countByExample(example);
        if(cnt == 0){
            return false;
        }
        return true;

    }

    /**
     * 댓글이벤트 참여
     */
    @Override
    public void insertReply(String userkey, String snsname, LanguageEnum languageEnum, String message) {
        SnsReply snsReply = new SnsReply();
        snsReply.setUserkey(userkey);
        snsReply.setSnsname(snsname);
        snsReply.setLocal(languageEnum.name());
        snsReply.setMsg(message);
        snsReply.setRegdate(new Date());
        snsReplyMapper.insert(snsReply);

    }

    /**
     * 언어별 댓글 5개 가져오기
     */
    @Transactional(readOnly=true)
    @Override
    public List<SnsReply> getReplyTop5(LanguageEnum languageEnum) {
        SnsReplyExample example = new SnsReplyExample();
        if(languageEnum == LanguageEnum.KR){
            example.createCriteria().andLocalEqualTo(languageEnum.name());
        }else{
            List<String> languageList = new ArrayList<String>();
            languageList.add(LanguageEnum.EN.name());
            languageList.add(LanguageEnum.JP.name());
            languageList.add(LanguageEnum.TH.name());
            languageList.add(LanguageEnum.TW.name());
            example.createCriteria().andLocalIn(languageList);
        }
        example.setOrderByClause("seq desc limit 0, 5");
        List<SnsReply> list = snsReplyMapper.selectByExampleWithBLOBs(example);
        return list;

    }


    /**
     * 댓글삭제
     */
    @Override
    public int deleteReply(long seq) {
        int delete = snsReplyMapper.deleteByPrimaryKey(seq);
        return delete;
    }
}
