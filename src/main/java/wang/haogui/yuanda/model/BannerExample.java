package wang.haogui.yuanda.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BannerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BannerExample() {
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

        public Criteria andBannerIdIsNull() {
            addCriterion("banner_id is null");
            return (Criteria) this;
        }

        public Criteria andBannerIdIsNotNull() {
            addCriterion("banner_id is not null");
            return (Criteria) this;
        }

        public Criteria andBannerIdEqualTo(Integer value) {
            addCriterion("banner_id =", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdNotEqualTo(Integer value) {
            addCriterion("banner_id <>", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdGreaterThan(Integer value) {
            addCriterion("banner_id >", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("banner_id >=", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdLessThan(Integer value) {
            addCriterion("banner_id <", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdLessThanOrEqualTo(Integer value) {
            addCriterion("banner_id <=", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdIn(List<Integer> values) {
            addCriterion("banner_id in", values, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdNotIn(List<Integer> values) {
            addCriterion("banner_id not in", values, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdBetween(Integer value1, Integer value2) {
            addCriterion("banner_id between", value1, value2, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("banner_id not between", value1, value2, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerPicIsNull() {
            addCriterion("banner_pic is null");
            return (Criteria) this;
        }

        public Criteria andBannerPicIsNotNull() {
            addCriterion("banner_pic is not null");
            return (Criteria) this;
        }

        public Criteria andBannerPicEqualTo(String value) {
            addCriterion("banner_pic =", value, "bannerPic");
            return (Criteria) this;
        }

        public Criteria andBannerPicNotEqualTo(String value) {
            addCriterion("banner_pic <>", value, "bannerPic");
            return (Criteria) this;
        }

        public Criteria andBannerPicGreaterThan(String value) {
            addCriterion("banner_pic >", value, "bannerPic");
            return (Criteria) this;
        }

        public Criteria andBannerPicGreaterThanOrEqualTo(String value) {
            addCriterion("banner_pic >=", value, "bannerPic");
            return (Criteria) this;
        }

        public Criteria andBannerPicLessThan(String value) {
            addCriterion("banner_pic <", value, "bannerPic");
            return (Criteria) this;
        }

        public Criteria andBannerPicLessThanOrEqualTo(String value) {
            addCriterion("banner_pic <=", value, "bannerPic");
            return (Criteria) this;
        }

        public Criteria andBannerPicLike(String value) {
            addCriterion("banner_pic like", value, "bannerPic");
            return (Criteria) this;
        }

        public Criteria andBannerPicNotLike(String value) {
            addCriterion("banner_pic not like", value, "bannerPic");
            return (Criteria) this;
        }

        public Criteria andBannerPicIn(List<String> values) {
            addCriterion("banner_pic in", values, "bannerPic");
            return (Criteria) this;
        }

        public Criteria andBannerPicNotIn(List<String> values) {
            addCriterion("banner_pic not in", values, "bannerPic");
            return (Criteria) this;
        }

        public Criteria andBannerPicBetween(String value1, String value2) {
            addCriterion("banner_pic between", value1, value2, "bannerPic");
            return (Criteria) this;
        }

        public Criteria andBannerPicNotBetween(String value1, String value2) {
            addCriterion("banner_pic not between", value1, value2, "bannerPic");
            return (Criteria) this;
        }

        public Criteria andBannerContentIsNull() {
            addCriterion("banner_content is null");
            return (Criteria) this;
        }

        public Criteria andBannerContentIsNotNull() {
            addCriterion("banner_content is not null");
            return (Criteria) this;
        }

        public Criteria andBannerContentEqualTo(String value) {
            addCriterion("banner_content =", value, "bannerContent");
            return (Criteria) this;
        }

        public Criteria andBannerContentNotEqualTo(String value) {
            addCriterion("banner_content <>", value, "bannerContent");
            return (Criteria) this;
        }

        public Criteria andBannerContentGreaterThan(String value) {
            addCriterion("banner_content >", value, "bannerContent");
            return (Criteria) this;
        }

        public Criteria andBannerContentGreaterThanOrEqualTo(String value) {
            addCriterion("banner_content >=", value, "bannerContent");
            return (Criteria) this;
        }

        public Criteria andBannerContentLessThan(String value) {
            addCriterion("banner_content <", value, "bannerContent");
            return (Criteria) this;
        }

        public Criteria andBannerContentLessThanOrEqualTo(String value) {
            addCriterion("banner_content <=", value, "bannerContent");
            return (Criteria) this;
        }

        public Criteria andBannerContentLike(String value) {
            addCriterion("banner_content like", value, "bannerContent");
            return (Criteria) this;
        }

        public Criteria andBannerContentNotLike(String value) {
            addCriterion("banner_content not like", value, "bannerContent");
            return (Criteria) this;
        }

        public Criteria andBannerContentIn(List<String> values) {
            addCriterion("banner_content in", values, "bannerContent");
            return (Criteria) this;
        }

        public Criteria andBannerContentNotIn(List<String> values) {
            addCriterion("banner_content not in", values, "bannerContent");
            return (Criteria) this;
        }

        public Criteria andBannerContentBetween(String value1, String value2) {
            addCriterion("banner_content between", value1, value2, "bannerContent");
            return (Criteria) this;
        }

        public Criteria andBannerContentNotBetween(String value1, String value2) {
            addCriterion("banner_content not between", value1, value2, "bannerContent");
            return (Criteria) this;
        }

        public Criteria andBannerTitleIsNull() {
            addCriterion("banner_title is null");
            return (Criteria) this;
        }

        public Criteria andBannerTitleIsNotNull() {
            addCriterion("banner_title is not null");
            return (Criteria) this;
        }

        public Criteria andBannerTitleEqualTo(String value) {
            addCriterion("banner_title =", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleNotEqualTo(String value) {
            addCriterion("banner_title <>", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleGreaterThan(String value) {
            addCriterion("banner_title >", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleGreaterThanOrEqualTo(String value) {
            addCriterion("banner_title >=", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleLessThan(String value) {
            addCriterion("banner_title <", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleLessThanOrEqualTo(String value) {
            addCriterion("banner_title <=", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleLike(String value) {
            addCriterion("banner_title like", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleNotLike(String value) {
            addCriterion("banner_title not like", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleIn(List<String> values) {
            addCriterion("banner_title in", values, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleNotIn(List<String> values) {
            addCriterion("banner_title not in", values, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleBetween(String value1, String value2) {
            addCriterion("banner_title between", value1, value2, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleNotBetween(String value1, String value2) {
            addCriterion("banner_title not between", value1, value2, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerStatusIsNull() {
            addCriterion("banner_status is null");
            return (Criteria) this;
        }

        public Criteria andBannerStatusIsNotNull() {
            addCriterion("banner_status is not null");
            return (Criteria) this;
        }

        public Criteria andBannerStatusEqualTo(Boolean value) {
            addCriterion("banner_status =", value, "bannerStatus");
            return (Criteria) this;
        }

        public Criteria andBannerStatusNotEqualTo(Boolean value) {
            addCriterion("banner_status <>", value, "bannerStatus");
            return (Criteria) this;
        }

        public Criteria andBannerStatusGreaterThan(Boolean value) {
            addCriterion("banner_status >", value, "bannerStatus");
            return (Criteria) this;
        }

        public Criteria andBannerStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("banner_status >=", value, "bannerStatus");
            return (Criteria) this;
        }

        public Criteria andBannerStatusLessThan(Boolean value) {
            addCriterion("banner_status <", value, "bannerStatus");
            return (Criteria) this;
        }

        public Criteria andBannerStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("banner_status <=", value, "bannerStatus");
            return (Criteria) this;
        }

        public Criteria andBannerStatusIn(List<Boolean> values) {
            addCriterion("banner_status in", values, "bannerStatus");
            return (Criteria) this;
        }

        public Criteria andBannerStatusNotIn(List<Boolean> values) {
            addCriterion("banner_status not in", values, "bannerStatus");
            return (Criteria) this;
        }

        public Criteria andBannerStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("banner_status between", value1, value2, "bannerStatus");
            return (Criteria) this;
        }

        public Criteria andBannerStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("banner_status not between", value1, value2, "bannerStatus");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeIsNull() {
            addCriterion("banner_create_time is null");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeIsNotNull() {
            addCriterion("banner_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeEqualTo(Date value) {
            addCriterion("banner_create_time =", value, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeNotEqualTo(Date value) {
            addCriterion("banner_create_time <>", value, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeGreaterThan(Date value) {
            addCriterion("banner_create_time >", value, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("banner_create_time >=", value, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeLessThan(Date value) {
            addCriterion("banner_create_time <", value, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("banner_create_time <=", value, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeIn(List<Date> values) {
            addCriterion("banner_create_time in", values, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeNotIn(List<Date> values) {
            addCriterion("banner_create_time not in", values, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeBetween(Date value1, Date value2) {
            addCriterion("banner_create_time between", value1, value2, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("banner_create_time not between", value1, value2, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerFailureTimeIsNull() {
            addCriterion("banner_failure_time is null");
            return (Criteria) this;
        }

        public Criteria andBannerFailureTimeIsNotNull() {
            addCriterion("banner_failure_time is not null");
            return (Criteria) this;
        }

        public Criteria andBannerFailureTimeEqualTo(Date value) {
            addCriterion("banner_failure_time =", value, "bannerFailureTime");
            return (Criteria) this;
        }

        public Criteria andBannerFailureTimeNotEqualTo(Date value) {
            addCriterion("banner_failure_time <>", value, "bannerFailureTime");
            return (Criteria) this;
        }

        public Criteria andBannerFailureTimeGreaterThan(Date value) {
            addCriterion("banner_failure_time >", value, "bannerFailureTime");
            return (Criteria) this;
        }

        public Criteria andBannerFailureTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("banner_failure_time >=", value, "bannerFailureTime");
            return (Criteria) this;
        }

        public Criteria andBannerFailureTimeLessThan(Date value) {
            addCriterion("banner_failure_time <", value, "bannerFailureTime");
            return (Criteria) this;
        }

        public Criteria andBannerFailureTimeLessThanOrEqualTo(Date value) {
            addCriterion("banner_failure_time <=", value, "bannerFailureTime");
            return (Criteria) this;
        }

        public Criteria andBannerFailureTimeIn(List<Date> values) {
            addCriterion("banner_failure_time in", values, "bannerFailureTime");
            return (Criteria) this;
        }

        public Criteria andBannerFailureTimeNotIn(List<Date> values) {
            addCriterion("banner_failure_time not in", values, "bannerFailureTime");
            return (Criteria) this;
        }

        public Criteria andBannerFailureTimeBetween(Date value1, Date value2) {
            addCriterion("banner_failure_time between", value1, value2, "bannerFailureTime");
            return (Criteria) this;
        }

        public Criteria andBannerFailureTimeNotBetween(Date value1, Date value2) {
            addCriterion("banner_failure_time not between", value1, value2, "bannerFailureTime");
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