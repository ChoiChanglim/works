package com.nhn.base.constant;

import java.util.ArrayList;
import java.util.List;


public class LangeageIndex {
    public static enum LanguageEnum{
        KR(1, "한국어", "ko", "NHN"),
        EN(2, "English", "en", "EN"),
        JP(3, "日本語", "ja", "JP"),
        TW(4, "中文(繁體)", "zh-TW", "TW"),
        TH(5, "Thailand", "th", "TH");

        private int langIndex;
        private String descript;
        private String htmlLang;
        private String alias;

        private LanguageEnum(int langIndex, String descript, String htmlLang, String alias){
            this.langIndex = langIndex;
            this.descript = descript;
            this.htmlLang = htmlLang;
            this.alias = alias;

        }

        public int getLanguageIndex(){
            return langIndex;
        }
        public String getDescript(){
            return descript;
        }
        public String getHtmlLang(){
            return htmlLang;
        }
        public String getAlias(){
            return alias;
        }
        public static boolean ValidLanguage(String code){
            for(int i=0;i<LanguageEnum.values().length;i++){
                LanguageEnum o = LanguageEnum.values()[i];
                if(o.getAlias().equals(code.toUpperCase()) || o.name().equals(code.toUpperCase())){
                    return true;
                }
            }
            return false;
        }
        public static boolean isSuportRegistPageLanguage(String alias){
            for(int i=0;i<LanguageEnum.values().length;i++){
                LanguageEnum o = LanguageEnum.values()[i];
                if(o.getAlias().equals(alias.toUpperCase())){
                    return true;
                }
            }
            return false;
        }

        public static LanguageEnum GetRegistPageLanguage(String alias){
            for(int i=0;i<LanguageEnum.values().length;i++){
                LanguageEnum o = LanguageEnum.values()[i];
                if(o.getAlias().equals(alias.toUpperCase()) || o.name().equals(alias.toUpperCase())){
                    return o;
                }
            }
            return LanguageEnum.EN;
        }
        public static LanguageEnum GetSupportLanguage(String code){
            for(int i=0;i<LanguageEnum.values().length;i++){
                LanguageEnum o = LanguageEnum.values()[i];
                if(o.name().equals(code.toUpperCase())){
                    return o;
                }
            }
            return LanguageEnum.EN;
        }

        public static List<LanguageEnum> PreRegistGlobalLanguageList(){
            List<LanguageEnum> list = new ArrayList<LanguageEnum>();
            for(int i=0;i<LanguageEnum.values().length;i++){
                LanguageEnum o = LanguageEnum.values()[i];
                if(o != LanguageEnum.KR){
                    list.add(o);
                }

            }
            return list;
        }
        public static List<LanguageEnum> GetLanguageList(){
            List<LanguageEnum> list = new ArrayList<LanguageEnum>();
            for(int i=0;i<LanguageEnum.values().length;i++){
                LanguageEnum o = LanguageEnum.values()[i];
                list.add(o);
            }
            return list;
        }

    }
}
