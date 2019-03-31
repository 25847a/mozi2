package com.sy.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EquipmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EquipmentExample() {
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

        public Criteria andLordpowerIsNull() {
            addCriterion("lordpower is null");
            return (Criteria) this;
        }

        public Criteria andLordpowerIsNotNull() {
            addCriterion("lordpower is not null");
            return (Criteria) this;
        }

        public Criteria andLordpowerEqualTo(Integer value) {
            addCriterion("lordpower =", value, "lordpower");
            return (Criteria) this;
        }

        public Criteria andLordpowerNotEqualTo(Integer value) {
            addCriterion("lordpower <>", value, "lordpower");
            return (Criteria) this;
        }

        public Criteria andLordpowerGreaterThan(Integer value) {
            addCriterion("lordpower >", value, "lordpower");
            return (Criteria) this;
        }

        public Criteria andLordpowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("lordpower >=", value, "lordpower");
            return (Criteria) this;
        }

        public Criteria andLordpowerLessThan(Integer value) {
            addCriterion("lordpower <", value, "lordpower");
            return (Criteria) this;
        }

        public Criteria andLordpowerLessThanOrEqualTo(Integer value) {
            addCriterion("lordpower <=", value, "lordpower");
            return (Criteria) this;
        }

        public Criteria andLordpowerIn(List<Integer> values) {
            addCriterion("lordpower in", values, "lordpower");
            return (Criteria) this;
        }

        public Criteria andLordpowerNotIn(List<Integer> values) {
            addCriterion("lordpower not in", values, "lordpower");
            return (Criteria) this;
        }

        public Criteria andLordpowerBetween(Integer value1, Integer value2) {
            addCriterion("lordpower between", value1, value2, "lordpower");
            return (Criteria) this;
        }

        public Criteria andLordpowerNotBetween(Integer value1, Integer value2) {
            addCriterion("lordpower not between", value1, value2, "lordpower");
            return (Criteria) this;
        }

        public Criteria andSignalxhaoIsNull() {
            addCriterion("signalxhao is null");
            return (Criteria) this;
        }

        public Criteria andSignalxhaoIsNotNull() {
            addCriterion("signalxhao is not null");
            return (Criteria) this;
        }

        public Criteria andSignalxhaoEqualTo(String value) {
            addCriterion("signalxhao =", value, "signalxhao");
            return (Criteria) this;
        }

        public Criteria andSignalxhaoNotEqualTo(String value) {
            addCriterion("signalxhao <>", value, "signalxhao");
            return (Criteria) this;
        }

        public Criteria andSignalxhaoGreaterThan(String value) {
            addCriterion("signalxhao >", value, "signalxhao");
            return (Criteria) this;
        }

        public Criteria andSignalxhaoGreaterThanOrEqualTo(String value) {
            addCriterion("signalxhao >=", value, "signalxhao");
            return (Criteria) this;
        }

        public Criteria andSignalxhaoLessThan(String value) {
            addCriterion("signalxhao <", value, "signalxhao");
            return (Criteria) this;
        }

        public Criteria andSignalxhaoLessThanOrEqualTo(String value) {
            addCriterion("signalxhao <=", value, "signalxhao");
            return (Criteria) this;
        }

        public Criteria andSignalxhaoLike(String value) {
            addCriterion("signalxhao like", value, "signalxhao");
            return (Criteria) this;
        }

        public Criteria andSignalxhaoNotLike(String value) {
            addCriterion("signalxhao not like", value, "signalxhao");
            return (Criteria) this;
        }

        public Criteria andSignalxhaoIn(List<String> values) {
            addCriterion("signalxhao in", values, "signalxhao");
            return (Criteria) this;
        }

        public Criteria andSignalxhaoNotIn(List<String> values) {
            addCriterion("signalxhao not in", values, "signalxhao");
            return (Criteria) this;
        }

        public Criteria andSignalxhaoBetween(String value1, String value2) {
            addCriterion("signalxhao between", value1, value2, "signalxhao");
            return (Criteria) this;
        }

        public Criteria andSignalxhaoNotBetween(String value1, String value2) {
            addCriterion("signalxhao not between", value1, value2, "signalxhao");
            return (Criteria) this;
        }

        public Criteria andBluetoothTypeIsNull() {
            addCriterion("bluetooth_type is null");
            return (Criteria) this;
        }

        public Criteria andBluetoothTypeIsNotNull() {
            addCriterion("bluetooth_type is not null");
            return (Criteria) this;
        }

        public Criteria andBluetoothTypeEqualTo(String value) {
            addCriterion("bluetooth_type =", value, "bluetoothType");
            return (Criteria) this;
        }

        public Criteria andBluetoothTypeNotEqualTo(String value) {
            addCriterion("bluetooth_type <>", value, "bluetoothType");
            return (Criteria) this;
        }

        public Criteria andBluetoothTypeGreaterThan(String value) {
            addCriterion("bluetooth_type >", value, "bluetoothType");
            return (Criteria) this;
        }

        public Criteria andBluetoothTypeGreaterThanOrEqualTo(String value) {
            addCriterion("bluetooth_type >=", value, "bluetoothType");
            return (Criteria) this;
        }

        public Criteria andBluetoothTypeLessThan(String value) {
            addCriterion("bluetooth_type <", value, "bluetoothType");
            return (Criteria) this;
        }

        public Criteria andBluetoothTypeLessThanOrEqualTo(String value) {
            addCriterion("bluetooth_type <=", value, "bluetoothType");
            return (Criteria) this;
        }

        public Criteria andBluetoothTypeLike(String value) {
            addCriterion("bluetooth_type like", value, "bluetoothType");
            return (Criteria) this;
        }

        public Criteria andBluetoothTypeNotLike(String value) {
            addCriterion("bluetooth_type not like", value, "bluetoothType");
            return (Criteria) this;
        }

        public Criteria andBluetoothTypeIn(List<String> values) {
            addCriterion("bluetooth_type in", values, "bluetoothType");
            return (Criteria) this;
        }

        public Criteria andBluetoothTypeNotIn(List<String> values) {
            addCriterion("bluetooth_type not in", values, "bluetoothType");
            return (Criteria) this;
        }

        public Criteria andBluetoothTypeBetween(String value1, String value2) {
            addCriterion("bluetooth_type between", value1, value2, "bluetoothType");
            return (Criteria) this;
        }

        public Criteria andBluetoothTypeNotBetween(String value1, String value2) {
            addCriterion("bluetooth_type not between", value1, value2, "bluetoothType");
            return (Criteria) this;
        }

        public Criteria andEqStatusIsNull() {
            addCriterion("eq_status is null");
            return (Criteria) this;
        }

        public Criteria andEqStatusIsNotNull() {
            addCriterion("eq_status is not null");
            return (Criteria) this;
        }

        public Criteria andEqStatusEqualTo(String value) {
            addCriterion("eq_status =", value, "eqStatus");
            return (Criteria) this;
        }

        public Criteria andEqStatusNotEqualTo(String value) {
            addCriterion("eq_status <>", value, "eqStatus");
            return (Criteria) this;
        }

        public Criteria andEqStatusGreaterThan(String value) {
            addCriterion("eq_status >", value, "eqStatus");
            return (Criteria) this;
        }

        public Criteria andEqStatusGreaterThanOrEqualTo(String value) {
            addCriterion("eq_status >=", value, "eqStatus");
            return (Criteria) this;
        }

        public Criteria andEqStatusLessThan(String value) {
            addCriterion("eq_status <", value, "eqStatus");
            return (Criteria) this;
        }

        public Criteria andEqStatusLessThanOrEqualTo(String value) {
            addCriterion("eq_status <=", value, "eqStatus");
            return (Criteria) this;
        }

        public Criteria andEqStatusLike(String value) {
            addCriterion("eq_status like", value, "eqStatus");
            return (Criteria) this;
        }

        public Criteria andEqStatusNotLike(String value) {
            addCriterion("eq_status not like", value, "eqStatus");
            return (Criteria) this;
        }

        public Criteria andEqStatusIn(List<String> values) {
            addCriterion("eq_status in", values, "eqStatus");
            return (Criteria) this;
        }

        public Criteria andEqStatusNotIn(List<String> values) {
            addCriterion("eq_status not in", values, "eqStatus");
            return (Criteria) this;
        }

        public Criteria andEqStatusBetween(String value1, String value2) {
            addCriterion("eq_status between", value1, value2, "eqStatus");
            return (Criteria) this;
        }

        public Criteria andEqStatusNotBetween(String value1, String value2) {
            addCriterion("eq_status not between", value1, value2, "eqStatus");
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

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andEqtypeIsNull() {
            addCriterion("eqtype is null");
            return (Criteria) this;
        }

        public Criteria andEqtypeIsNotNull() {
            addCriterion("eqtype is not null");
            return (Criteria) this;
        }

        public Criteria andEqtypeEqualTo(String value) {
            addCriterion("eqtype =", value, "eqtype");
            return (Criteria) this;
        }

        public Criteria andEqtypeNotEqualTo(String value) {
            addCriterion("eqtype <>", value, "eqtype");
            return (Criteria) this;
        }

        public Criteria andEqtypeGreaterThan(String value) {
            addCriterion("eqtype >", value, "eqtype");
            return (Criteria) this;
        }

        public Criteria andEqtypeGreaterThanOrEqualTo(String value) {
            addCriterion("eqtype >=", value, "eqtype");
            return (Criteria) this;
        }

        public Criteria andEqtypeLessThan(String value) {
            addCriterion("eqtype <", value, "eqtype");
            return (Criteria) this;
        }

        public Criteria andEqtypeLessThanOrEqualTo(String value) {
            addCriterion("eqtype <=", value, "eqtype");
            return (Criteria) this;
        }

        public Criteria andEqtypeLike(String value) {
            addCriterion("eqtype like", value, "eqtype");
            return (Criteria) this;
        }

        public Criteria andEqtypeNotLike(String value) {
            addCriterion("eqtype not like", value, "eqtype");
            return (Criteria) this;
        }

        public Criteria andEqtypeIn(List<String> values) {
            addCriterion("eqtype in", values, "eqtype");
            return (Criteria) this;
        }

        public Criteria andEqtypeNotIn(List<String> values) {
            addCriterion("eqtype not in", values, "eqtype");
            return (Criteria) this;
        }

        public Criteria andEqtypeBetween(String value1, String value2) {
            addCriterion("eqtype between", value1, value2, "eqtype");
            return (Criteria) this;
        }

        public Criteria andEqtypeNotBetween(String value1, String value2) {
            addCriterion("eqtype not between", value1, value2, "eqtype");
            return (Criteria) this;
        }

        public Criteria andBluetoothNameIsNull() {
            addCriterion("bluetooth_name is null");
            return (Criteria) this;
        }

        public Criteria andBluetoothNameIsNotNull() {
            addCriterion("bluetooth_name is not null");
            return (Criteria) this;
        }

        public Criteria andBluetoothNameEqualTo(String value) {
            addCriterion("bluetooth_name =", value, "bluetoothName");
            return (Criteria) this;
        }

        public Criteria andBluetoothNameNotEqualTo(String value) {
            addCriterion("bluetooth_name <>", value, "bluetoothName");
            return (Criteria) this;
        }

        public Criteria andBluetoothNameGreaterThan(String value) {
            addCriterion("bluetooth_name >", value, "bluetoothName");
            return (Criteria) this;
        }

        public Criteria andBluetoothNameGreaterThanOrEqualTo(String value) {
            addCriterion("bluetooth_name >=", value, "bluetoothName");
            return (Criteria) this;
        }

        public Criteria andBluetoothNameLessThan(String value) {
            addCriterion("bluetooth_name <", value, "bluetoothName");
            return (Criteria) this;
        }

        public Criteria andBluetoothNameLessThanOrEqualTo(String value) {
            addCriterion("bluetooth_name <=", value, "bluetoothName");
            return (Criteria) this;
        }

        public Criteria andBluetoothNameLike(String value) {
            addCriterion("bluetooth_name like", value, "bluetoothName");
            return (Criteria) this;
        }

        public Criteria andBluetoothNameNotLike(String value) {
            addCriterion("bluetooth_name not like", value, "bluetoothName");
            return (Criteria) this;
        }

        public Criteria andBluetoothNameIn(List<String> values) {
            addCriterion("bluetooth_name in", values, "bluetoothName");
            return (Criteria) this;
        }

        public Criteria andBluetoothNameNotIn(List<String> values) {
            addCriterion("bluetooth_name not in", values, "bluetoothName");
            return (Criteria) this;
        }

        public Criteria andBluetoothNameBetween(String value1, String value2) {
            addCriterion("bluetooth_name between", value1, value2, "bluetoothName");
            return (Criteria) this;
        }

        public Criteria andBluetoothNameNotBetween(String value1, String value2) {
            addCriterion("bluetooth_name not between", value1, value2, "bluetoothName");
            return (Criteria) this;
        }

        public Criteria andBluetoothStatusIsNull() {
            addCriterion("bluetooth_status is null");
            return (Criteria) this;
        }

        public Criteria andBluetoothStatusIsNotNull() {
            addCriterion("bluetooth_status is not null");
            return (Criteria) this;
        }

        public Criteria andBluetoothStatusEqualTo(String value) {
            addCriterion("bluetooth_status =", value, "bluetoothStatus");
            return (Criteria) this;
        }

        public Criteria andBluetoothStatusNotEqualTo(String value) {
            addCriterion("bluetooth_status <>", value, "bluetoothStatus");
            return (Criteria) this;
        }

        public Criteria andBluetoothStatusGreaterThan(String value) {
            addCriterion("bluetooth_status >", value, "bluetoothStatus");
            return (Criteria) this;
        }

        public Criteria andBluetoothStatusGreaterThanOrEqualTo(String value) {
            addCriterion("bluetooth_status >=", value, "bluetoothStatus");
            return (Criteria) this;
        }

        public Criteria andBluetoothStatusLessThan(String value) {
            addCriterion("bluetooth_status <", value, "bluetoothStatus");
            return (Criteria) this;
        }

        public Criteria andBluetoothStatusLessThanOrEqualTo(String value) {
            addCriterion("bluetooth_status <=", value, "bluetoothStatus");
            return (Criteria) this;
        }

        public Criteria andBluetoothStatusLike(String value) {
            addCriterion("bluetooth_status like", value, "bluetoothStatus");
            return (Criteria) this;
        }

        public Criteria andBluetoothStatusNotLike(String value) {
            addCriterion("bluetooth_status not like", value, "bluetoothStatus");
            return (Criteria) this;
        }

        public Criteria andBluetoothStatusIn(List<String> values) {
            addCriterion("bluetooth_status in", values, "bluetoothStatus");
            return (Criteria) this;
        }

        public Criteria andBluetoothStatusNotIn(List<String> values) {
            addCriterion("bluetooth_status not in", values, "bluetoothStatus");
            return (Criteria) this;
        }

        public Criteria andBluetoothStatusBetween(String value1, String value2) {
            addCriterion("bluetooth_status between", value1, value2, "bluetoothStatus");
            return (Criteria) this;
        }

        public Criteria andBluetoothStatusNotBetween(String value1, String value2) {
            addCriterion("bluetooth_status not between", value1, value2, "bluetoothStatus");
            return (Criteria) this;
        }

        public Criteria andBluetoothElectricityIsNull() {
            addCriterion("bluetooth_electricity is null");
            return (Criteria) this;
        }

        public Criteria andBluetoothElectricityIsNotNull() {
            addCriterion("bluetooth_electricity is not null");
            return (Criteria) this;
        }

        public Criteria andBluetoothElectricityEqualTo(Integer value) {
            addCriterion("bluetooth_electricity =", value, "bluetoothElectricity");
            return (Criteria) this;
        }

        public Criteria andBluetoothElectricityNotEqualTo(Integer value) {
            addCriterion("bluetooth_electricity <>", value, "bluetoothElectricity");
            return (Criteria) this;
        }

        public Criteria andBluetoothElectricityGreaterThan(Integer value) {
            addCriterion("bluetooth_electricity >", value, "bluetoothElectricity");
            return (Criteria) this;
        }

        public Criteria andBluetoothElectricityGreaterThanOrEqualTo(Integer value) {
            addCriterion("bluetooth_electricity >=", value, "bluetoothElectricity");
            return (Criteria) this;
        }

        public Criteria andBluetoothElectricityLessThan(Integer value) {
            addCriterion("bluetooth_electricity <", value, "bluetoothElectricity");
            return (Criteria) this;
        }

        public Criteria andBluetoothElectricityLessThanOrEqualTo(Integer value) {
            addCriterion("bluetooth_electricity <=", value, "bluetoothElectricity");
            return (Criteria) this;
        }

        public Criteria andBluetoothElectricityIn(List<Integer> values) {
            addCriterion("bluetooth_electricity in", values, "bluetoothElectricity");
            return (Criteria) this;
        }

        public Criteria andBluetoothElectricityNotIn(List<Integer> values) {
            addCriterion("bluetooth_electricity not in", values, "bluetoothElectricity");
            return (Criteria) this;
        }

        public Criteria andBluetoothElectricityBetween(Integer value1, Integer value2) {
            addCriterion("bluetooth_electricity between", value1, value2, "bluetoothElectricity");
            return (Criteria) this;
        }

        public Criteria andBluetoothElectricityNotBetween(Integer value1, Integer value2) {
            addCriterion("bluetooth_electricity not between", value1, value2, "bluetoothElectricity");
            return (Criteria) this;
        }

        public Criteria andClockIsNull() {
            addCriterion("clock is null");
            return (Criteria) this;
        }

        public Criteria andClockIsNotNull() {
            addCriterion("clock is not null");
            return (Criteria) this;
        }

        public Criteria andClockEqualTo(String value) {
            addCriterion("clock =", value, "clock");
            return (Criteria) this;
        }

        public Criteria andClockNotEqualTo(String value) {
            addCriterion("clock <>", value, "clock");
            return (Criteria) this;
        }

        public Criteria andClockGreaterThan(String value) {
            addCriterion("clock >", value, "clock");
            return (Criteria) this;
        }

        public Criteria andClockGreaterThanOrEqualTo(String value) {
            addCriterion("clock >=", value, "clock");
            return (Criteria) this;
        }

        public Criteria andClockLessThan(String value) {
            addCriterion("clock <", value, "clock");
            return (Criteria) this;
        }

        public Criteria andClockLessThanOrEqualTo(String value) {
            addCriterion("clock <=", value, "clock");
            return (Criteria) this;
        }

        public Criteria andClockLike(String value) {
            addCriterion("clock like", value, "clock");
            return (Criteria) this;
        }

        public Criteria andClockNotLike(String value) {
            addCriterion("clock not like", value, "clock");
            return (Criteria) this;
        }

        public Criteria andClockIn(List<String> values) {
            addCriterion("clock in", values, "clock");
            return (Criteria) this;
        }

        public Criteria andClockNotIn(List<String> values) {
            addCriterion("clock not in", values, "clock");
            return (Criteria) this;
        }

        public Criteria andClockBetween(String value1, String value2) {
            addCriterion("clock between", value1, value2, "clock");
            return (Criteria) this;
        }

        public Criteria andClockNotBetween(String value1, String value2) {
            addCriterion("clock not between", value1, value2, "clock");
            return (Criteria) this;
        }

        public Criteria andPhone1IsNull() {
            addCriterion("phone1 is null");
            return (Criteria) this;
        }

        public Criteria andPhone1IsNotNull() {
            addCriterion("phone1 is not null");
            return (Criteria) this;
        }

        public Criteria andPhone1EqualTo(String value) {
            addCriterion("phone1 =", value, "phone1");
            return (Criteria) this;
        }

        public Criteria andPhone1NotEqualTo(String value) {
            addCriterion("phone1 <>", value, "phone1");
            return (Criteria) this;
        }

        public Criteria andPhone1GreaterThan(String value) {
            addCriterion("phone1 >", value, "phone1");
            return (Criteria) this;
        }

        public Criteria andPhone1GreaterThanOrEqualTo(String value) {
            addCriterion("phone1 >=", value, "phone1");
            return (Criteria) this;
        }

        public Criteria andPhone1LessThan(String value) {
            addCriterion("phone1 <", value, "phone1");
            return (Criteria) this;
        }

        public Criteria andPhone1LessThanOrEqualTo(String value) {
            addCriterion("phone1 <=", value, "phone1");
            return (Criteria) this;
        }

        public Criteria andPhone1Like(String value) {
            addCriterion("phone1 like", value, "phone1");
            return (Criteria) this;
        }

        public Criteria andPhone1NotLike(String value) {
            addCriterion("phone1 not like", value, "phone1");
            return (Criteria) this;
        }

        public Criteria andPhone1In(List<String> values) {
            addCriterion("phone1 in", values, "phone1");
            return (Criteria) this;
        }

        public Criteria andPhone1NotIn(List<String> values) {
            addCriterion("phone1 not in", values, "phone1");
            return (Criteria) this;
        }

        public Criteria andPhone1Between(String value1, String value2) {
            addCriterion("phone1 between", value1, value2, "phone1");
            return (Criteria) this;
        }

        public Criteria andPhone1NotBetween(String value1, String value2) {
            addCriterion("phone1 not between", value1, value2, "phone1");
            return (Criteria) this;
        }

        public Criteria andPhone2IsNull() {
            addCriterion("phone2 is null");
            return (Criteria) this;
        }

        public Criteria andPhone2IsNotNull() {
            addCriterion("phone2 is not null");
            return (Criteria) this;
        }

        public Criteria andPhone2EqualTo(String value) {
            addCriterion("phone2 =", value, "phone2");
            return (Criteria) this;
        }

        public Criteria andPhone2NotEqualTo(String value) {
            addCriterion("phone2 <>", value, "phone2");
            return (Criteria) this;
        }

        public Criteria andPhone2GreaterThan(String value) {
            addCriterion("phone2 >", value, "phone2");
            return (Criteria) this;
        }

        public Criteria andPhone2GreaterThanOrEqualTo(String value) {
            addCriterion("phone2 >=", value, "phone2");
            return (Criteria) this;
        }

        public Criteria andPhone2LessThan(String value) {
            addCriterion("phone2 <", value, "phone2");
            return (Criteria) this;
        }

        public Criteria andPhone2LessThanOrEqualTo(String value) {
            addCriterion("phone2 <=", value, "phone2");
            return (Criteria) this;
        }

        public Criteria andPhone2Like(String value) {
            addCriterion("phone2 like", value, "phone2");
            return (Criteria) this;
        }

        public Criteria andPhone2NotLike(String value) {
            addCriterion("phone2 not like", value, "phone2");
            return (Criteria) this;
        }

        public Criteria andPhone2In(List<String> values) {
            addCriterion("phone2 in", values, "phone2");
            return (Criteria) this;
        }

        public Criteria andPhone2NotIn(List<String> values) {
            addCriterion("phone2 not in", values, "phone2");
            return (Criteria) this;
        }

        public Criteria andPhone2Between(String value1, String value2) {
            addCriterion("phone2 between", value1, value2, "phone2");
            return (Criteria) this;
        }

        public Criteria andPhone2NotBetween(String value1, String value2) {
            addCriterion("phone2 not between", value1, value2, "phone2");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andUploadtimeIsNull() {
            addCriterion("uploadtime is null");
            return (Criteria) this;
        }

        public Criteria andUploadtimeIsNotNull() {
            addCriterion("uploadtime is not null");
            return (Criteria) this;
        }

        public Criteria andUploadtimeEqualTo(Date value) {
            addCriterion("uploadtime =", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeNotEqualTo(Date value) {
            addCriterion("uploadtime <>", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeGreaterThan(Date value) {
            addCriterion("uploadtime >", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("uploadtime >=", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeLessThan(Date value) {
            addCriterion("uploadtime <", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeLessThanOrEqualTo(Date value) {
            addCriterion("uploadtime <=", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeIn(List<Date> values) {
            addCriterion("uploadtime in", values, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeNotIn(List<Date> values) {
            addCriterion("uploadtime not in", values, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeBetween(Date value1, Date value2) {
            addCriterion("uploadtime between", value1, value2, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeNotBetween(Date value1, Date value2) {
            addCriterion("uploadtime not between", value1, value2, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andBluetoothmacIsNull() {
            addCriterion("bluetoothmac is null");
            return (Criteria) this;
        }

        public Criteria andBluetoothmacIsNotNull() {
            addCriterion("bluetoothmac is not null");
            return (Criteria) this;
        }

        public Criteria andBluetoothmacEqualTo(String value) {
            addCriterion("bluetoothmac =", value, "bluetoothmac");
            return (Criteria) this;
        }

        public Criteria andBluetoothmacNotEqualTo(String value) {
            addCriterion("bluetoothmac <>", value, "bluetoothmac");
            return (Criteria) this;
        }

        public Criteria andBluetoothmacGreaterThan(String value) {
            addCriterion("bluetoothmac >", value, "bluetoothmac");
            return (Criteria) this;
        }

        public Criteria andBluetoothmacGreaterThanOrEqualTo(String value) {
            addCriterion("bluetoothmac >=", value, "bluetoothmac");
            return (Criteria) this;
        }

        public Criteria andBluetoothmacLessThan(String value) {
            addCriterion("bluetoothmac <", value, "bluetoothmac");
            return (Criteria) this;
        }

        public Criteria andBluetoothmacLessThanOrEqualTo(String value) {
            addCriterion("bluetoothmac <=", value, "bluetoothmac");
            return (Criteria) this;
        }

        public Criteria andBluetoothmacLike(String value) {
            addCriterion("bluetoothmac like", value, "bluetoothmac");
            return (Criteria) this;
        }

        public Criteria andBluetoothmacNotLike(String value) {
            addCriterion("bluetoothmac not like", value, "bluetoothmac");
            return (Criteria) this;
        }

        public Criteria andBluetoothmacIn(List<String> values) {
            addCriterion("bluetoothmac in", values, "bluetoothmac");
            return (Criteria) this;
        }

        public Criteria andBluetoothmacNotIn(List<String> values) {
            addCriterion("bluetoothmac not in", values, "bluetoothmac");
            return (Criteria) this;
        }

        public Criteria andBluetoothmacBetween(String value1, String value2) {
            addCriterion("bluetoothmac between", value1, value2, "bluetoothmac");
            return (Criteria) this;
        }

        public Criteria andBluetoothmacNotBetween(String value1, String value2) {
            addCriterion("bluetoothmac not between", value1, value2, "bluetoothmac");
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