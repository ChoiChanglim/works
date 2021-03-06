package com.nhn.www.event.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nhn.www.event.bean.BannerEventItem;
import com.nhn.www.event.bean.BannerEventItemAndImg;
import com.nhn.www.event.bean.BannerEventItemExample;

public interface BannerEventItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item
     *
     * @mbggenerated Wed Apr 26 16:14:34 KST 2017
     */
    int countByExample(BannerEventItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item
     *
     * @mbggenerated Wed Apr 26 16:14:34 KST 2017
     */
    int deleteByExample(BannerEventItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item
     *
     * @mbggenerated Wed Apr 26 16:14:34 KST 2017
     */
    int deleteByPrimaryKey(Integer itemKey);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item
     *
     * @mbggenerated Wed Apr 26 16:14:34 KST 2017
     */
    int insert(BannerEventItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item
     *
     * @mbggenerated Wed Apr 26 16:14:34 KST 2017
     */
    int insertSelective(BannerEventItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item
     *
     * @mbggenerated Wed Apr 26 16:14:34 KST 2017
     */
    List<BannerEventItem> selectByExample(BannerEventItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item
     *
     * @mbggenerated Wed Apr 26 16:14:34 KST 2017
     */
    BannerEventItem selectByPrimaryKey(Integer itemKey);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item
     *
     * @mbggenerated Wed Apr 26 16:14:34 KST 2017
     */
    int updateByExampleSelective(@Param("record") BannerEventItem record, @Param("example") BannerEventItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item
     *
     * @mbggenerated Wed Apr 26 16:14:34 KST 2017
     */
    int updateByExample(@Param("record") BannerEventItem record, @Param("example") BannerEventItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item
     *
     * @mbggenerated Wed Apr 26 16:14:34 KST 2017
     */
    int updateByPrimaryKeySelective(BannerEventItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_event_item
     *
     * @mbggenerated Wed Apr 26 16:14:34 KST 2017
     */
    int updateByPrimaryKey(BannerEventItem record);

    List<BannerEventItemAndImg> selectBannerEventItemAndImg(String lang);
    List<BannerEventItemAndImg> getLiveItemsAndLocalUrl();
    List<BannerEventItemAndImg> getLiveItems();
}