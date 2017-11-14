package com.nhn.www.sns;



public class TwitterContents {
    public enum SNSShareCodeEnum{
        LOGIN("로그인 확인"),
        PRE_REGIST("사전등록 공유 이벤트"),
        PRE_REGIST_REPLY("사전등록 기대평 댓글 이벤트"),
        PRE_REGIST_LIKE("사전등록 공식 운영페북/트위터 좋아요 이벤트");

        private String descript;
        private SNSShareCodeEnum(String descript){
            this.descript = descript;
        }

        public String getDescript(){
            return descript;
        }
        public static SNSShareCodeEnum getSNSShareCodeEnum(String code){
            for(int i=0;i<SNSShareCodeEnum.values().length;i++){
                SNSShareCodeEnum o = SNSShareCodeEnum.values()[i];
                if(o.name().equals(code.toUpperCase())){
                    return o;
                }
            }
            return null;
        }

        public static boolean isValid(String code) {
            for(int i=0;i<SNSShareCodeEnum.values().length;i++){
                SNSShareCodeEnum o = SNSShareCodeEnum.values()[i];
                if(o.name().equals(code.toUpperCase())){
                    return true;
                }
            }
            return false;
        }
        public static SNSShareCodeEnum GetSNSShareCodeEnum(String code) {
            for(int i=0;i<SNSShareCodeEnum.values().length;i++){
                SNSShareCodeEnum o = SNSShareCodeEnum.values()[i];
                if(o.name().equals(code.toUpperCase())){
                    return o;
                }
            }
            return null;
        }

    }

    public static enum TwitterMessagePictureByLanguage{
        KR("앵그리버드와 피기의 미지의 섬 생존기 미지의 섬에 불시착한 버드와 피기와 함께 모험을 떠나 보세요! http://hgurl.me/bXR", "https://images.toast.com/toast/s629/angry/web/sns/sns_share.jpg"),
        EN("Birds and Pigs team up to survive on the Mysterious Islands! http://hgurl.me/bXR", "https://images.toast.com/toast/s629/angry/web/sns/sns_share.jpg"),
        JP("アングリーバード＆ピギーによる、未知の島でのサバイバル 未知の島に不時着したバードやピギーと一緒に、冒険に出よう！  http://hgurl.me/bXR", "https://images.toast.com/toast/s629/angry/web/sns/sns_share.jpg"),
        TW("為了在神秘島上生存，憤怒鳥與搗蛋豬聯手合作！ 加入憤怒鳥和搗蛋豬的行列，一起到神秘島上探險！  http://hgurl.me/bXR", "https://images.toast.com/toast/s629/angry/web/sns/sns_share.jpg"),
        TH("นกและหมูร่วมมือกันเพื่อเอาชีวิตรอดบนเกาะลึกลับ! http://hgurl.me/bXR", "https://images.toast.com/toast/s629/angry/web/sns/sns_share.jpg");

        private String message;
        private String imgurl;

        private TwitterMessagePictureByLanguage(String message, String imgurl){
            this.message = message;
            this.imgurl = imgurl;
        }

        public static TwitterMessagePictureByLanguage getTwitterMessagePictureByLanguage(String local){
            for(int i=0;i<TwitterMessagePictureByLanguage.values().length;i++){
                TwitterMessagePictureByLanguage o = TwitterMessagePictureByLanguage.values()[i];
                if(o.name().equals(local.trim().toUpperCase())){
                    return o;
                }
            }
            return TwitterMessagePictureByLanguage.EN;
        }

        public String getMessage(){
            return message;
        }
        public String getImgurl(){
            return imgurl;
        }
    }


    public static enum TwitterFanpageByLanguage{
        JP("https://twitter.com/ABIslands");

        private String link;
        private TwitterFanpageByLanguage(String link){
            this.link = link;
        }
        public String getLink(){
            return link;
        }
        public static TwitterFanpageByLanguage GetTwitterFanpageByLanguage(String local){
            for(int i=0;i<TwitterFanpageByLanguage.values().length;i++){
                TwitterFanpageByLanguage o = TwitterFanpageByLanguage.values()[i];
                if(o.name().equals(local.toUpperCase())){
                    return o;
                }
            }
            return TwitterFanpageByLanguage.JP;
        }
    }
}
