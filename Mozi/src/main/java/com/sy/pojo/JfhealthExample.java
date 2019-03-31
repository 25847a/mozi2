package com.sy.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JfhealthExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JfhealthExample() {
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

        public Criteria andHrvIsNull() {
            addCriterion("HRV is null");
            return (Criteria) this;
        }

        public Criteria andHrvIsNotNull() {
            addCriterion("HRV is not null");
            return (Criteria) this;
        }

        public Criteria andHrvEqualTo(Integer value) {
            addCriterion("HRV =", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvNotEqualTo(Integer value) {
            addCriterion("HRV <>", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvGreaterThan(Integer value) {
            addCriterion("HRV >", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvGreaterThanOrEqualTo(Integer value) {
            addCriterion("HRV >=", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvLessThan(Integer value) {
            addCriterion("HRV <", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvLessThanOrEqualTo(Integer value) {
            addCriterion("HRV <=", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvIn(List<Integer> values) {
            addCriterion("HRV in", values, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvNotIn(List<Integer> values) {
            addCriterion("HRV not in", values, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvBetween(Integer value1, Integer value2) {
            addCriterion("HRV between", value1, value2, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvNotBetween(Integer value1, Integer value2) {
            addCriterion("HRV not between", value1, value2, "hrv");
            return (Criteria) this;
        }

        public Criteria andSbpAveIsNull() {
            addCriterion("sbp_ave is null");
            return (Criteria) this;
        }

        public Criteria andSbpAveIsNotNull() {
            addCriterion("sbp_ave is not null");
            return (Criteria) this;
        }

        public Criteria andSbpAveEqualTo(Integer value) {
            addCriterion("sbp_ave =", value, "sbpAve");
            return (Criteria) this;
        }

        public Criteria andSbpAveNotEqualTo(Integer value) {
            addCriterion("sbp_ave <>", value, "sbpAve");
            return (Criteria) this;
        }

        public Criteria andSbpAveGreaterThan(Integer value) {
            addCriterion("sbp_ave >", value, "sbpAve");
            return (Criteria) this;
        }

        public Criteria andSbpAveGreaterThanOrEqualTo(Integer value) {
            addCriterion("sbp_ave >=", value, "sbpAve");
            return (Criteria) this;
        }

        public Criteria andSbpAveLessThan(Integer value) {
            addCriterion("sbp_ave <", value, "sbpAve");
            return (Criteria) this;
        }

        public Criteria andSbpAveLessThanOrEqualTo(Integer value) {
            addCriterion("sbp_ave <=", value, "sbpAve");
            return (Criteria) this;
        }

        public Criteria andSbpAveIn(List<Integer> values) {
            addCriterion("sbp_ave in", values, "sbpAve");
            return (Criteria) this;
        }

        public Criteria andSbpAveNotIn(List<Integer> values) {
            addCriterion("sbp_ave not in", values, "sbpAve");
            return (Criteria) this;
        }

        public Criteria andSbpAveBetween(Integer value1, Integer value2) {
            addCriterion("sbp_ave between", value1, value2, "sbpAve");
            return (Criteria) this;
        }

        public Criteria andSbpAveNotBetween(Integer value1, Integer value2) {
            addCriterion("sbp_ave not between", value1, value2, "sbpAve");
            return (Criteria) this;
        }

        public Criteria andDbpAveIsNull() {
            addCriterion("dbp_ave is null");
            return (Criteria) this;
        }

        public Criteria andDbpAveIsNotNull() {
            addCriterion("dbp_ave is not null");
            return (Criteria) this;
        }

        public Criteria andDbpAveEqualTo(Integer value) {
            addCriterion("dbp_ave =", value, "dbpAve");
            return (Criteria) this;
        }

        public Criteria andDbpAveNotEqualTo(Integer value) {
            addCriterion("dbp_ave <>", value, "dbpAve");
            return (Criteria) this;
        }

        public Criteria andDbpAveGreaterThan(Integer value) {
            addCriterion("dbp_ave >", value, "dbpAve");
            return (Criteria) this;
        }

        public Criteria andDbpAveGreaterThanOrEqualTo(Integer value) {
            addCriterion("dbp_ave >=", value, "dbpAve");
            return (Criteria) this;
        }

        public Criteria andDbpAveLessThan(Integer value) {
            addCriterion("dbp_ave <", value, "dbpAve");
            return (Criteria) this;
        }

        public Criteria andDbpAveLessThanOrEqualTo(Integer value) {
            addCriterion("dbp_ave <=", value, "dbpAve");
            return (Criteria) this;
        }

        public Criteria andDbpAveIn(List<Integer> values) {
            addCriterion("dbp_ave in", values, "dbpAve");
            return (Criteria) this;
        }

        public Criteria andDbpAveNotIn(List<Integer> values) {
            addCriterion("dbp_ave not in", values, "dbpAve");
            return (Criteria) this;
        }

        public Criteria andDbpAveBetween(Integer value1, Integer value2) {
            addCriterion("dbp_ave between", value1, value2, "dbpAve");
            return (Criteria) this;
        }

        public Criteria andDbpAveNotBetween(Integer value1, Integer value2) {
            addCriterion("dbp_ave not between", value1, value2, "dbpAve");
            return (Criteria) this;
        }

        public Criteria andHeartrateIsNull() {
            addCriterion("Heartrate is null");
            return (Criteria) this;
        }

        public Criteria andHeartrateIsNotNull() {
            addCriterion("Heartrate is not null");
            return (Criteria) this;
        }

        public Criteria andHeartrateEqualTo(Integer value) {
            addCriterion("Heartrate =", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateNotEqualTo(Integer value) {
            addCriterion("Heartrate <>", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateGreaterThan(Integer value) {
            addCriterion("Heartrate >", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateGreaterThanOrEqualTo(Integer value) {
            addCriterion("Heartrate >=", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateLessThan(Integer value) {
            addCriterion("Heartrate <", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateLessThanOrEqualTo(Integer value) {
            addCriterion("Heartrate <=", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateIn(List<Integer> values) {
            addCriterion("Heartrate in", values, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateNotIn(List<Integer> values) {
            addCriterion("Heartrate not in", values, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateBetween(Integer value1, Integer value2) {
            addCriterion("Heartrate between", value1, value2, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateNotBetween(Integer value1, Integer value2) {
            addCriterion("Heartrate not between", value1, value2, "heartrate");
            return (Criteria) this;
        }

        public Criteria andBloodoxygenIsNull() {
            addCriterion("Bloodoxygen is null");
            return (Criteria) this;
        }

        public Criteria andBloodoxygenIsNotNull() {
            addCriterion("Bloodoxygen is not null");
            return (Criteria) this;
        }

        public Criteria andBloodoxygenEqualTo(Integer value) {
            addCriterion("Bloodoxygen =", value, "bloodoxygen");
            return (Criteria) this;
        }

        public Criteria andBloodoxygenNotEqualTo(Integer value) {
            addCriterion("Bloodoxygen <>", value, "bloodoxygen");
            return (Criteria) this;
        }

        public Criteria andBloodoxygenGreaterThan(Integer value) {
            addCriterion("Bloodoxygen >", value, "bloodoxygen");
            return (Criteria) this;
        }

        public Criteria andBloodoxygenGreaterThanOrEqualTo(Integer value) {
            addCriterion("Bloodoxygen >=", value, "bloodoxygen");
            return (Criteria) this;
        }

        public Criteria andBloodoxygenLessThan(Integer value) {
            addCriterion("Bloodoxygen <", value, "bloodoxygen");
            return (Criteria) this;
        }

        public Criteria andBloodoxygenLessThanOrEqualTo(Integer value) {
            addCriterion("Bloodoxygen <=", value, "bloodoxygen");
            return (Criteria) this;
        }

        public Criteria andBloodoxygenIn(List<Integer> values) {
            addCriterion("Bloodoxygen in", values, "bloodoxygen");
            return (Criteria) this;
        }

        public Criteria andBloodoxygenNotIn(List<Integer> values) {
            addCriterion("Bloodoxygen not in", values, "bloodoxygen");
            return (Criteria) this;
        }

        public Criteria andBloodoxygenBetween(Integer value1, Integer value2) {
            addCriterion("Bloodoxygen between", value1, value2, "bloodoxygen");
            return (Criteria) this;
        }

        public Criteria andBloodoxygenNotBetween(Integer value1, Integer value2) {
            addCriterion("Bloodoxygen not between", value1, value2, "bloodoxygen");
            return (Criteria) this;
        }

        public Criteria andMicrocirculationIsNull() {
            addCriterion("microcirculation is null");
            return (Criteria) this;
        }

        public Criteria andMicrocirculationIsNotNull() {
            addCriterion("microcirculation is not null");
            return (Criteria) this;
        }

        public Criteria andMicrocirculationEqualTo(Integer value) {
            addCriterion("microcirculation =", value, "microcirculation");
            return (Criteria) this;
        }

        public Criteria andMicrocirculationNotEqualTo(Integer value) {
            addCriterion("microcirculation <>", value, "microcirculation");
            return (Criteria) this;
        }

        public Criteria andMicrocirculationGreaterThan(Integer value) {
            addCriterion("microcirculation >", value, "microcirculation");
            return (Criteria) this;
        }

        public Criteria andMicrocirculationGreaterThanOrEqualTo(Integer value) {
            addCriterion("microcirculation >=", value, "microcirculation");
            return (Criteria) this;
        }

        public Criteria andMicrocirculationLessThan(Integer value) {
            addCriterion("microcirculation <", value, "microcirculation");
            return (Criteria) this;
        }

        public Criteria andMicrocirculationLessThanOrEqualTo(Integer value) {
            addCriterion("microcirculation <=", value, "microcirculation");
            return (Criteria) this;
        }

        public Criteria andMicrocirculationIn(List<Integer> values) {
            addCriterion("microcirculation in", values, "microcirculation");
            return (Criteria) this;
        }

        public Criteria andMicrocirculationNotIn(List<Integer> values) {
            addCriterion("microcirculation not in", values, "microcirculation");
            return (Criteria) this;
        }

        public Criteria andMicrocirculationBetween(Integer value1, Integer value2) {
            addCriterion("microcirculation between", value1, value2, "microcirculation");
            return (Criteria) this;
        }

        public Criteria andMicrocirculationNotBetween(Integer value1, Integer value2) {
            addCriterion("microcirculation not between", value1, value2, "microcirculation");
            return (Criteria) this;
        }

        public Criteria andRespirationrateIsNull() {
            addCriterion("respirationrate is null");
            return (Criteria) this;
        }

        public Criteria andRespirationrateIsNotNull() {
            addCriterion("respirationrate is not null");
            return (Criteria) this;
        }

        public Criteria andRespirationrateEqualTo(Integer value) {
            addCriterion("respirationrate =", value, "respirationrate");
            return (Criteria) this;
        }

        public Criteria andRespirationrateNotEqualTo(Integer value) {
            addCriterion("respirationrate <>", value, "respirationrate");
            return (Criteria) this;
        }

        public Criteria andRespirationrateGreaterThan(Integer value) {
            addCriterion("respirationrate >", value, "respirationrate");
            return (Criteria) this;
        }

        public Criteria andRespirationrateGreaterThanOrEqualTo(Integer value) {
            addCriterion("respirationrate >=", value, "respirationrate");
            return (Criteria) this;
        }

        public Criteria andRespirationrateLessThan(Integer value) {
            addCriterion("respirationrate <", value, "respirationrate");
            return (Criteria) this;
        }

        public Criteria andRespirationrateLessThanOrEqualTo(Integer value) {
            addCriterion("respirationrate <=", value, "respirationrate");
            return (Criteria) this;
        }

        public Criteria andRespirationrateIn(List<Integer> values) {
            addCriterion("respirationrate in", values, "respirationrate");
            return (Criteria) this;
        }

        public Criteria andRespirationrateNotIn(List<Integer> values) {
            addCriterion("respirationrate not in", values, "respirationrate");
            return (Criteria) this;
        }

        public Criteria andRespirationrateBetween(Integer value1, Integer value2) {
            addCriterion("respirationrate between", value1, value2, "respirationrate");
            return (Criteria) this;
        }

        public Criteria andRespirationrateNotBetween(Integer value1, Integer value2) {
            addCriterion("respirationrate not between", value1, value2, "respirationrate");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
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

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
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