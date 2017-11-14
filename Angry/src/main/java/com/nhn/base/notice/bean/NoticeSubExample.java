package com.nhn.base.notice.bean;

import java.util.ArrayList;
import java.util.List;

public class NoticeSubExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
     */
    public NoticeSubExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
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
     * This method corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
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

        public Criteria andNoticeIdxIsNull() {
            addCriterion("notice_idx is null");
            return (Criteria) this;
        }

        public Criteria andNoticeIdxIsNotNull() {
            addCriterion("notice_idx is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeIdxEqualTo(Integer value) {
            addCriterion("notice_idx =", value, "noticeIdx");
            return (Criteria) this;
        }

        public Criteria andNoticeIdxNotEqualTo(Integer value) {
            addCriterion("notice_idx <>", value, "noticeIdx");
            return (Criteria) this;
        }

        public Criteria andNoticeIdxGreaterThan(Integer value) {
            addCriterion("notice_idx >", value, "noticeIdx");
            return (Criteria) this;
        }

        public Criteria andNoticeIdxGreaterThanOrEqualTo(Integer value) {
            addCriterion("notice_idx >=", value, "noticeIdx");
            return (Criteria) this;
        }

        public Criteria andNoticeIdxLessThan(Integer value) {
            addCriterion("notice_idx <", value, "noticeIdx");
            return (Criteria) this;
        }

        public Criteria andNoticeIdxLessThanOrEqualTo(Integer value) {
            addCriterion("notice_idx <=", value, "noticeIdx");
            return (Criteria) this;
        }

        public Criteria andNoticeIdxIn(List<Integer> values) {
            addCriterion("notice_idx in", values, "noticeIdx");
            return (Criteria) this;
        }

        public Criteria andNoticeIdxNotIn(List<Integer> values) {
            addCriterion("notice_idx not in", values, "noticeIdx");
            return (Criteria) this;
        }

        public Criteria andNoticeIdxBetween(Integer value1, Integer value2) {
            addCriterion("notice_idx between", value1, value2, "noticeIdx");
            return (Criteria) this;
        }

        public Criteria andNoticeIdxNotBetween(Integer value1, Integer value2) {
            addCriterion("notice_idx not between", value1, value2, "noticeIdx");
            return (Criteria) this;
        }

        public Criteria andSubIdxIsNull() {
            addCriterion("sub_idx is null");
            return (Criteria) this;
        }

        public Criteria andSubIdxIsNotNull() {
            addCriterion("sub_idx is not null");
            return (Criteria) this;
        }

        public Criteria andSubIdxEqualTo(Integer value) {
            addCriterion("sub_idx =", value, "subIdx");
            return (Criteria) this;
        }

        public Criteria andSubIdxNotEqualTo(Integer value) {
            addCriterion("sub_idx <>", value, "subIdx");
            return (Criteria) this;
        }

        public Criteria andSubIdxGreaterThan(Integer value) {
            addCriterion("sub_idx >", value, "subIdx");
            return (Criteria) this;
        }

        public Criteria andSubIdxGreaterThanOrEqualTo(Integer value) {
            addCriterion("sub_idx >=", value, "subIdx");
            return (Criteria) this;
        }

        public Criteria andSubIdxLessThan(Integer value) {
            addCriterion("sub_idx <", value, "subIdx");
            return (Criteria) this;
        }

        public Criteria andSubIdxLessThanOrEqualTo(Integer value) {
            addCriterion("sub_idx <=", value, "subIdx");
            return (Criteria) this;
        }

        public Criteria andSubIdxIn(List<Integer> values) {
            addCriterion("sub_idx in", values, "subIdx");
            return (Criteria) this;
        }

        public Criteria andSubIdxNotIn(List<Integer> values) {
            addCriterion("sub_idx not in", values, "subIdx");
            return (Criteria) this;
        }

        public Criteria andSubIdxBetween(Integer value1, Integer value2) {
            addCriterion("sub_idx between", value1, value2, "subIdx");
            return (Criteria) this;
        }

        public Criteria andSubIdxNotBetween(Integer value1, Integer value2) {
            addCriterion("sub_idx not between", value1, value2, "subIdx");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andImgaddrIsNull() {
            addCriterion("imgAddr is null");
            return (Criteria) this;
        }

        public Criteria andImgaddrIsNotNull() {
            addCriterion("imgAddr is not null");
            return (Criteria) this;
        }

        public Criteria andImgaddrEqualTo(String value) {
            addCriterion("imgAddr =", value, "imgaddr");
            return (Criteria) this;
        }

        public Criteria andImgaddrNotEqualTo(String value) {
            addCriterion("imgAddr <>", value, "imgaddr");
            return (Criteria) this;
        }

        public Criteria andImgaddrGreaterThan(String value) {
            addCriterion("imgAddr >", value, "imgaddr");
            return (Criteria) this;
        }

        public Criteria andImgaddrGreaterThanOrEqualTo(String value) {
            addCriterion("imgAddr >=", value, "imgaddr");
            return (Criteria) this;
        }

        public Criteria andImgaddrLessThan(String value) {
            addCriterion("imgAddr <", value, "imgaddr");
            return (Criteria) this;
        }

        public Criteria andImgaddrLessThanOrEqualTo(String value) {
            addCriterion("imgAddr <=", value, "imgaddr");
            return (Criteria) this;
        }

        public Criteria andImgaddrLike(String value) {
            addCriterion("imgAddr like", value, "imgaddr");
            return (Criteria) this;
        }

        public Criteria andImgaddrNotLike(String value) {
            addCriterion("imgAddr not like", value, "imgaddr");
            return (Criteria) this;
        }

        public Criteria andImgaddrIn(List<String> values) {
            addCriterion("imgAddr in", values, "imgaddr");
            return (Criteria) this;
        }

        public Criteria andImgaddrNotIn(List<String> values) {
            addCriterion("imgAddr not in", values, "imgaddr");
            return (Criteria) this;
        }

        public Criteria andImgaddrBetween(String value1, String value2) {
            addCriterion("imgAddr between", value1, value2, "imgaddr");
            return (Criteria) this;
        }

        public Criteria andImgaddrNotBetween(String value1, String value2) {
            addCriterion("imgAddr not between", value1, value2, "imgaddr");
            return (Criteria) this;
        }

        public Criteria andLinkaddrIsNull() {
            addCriterion("linkAddr is null");
            return (Criteria) this;
        }

        public Criteria andLinkaddrIsNotNull() {
            addCriterion("linkAddr is not null");
            return (Criteria) this;
        }

        public Criteria andLinkaddrEqualTo(String value) {
            addCriterion("linkAddr =", value, "linkaddr");
            return (Criteria) this;
        }

        public Criteria andLinkaddrNotEqualTo(String value) {
            addCriterion("linkAddr <>", value, "linkaddr");
            return (Criteria) this;
        }

        public Criteria andLinkaddrGreaterThan(String value) {
            addCriterion("linkAddr >", value, "linkaddr");
            return (Criteria) this;
        }

        public Criteria andLinkaddrGreaterThanOrEqualTo(String value) {
            addCriterion("linkAddr >=", value, "linkaddr");
            return (Criteria) this;
        }

        public Criteria andLinkaddrLessThan(String value) {
            addCriterion("linkAddr <", value, "linkaddr");
            return (Criteria) this;
        }

        public Criteria andLinkaddrLessThanOrEqualTo(String value) {
            addCriterion("linkAddr <=", value, "linkaddr");
            return (Criteria) this;
        }

        public Criteria andLinkaddrLike(String value) {
            addCriterion("linkAddr like", value, "linkaddr");
            return (Criteria) this;
        }

        public Criteria andLinkaddrNotLike(String value) {
            addCriterion("linkAddr not like", value, "linkaddr");
            return (Criteria) this;
        }

        public Criteria andLinkaddrIn(List<String> values) {
            addCriterion("linkAddr in", values, "linkaddr");
            return (Criteria) this;
        }

        public Criteria andLinkaddrNotIn(List<String> values) {
            addCriterion("linkAddr not in", values, "linkaddr");
            return (Criteria) this;
        }

        public Criteria andLinkaddrBetween(String value1, String value2) {
            addCriterion("linkAddr between", value1, value2, "linkaddr");
            return (Criteria) this;
        }

        public Criteria andLinkaddrNotBetween(String value1, String value2) {
            addCriterion("linkAddr not between", value1, value2, "linkaddr");
            return (Criteria) this;
        }

        public Criteria andNewpopupIsNull() {
            addCriterion("newPopup is null");
            return (Criteria) this;
        }

        public Criteria andNewpopupIsNotNull() {
            addCriterion("newPopup is not null");
            return (Criteria) this;
        }

        public Criteria andNewpopupEqualTo(Integer value) {
            addCriterion("newPopup =", value, "newpopup");
            return (Criteria) this;
        }

        public Criteria andNewpopupNotEqualTo(Integer value) {
            addCriterion("newPopup <>", value, "newpopup");
            return (Criteria) this;
        }

        public Criteria andNewpopupGreaterThan(Integer value) {
            addCriterion("newPopup >", value, "newpopup");
            return (Criteria) this;
        }

        public Criteria andNewpopupGreaterThanOrEqualTo(Integer value) {
            addCriterion("newPopup >=", value, "newpopup");
            return (Criteria) this;
        }

        public Criteria andNewpopupLessThan(Integer value) {
            addCriterion("newPopup <", value, "newpopup");
            return (Criteria) this;
        }

        public Criteria andNewpopupLessThanOrEqualTo(Integer value) {
            addCriterion("newPopup <=", value, "newpopup");
            return (Criteria) this;
        }

        public Criteria andNewpopupIn(List<Integer> values) {
            addCriterion("newPopup in", values, "newpopup");
            return (Criteria) this;
        }

        public Criteria andNewpopupNotIn(List<Integer> values) {
            addCriterion("newPopup not in", values, "newpopup");
            return (Criteria) this;
        }

        public Criteria andNewpopupBetween(Integer value1, Integer value2) {
            addCriterion("newPopup between", value1, value2, "newpopup");
            return (Criteria) this;
        }

        public Criteria andNewpopupNotBetween(Integer value1, Integer value2) {
            addCriterion("newPopup not between", value1, value2, "newpopup");
            return (Criteria) this;
        }

        public Criteria andDelIsNull() {
            addCriterion("del is null");
            return (Criteria) this;
        }

        public Criteria andDelIsNotNull() {
            addCriterion("del is not null");
            return (Criteria) this;
        }

        public Criteria andDelEqualTo(Integer value) {
            addCriterion("del =", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotEqualTo(Integer value) {
            addCriterion("del <>", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelGreaterThan(Integer value) {
            addCriterion("del >", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("del >=", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelLessThan(Integer value) {
            addCriterion("del <", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelLessThanOrEqualTo(Integer value) {
            addCriterion("del <=", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelIn(List<Integer> values) {
            addCriterion("del in", values, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotIn(List<Integer> values) {
            addCriterion("del not in", values, "del");
            return (Criteria) this;
        }

        public Criteria andDelBetween(Integer value1, Integer value2) {
            addCriterion("del between", value1, value2, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotBetween(Integer value1, Integer value2) {
            addCriterion("del not between", value1, value2, "del");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated do_not_delete_during_merge Mon Feb 06 15:02:22 KST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table manage_event_notice_sub
     *
     * @mbggenerated Mon Feb 06 15:02:22 KST 2017
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