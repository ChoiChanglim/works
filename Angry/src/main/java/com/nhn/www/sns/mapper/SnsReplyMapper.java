package com.nhn.www.sns.mapper;

import com.nhn.www.preregist.bean.SnsReply;
import com.nhn.www.preregist.bean.SnsReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SnsReplyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sns_reply
     *
     * @mbggenerated Sun Mar 19 12:55:57 KST 2017
     */
    int countByExample(SnsReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sns_reply
     *
     * @mbggenerated Sun Mar 19 12:55:57 KST 2017
     */
    int deleteByExample(SnsReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sns_reply
     *
     * @mbggenerated Sun Mar 19 12:55:57 KST 2017
     */
    int deleteByPrimaryKey(Long seq);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sns_reply
     *
     * @mbggenerated Sun Mar 19 12:55:57 KST 2017
     */
    int insert(SnsReply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sns_reply
     *
     * @mbggenerated Sun Mar 19 12:55:57 KST 2017
     */
    int insertSelective(SnsReply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sns_reply
     *
     * @mbggenerated Sun Mar 19 12:55:57 KST 2017
     */
    List<SnsReply> selectByExampleWithBLOBs(SnsReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sns_reply
     *
     * @mbggenerated Sun Mar 19 12:55:57 KST 2017
     */
    List<SnsReply> selectByExample(SnsReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sns_reply
     *
     * @mbggenerated Sun Mar 19 12:55:57 KST 2017
     */
    SnsReply selectByPrimaryKey(Long seq);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sns_reply
     *
     * @mbggenerated Sun Mar 19 12:55:57 KST 2017
     */
    int updateByExampleSelective(@Param("record") SnsReply record, @Param("example") SnsReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sns_reply
     *
     * @mbggenerated Sun Mar 19 12:55:57 KST 2017
     */
    int updateByExampleWithBLOBs(@Param("record") SnsReply record, @Param("example") SnsReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sns_reply
     *
     * @mbggenerated Sun Mar 19 12:55:57 KST 2017
     */
    int updateByExample(@Param("record") SnsReply record, @Param("example") SnsReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sns_reply
     *
     * @mbggenerated Sun Mar 19 12:55:57 KST 2017
     */
    int updateByPrimaryKeySelective(SnsReply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sns_reply
     *
     * @mbggenerated Sun Mar 19 12:55:57 KST 2017
     */
    int updateByPrimaryKeyWithBLOBs(SnsReply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sns_reply
     *
     * @mbggenerated Sun Mar 19 12:55:57 KST 2017
     */
    int updateByPrimaryKey(SnsReply record);
}