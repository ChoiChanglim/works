package com.nhn.game.service;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.nhn.common.exception.CustomException;


@Service
public class MasterServiceImpl implements MasterService{
    private static Logger LOG = LoggerFactory.getLogger(MasterServiceImpl.class);

    @Autowired
    private RedisTemplate<String, String> masterRedisTemplate;

    @Resource(name="masterRedisTemplate")
    private HashOperations<String, String, String> hashOps;

    /**
     * 레디스에 올린 마스터데이터 조회
     * @param key
     * @param version
     * @return
     */
    @Override
    public JSONObject get(String key, String version) {
        boolean hasKey = masterRedisTemplate.opsForHash().hasKey(key, version);
        if(hasKey == false){
            throw CustomException.INVALID_KEY_OR_VERSION;
        }

        String val = hashOps.get(key, version);
        return new JSONObject(val);
    }
}
