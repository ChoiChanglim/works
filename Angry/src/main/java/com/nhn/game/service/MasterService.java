package com.nhn.game.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface MasterService {
    /**
     * 레디스에 올린 마스터데이터 조회
     * @param key
     * @param version
     * @return
     */
    public JSONObject get(String key, String version);
}
