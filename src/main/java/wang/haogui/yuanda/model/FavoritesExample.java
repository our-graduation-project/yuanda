package wang.haogui.yuanda.model;

import java.util.ArrayList;
import java.util.List;

public class FavoritesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FavoritesExample() {
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

        public Criteria andFavoritesIdIsNull() {
            addCriterion("favorites_id is null");
            return (Criteria) this;
        }

        public Criteria andFavoritesIdIsNotNull() {
            addCriterion("favorites_id is not null");
            return (Criteria) this;
        }

        public Criteria andFavoritesIdEqualTo(Byte value) {
            addCriterion("favorites_id =", value, "favoritesId");
            return (Criteria) this;
        }

        public Criteria andFavoritesIdNotEqualTo(Byte value) {
            addCriterion("favorites_id <>", value, "favoritesId");
            return (Criteria) this;
        }

        public Criteria andFavoritesIdGreaterThan(Byte value) {
            addCriterion("favorites_id >", value, "favoritesId");
            return (Criteria) this;
        }

        public Criteria andFavoritesIdGreaterThanOrEqualTo(Byte value) {
            addCriterion("favorites_id >=", value, "favoritesId");
            return (Criteria) this;
        }

        public Criteria andFavoritesIdLessThan(Byte value) {
            addCriterion("favorites_id <", value, "favoritesId");
            return (Criteria) this;
        }

        public Criteria andFavoritesIdLessThanOrEqualTo(Byte value) {
            addCriterion("favorites_id <=", value, "favoritesId");
            return (Criteria) this;
        }

        public Criteria andFavoritesIdIn(List<Byte> values) {
            addCriterion("favorites_id in", values, "favoritesId");
            return (Criteria) this;
        }

        public Criteria andFavoritesIdNotIn(List<Byte> values) {
            addCriterion("favorites_id not in", values, "favoritesId");
            return (Criteria) this;
        }

        public Criteria andFavoritesIdBetween(Byte value1, Byte value2) {
            addCriterion("favorites_id between", value1, value2, "favoritesId");
            return (Criteria) this;
        }

        public Criteria andFavoritesIdNotBetween(Byte value1, Byte value2) {
            addCriterion("favorites_id not between", value1, value2, "favoritesId");
            return (Criteria) this;
        }

        public Criteria andFavoritesNameIsNull() {
            addCriterion("favorites_name is null");
            return (Criteria) this;
        }

        public Criteria andFavoritesNameIsNotNull() {
            addCriterion("favorites_name is not null");
            return (Criteria) this;
        }

        public Criteria andFavoritesNameEqualTo(String value) {
            addCriterion("favorites_name =", value, "favoritesName");
            return (Criteria) this;
        }

        public Criteria andFavoritesNameNotEqualTo(String value) {
            addCriterion("favorites_name <>", value, "favoritesName");
            return (Criteria) this;
        }

        public Criteria andFavoritesNameGreaterThan(String value) {
            addCriterion("favorites_name >", value, "favoritesName");
            return (Criteria) this;
        }

        public Criteria andFavoritesNameGreaterThanOrEqualTo(String value) {
            addCriterion("favorites_name >=", value, "favoritesName");
            return (Criteria) this;
        }

        public Criteria andFavoritesNameLessThan(String value) {
            addCriterion("favorites_name <", value, "favoritesName");
            return (Criteria) this;
        }

        public Criteria andFavoritesNameLessThanOrEqualTo(String value) {
            addCriterion("favorites_name <=", value, "favoritesName");
            return (Criteria) this;
        }

        public Criteria andFavoritesNameLike(String value) {
            addCriterion("favorites_name like", value, "favoritesName");
            return (Criteria) this;
        }

        public Criteria andFavoritesNameNotLike(String value) {
            addCriterion("favorites_name not like", value, "favoritesName");
            return (Criteria) this;
        }

        public Criteria andFavoritesNameIn(List<String> values) {
            addCriterion("favorites_name in", values, "favoritesName");
            return (Criteria) this;
        }

        public Criteria andFavoritesNameNotIn(List<String> values) {
            addCriterion("favorites_name not in", values, "favoritesName");
            return (Criteria) this;
        }

        public Criteria andFavoritesNameBetween(String value1, String value2) {
            addCriterion("favorites_name between", value1, value2, "favoritesName");
            return (Criteria) this;
        }

        public Criteria andFavoritesNameNotBetween(String value1, String value2) {
            addCriterion("favorites_name not between", value1, value2, "favoritesName");
            return (Criteria) this;
        }

        public Criteria andFavoritesNumberIsNull() {
            addCriterion("favorites_number is null");
            return (Criteria) this;
        }

        public Criteria andFavoritesNumberIsNotNull() {
            addCriterion("favorites_number is not null");
            return (Criteria) this;
        }

        public Criteria andFavoritesNumberEqualTo(Integer value) {
            addCriterion("favorites_number =", value, "favoritesNumber");
            return (Criteria) this;
        }

        public Criteria andFavoritesNumberNotEqualTo(Integer value) {
            addCriterion("favorites_number <>", value, "favoritesNumber");
            return (Criteria) this;
        }

        public Criteria andFavoritesNumberGreaterThan(Integer value) {
            addCriterion("favorites_number >", value, "favoritesNumber");
            return (Criteria) this;
        }

        public Criteria andFavoritesNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("favorites_number >=", value, "favoritesNumber");
            return (Criteria) this;
        }

        public Criteria andFavoritesNumberLessThan(Integer value) {
            addCriterion("favorites_number <", value, "favoritesNumber");
            return (Criteria) this;
        }

        public Criteria andFavoritesNumberLessThanOrEqualTo(Integer value) {
            addCriterion("favorites_number <=", value, "favoritesNumber");
            return (Criteria) this;
        }

        public Criteria andFavoritesNumberIn(List<Integer> values) {
            addCriterion("favorites_number in", values, "favoritesNumber");
            return (Criteria) this;
        }

        public Criteria andFavoritesNumberNotIn(List<Integer> values) {
            addCriterion("favorites_number not in", values, "favoritesNumber");
            return (Criteria) this;
        }

        public Criteria andFavoritesNumberBetween(Integer value1, Integer value2) {
            addCriterion("favorites_number between", value1, value2, "favoritesNumber");
            return (Criteria) this;
        }

        public Criteria andFavoritesNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("favorites_number not between", value1, value2, "favoritesNumber");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemark2IsNull() {
            addCriterion("remark2 is null");
            return (Criteria) this;
        }

        public Criteria andRemark2IsNotNull() {
            addCriterion("remark2 is not null");
            return (Criteria) this;
        }

        public Criteria andRemark2EqualTo(String value) {
            addCriterion("remark2 =", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotEqualTo(String value) {
            addCriterion("remark2 <>", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2GreaterThan(String value) {
            addCriterion("remark2 >", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2GreaterThanOrEqualTo(String value) {
            addCriterion("remark2 >=", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2LessThan(String value) {
            addCriterion("remark2 <", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2LessThanOrEqualTo(String value) {
            addCriterion("remark2 <=", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2Like(String value) {
            addCriterion("remark2 like", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotLike(String value) {
            addCriterion("remark2 not like", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2In(List<String> values) {
            addCriterion("remark2 in", values, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotIn(List<String> values) {
            addCriterion("remark2 not in", values, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2Between(String value1, String value2) {
            addCriterion("remark2 between", value1, value2, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotBetween(String value1, String value2) {
            addCriterion("remark2 not between", value1, value2, "remark2");
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