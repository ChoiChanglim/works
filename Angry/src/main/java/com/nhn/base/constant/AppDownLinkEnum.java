package com.nhn.base.constant;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;

public enum AppDownLinkEnum {
    IOS_KR("https://itunes.apple.com/kr/app/id1016807557"),
    IOS_EN("https://itunes.apple.com/id/app/id1016807557"),
    IOS_JP("https://itunes.apple.com/jp/app/id1016807557"),
    IOS_TW("https://itunes.apple.com/tw/app/id1016807557"),
    IOS_TH("https://itunes.apple.com/th/app/id1016807557"),
    AOS_NORMAL("https://play.google.com/store/apps/details?id=com.studio629.angrysaga");

    private String link;
    private AppDownLinkEnum(String link){
        this.link = link;
    }
    public String getLink(){
        return link;
    }
    public static String GetUrl(LanguageEnum languageEnum, SupportOSEnum supportOSEnum){
        if(supportOSEnum == SupportOSEnum.IOS){
            if(languageEnum == LanguageEnum.KR){
                return IOS_KR.getLink();
            }
            if(languageEnum == LanguageEnum.JP){
                return IOS_JP.getLink();
            }
            if(languageEnum == LanguageEnum.TW){
                return IOS_TW.getLink();
            }
            if(languageEnum == LanguageEnum.TH){
                return IOS_TH.getLink();
            }
            return IOS_EN.getLink();
        }else{
            return AppDownLinkEnum.AOS_NORMAL.getLink();
        }

    }
}
