package com.sy.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EquipmentDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EquipmentDataExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andHeartrateIsNull() {
            addCriterion("heartrate is null");
            return (Criteria) this;
        }

        public Criteria andHeartrateIsNotNull() {
            addCriterion("heartrate is not null");
            return (Criteria) this;
        }

        public Criteria andHeartrateEqualTo(Integer value) {
            addCriterion("heartrate =", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateNotEqualTo(Integer value) {
            addCriterion("heartrate <>", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateGreaterThan(Integer value) {
            addCriterion("heartrate >", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateGreaterThanOrEqualTo(Integer value) {
            addCriterion("heartrate >=", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateLessThan(Integer value) {
            addCriterion("heartrate <", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateLessThanOrEqualTo(Integer value) {
            addCriterion("heartrate <=", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateIn(List<Integer> values) {
            addCriterion("heartrate in", values, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateNotIn(List<Integer> values) {
            addCriterion("heartrate not in", values, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateBetween(Integer value1, Integer value2) {
            addCriterion("heartrate between", value1, value2, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateNotBetween(Integer value1, Integer value2) {
            addCriterion("heartrate not between", value1, value2, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHighpressureIsNull() {
            addCriterion("highpressure is null");
            return (Criteria) this;
        }

        public Criteria andHighpressureIsNotNull() {
            addCriterion("highpressure is not null");
            return (Criteria) this;
        }

        public Criteria andHighpressureEqualTo(Integer value) {
            addCriterion("highpressure =", value, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureNotEqualTo(Integer value) {
            addCriterion("highpressure <>", value, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureGreaterThan(Integer value) {
            addCriterion("highpressure >", value, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureGreaterThanOrEqualTo(Integer value) {
            addCriterion("highpressure >=", value, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureLessThan(Integer value) {
            addCriterion("highpressure <", value, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureLessThanOrEqualTo(Integer value) {
            addCriterion("highpressure <=", value, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureIn(List<Integer> values) {
            addCriterion("highpressure in", values, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureNotIn(List<Integer> values) {
            addCriterion("highpressure not in", values, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureBetween(Integer value1, Integer value2) {
            addCriterion("highpressure between", value1, value2, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureNotBetween(Integer value1, Integer value2) {
            addCriterion("highpressure not between", value1, value2, "highpressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureIsNull() {
            addCriterion("bottompressure is null");
            return (Criteria) this;
        }

        public Criteria andBottompressureIsNotNull() {
            addCriterion("bottompressure is not null");
            return (Criteria) this;
        }

        public Criteria andBottompressureEqualTo(Integer value) {
            addCriterion("bottompressure =", value, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureNotEqualTo(Integer value) {
            addCriterion("bottompressure <>", value, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureGreaterThan(Integer value) {
            addCriterion("bottompressure >", value, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureGreaterThanOrEqualTo(Integer value) {
            addCriterion("bottompressure >=", value, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureLessThan(Integer value) {
            addCriterion("bottompressure <", value, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureLessThanOrEqualTo(Integer value) {
            addCriterion("bottompressure <=", value, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureIn(List<Integer> values) {
            addCriterion("bottompressure in", values, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureNotIn(List<Integer> values) {
            addCriterion("bottompressure not in", values, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureBetween(Integer value1, Integer value2) {
            addCriterion("bottompressure between", value1, value2, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureNotBetween(Integer value1, Integer value2) {
            addCriterion("bottompressure not between", value1, value2, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureIsNull() {
            addCriterion("bloodpressure is null");
            return (Criteria) this;
        }

        public Criteria andBloodpressureIsNotNull() {
            addCriterion("bloodpressure is not null");
            return (Criteria) this;
        }

        public Criteria andBloodpressureEqualTo(Integer value) {
            addCriterion("bloodpressure =", value, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureNotEqualTo(Integer value) {
            addCriterion("bloodpressure <>", value, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureGreaterThan(Integer value) {
            addCriterion("bloodpressure >", value, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureGreaterThanOrEqualTo(Integer value) {
            addCriterion("bloodpressure >=", value, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureLessThan(Integer value) {
            addCriterion("bloodpressure <", value, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureLessThanOrEqualTo(Integer value) {
            addCriterion("bloodpressure <=", value, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureIn(List<Integer> values) {
            addCriterion("bloodpressure in", values, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureNotIn(List<Integer> values) {
            addCriterion("bloodpressure not in", values, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureBetween(Integer value1, Integer value2) {
            addCriterion("bloodpressure between", value1, value2, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureNotBetween(Integer value1, Integer value2) {
            addCriterion("bloodpressure not between", value1, value2, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationIsNull() {
            addCriterion("mocrocirculation is null");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationIsNotNull() {
            addCriterion("mocrocirculation is not null");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationEqualTo(Integer value) {
            addCriterion("mocrocirculation =", value, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationNotEqualTo(Integer value) {
            addCriterion("mocrocirculation <>", value, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationGreaterThan(Integer value) {
            addCriterion("mocrocirculation >", value, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationGreaterThanOrEqualTo(Integer value) {
            addCriterion("mocrocirculation >=", value, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationLessThan(Integer value) {
            addCriterion("mocrocirculation <", value, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationLessThanOrEqualTo(Integer value) {
            addCriterion("mocrocirculation <=", value, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationIn(List<Integer> values) {
            addCriterion("mocrocirculation in", values, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationNotIn(List<Integer> values) {
            addCriterion("mocrocirculation not in", values, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationBetween(Integer value1, Integer value2) {
            addCriterion("mocrocirculation between", value1, value2, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationNotBetween(Integer value1, Integer value2) {
            addCriterion("mocrocirculation not between", value1, value2, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andBreatheIsNull() {
            addCriterion("breathe is null");
            return (Criteria) this;
        }

        public Criteria andBreatheIsNotNull() {
            addCriterion("breathe is not null");
            return (Criteria) this;
        }

        public Criteria andBreatheEqualTo(Integer value) {
            addCriterion("breathe =", value, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheNotEqualTo(Integer value) {
            addCriterion("breathe <>", value, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheGreaterThan(Integer value) {
            addCriterion("breathe >", value, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheGreaterThanOrEqualTo(Integer value) {
            addCriterion("breathe >=", value, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheLessThan(Integer value) {
            addCriterion("breathe <", value, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheLessThanOrEqualTo(Integer value) {
            addCriterion("breathe <=", value, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheIn(List<Integer> values) {
            addCriterion("breathe in", values, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheNotIn(List<Integer> values) {
            addCriterion("breathe not in", values, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheBetween(Integer value1, Integer value2) {
            addCriterion("breathe between", value1, value2, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheNotBetween(Integer value1, Integer value2) {
            addCriterion("breathe not between", value1, value2, "breathe");
            return (Criteria) this;
        }

        public Criteria andSleepingIsNull() {
            addCriterion("sleeping is null");
            return (Criteria) this;
        }

        public Criteria andSleepingIsNotNull() {
            addCriterion("sleeping is not null");
            return (Criteria) this;
        }

        public Criteria andSleepingEqualTo(Double value) {
            addCriterion("sleeping =", value, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingNotEqualTo(Double value) {
            addCriterion("sleeping <>", value, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingGreaterThan(Double value) {
            addCriterion("sleeping >", value, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingGreaterThanOrEqualTo(Double value) {
            addCriterion("sleeping >=", value, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingLessThan(Double value) {
            addCriterion("sleeping <", value, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingLessThanOrEqualTo(Double value) {
            addCriterion("sleeping <=", value, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingIn(List<Double> values) {
            addCriterion("sleeping in", values, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingNotIn(List<Double> values) {
            addCriterion("sleeping not in", values, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingBetween(Double value1, Double value2) {
            addCriterion("sleeping between", value1, value2, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingNotBetween(Double value1, Double value2) {
            addCriterion("sleeping not between", value1, value2, "sleeping");
            return (Criteria) this;
        }

        public Criteria andStepWhenIsNull() {
            addCriterion("step_when is null");
            return (Criteria) this;
        }

        public Criteria andStepWhenIsNotNull() {
            addCriterion("step_when is not null");
            return (Criteria) this;
        }

        public Criteria andStepWhenEqualTo(Integer value) {
            addCriterion("step_when =", value, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenNotEqualTo(Integer value) {
            addCriterion("step_when <>", value, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenGreaterThan(Integer value) {
            addCriterion("step_when >", value, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenGreaterThanOrEqualTo(Integer value) {
            addCriterion("step_when >=", value, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenLessThan(Integer value) {
            addCriterion("step_when <", value, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenLessThanOrEqualTo(Integer value) {
            addCriterion("step_when <=", value, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenIn(List<Integer> values) {
            addCriterion("step_when in", values, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenNotIn(List<Integer> values) {
            addCriterion("step_when not in", values, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenBetween(Integer value1, Integer value2) {
            addCriterion("step_when between", value1, value2, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenNotBetween(Integer value1, Integer value2) {
            addCriterion("step_when not between", value1, value2, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andCarrieroadIsNull() {
            addCriterion("carrieroad is null");
            return (Criteria) this;
        }

        public Criteria andCarrieroadIsNotNull() {
            addCriterion("carrieroad is not null");
            return (Criteria) this;
        }

        public Criteria andCarrieroadEqualTo(Integer value) {
            addCriterion("carrieroad =", value, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadNotEqualTo(Integer value) {
            addCriterion("carrieroad <>", value, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadGreaterThan(Integer value) {
            addCriterion("carrieroad >", value, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadGreaterThanOrEqualTo(Integer value) {
            addCriterion("carrieroad >=", value, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadLessThan(Integer value) {
            addCriterion("carrieroad <", value, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadLessThanOrEqualTo(Integer value) {
            addCriterion("carrieroad <=", value, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadIn(List<Integer> values) {
            addCriterion("carrieroad in", values, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadNotIn(List<Integer> values) {
            addCriterion("carrieroad not in", values, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadBetween(Integer value1, Integer value2) {
            addCriterion("carrieroad between", value1, value2, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadNotBetween(Integer value1, Integer value2) {
            addCriterion("carrieroad not between", value1, value2, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andSedentaryIsNull() {
            addCriterion("sedentary is null");
            return (Criteria) this;
        }

        public Criteria andSedentaryIsNotNull() {
            addCriterion("sedentary is not null");
            return (Criteria) this;
        }

        public Criteria andSedentaryEqualTo(String value) {
            addCriterion("sedentary =", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryNotEqualTo(String value) {
            addCriterion("sedentary <>", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryGreaterThan(String value) {
            addCriterion("sedentary >", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryGreaterThanOrEqualTo(String value) {
            addCriterion("sedentary >=", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryLessThan(String value) {
            addCriterion("sedentary <", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryLessThanOrEqualTo(String value) {
            addCriterion("sedentary <=", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryLike(String value) {
            addCriterion("sedentary like", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryNotLike(String value) {
            addCriterion("sedentary not like", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryIn(List<String> values) {
            addCriterion("sedentary in", values, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryNotIn(List<String> values) {
            addCriterion("sedentary not in", values, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryBetween(String value1, String value2) {
            addCriterion("sedentary between", value1, value2, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryNotBetween(String value1, String value2) {
            addCriterion("sedentary not between", value1, value2, "sedentary");
            return (Criteria) this;
        }

        public Criteria andMovementstateIsNull() {
            addCriterion("movementstate is null");
            return (Criteria) this;
        }

        public Criteria andMovementstateIsNotNull() {
            addCriterion("movementstate is not null");
            return (Criteria) this;
        }

        public Criteria andMovementstateEqualTo(Integer value) {
            addCriterion("movementstate =", value, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateNotEqualTo(Integer value) {
            addCriterion("movementstate <>", value, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateGreaterThan(Integer value) {
            addCriterion("movementstate >", value, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("movementstate >=", value, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateLessThan(Integer value) {
            addCriterion("movementstate <", value, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateLessThanOrEqualTo(Integer value) {
            addCriterion("movementstate <=", value, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateIn(List<Integer> values) {
            addCriterion("movementstate in", values, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateNotIn(List<Integer> values) {
            addCriterion("movementstate not in", values, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateBetween(Integer value1, Integer value2) {
            addCriterion("movementstate between", value1, value2, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateNotBetween(Integer value1, Integer value2) {
            addCriterion("movementstate not between", value1, value2, "movementstate");
            return (Criteria) this;
        }

        public Criteria andBodytempIsNull() {
            addCriterion("bodytemp is null");
            return (Criteria) this;
        }

        public Criteria andBodytempIsNotNull() {
            addCriterion("bodytemp is not null");
            return (Criteria) this;
        }

        public Criteria andBodytempEqualTo(Integer value) {
            addCriterion("bodytemp =", value, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempNotEqualTo(Integer value) {
            addCriterion("bodytemp <>", value, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempGreaterThan(Integer value) {
            addCriterion("bodytemp >", value, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempGreaterThanOrEqualTo(Integer value) {
            addCriterion("bodytemp >=", value, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempLessThan(Integer value) {
            addCriterion("bodytemp <", value, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempLessThanOrEqualTo(Integer value) {
            addCriterion("bodytemp <=", value, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempIn(List<Integer> values) {
            addCriterion("bodytemp in", values, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempNotIn(List<Integer> values) {
            addCriterion("bodytemp not in", values, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempBetween(Integer value1, Integer value2) {
            addCriterion("bodytemp between", value1, value2, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempNotBetween(Integer value1, Integer value2) {
            addCriterion("bodytemp not between", value1, value2, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andHumidityIsNull() {
            addCriterion("humidity is null");
            return (Criteria) this;
        }

        public Criteria andHumidityIsNotNull() {
            addCriterion("humidity is not null");
            return (Criteria) this;
        }

        public Criteria andHumidityEqualTo(Integer value) {
            addCriterion("humidity =", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityNotEqualTo(Integer value) {
            addCriterion("humidity <>", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityGreaterThan(Integer value) {
            addCriterion("humidity >", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityGreaterThanOrEqualTo(Integer value) {
            addCriterion("humidity >=", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityLessThan(Integer value) {
            addCriterion("humidity <", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityLessThanOrEqualTo(Integer value) {
            addCriterion("humidity <=", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityIn(List<Integer> values) {
            addCriterion("humidity in", values, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityNotIn(List<Integer> values) {
            addCriterion("humidity not in", values, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityBetween(Integer value1, Integer value2) {
            addCriterion("humidity between", value1, value2, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityNotBetween(Integer value1, Integer value2) {
            addCriterion("humidity not between", value1, value2, "humidity");
            return (Criteria) this;
        }

        public Criteria andCrashIsNull() {
            addCriterion("crash is null");
            return (Criteria) this;
        }

        public Criteria andCrashIsNotNull() {
            addCriterion("crash is not null");
            return (Criteria) this;
        }

        public Criteria andCrashEqualTo(Integer value) {
            addCriterion("crash =", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashNotEqualTo(Integer value) {
            addCriterion("crash <>", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashGreaterThan(Integer value) {
            addCriterion("crash >", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashGreaterThanOrEqualTo(Integer value) {
            addCriterion("crash >=", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashLessThan(Integer value) {
            addCriterion("crash <", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashLessThanOrEqualTo(Integer value) {
            addCriterion("crash <=", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashIn(List<Integer> values) {
            addCriterion("crash in", values, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashNotIn(List<Integer> values) {
            addCriterion("crash not in", values, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashBetween(Integer value1, Integer value2) {
            addCriterion("crash between", value1, value2, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashNotBetween(Integer value1, Integer value2) {
            addCriterion("crash not between", value1, value2, "crash");
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

        public Criteria andQxygenIsNull() {
            addCriterion("qxygen is null");
            return (Criteria) this;
        }

        public Criteria andQxygenIsNotNull() {
            addCriterion("qxygen is not null");
            return (Criteria) this;
        }

        public Criteria andQxygenEqualTo(Integer value) {
            addCriterion("qxygen =", value, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenNotEqualTo(Integer value) {
            addCriterion("qxygen <>", value, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenGreaterThan(Integer value) {
            addCriterion("qxygen >", value, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenGreaterThanOrEqualTo(Integer value) {
            addCriterion("qxygen >=", value, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenLessThan(Integer value) {
            addCriterion("qxygen <", value, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenLessThanOrEqualTo(Integer value) {
            addCriterion("qxygen <=", value, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenIn(List<Integer> values) {
            addCriterion("qxygen in", values, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenNotIn(List<Integer> values) {
            addCriterion("qxygen not in", values, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenBetween(Integer value1, Integer value2) {
            addCriterion("qxygen between", value1, value2, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenNotBetween(Integer value1, Integer value2) {
            addCriterion("qxygen not between", value1, value2, "qxygen");
            return (Criteria) this;
        }

        public Criteria andSleepingSIsNull() {
            addCriterion("sleeping_s is null");
            return (Criteria) this;
        }

        public Criteria andSleepingSIsNotNull() {
            addCriterion("sleeping_s is not null");
            return (Criteria) this;
        }

        public Criteria andSleepingSEqualTo(Integer value) {
            addCriterion("sleeping_s =", value, "sleepingS");
            return (Criteria) this;
        }

        public Criteria andSleepingSNotEqualTo(Integer value) {
            addCriterion("sleeping_s <>", value, "sleepingS");
            return (Criteria) this;
        }

        public Criteria andSleepingSGreaterThan(Integer value) {
            addCriterion("sleeping_s >", value, "sleepingS");
            return (Criteria) this;
        }

        public Criteria andSleepingSGreaterThanOrEqualTo(Integer value) {
            addCriterion("sleeping_s >=", value, "sleepingS");
            return (Criteria) this;
        }

        public Criteria andSleepingSLessThan(Integer value) {
            addCriterion("sleeping_s <", value, "sleepingS");
            return (Criteria) this;
        }

        public Criteria andSleepingSLessThanOrEqualTo(Integer value) {
            addCriterion("sleeping_s <=", value, "sleepingS");
            return (Criteria) this;
        }

        public Criteria andSleepingSIn(List<Integer> values) {
            addCriterion("sleeping_s in", values, "sleepingS");
            return (Criteria) this;
        }

        public Criteria andSleepingSNotIn(List<Integer> values) {
            addCriterion("sleeping_s not in", values, "sleepingS");
            return (Criteria) this;
        }

        public Criteria andSleepingSBetween(Integer value1, Integer value2) {
            addCriterion("sleeping_s between", value1, value2, "sleepingS");
            return (Criteria) this;
        }

        public Criteria andSleepingSNotBetween(Integer value1, Integer value2) {
            addCriterion("sleeping_s not between", value1, value2, "sleepingS");
            return (Criteria) this;
        }

        public Criteria andRunSIsNull() {
            addCriterion("run_s is null");
            return (Criteria) this;
        }

        public Criteria andRunSIsNotNull() {
            addCriterion("run_s is not null");
            return (Criteria) this;
        }

        public Criteria andRunSEqualTo(Integer value) {
            addCriterion("run_s =", value, "runS");
            return (Criteria) this;
        }

        public Criteria andRunSNotEqualTo(Integer value) {
            addCriterion("run_s <>", value, "runS");
            return (Criteria) this;
        }

        public Criteria andRunSGreaterThan(Integer value) {
            addCriterion("run_s >", value, "runS");
            return (Criteria) this;
        }

        public Criteria andRunSGreaterThanOrEqualTo(Integer value) {
            addCriterion("run_s >=", value, "runS");
            return (Criteria) this;
        }

        public Criteria andRunSLessThan(Integer value) {
            addCriterion("run_s <", value, "runS");
            return (Criteria) this;
        }

        public Criteria andRunSLessThanOrEqualTo(Integer value) {
            addCriterion("run_s <=", value, "runS");
            return (Criteria) this;
        }

        public Criteria andRunSIn(List<Integer> values) {
            addCriterion("run_s in", values, "runS");
            return (Criteria) this;
        }

        public Criteria andRunSNotIn(List<Integer> values) {
            addCriterion("run_s not in", values, "runS");
            return (Criteria) this;
        }

        public Criteria andRunSBetween(Integer value1, Integer value2) {
            addCriterion("run_s between", value1, value2, "runS");
            return (Criteria) this;
        }

        public Criteria andRunSNotBetween(Integer value1, Integer value2) {
            addCriterion("run_s not between", value1, value2, "runS");
            return (Criteria) this;
        }

        public Criteria andStepTimeIsNull() {
            addCriterion("step_time is null");
            return (Criteria) this;
        }

        public Criteria andStepTimeIsNotNull() {
            addCriterion("step_time is not null");
            return (Criteria) this;
        }

        public Criteria andStepTimeEqualTo(Integer value) {
            addCriterion("step_time =", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeNotEqualTo(Integer value) {
            addCriterion("step_time <>", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeGreaterThan(Integer value) {
            addCriterion("step_time >", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("step_time >=", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeLessThan(Integer value) {
            addCriterion("step_time <", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeLessThanOrEqualTo(Integer value) {
            addCriterion("step_time <=", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeIn(List<Integer> values) {
            addCriterion("step_time in", values, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeNotIn(List<Integer> values) {
            addCriterion("step_time not in", values, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeBetween(Integer value1, Integer value2) {
            addCriterion("step_time between", value1, value2, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("step_time not between", value1, value2, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepEachIsNull() {
            addCriterion("step_each is null");
            return (Criteria) this;
        }

        public Criteria andStepEachIsNotNull() {
            addCriterion("step_each is not null");
            return (Criteria) this;
        }

        public Criteria andStepEachEqualTo(Integer value) {
            addCriterion("step_each =", value, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachNotEqualTo(Integer value) {
            addCriterion("step_each <>", value, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachGreaterThan(Integer value) {
            addCriterion("step_each >", value, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachGreaterThanOrEqualTo(Integer value) {
            addCriterion("step_each >=", value, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachLessThan(Integer value) {
            addCriterion("step_each <", value, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachLessThanOrEqualTo(Integer value) {
            addCriterion("step_each <=", value, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachIn(List<Integer> values) {
            addCriterion("step_each in", values, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachNotIn(List<Integer> values) {
            addCriterion("step_each not in", values, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachBetween(Integer value1, Integer value2) {
            addCriterion("step_each between", value1, value2, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachNotBetween(Integer value1, Integer value2) {
            addCriterion("step_each not between", value1, value2, "stepEach");
            return (Criteria) this;
        }

        public Criteria andHrvIsNull() {
            addCriterion("hrv is null");
            return (Criteria) this;
        }

        public Criteria andHrvIsNotNull() {
            addCriterion("hrv is not null");
            return (Criteria) this;
        }

        public Criteria andHrvEqualTo(Integer value) {
            addCriterion("hrv =", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvNotEqualTo(Integer value) {
            addCriterion("hrv <>", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvGreaterThan(Integer value) {
            addCriterion("hrv >", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvGreaterThanOrEqualTo(Integer value) {
            addCriterion("hrv >=", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvLessThan(Integer value) {
            addCriterion("hrv <", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvLessThanOrEqualTo(Integer value) {
            addCriterion("hrv <=", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvIn(List<Integer> values) {
            addCriterion("hrv in", values, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvNotIn(List<Integer> values) {
            addCriterion("hrv not in", values, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvBetween(Integer value1, Integer value2) {
            addCriterion("hrv between", value1, value2, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvNotBetween(Integer value1, Integer value2) {
            addCriterion("hrv not between", value1, value2, "hrv");
            return (Criteria) this;
        }

        public Criteria andMoodIsNull() {
            addCriterion("mood is null");
            return (Criteria) this;
        }

        public Criteria andMoodIsNotNull() {
            addCriterion("mood is not null");
            return (Criteria) this;
        }

        public Criteria andMoodEqualTo(Integer value) {
            addCriterion("mood =", value, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodNotEqualTo(Integer value) {
            addCriterion("mood <>", value, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodGreaterThan(Integer value) {
            addCriterion("mood >", value, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodGreaterThanOrEqualTo(Integer value) {
            addCriterion("mood >=", value, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodLessThan(Integer value) {
            addCriterion("mood <", value, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodLessThanOrEqualTo(Integer value) {
            addCriterion("mood <=", value, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodIn(List<Integer> values) {
            addCriterion("mood in", values, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodNotIn(List<Integer> values) {
            addCriterion("mood not in", values, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodBetween(Integer value1, Integer value2) {
            addCriterion("mood between", value1, value2, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodNotBetween(Integer value1, Integer value2) {
            addCriterion("mood not between", value1, value2, "mood");
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