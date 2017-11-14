package com.nhn.base.coupon.mapper;

import com.nhn.base.coupon.bean.Coupon;
import com.nhn.base.coupon.bean.CouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event
     *
     * @mbggenerated Mon Feb 06 15:02:19 KST 2017
     */
    int countByExample(CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event
     *
     * @mbggenerated Mon Feb 06 15:02:19 KST 2017
     */
    int deleteByExample(CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event
     *
     * @mbggenerated Mon Feb 06 15:02:19 KST 2017
     */
    int deleteByPrimaryKey(Integer idx);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event
     *
     * @mbggenerated Mon Feb 06 15:02:19 KST 2017
     */
    int insert(Coupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event
     *
     * @mbggenerated Mon Feb 06 15:02:19 KST 2017
     */
    int insertSelective(Coupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event
     *
     * @mbggenerated Mon Feb 06 15:02:19 KST 2017
     */
    List<Coupon> selectByExample(CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event
     *
     * @mbggenerated Mon Feb 06 15:02:19 KST 2017
     */
    Coupon selectByPrimaryKey(Integer idx);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event
     *
     * @mbggenerated Mon Feb 06 15:02:19 KST 2017
     */
    int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event
     *
     * @mbggenerated Mon Feb 06 15:02:19 KST 2017
     */
    int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event
     *
     * @mbggenerated Mon Feb 06 15:02:19 KST 2017
     */
    int updateByPrimaryKeySelective(Coupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event
     *
     * @mbggenerated Mon Feb 06 15:02:19 KST 2017
     */
    int updateByPrimaryKey(Coupon record);
}