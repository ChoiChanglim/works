package com.nhn.base.notice.service;

import java.util.HashMap;
import java.util.List;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.base.notice.bean.Notice;

public interface NoticeService {
    Notice getNotice(int idx, LanguageEnum languageEnum);

    List<HashMap<String, Object>> getPopupNoticeList(LanguageEnum languageEnum, int level, long regist_time);

    List<HashMap<String, Object>> getLoadingImgList(LanguageEnum languageEnum);




}
