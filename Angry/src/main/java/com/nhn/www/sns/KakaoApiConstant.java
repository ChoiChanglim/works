package com.nhn.www.sns;

public class KakaoApiConstant {
    public static final String KakaoAccessTokenCookieName = "kact";
    public static final String KakaoQstrAtOauthCookieName = "koqs";
    public static final String KakaoAppkey = "5406a0e5c5975eb4a2cba575bb21ddbc";
    public static final String KakaoCallbackUri = "/sns/kakao/callback";

    public enum KakaoApi {
        GET_CODE("/oauth/authorize"),
        GET_TOKEN("/oauth/token"),
        STORY_PROFILE("/v1/api/story/profile"),
        STORY_POSTING_NOTE("/v1/api/story/post/note");


        private String uri;
        private KakaoApi(String uri){
            this.uri = uri;
        }

        public String getOauthApi(){
            String api = "https://kauth.kakao.com"+uri;
            return api;
        }

        public String getStoryApi(){
            String api = "https://kapi.kakao.com"+uri;
            return api;
        }
     }

    public enum KakaoResponseBody {
        NotExistKakaoTalkUser(-501, "카카오톡 미가입 사용자가 카카오톡 API를 호출 하였을 경우"),
        NotExistKakaoStoryUser(-601, "카카오스토리 미가입 사용자가 카카오스토리 API를 호출 하였을 경우"),
        Unkown(-999, "알수없는 오류 개발자 가이드가서 확인해보시오"),
        Systemfail(-9999, "카카오 api 호출 실패");

        private int code;
        private String desc;
        private KakaoResponseBody(int code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode(){
            return code;
        }
        public String getDesc(){
            return desc;
        }

        public static KakaoResponseBody GetKakaoResponseBody(int code){
            for(int i=0; i<KakaoResponseBody.values().length;i++){
                KakaoResponseBody o = KakaoResponseBody.values()[i];
                if(o.getCode() == code){
                    return o;
                }
            }
            return KakaoResponseBody.Unkown;
        }
    }
}
