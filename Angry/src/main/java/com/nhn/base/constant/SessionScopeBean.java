package com.nhn.base.constant;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.common.util.BaseRequestInfo;
import com.nhn.common.util.RequestUtil;
import com.nhn.common.util.StringUtil;



/**
 * @author NHNEnt
 *
 */
@Scope(value="session")
@Component("sessionScopebean")
public class SessionScopeBean extends BaseRequestInfo implements Serializable{

    private static final long serialVersionUID = 3080964111413624631L;
    private static Logger LOG = LoggerFactory.getLogger(SessionScopeBean.class);
    public static final String SNO_COOKIE_KEY    = "SNO";        //회원번호
    public static final String LOCALE_COOKIE_KEY = "LOCAL";      //지역 코드
    public static final String OS_COOKIE_KEY     = "OS";         //클라에서 전달받은 OS정보
    public static final int COOKIE_UNTILL_DATE   = 7;


    long sno = 0;
    String language = "EN";
    LanguageEnum languageEnum = LanguageEnum.EN;
    String domain="angry.hangame.com";
    String Zone;

    public long getSno() {
        String returnValue = "0";
        if(getParamMap().containsKey("sno")){
            if(StringUtil.isDigit((String)getParamMap().get("sno"))){
                returnValue = (String)getParamMap().get("sno");
            }
        }else if(getParamMap().containsKey("_memberno")){
            if(StringUtil.isDigit((String)getParamMap().get("_memberno"))){
                returnValue = (String)getParamMap().get("_memberno");
            }
        }else{
            if(getCookieMap().containsKey(SNO_COOKIE_KEY)){
                if(StringUtil.isDigit(getCookieMap().get(SNO_COOKIE_KEY))){
                    returnValue = getCookieMap().get(SNO_COOKIE_KEY);
                }
            }
        }
        return Long.valueOf(returnValue);

    }
    public String getLanguage() {
        if(getParamMap().containsKey("local")){
            String local = (String)getParamMap().get("local");
            local = local.toUpperCase().replace("US", "en");
            if(Pattern.matches("^[a-zA-Z]*$", local) == true && LanguageEnum.ValidLanguage(local) == true){
                try{
                    LanguageEnum lang = LanguageEnum.GetRegistPageLanguage(local);
                    return lang.name();
                }catch(Exception e){
                    LOG.error("Parameter[local]  resolver! - #1"+local);
                }
            }else{
                return LanguageEnum.EN.name();
            }
        }else if(getParamMap().containsKey("_countrycode")){
            String local = (String)getParamMap().get("_countrycode");
            local = local.toUpperCase().replace("US", "en");
            if(Pattern.matches("^[a-zA-Z]*$", local) == true && LanguageEnum.ValidLanguage(local) == true){
                try{
                    LanguageEnum lang = LanguageEnum.GetRegistPageLanguage(local);
                    return lang.name();
                }catch(Exception e){
                    LOG.error("Parameter[local]  resolver! - #1"+local);
                }
            }else{
                return LanguageEnum.EN.name();
            }
        }else{
            if(getCookieMap().containsKey(LOCALE_COOKIE_KEY)){
                String local = getCookieMap().get(LOCALE_COOKIE_KEY);
                if(Pattern.matches("^[a-zA-Z]*$", local) == true){
                    try{
                        LanguageEnum lang = LanguageEnum.GetRegistPageLanguage(local.toUpperCase());
                        return lang.name();
                    }catch(Exception e){
                        LOG.error("Cookie[local] is not resolver! - #3"+local);
                    }
                }
            }
        }
        return language;
    }



    public OSInfo getOSInfo(){
        if(getParamMap().containsKey("os")){
            String os = (String)getParamMap().get("os");
            if(OSInfo.ValidOS(os)){
                try{
                    OSInfo osinfo = OSInfo.valueOf(os.toUpperCase());
                    return osinfo;
                }catch(Exception e){
                    LOG.error("Parameter[os] is not resolver! - "+os);
                }
            }else{
                if(getCookieMap().containsKey(OS_COOKIE_KEY)){
                    String cookie_os = getCookieMap().get(OS_COOKIE_KEY);
                    try{
                        OSInfo osinfo = OSInfo.valueOf(cookie_os.toUpperCase());
                        return osinfo;
                    }catch(Exception e){
                        LOG.error("Cookie["+OS_COOKIE_KEY+"] is not resolver! - "+os);
                    }
                }
            }
        }else{
            if(getCookieMap().containsKey(OS_COOKIE_KEY)){
                String os = getCookieMap().get(OS_COOKIE_KEY);
                try{
                    OSInfo osinfo = OSInfo.valueOf(os.toUpperCase());
                    return osinfo;
                }catch(Exception e){
                    LOG.error("Cookie["+OS_COOKIE_KEY+"] is not resolver! - "+os);
                }
            }
        }
        return null;
    }


    public void BaseRequestInfoSetting(HttpServletRequest req) {
        setUri(req.getRequestURI());
        setUrl(req.getRequestURL().toString());
        setServerName(req.getServerName());
        setQueryString(req.getQueryString());
        setRemoteAddr(req.getRemoteAddr());
        setRemoteHost(req.getRemoteHost());

        setParamMap(RequestUtil.reqParamToMap(req));
        setCookieMap(RequestUtil.getCookieMap(req));


    }

    public LanguageEnum getLanguageEnum() {
        return LanguageEnum.valueOf(getLanguage().toUpperCase());
    }





    public static enum OSInfo{
        IOS(1),
        AOS(2),
        UNKNOWN(3);
        private int osNo;

        private OSInfo(int osNo){
            this.osNo = osNo;
        }
        public int getOsNo(){
            return osNo;
        }
        public static OSInfo GetOsInfo(int osNo){
            for(int i=0;i<OSInfo.values().length;i++){
                OSInfo o = OSInfo.values()[i];
                if(o.getOsNo() == osNo){
                    return o;
                }
            }
            return OSInfo.AOS;
        }

        public static OSInfo GetOsInfoByName(String osName){
            for(int i=0;i<OSInfo.values().length;i++){
                OSInfo o = OSInfo.values()[i];
                if(o.name().equals(osName.toUpperCase())){
                    return o;
                }
            }
            return OSInfo.AOS;
        }

        public static boolean ValidOS(String osName){
            for(int i=0;i<OSInfo.values().length;i++){
                OSInfo o = OSInfo.values()[i];
                if(o.name().equals(osName.toUpperCase())){
                    return true;
                }
            }
            return false;
        }
    }





    public String getDomain() {
        if("Real".equals(Zone)){
            return domain;
        }else{
            return "alpha-"+domain;
        }

    }
    public String getZone() {
        return Zone;
    }
    public void setZone(String zone) {
        Zone = zone;
    }


}
