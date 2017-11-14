package com.nhn.elog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhn.elog.bean.AbsEventlog;
import com.nhn.elog.bean.AbsEventlogExample;
import com.nhn.elog.mapper.AbsEventlogMapper;

@Transactional(value="elogTransactionManager", readOnly=true)
@Service
public class EventlogServiceImpl implements EventlogService{
    @Autowired
    AbsEventlogMapper absEventlogMapper;

    /**
     * 이벤트 로그 조회
     */
    @Override
    public List<AbsEventlog> getUserEventlogList(long usn, List<String> logs) {
        AbsEventlogExample example = new AbsEventlogExample();
        example.createCriteria().andUseridEqualTo(usn).andLogIn(logs);
        List<AbsEventlog> list = absEventlogMapper.selectByExample(example);
        return list;
    }

}
