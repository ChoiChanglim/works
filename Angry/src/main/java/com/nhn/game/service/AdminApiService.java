package com.nhn.game.service;

import java.util.HashMap;

import com.nhn.common.util.HttpWebURLResponse;
import com.nhn.game.api.AdminGetUserInfoRequest;

public interface AdminApiService {
    /**
     * 유저 기본 정보 요청. admin api 호출
     * @param usn
     * @return
     */
    public AdminGetUserInfoRequest getAdminGetUserInfoRequest(long usn);
    /**
     * admin api 호출 모듈.
     * @param param
     * @return
     */
    public HttpWebURLResponse AdminHttpCall(HashMap<String, Object> param);
    /**
     * 우편함에 아이템 지급
     * true: 지급 성공
     * false: 지급 실패
     */
    public boolean isSendItemMail(long usn, int itemType, int itemId, long itemCount);
}
