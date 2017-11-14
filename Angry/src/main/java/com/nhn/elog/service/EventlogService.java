package com.nhn.elog.service;

import java.util.List;

import com.nhn.elog.bean.AbsEventlog;

public interface EventlogService {
    /**
     * 이벤트 로그 조회
     */
    public List<AbsEventlog> getUserEventlogList(long usn, List<String> logs);
}
