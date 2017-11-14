package com.nhn.www.event.mapper;

import com.nhn.www.event.bean.BannerEventResult;
import com.nhn.www.event.bean.BannerEventResultExample;
import com.nhn.www.event.bean.BannerEventResultKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BannerEventResultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_result
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    int countByExample(BannerEventResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_result
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    int deleteByExample(BannerEventResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_result
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    int deleteByPrimaryKey(BannerEventResultKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_result
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    int insert(BannerEventResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_result
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    int insertSelective(BannerEventResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_result
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    List<BannerEventResult> selectByExample(BannerEventResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_result
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    BannerEventResult selectByPrimaryKey(BannerEventResultKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_result
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    int updateByExampleSelective(@Param("record") BannerEventResult record, @Param("example") BannerEventResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_result
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    int updateByExample(@Param("record") BannerEventResult record, @Param("example") BannerEventResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_result
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    int updateByPrimaryKeySelective(BannerEventResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_result
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    int updateByPrimaryKey(BannerEventResult record);
}