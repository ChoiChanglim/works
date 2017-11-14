package com.nhn.www.sns;

public class SnsApiAccount {
    public static enum TwitterApiAccount{
        JP("ejGDtI08Urdv3pjcHiODmZrV6", "VcC4SX8Kp9nyKayNLBhejUBd3hUCdWARBDAWmbResCoMOrgWHr");

        String key;
        String secret;

        private TwitterApiAccount(String consumerKey, String consumerSecret){
            this.key = consumerKey;
            this.secret = consumerSecret;
        }

        public String getConsumerKey(){
            return key;
        }
        public String getConsumerSecret(){
            return secret;
        }
        public static boolean isSuportLanguage(String local){
            for(int i=0;i<TwitterApiAccount.values().length;i++){
                TwitterApiAccount account = TwitterApiAccount.values()[i];
                if(account.name().equals(local.trim().toUpperCase())){
                    return true;
                }
            }
            return false;
        }
    }
}
