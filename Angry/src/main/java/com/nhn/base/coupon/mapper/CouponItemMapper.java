package com.nhn.base.coupon.mapper;

import com.nhn.base.coupon.bean.CouponItem;
import com.nhn.base.coupon.bean.CouponItemExample;
import com.nhn.base.coupon.bean.CouponItemKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_item
     *
     * @mbggenerated Mon Feb 06 15:02:12 KST 2017
     */
    int countByExample(CouponItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_item
     *
     * @mbggenerated Mon Feb 06 15:02:12 KST 2017
     */
    int deleteByExample(CouponItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_item
     *
     * @mbggenerated Mon Feb 06 15:02:12 KST 2017
     */
    int deleteByPrimaryKey(CouponItemKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_item
     *
     * @mbggenerated Mon Feb 06 15:02:12 KST 2017
     */
    int insert(CouponItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_item
     *
     * @mbggenerated Mon Feb 06 15:02:12 KST 2017
     */
    int insertSelective(CouponItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_item
     *
     * @mbggenerated Mon Feb 06 15:02:12 KST 2017
     */
    List<CouponItem> selectByExample(CouponItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_item
     *
     * @mbggenerated Mon Feb 06 15:02:12 KST 2017
     */
    CouponItem selectByPrimaryKey(CouponItemKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_item
     *
     * @mbggenerated Mon Feb 06 15:02:12 KST 2017
     */
    int updateByExampleSelective(@Param("record") CouponItem record, @Param("example") CouponItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_item
     *
     * @mbggenerated Mon Feb 06 15:02:12 KST 2017
     */
    int updateByExample(@Param("record") CouponItem record, @Param("example") CouponItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_item
     *
     * @mbggenerated Mon Feb 06 15:02:12 KST 2017
     */
    int updateByPrimaryKeySelective(CouponItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_item
     *
     * @mbggenerated Mon Feb 06 15:02:12 KST 2017
     */
    int updateByPrimaryKey(CouponItem record);
}