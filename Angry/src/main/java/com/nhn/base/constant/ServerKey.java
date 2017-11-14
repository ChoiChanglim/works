package com.nhn.base.constant;

public class ServerKey {
    public enum ServerKeyEnum{
        PRE_REGIST("xboxscorpio");

        private String key;
        private ServerKeyEnum(String key){
            this.key = key;
        }
        public String getKey(){
            return key;
        }

    }
}
