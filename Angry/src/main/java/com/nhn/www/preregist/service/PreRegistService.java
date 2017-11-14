package com.nhn.www.preregist.service;

import java.util.List;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.www.preregist.bean.PreRegist;
import com.nhn.www.preregist.bean.PreRegistStatus;

public interface PreRegistService {

    boolean isRegist(String userkey);

    int getRegistTryCountByIp(String remoteAddr);

    void registTry(String remoteAddr);

    PreRegist regist(String store, LanguageEnum languageEnum, String userkey);

    PreRegist getUser(String userkey);

    List<PreRegistStatus> getRegistStatus();

}
