package com.nhn.www.cbt.service;


public interface CbtService {

    boolean hasUsn(long sno);

    boolean hasUserKey(String userkey);

    void registUser(long usn, String userkey);

    String getUserKey(long usn);

}
