package com.nhn.www.event.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nhn.www.event.bean.FlowerEventLog;
import com.nhn.www.event.bean.FlowerEventLogExample;
import com.nhn.www.event.bean.MyWin;

public interface FlowerEventLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_log
     *
     * @mbggenerated Mon Apr 10 19:55:08 KST 2017
     */
    int countByExample(FlowerEventLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_log
     *
     * @mbggenerated Mon Apr 10 19:55:08 KST 2017
     */
    int deleteByExample(FlowerEventLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_log
     *
     * @mbggenerated Mon Apr 10 19:55:08 KST 2017
     */
    int deleteByPrimaryKey(Long seq);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_log
     *
     * @mbggenerated Mon Apr 10 19:55:08 KST 2017
     */
    int insert(FlowerEventLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_log
     *
     * @mbggenerated Mon Apr 10 19:55:08 KST 2017
     */
    int insertSelective(FlowerEventLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_log
     *
     * @mbggenerated Mon Apr 10 19:55:08 KST 2017
     */
    List<FlowerEventLog> selectByExample(FlowerEventLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_log
     *
     * @mbggenerated Mon Apr 10 19:55:08 KST 2017
     */
    FlowerEventLog selectByPrimaryKey(Long seq);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_log
     *
     * @mbggenerated Mon Apr 10 19:55:08 KST 2017
     */
    int updateByExampleSelective(@Param("record") FlowerEventLog record, @Param("example") FlowerEventLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_log
     *
     * @mbggenerated Mon Apr 10 19:55:08 KST 2017
     */
    int updateByExample(@Param("record") FlowerEventLog record, @Param("example") FlowerEventLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_log
     *
     * @mbggenerated Mon Apr 10 19:55:08 KST 2017
     */
    int updateByPrimaryKeySelective(FlowerEventLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_log
     *
     * @mbggenerated Mon Apr 10 19:55:08 KST 2017
     */
    int updateByPrimaryKey(FlowerEventLog record);

    List<MyWin> myWinStatus(FlowerEventLogExample example);
}