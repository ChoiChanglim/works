package com.nhn.www.sns;


public class FacebookContents {
    public static final String FacebookAccessTokenCookieName = "fact";
    public static final String FacebookQstrAtOauthCookieName = "foqs";
    public static final String FacebookAppId = "1661537384082631";
    public static final String FacebookAppSecret = "5eeaedd3e3302c94274a63cad4e13d2c";

    /*public static enum FacebookMessagePictureByLanguage{
        KR("앵그리버드 아일랜드!", "http://images.toast.com/toast/s629/woopang/sns_img.png"),
        EN("The Cutest Puzzle Game, WooparooPang(Match 3 Puzzle) Download it now! (Free App) https://goo.gl/uo3Pxp", "http://images.toast.com/toast/s629/woopang/sns_img.png"),
        JP("カワイイ×パズル 「ウパルパン」 3マッチパズルとキャラクターコレクションを楽しもう! 今すぐダウンロードしよう! https://goo.gl/uo3Pxp", "http://images.toast.com/toast/s629/woopang/sns_JP.png"),
        TW("烏法魯萌萌消! 盡情體驗三消和收集角色的樂趣吧！現在馬上下載遊戲吧！ https://goo.gl/uo3Pxp", "http://images.toast.com/toast/s629/woopang/sns_HK.png");

        private String message;
        private String imgurl;
        //private String link = "https://goo.gl/uo3Pxp";
        private String link = "http://alpha-angry.hangame.com/";

        private FacebookMessagePictureByLanguage(String message, String imgurl){
            this.message = message;
            this.imgurl = imgurl;
        }

        public static FacebookMessagePictureByLanguage getFacebookMessagePictureByLanguage(String local){
            for(int i=0;i<FacebookMessagePictureByLanguage.values().length;i++){
                FacebookMessagePictureByLanguage o = FacebookMessagePictureByLanguage.values()[i];
                if(o.name().equals(local.trim().toUpperCase())){
                    return o;
                }
            }
            return FacebookMessagePictureByLanguage.EN;
        }

        public String getMessage(){
            return message;
        }
        public String getImgurl(){
            return imgurl;
        }
        public String getLink(){
            return link+this.name().toLowerCase();
        }
    }*/
    public static enum FacebookFanpageByLanguage{
        EN("https://www.facebook.com/AngryBirdsIslands"),
        TW("https://www.facebook.com/AngryBirdsIslandsTW");

        private String link;
        private FacebookFanpageByLanguage(String link){
            this.link = link;
        }
        public String getLink(){
            return link;
        }
        public static FacebookFanpageByLanguage GetFacebookFanpageByLanguage(String local){
            for(int i=0;i<FacebookFanpageByLanguage.values().length;i++){
                FacebookFanpageByLanguage o = FacebookFanpageByLanguage.values()[i];
                if(o.name().equals(local.toUpperCase())){
                    return o;
                }
            }
            return FacebookFanpageByLanguage.EN;
        }
    }

}
