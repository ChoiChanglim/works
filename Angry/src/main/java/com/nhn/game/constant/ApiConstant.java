package com.nhn.game.constant;


public class ApiConstant{
    public enum AdminType{
        WEB_EVENT("1");
        private String id;
        private AdminType(String id){
            this.id = id;
        }
        public String getId(){
            return id;
        }
    }

    public enum ApiEnum {
        AdminGetUserInfoRequest("{UserID}", "HSP회원번호로 유저 기본정보 조회"),
        AdminGetUserInfoByNameRequest("{Nickname}", "닉네임으로 유저 기본정보 조회"),
        AdminSendItemRequest("{UserID, RewardType, RewardId, RewardCount, ExpireTime, Sender}", "유저에게 아이템(보상, 재화) 지급 요청"),
        AdminGetUserItemListRequest("{UserID}", "유저 아이템 정보 조회 요청 (UID 이용)");

        private String params;
        private String desc;
        private ApiEnum(String params, String desc){
            this.params = params;
            this.desc = desc;
        }

        public String getParams(){
            return params;
        }

        public String getDesc(){
            return desc;
        }
    }
}
