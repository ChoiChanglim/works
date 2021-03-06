package com.nhn.base.coupon.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponLogExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    public CouponLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdxIsNull() {
            addCriterion("idx is null");
            return (Criteria) this;
        }

        public Criteria andIdxIsNotNull() {
            addCriterion("idx is not null");
            return (Criteria) this;
        }

        public Criteria andIdxEqualTo(Long value) {
            addCriterion("idx =", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxNotEqualTo(Long value) {
            addCriterion("idx <>", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxGreaterThan(Long value) {
            addCriterion("idx >", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxGreaterThanOrEqualTo(Long value) {
            addCriterion("idx >=", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxLessThan(Long value) {
            addCriterion("idx <", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxLessThanOrEqualTo(Long value) {
            addCriterion("idx <=", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxIn(List<Long> values) {
            addCriterion("idx in", values, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxNotIn(List<Long> values) {
            addCriterion("idx not in", values, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxBetween(Long value1, Long value2) {
            addCriterion("idx between", value1, value2, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxNotBetween(Long value1, Long value2) {
            addCriterion("idx not between", value1, value2, "idx");
            return (Criteria) this;
        }

        public Criteria andSusstypeIsNull() {
            addCriterion("sussType is null");
            return (Criteria) this;
        }

        public Criteria andSusstypeIsNotNull() {
            addCriterion("sussType is not null");
            return (Criteria) this;
        }

        public Criteria andSusstypeEqualTo(Integer value) {
            addCriterion("sussType =", value, "susstype");
            return (Criteria) this;
        }

        public Criteria andSusstypeNotEqualTo(Integer value) {
            addCriterion("sussType <>", value, "susstype");
            return (Criteria) this;
        }

        public Criteria andSusstypeGreaterThan(Integer value) {
            addCriterion("sussType >", value, "susstype");
            return (Criteria) this;
        }

        public Criteria andSusstypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sussType >=", value, "susstype");
            return (Criteria) this;
        }

        public Criteria andSusstypeLessThan(Integer value) {
            addCriterion("sussType <", value, "susstype");
            return (Criteria) this;
        }

        public Criteria andSusstypeLessThanOrEqualTo(Integer value) {
            addCriterion("sussType <=", value, "susstype");
            return (Criteria) this;
        }

        public Criteria andSusstypeIn(List<Integer> values) {
            addCriterion("sussType in", values, "susstype");
            return (Criteria) this;
        }

        public Criteria andSusstypeNotIn(List<Integer> values) {
            addCriterion("sussType not in", values, "susstype");
            return (Criteria) this;
        }

        public Criteria andSusstypeBetween(Integer value1, Integer value2) {
            addCriterion("sussType between", value1, value2, "susstype");
            return (Criteria) this;
        }

        public Criteria andSusstypeNotBetween(Integer value1, Integer value2) {
            addCriterion("sussType not between", value1, value2, "susstype");
            return (Criteria) this;
        }

        public Criteria andSnoIsNull() {
            addCriterion("sno is null");
            return (Criteria) this;
        }

        public Criteria andSnoIsNotNull() {
            addCriterion("sno is not null");
            return (Criteria) this;
        }

        public Criteria andSnoEqualTo(Long value) {
            addCriterion("sno =", value, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoNotEqualTo(Long value) {
            addCriterion("sno <>", value, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoGreaterThan(Long value) {
            addCriterion("sno >", value, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoGreaterThanOrEqualTo(Long value) {
            addCriterion("sno >=", value, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoLessThan(Long value) {
            addCriterion("sno <", value, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoLessThanOrEqualTo(Long value) {
            addCriterion("sno <=", value, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoIn(List<Long> values) {
            addCriterion("sno in", values, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoNotIn(List<Long> values) {
            addCriterion("sno not in", values, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoBetween(Long value1, Long value2) {
            addCriterion("sno between", value1, value2, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoNotBetween(Long value1, Long value2) {
            addCriterion("sno not between", value1, value2, "sno");
            return (Criteria) this;
        }

        public Criteria andEventIdxIsNull() {
            addCriterion("event_idx is null");
            return (Criteria) this;
        }

        public Criteria andEventIdxIsNotNull() {
            addCriterion("event_idx is not null");
            return (Criteria) this;
        }

        public Criteria andEventIdxEqualTo(Integer value) {
            addCriterion("event_idx =", value, "eventIdx");
            return (Criteria) this;
        }

        public Criteria andEventIdxNotEqualTo(Integer value) {
            addCriterion("event_idx <>", value, "eventIdx");
            return (Criteria) this;
        }

        public Criteria andEventIdxGreaterThan(Integer value) {
            addCriterion("event_idx >", value, "eventIdx");
            return (Criteria) this;
        }

        public Criteria andEventIdxGreaterThanOrEqualTo(Integer value) {
            addCriterion("event_idx >=", value, "eventIdx");
            return (Criteria) this;
        }

        public Criteria andEventIdxLessThan(Integer value) {
            addCriterion("event_idx <", value, "eventIdx");
            return (Criteria) this;
        }

        public Criteria andEventIdxLessThanOrEqualTo(Integer value) {
            addCriterion("event_idx <=", value, "eventIdx");
            return (Criteria) this;
        }

        public Criteria andEventIdxIn(List<Integer> values) {
            addCriterion("event_idx in", values, "eventIdx");
            return (Criteria) this;
        }

        public Criteria andEventIdxNotIn(List<Integer> values) {
            addCriterion("event_idx not in", values, "eventIdx");
            return (Criteria) this;
        }

        public Criteria andEventIdxBetween(Integer value1, Integer value2) {
            addCriterion("event_idx between", value1, value2, "eventIdx");
            return (Criteria) this;
        }

        public Criteria andEventIdxNotBetween(Integer value1, Integer value2) {
            addCriterion("event_idx not between", value1, value2, "eventIdx");
            return (Criteria) this;
        }

        public Criteria andCnumIsNull() {
            addCriterion("cnum is null");
            return (Criteria) this;
        }

        public Criteria andCnumIsNotNull() {
            addCriterion("cnum is not null");
            return (Criteria) this;
        }

        public Criteria andCnumEqualTo(String value) {
            addCriterion("cnum =", value, "cnum");
            return (Criteria) this;
        }

        public Criteria andCnumNotEqualTo(String value) {
            addCriterion("cnum <>", value, "cnum");
            return (Criteria) this;
        }

        public Criteria andCnumGreaterThan(String value) {
            addCriterion("cnum >", value, "cnum");
            return (Criteria) this;
        }

        public Criteria andCnumGreaterThanOrEqualTo(String value) {
            addCriterion("cnum >=", value, "cnum");
            return (Criteria) this;
        }

        public Criteria andCnumLessThan(String value) {
            addCriterion("cnum <", value, "cnum");
            return (Criteria) this;
        }

        public Criteria andCnumLessThanOrEqualTo(String value) {
            addCriterion("cnum <=", value, "cnum");
            return (Criteria) this;
        }

        public Criteria andCnumLike(String value) {
            addCriterion("cnum like", value, "cnum");
            return (Criteria) this;
        }

        public Criteria andCnumNotLike(String value) {
            addCriterion("cnum not like", value, "cnum");
            return (Criteria) this;
        }

        public Criteria andCnumIn(List<String> values) {
            addCriterion("cnum in", values, "cnum");
            return (Criteria) this;
        }

        public Criteria andCnumNotIn(List<String> values) {
            addCriterion("cnum not in", values, "cnum");
            return (Criteria) this;
        }

        public Criteria andCnumBetween(String value1, String value2) {
            addCriterion("cnum between", value1, value2, "cnum");
            return (Criteria) this;
        }

        public Criteria andCnumNotBetween(String value1, String value2) {
            addCriterion("cnum not between", value1, value2, "cnum");
            return (Criteria) this;
        }

        public Criteria andItempartIsNull() {
            addCriterion("itemPart is null");
            return (Criteria) this;
        }

        public Criteria andItempartIsNotNull() {
            addCriterion("itemPart is not null");
            return (Criteria) this;
        }

        public Criteria andItempartEqualTo(String value) {
            addCriterion("itemPart =", value, "itempart");
            return (Criteria) this;
        }

        public Criteria andItempartNotEqualTo(String value) {
            addCriterion("itemPart <>", value, "itempart");
            return (Criteria) this;
        }

        public Criteria andItempartGreaterThan(String value) {
            addCriterion("itemPart >", value, "itempart");
            return (Criteria) this;
        }

        public Criteria andItempartGreaterThanOrEqualTo(String value) {
            addCriterion("itemPart >=", value, "itempart");
            return (Criteria) this;
        }

        public Criteria andItempartLessThan(String value) {
            addCriterion("itemPart <", value, "itempart");
            return (Criteria) this;
        }

        public Criteria andItempartLessThanOrEqualTo(String value) {
            addCriterion("itemPart <=", value, "itempart");
            return (Criteria) this;
        }

        public Criteria andItempartLike(String value) {
            addCriterion("itemPart like", value, "itempart");
            return (Criteria) this;
        }

        public Criteria andItempartNotLike(String value) {
            addCriterion("itemPart not like", value, "itempart");
            return (Criteria) this;
        }

        public Criteria andItempartIn(List<String> values) {
            addCriterion("itemPart in", values, "itempart");
            return (Criteria) this;
        }

        public Criteria andItempartNotIn(List<String> values) {
            addCriterion("itemPart not in", values, "itempart");
            return (Criteria) this;
        }

        public Criteria andItempartBetween(String value1, String value2) {
            addCriterion("itemPart between", value1, value2, "itempart");
            return (Criteria) this;
        }

        public Criteria andItempartNotBetween(String value1, String value2) {
            addCriterion("itemPart not between", value1, value2, "itempart");
            return (Criteria) this;
        }

        public Criteria andItemtypeIsNull() {
            addCriterion("itemType is null");
            return (Criteria) this;
        }

        public Criteria andItemtypeIsNotNull() {
            addCriterion("itemType is not null");
            return (Criteria) this;
        }

        public Criteria andItemtypeEqualTo(String value) {
            addCriterion("itemType =", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeNotEqualTo(String value) {
            addCriterion("itemType <>", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeGreaterThan(String value) {
            addCriterion("itemType >", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeGreaterThanOrEqualTo(String value) {
            addCriterion("itemType >=", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeLessThan(String value) {
            addCriterion("itemType <", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeLessThanOrEqualTo(String value) {
            addCriterion("itemType <=", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeLike(String value) {
            addCriterion("itemType like", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeNotLike(String value) {
            addCriterion("itemType not like", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeIn(List<String> values) {
            addCriterion("itemType in", values, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeNotIn(List<String> values) {
            addCriterion("itemType not in", values, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeBetween(String value1, String value2) {
            addCriterion("itemType between", value1, value2, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeNotBetween(String value1, String value2) {
            addCriterion("itemType not between", value1, value2, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemidIsNull() {
            addCriterion("itemID is null");
            return (Criteria) this;
        }

        public Criteria andItemidIsNotNull() {
            addCriterion("itemID is not null");
            return (Criteria) this;
        }

        public Criteria andItemidEqualTo(String value) {
            addCriterion("itemID =", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotEqualTo(String value) {
            addCriterion("itemID <>", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidGreaterThan(String value) {
            addCriterion("itemID >", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidGreaterThanOrEqualTo(String value) {
            addCriterion("itemID >=", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidLessThan(String value) {
            addCriterion("itemID <", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidLessThanOrEqualTo(String value) {
            addCriterion("itemID <=", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidLike(String value) {
            addCriterion("itemID like", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotLike(String value) {
            addCriterion("itemID not like", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidIn(List<String> values) {
            addCriterion("itemID in", values, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotIn(List<String> values) {
            addCriterion("itemID not in", values, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidBetween(String value1, String value2) {
            addCriterion("itemID between", value1, value2, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotBetween(String value1, String value2) {
            addCriterion("itemID not between", value1, value2, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemcountIsNull() {
            addCriterion("itemCount is null");
            return (Criteria) this;
        }

        public Criteria andItemcountIsNotNull() {
            addCriterion("itemCount is not null");
            return (Criteria) this;
        }

        public Criteria andItemcountEqualTo(String value) {
            addCriterion("itemCount =", value, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountNotEqualTo(String value) {
            addCriterion("itemCount <>", value, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountGreaterThan(String value) {
            addCriterion("itemCount >", value, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountGreaterThanOrEqualTo(String value) {
            addCriterion("itemCount >=", value, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountLessThan(String value) {
            addCriterion("itemCount <", value, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountLessThanOrEqualTo(String value) {
            addCriterion("itemCount <=", value, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountLike(String value) {
            addCriterion("itemCount like", value, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountNotLike(String value) {
            addCriterion("itemCount not like", value, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountIn(List<String> values) {
            addCriterion("itemCount in", values, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountNotIn(List<String> values) {
            addCriterion("itemCount not in", values, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountBetween(String value1, String value2) {
            addCriterion("itemCount between", value1, value2, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountNotBetween(String value1, String value2) {
            addCriterion("itemCount not between", value1, value2, "itemcount");
            return (Criteria) this;
        }

        public Criteria andErrcodeIsNull() {
            addCriterion("errCode is null");
            return (Criteria) this;
        }

        public Criteria andErrcodeIsNotNull() {
            addCriterion("errCode is not null");
            return (Criteria) this;
        }

        public Criteria andErrcodeEqualTo(String value) {
            addCriterion("errCode =", value, "errcode");
            return (Criteria) this;
        }

        public Criteria andErrcodeNotEqualTo(String value) {
            addCriterion("errCode <>", value, "errcode");
            return (Criteria) this;
        }

        public Criteria andErrcodeGreaterThan(String value) {
            addCriterion("errCode >", value, "errcode");
            return (Criteria) this;
        }

        public Criteria andErrcodeGreaterThanOrEqualTo(String value) {
            addCriterion("errCode >=", value, "errcode");
            return (Criteria) this;
        }

        public Criteria andErrcodeLessThan(String value) {
            addCriterion("errCode <", value, "errcode");
            return (Criteria) this;
        }

        public Criteria andErrcodeLessThanOrEqualTo(String value) {
            addCriterion("errCode <=", value, "errcode");
            return (Criteria) this;
        }

        public Criteria andErrcodeLike(String value) {
            addCriterion("errCode like", value, "errcode");
            return (Criteria) this;
        }

        public Criteria andErrcodeNotLike(String value) {
            addCriterion("errCode not like", value, "errcode");
            return (Criteria) this;
        }

        public Criteria andErrcodeIn(List<String> values) {
            addCriterion("errCode in", values, "errcode");
            return (Criteria) this;
        }

        public Criteria andErrcodeNotIn(List<String> values) {
            addCriterion("errCode not in", values, "errcode");
            return (Criteria) this;
        }

        public Criteria andErrcodeBetween(String value1, String value2) {
            addCriterion("errCode between", value1, value2, "errcode");
            return (Criteria) this;
        }

        public Criteria andErrcodeNotBetween(String value1, String value2) {
            addCriterion("errCode not between", value1, value2, "errcode");
            return (Criteria) this;
        }

        public Criteria andRegdateIsNull() {
            addCriterion("regDate is null");
            return (Criteria) this;
        }

        public Criteria andRegdateIsNotNull() {
            addCriterion("regDate is not null");
            return (Criteria) this;
        }

        public Criteria andRegdateEqualTo(Date value) {
            addCriterion("regDate =", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateNotEqualTo(Date value) {
            addCriterion("regDate <>", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateGreaterThan(Date value) {
            addCriterion("regDate >", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateGreaterThanOrEqualTo(Date value) {
            addCriterion("regDate >=", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateLessThan(Date value) {
            addCriterion("regDate <", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateLessThanOrEqualTo(Date value) {
            addCriterion("regDate <=", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateIn(List<Date> values) {
            addCriterion("regDate in", values, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateNotIn(List<Date> values) {
            addCriterion("regDate not in", values, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateBetween(Date value1, Date value2) {
            addCriterion("regDate between", value1, value2, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateNotBetween(Date value1, Date value2) {
            addCriterion("regDate not between", value1, value2, "regdate");
            return (Criteria) this;
        }

        public Criteria andTemp1IsNull() {
            addCriterion("temp1 is null");
            return (Criteria) this;
        }

        public Criteria andTemp1IsNotNull() {
            addCriterion("temp1 is not null");
            return (Criteria) this;
        }

        public Criteria andTemp1EqualTo(Integer value) {
            addCriterion("temp1 =", value, "temp1");
            return (Criteria) this;
        }

        public Criteria andTemp1NotEqualTo(Integer value) {
            addCriterion("temp1 <>", value, "temp1");
            return (Criteria) this;
        }

        public Criteria andTemp1GreaterThan(Integer value) {
            addCriterion("temp1 >", value, "temp1");
            return (Criteria) this;
        }

        public Criteria andTemp1GreaterThanOrEqualTo(Integer value) {
            addCriterion("temp1 >=", value, "temp1");
            return (Criteria) this;
        }

        public Criteria andTemp1LessThan(Integer value) {
            addCriterion("temp1 <", value, "temp1");
            return (Criteria) this;
        }

        public Criteria andTemp1LessThanOrEqualTo(Integer value) {
            addCriterion("temp1 <=", value, "temp1");
            return (Criteria) this;
        }

        public Criteria andTemp1In(List<Integer> values) {
            addCriterion("temp1 in", values, "temp1");
            return (Criteria) this;
        }

        public Criteria andTemp1NotIn(List<Integer> values) {
            addCriterion("temp1 not in", values, "temp1");
            return (Criteria) this;
        }

        public Criteria andTemp1Between(Integer value1, Integer value2) {
            addCriterion("temp1 between", value1, value2, "temp1");
            return (Criteria) this;
        }

        public Criteria andTemp1NotBetween(Integer value1, Integer value2) {
            addCriterion("temp1 not between", value1, value2, "temp1");
            return (Criteria) this;
        }

        public Criteria andTemp2IsNull() {
            addCriterion("temp2 is null");
            return (Criteria) this;
        }

        public Criteria andTemp2IsNotNull() {
            addCriterion("temp2 is not null");
            return (Criteria) this;
        }

        public Criteria andTemp2EqualTo(String value) {
            addCriterion("temp2 =", value, "temp2");
            return (Criteria) this;
        }

        public Criteria andTemp2NotEqualTo(String value) {
            addCriterion("temp2 <>", value, "temp2");
            return (Criteria) this;
        }

        public Criteria andTemp2GreaterThan(String value) {
            addCriterion("temp2 >", value, "temp2");
            return (Criteria) this;
        }

        public Criteria andTemp2GreaterThanOrEqualTo(String value) {
            addCriterion("temp2 >=", value, "temp2");
            return (Criteria) this;
        }

        public Criteria andTemp2LessThan(String value) {
            addCriterion("temp2 <", value, "temp2");
            return (Criteria) this;
        }

        public Criteria andTemp2LessThanOrEqualTo(String value) {
            addCriterion("temp2 <=", value, "temp2");
            return (Criteria) this;
        }

        public Criteria andTemp2Like(String value) {
            addCriterion("temp2 like", value, "temp2");
            return (Criteria) this;
        }

        public Criteria andTemp2NotLike(String value) {
            addCriterion("temp2 not like", value, "temp2");
            return (Criteria) this;
        }

        public Criteria andTemp2In(List<String> values) {
            addCriterion("temp2 in", values, "temp2");
            return (Criteria) this;
        }

        public Criteria andTemp2NotIn(List<String> values) {
            addCriterion("temp2 not in", values, "temp2");
            return (Criteria) this;
        }

        public Criteria andTemp2Between(String value1, String value2) {
            addCriterion("temp2 between", value1, value2, "temp2");
            return (Criteria) this;
        }

        public Criteria andTemp2NotBetween(String value1, String value2) {
            addCriterion("temp2 not between", value1, value2, "temp2");
            return (Criteria) this;
        }

        public Criteria andTemp3IsNull() {
            addCriterion("temp3 is null");
            return (Criteria) this;
        }

        public Criteria andTemp3IsNotNull() {
            addCriterion("temp3 is not null");
            return (Criteria) this;
        }

        public Criteria andTemp3EqualTo(Integer value) {
            addCriterion("temp3 =", value, "temp3");
            return (Criteria) this;
        }

        public Criteria andTemp3NotEqualTo(Integer value) {
            addCriterion("temp3 <>", value, "temp3");
            return (Criteria) this;
        }

        public Criteria andTemp3GreaterThan(Integer value) {
            addCriterion("temp3 >", value, "temp3");
            return (Criteria) this;
        }

        public Criteria andTemp3GreaterThanOrEqualTo(Integer value) {
            addCriterion("temp3 >=", value, "temp3");
            return (Criteria) this;
        }

        public Criteria andTemp3LessThan(Integer value) {
            addCriterion("temp3 <", value, "temp3");
            return (Criteria) this;
        }

        public Criteria andTemp3LessThanOrEqualTo(Integer value) {
            addCriterion("temp3 <=", value, "temp3");
            return (Criteria) this;
        }

        public Criteria andTemp3In(List<Integer> values) {
            addCriterion("temp3 in", values, "temp3");
            return (Criteria) this;
        }

        public Criteria andTemp3NotIn(List<Integer> values) {
            addCriterion("temp3 not in", values, "temp3");
            return (Criteria) this;
        }

        public Criteria andTemp3Between(Integer value1, Integer value2) {
            addCriterion("temp3 between", value1, value2, "temp3");
            return (Criteria) this;
        }

        public Criteria andTemp3NotBetween(Integer value1, Integer value2) {
            addCriterion("temp3 not between", value1, value2, "temp3");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated do_not_delete_during_merge Mon Feb 06 15:02:16 KST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table manage_event_coupon_log
     *
     * @mbggenerated Mon Feb 06 15:02:16 KST 2017
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}