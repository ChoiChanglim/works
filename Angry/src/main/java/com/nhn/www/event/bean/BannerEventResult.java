package com.nhn.www.event.bean;

import java.util.Date;

public class BannerEventResult extends BannerEventResultKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner_event_result.item_key
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    private Integer itemKey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner_event_result.local
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    private String local;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner_event_result.regdate
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    private Date regdate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner_event_result.item_key
     *
     * @return the value of banner_event_result.item_key
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    public Integer getItemKey() {
        return itemKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner_event_result.item_key
     *
     * @param itemKey the value for banner_event_result.item_key
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    public void setItemKey(Integer itemKey) {
        this.itemKey = itemKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner_event_result.local
     *
     * @return the value of banner_event_result.local
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    public String getLocal() {
        return local;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner_event_result.local
     *
     * @param local the value for banner_event_result.local
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner_event_result.regdate
     *
     * @return the value of banner_event_result.regdate
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    public Date getRegdate() {
        return regdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner_event_result.regdate
     *
     * @param regdate the value for banner_event_result.regdate
     *
     * @mbggenerated Wed Apr 26 17:50:13 KST 2017
     */
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
}