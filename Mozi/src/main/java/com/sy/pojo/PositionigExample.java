package com.sy.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PositionigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PositionigExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPositioningSIsNull() {
            addCriterion("positioning_s is null");
            return (Criteria) this;
        }

        public Criteria andPositioningSIsNotNull() {
            addCriterion("positioning_s is not null");
            return (Criteria) this;
        }

        public Criteria andPositioningSEqualTo(String value) {
            addCriterion("positioning_s =", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSNotEqualTo(String value) {
            addCriterion("positioning_s <>", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSGreaterThan(String value) {
            addCriterion("positioning_s >", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSGreaterThanOrEqualTo(String value) {
            addCriterion("positioning_s >=", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSLessThan(String value) {
            addCriterion("positioning_s <", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSLessThanOrEqualTo(String value) {
            addCriterion("positioning_s <=", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSLike(String value) {
            addCriterion("positioning_s like", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSNotLike(String value) {
            addCriterion("positioning_s not like", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSIn(List<String> values) {
            addCriterion("positioning_s in", values, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSNotIn(List<String> values) {
            addCriterion("positioning_s not in", values, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSBetween(String value1, String value2) {
            addCriterion("positioning_s between", value1, value2, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSNotBetween(String value1, String value2) {
            addCriterion("positioning_s not between", value1, value2, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningDataIsNull() {
            addCriterion("positioning_data is null");
            return (Criteria) this;
        }

        public Criteria andPositioningDataIsNotNull() {
            addCriterion("positioning_data is not null");
            return (Criteria) this;
        }

        public Criteria andPositioningDataEqualTo(String value) {
            addCriterion("positioning_data =", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataNotEqualTo(String value) {
            addCriterion("positioning_data <>", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataGreaterThan(String value) {
            addCriterion("positioning_data >", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataGreaterThanOrEqualTo(String value) {
            addCriterion("positioning_data >=", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataLessThan(String value) {
            addCriterion("positioning_data <", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataLessThanOrEqualTo(String value) {
            addCriterion("positioning_data <=", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataLike(String value) {
            addCriterion("positioning_data like", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataNotLike(String value) {
            addCriterion("positioning_data not like", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataIn(List<String> values) {
            addCriterion("positioning_data in", values, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataNotIn(List<String> values) {
            addCriterion("positioning_data not in", values, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataBetween(String value1, String value2) {
            addCriterion("positioning_data between", value1, value2, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataNotBetween(String value1, String value2) {
            addCriterion("positioning_data not between", value1, value2, "positioningData");
            return (Criteria) this;
        }

        public Criteria andCratetimeIsNull() {
            addCriterion("cratetime is null");
            return (Criteria) this;
        }

        public Criteria andCratetimeIsNotNull() {
            addCriterion("cratetime is not null");
            return (Criteria) this;
        }

        public Criteria andCratetimeEqualTo(Date value) {
            addCriterion("cratetime =", value, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeNotEqualTo(Date value) {
            addCriterion("cratetime <>", value, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeGreaterThan(Date value) {
            addCriterion("cratetime >", value, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cratetime >=", value, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeLessThan(Date value) {
            addCriterion("cratetime <", value, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeLessThanOrEqualTo(Date value) {
            addCriterion("cratetime <=", value, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeIn(List<Date> values) {
            addCriterion("cratetime in", values, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeNotIn(List<Date> values) {
            addCriterion("cratetime not in", values, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeBetween(Date value1, Date value2) {
            addCriterion("cratetime between", value1, value2, "cratetime");
            return (Criteria) this;
        }

        public Criteria andCratetimeNotBetween(Date value1, Date value2) {
            addCriterion("cratetime not between", value1, value2, "cratetime");
            return (Criteria) this;
        }

        public Criteria andImeiIsNull() {
            addCriterion("imei is null");
            return (Criteria) this;
        }

        public Criteria andImeiIsNotNull() {
            addCriterion("imei is not null");
            return (Criteria) this;
        }

        public Criteria andImeiEqualTo(String value) {
            addCriterion("imei =", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotEqualTo(String value) {
            addCriterion("imei <>", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiGreaterThan(String value) {
            addCriterion("imei >", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiGreaterThanOrEqualTo(String value) {
            addCriterion("imei >=", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLessThan(String value) {
            addCriterion("imei <", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLessThanOrEqualTo(String value) {
            addCriterion("imei <=", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLike(String value) {
            addCriterion("imei like", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotLike(String value) {
            addCriterion("imei not like", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiIn(List<String> values) {
            addCriterion("imei in", values, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotIn(List<String> values) {
            addCriterion("imei not in", values, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiBetween(String value1, String value2) {
            addCriterion("imei between", value1, value2, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotBetween(String value1, String value2) {
            addCriterion("imei not between", value1, value2, "imei");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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