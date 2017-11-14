package com.nhn.www.preregist.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.www.preregist.bean.PreRegist;
import com.nhn.www.preregist.bean.PreRegistExample;
import com.nhn.www.preregist.bean.PreRegistStatus;
import com.nhn.www.preregist.bean.PreRegistTry;
import com.nhn.www.preregist.bean.PreRegistTryExample;
import com.nhn.www.preregist.mapper.PreRegistMapper;
import com.nhn.www.preregist.mapper.PreRegistTryMapper;

@Transactional(value="baseTransactionManager", readOnly=false)
@Service
public class PreRegistServiceImpl implements PreRegistService{

    @Autowired
    PreRegistMapper preRegistMapper;

    @Autowired
    PreRegistTryMapper preRegistTryMapper;

    /**
     * 사전등록 여부
     */
    @Override
    public boolean isRegist(String userkey) {
        PreRegistExample preRegistExample = new PreRegistExample();
        preRegistExample.createCriteria().andUserkeyEqualTo(userkey);
        int count = preRegistMapper.countByExample(preRegistExample);
        if(count  == 0){
            return false;
        }
        return true;
    }

    /**
     * IP별 사전등록시도 횟수조회 최근10일만 체크
     */
    @Override
    public int getRegistTryCountByIp(String ip) {
        PreRegistTryExample example = new PreRegistTryExample();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -10);
        example.createCriteria().andIpEqualTo(ip).andRegdateBetween(c.getTime(), new Date());
        int count = preRegistTryMapper.countByExample(example);
        return count;
    }

    /**
     * 사전등록 시도 기록
     */
    @Override
    public void registTry(String ip) {
        PreRegistTry preRegistTry = new PreRegistTry();
        preRegistTry.setIp(ip);
        preRegistTry.setRegdate(new Date());
        preRegistTryMapper.insert(preRegistTry);

    }

    /**
     * 사전등록 저장
     */
    @Override
    public PreRegist regist(String store, LanguageEnum languageEnum, String userkey) {
        PreRegist preRegist = new PreRegist();
        preRegist.setLocal(languageEnum.name());
        preRegist.setUserkey(userkey);
        preRegist.setStore(store);
        preRegist.setRegdate(new Date());
        preRegistMapper.insert(preRegist);
        return preRegist;
    }

    /**
     * 사전등록 유저 단건 조회
     */
    @Override
    public PreRegist getUser(String userkey) {
        PreRegist preRegist = preRegistMapper.selectByPrimaryKey(userkey);
        return preRegist;
    }

    /**
     * 사전등록 현황
     */
    @Override
    @Transactional(readOnly=false)
    public List<PreRegistStatus> getRegistStatus() {
        List<PreRegistStatus> list = preRegistMapper.getRegistStatus();
        return list;
    }

}
