package com.nhn.www.cbt.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhn.www.cbt.bean.CbtUserInfo;
import com.nhn.www.cbt.bean.CbtUserInfoExample;
import com.nhn.www.cbt.mapper.CbtUserInfoMapper;

@Transactional(value="baseTransactionManager", readOnly=false)
@Service
public class CbtServiceImpl implements CbtService{

    @Autowired
    CbtUserInfoMapper cbtUserInfoMapper;

    @Override
    public boolean hasUsn(long sno) {
        CbtUserInfo cbtUserInfo = cbtUserInfoMapper.selectByPrimaryKey(sno);
        if(cbtUserInfo == null){
            return false;
        }
        return true;
    }

    @Override
    public boolean hasUserKey(String userkey) {
        CbtUserInfoExample example = new CbtUserInfoExample();
        example.createCriteria().andUserKeyEqualTo(userkey);
        List<CbtUserInfo> list = cbtUserInfoMapper.selectByExample(example);
        if(list.size() == 0){
            return false;
        }
        return true;
    }

    @Override
    public void registUser(long usn, String userkey) {
        CbtUserInfo cbtUserInfo = new CbtUserInfo();
        cbtUserInfo.setUsn(usn);
        cbtUserInfo.setUserKey(userkey);
        cbtUserInfo.setRegdate(new Date());

        cbtUserInfoMapper.insert(cbtUserInfo);

    }

    @Override
    public String getUserKey(long usn) {
        CbtUserInfo cbtUserInfo = cbtUserInfoMapper.selectByPrimaryKey(usn);
        return cbtUserInfo.getUserKey();
    }

}
