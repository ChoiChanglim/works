package com.nhn.www.event.mapper;

import com.nhn.www.event.bean.BannerEventItemImg;
import com.nhn.www.event.bean.BannerEventItemImgExample;
import com.nhn.www.event.bean.BannerEventItemImgKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BannerEventItemImgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item_img
     *
     * @mbggenerated Wed Apr 26 15:56:35 KST 2017
     */
    int countByExample(BannerEventItemImgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item_img
     *
     * @mbggenerated Wed Apr 26 15:56:35 KST 2017
     */
    int deleteByExample(BannerEventItemImgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item_img
     *
     * @mbggenerated Wed Apr 26 15:56:35 KST 2017
     */
    int deleteByPrimaryKey(BannerEventItemImgKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item_img
     *
     * @mbggenerated Wed Apr 26 15:56:35 KST 2017
     */
    int insert(BannerEventItemImg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item_img
     *
     * @mbggenerated Wed Apr 26 15:56:35 KST 2017
     */
    int insertSelective(BannerEventItemImg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item_img
     *
     * @mbggenerated Wed Apr 26 15:56:35 KST 2017
     */
    List<BannerEventItemImg> selectByExample(BannerEventItemImgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item_img
     *
     * @mbggenerated Wed Apr 26 15:56:35 KST 2017
     */
    BannerEventItemImg selectByPrimaryKey(BannerEventItemImgKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item_img
     *
     * @mbggenerated Wed Apr 26 15:56:35 KST 2017
     */
    int updateByExampleSelective(@Param("record") BannerEventItemImg record, @Param("example") BannerEventItemImgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item_img
     *
     * @mbggenerated Wed Apr 26 15:56:35 KST 2017
     */
    int updateByExample(@Param("record") BannerEventItemImg record, @Param("example") BannerEventItemImgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item_img
     *
     * @mbggenerated Wed Apr 26 15:56:35 KST 2017
     */
    int updateByPrimaryKeySelective(BannerEventItemImg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item_img
     *
     * @mbggenerated Wed Apr 26 15:56:35 KST 2017
     */
    int updateByPrimaryKey(BannerEventItemImg record);
}