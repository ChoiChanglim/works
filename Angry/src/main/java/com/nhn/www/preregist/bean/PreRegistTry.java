package com.nhn.www.preregist.bean;

import java.util.Date;

public class PreRegistTry {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_regist_try.seq
     *
     * @mbggenerated Fri Mar 17 11:54:06 KST 2017
     */
    private Long seq;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_regist_try.ip
     *
     * @mbggenerated Fri Mar 17 11:54:06 KST 2017
     */
    private String ip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_regist_try.regdate
     *
     * @mbggenerated Fri Mar 17 11:54:06 KST 2017
     */
    private Date regdate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pre_regist_try.seq
     *
     * @return the value of pre_regist_try.seq
     *
     * @mbggenerated Fri Mar 17 11:54:06 KST 2017
     */
    public Long getSeq() {
        return seq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pre_regist_try.seq
     *
     * @param seq the value for pre_regist_try.seq
     *
     * @mbggenerated Fri Mar 17 11:54:06 KST 2017
     */
    public void setSeq(Long seq) {
        this.seq = seq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pre_regist_try.ip
     *
     * @return the value of pre_regist_try.ip
     *
     * @mbggenerated Fri Mar 17 11:54:06 KST 2017
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pre_regist_try.ip
     *
     * @param ip the value for pre_regist_try.ip
     *
     * @mbggenerated Fri Mar 17 11:54:06 KST 2017
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pre_regist_try.regdate
     *
     * @return the value of pre_regist_try.regdate
     *
     * @mbggenerated Fri Mar 17 11:54:06 KST 2017
     */
    public Date getRegdate() {
        return regdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pre_regist_try.regdate
     *
     * @param regdate the value for pre_regist_try.regdate
     *
     * @mbggenerated Fri Mar 17 11:54:06 KST 2017
     */
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
}