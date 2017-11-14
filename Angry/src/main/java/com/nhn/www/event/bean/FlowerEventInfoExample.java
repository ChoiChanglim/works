package com.nhn.www.event.bean;

import java.util.ArrayList;
import java.util.List;

public class FlowerEventInfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
     */
    public FlowerEventInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
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
     * This method corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
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

        public Criteria andItemCodeIsNull() {
            addCriterion("item_code is null");
            return (Criteria) this;
        }

        public Criteria andItemCodeIsNotNull() {
            addCriterion("item_code is not null");
            return (Criteria) this;
        }

        public Criteria andItemCodeEqualTo(String value) {
            addCriterion("item_code =", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotEqualTo(String value) {
            addCriterion("item_code <>", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeGreaterThan(String value) {
            addCriterion("item_code >", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeGreaterThanOrEqualTo(String value) {
            addCriterion("item_code >=", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLessThan(String value) {
            addCriterion("item_code <", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLessThanOrEqualTo(String value) {
            addCriterion("item_code <=", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLike(String value) {
            addCriterion("item_code like", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotLike(String value) {
            addCriterion("item_code not like", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeIn(List<String> values) {
            addCriterion("item_code in", values, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotIn(List<String> values) {
            addCriterion("item_code not in", values, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeBetween(String value1, String value2) {
            addCriterion("item_code between", value1, value2, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotBetween(String value1, String value2) {
            addCriterion("item_code not between", value1, value2, "itemCode");
            return (Criteria) this;
        }

        public Criteria andProbIsNull() {
            addCriterion("prob is null");
            return (Criteria) this;
        }

        public Criteria andProbIsNotNull() {
            addCriterion("prob is not null");
            return (Criteria) this;
        }

        public Criteria andProbEqualTo(Double value) {
            addCriterion("prob =", value, "prob");
            return (Criteria) this;
        }

        public Criteria andProbNotEqualTo(Double value) {
            addCriterion("prob <>", value, "prob");
            return (Criteria) this;
        }

        public Criteria andProbGreaterThan(Double value) {
            addCriterion("prob >", value, "prob");
            return (Criteria) this;
        }

        public Criteria andProbGreaterThanOrEqualTo(Double value) {
            addCriterion("prob >=", value, "prob");
            return (Criteria) this;
        }

        public Criteria andProbLessThan(Double value) {
            addCriterion("prob <", value, "prob");
            return (Criteria) this;
        }

        public Criteria andProbLessThanOrEqualTo(Double value) {
            addCriterion("prob <=", value, "prob");
            return (Criteria) this;
        }

        public Criteria andProbIn(List<Double> values) {
            addCriterion("prob in", values, "prob");
            return (Criteria) this;
        }

        public Criteria andProbNotIn(List<Double> values) {
            addCriterion("prob not in", values, "prob");
            return (Criteria) this;
        }

        public Criteria andProbBetween(Double value1, Double value2) {
            addCriterion("prob between", value1, value2, "prob");
            return (Criteria) this;
        }

        public Criteria andProbNotBetween(Double value1, Double value2) {
            addCriterion("prob not between", value1, value2, "prob");
            return (Criteria) this;
        }

        public Criteria andMaxCountIsNull() {
            addCriterion("max_count is null");
            return (Criteria) this;
        }

        public Criteria andMaxCountIsNotNull() {
            addCriterion("max_count is not null");
            return (Criteria) this;
        }

        public Criteria andMaxCountEqualTo(Integer value) {
            addCriterion("max_count =", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountNotEqualTo(Integer value) {
            addCriterion("max_count <>", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountGreaterThan(Integer value) {
            addCriterion("max_count >", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_count >=", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountLessThan(Integer value) {
            addCriterion("max_count <", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountLessThanOrEqualTo(Integer value) {
            addCriterion("max_count <=", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountIn(List<Integer> values) {
            addCriterion("max_count in", values, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountNotIn(List<Integer> values) {
            addCriterion("max_count not in", values, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountBetween(Integer value1, Integer value2) {
            addCriterion("max_count between", value1, value2, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountNotBetween(Integer value1, Integer value2) {
            addCriterion("max_count not between", value1, value2, "maxCount");
            return (Criteria) this;
        }

        public Criteria andDescriptIsNull() {
            addCriterion("descript is null");
            return (Criteria) this;
        }

        public Criteria andDescriptIsNotNull() {
            addCriterion("descript is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptEqualTo(String value) {
            addCriterion("descript =", value, "descript");
            return (Criteria) this;
        }

        public Criteria andDescriptNotEqualTo(String value) {
            addCriterion("descript <>", value, "descript");
            return (Criteria) this;
        }

        public Criteria andDescriptGreaterThan(String value) {
            addCriterion("descript >", value, "descript");
            return (Criteria) this;
        }

        public Criteria andDescriptGreaterThanOrEqualTo(String value) {
            addCriterion("descript >=", value, "descript");
            return (Criteria) this;
        }

        public Criteria andDescriptLessThan(String value) {
            addCriterion("descript <", value, "descript");
            return (Criteria) this;
        }

        public Criteria andDescriptLessThanOrEqualTo(String value) {
            addCriterion("descript <=", value, "descript");
            return (Criteria) this;
        }

        public Criteria andDescriptLike(String value) {
            addCriterion("descript like", value, "descript");
            return (Criteria) this;
        }

        public Criteria andDescriptNotLike(String value) {
            addCriterion("descript not like", value, "descript");
            return (Criteria) this;
        }

        public Criteria andDescriptIn(List<String> values) {
            addCriterion("descript in", values, "descript");
            return (Criteria) this;
        }

        public Criteria andDescriptNotIn(List<String> values) {
            addCriterion("descript not in", values, "descript");
            return (Criteria) this;
        }

        public Criteria andDescriptBetween(String value1, String value2) {
            addCriterion("descript between", value1, value2, "descript");
            return (Criteria) this;
        }

        public Criteria andDescriptNotBetween(String value1, String value2) {
            addCriterion("descript not between", value1, value2, "descript");
            return (Criteria) this;
        }

        public Criteria andItemSerialIsNull() {
            addCriterion("item_serial is null");
            return (Criteria) this;
        }

        public Criteria andItemSerialIsNotNull() {
            addCriterion("item_serial is not null");
            return (Criteria) this;
        }

        public Criteria andItemSerialEqualTo(Integer value) {
            addCriterion("item_serial =", value, "itemSerial");
            return (Criteria) this;
        }

        public Criteria andItemSerialNotEqualTo(Integer value) {
            addCriterion("item_serial <>", value, "itemSerial");
            return (Criteria) this;
        }

        public Criteria andItemSerialGreaterThan(Integer value) {
            addCriterion("item_serial >", value, "itemSerial");
            return (Criteria) this;
        }

        public Criteria andItemSerialGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_serial >=", value, "itemSerial");
            return (Criteria) this;
        }

        public Criteria andItemSerialLessThan(Integer value) {
            addCriterion("item_serial <", value, "itemSerial");
            return (Criteria) this;
        }

        public Criteria andItemSerialLessThanOrEqualTo(Integer value) {
            addCriterion("item_serial <=", value, "itemSerial");
            return (Criteria) this;
        }

        public Criteria andItemSerialIn(List<Integer> values) {
            addCriterion("item_serial in", values, "itemSerial");
            return (Criteria) this;
        }

        public Criteria andItemSerialNotIn(List<Integer> values) {
            addCriterion("item_serial not in", values, "itemSerial");
            return (Criteria) this;
        }

        public Criteria andItemSerialBetween(Integer value1, Integer value2) {
            addCriterion("item_serial between", value1, value2, "itemSerial");
            return (Criteria) this;
        }

        public Criteria andItemSerialNotBetween(Integer value1, Integer value2) {
            addCriterion("item_serial not between", value1, value2, "itemSerial");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNull() {
            addCriterion("item_type is null");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNotNull() {
            addCriterion("item_type is not null");
            return (Criteria) this;
        }

        public Criteria andItemTypeEqualTo(Integer value) {
            addCriterion("item_type =", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotEqualTo(Integer value) {
            addCriterion("item_type <>", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThan(Integer value) {
            addCriterion("item_type >", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_type >=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThan(Integer value) {
            addCriterion("item_type <", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThanOrEqualTo(Integer value) {
            addCriterion("item_type <=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeIn(List<Integer> values) {
            addCriterion("item_type in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotIn(List<Integer> values) {
            addCriterion("item_type not in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeBetween(Integer value1, Integer value2) {
            addCriterion("item_type between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("item_type not between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemCountIsNull() {
            addCriterion("item_count is null");
            return (Criteria) this;
        }

        public Criteria andItemCountIsNotNull() {
            addCriterion("item_count is not null");
            return (Criteria) this;
        }

        public Criteria andItemCountEqualTo(Integer value) {
            addCriterion("item_count =", value, "itemCount");
            return (Criteria) this;
        }

        public Criteria andItemCountNotEqualTo(Integer value) {
            addCriterion("item_count <>", value, "itemCount");
            return (Criteria) this;
        }

        public Criteria andItemCountGreaterThan(Integer value) {
            addCriterion("item_count >", value, "itemCount");
            return (Criteria) this;
        }

        public Criteria andItemCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_count >=", value, "itemCount");
            return (Criteria) this;
        }

        public Criteria andItemCountLessThan(Integer value) {
            addCriterion("item_count <", value, "itemCount");
            return (Criteria) this;
        }

        public Criteria andItemCountLessThanOrEqualTo(Integer value) {
            addCriterion("item_count <=", value, "itemCount");
            return (Criteria) this;
        }

        public Criteria andItemCountIn(List<Integer> values) {
            addCriterion("item_count in", values, "itemCount");
            return (Criteria) this;
        }

        public Criteria andItemCountNotIn(List<Integer> values) {
            addCriterion("item_count not in", values, "itemCount");
            return (Criteria) this;
        }

        public Criteria andItemCountBetween(Integer value1, Integer value2) {
            addCriterion("item_count between", value1, value2, "itemCount");
            return (Criteria) this;
        }

        public Criteria andItemCountNotBetween(Integer value1, Integer value2) {
            addCriterion("item_count not between", value1, value2, "itemCount");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table flower_event_info
     *
     * @mbggenerated do_not_delete_during_merge Mon Apr 10 16:52:27 KST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table flower_event_info
     *
     * @mbggenerated Mon Apr 10 16:52:27 KST 2017
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