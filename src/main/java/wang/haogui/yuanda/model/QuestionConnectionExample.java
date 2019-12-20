package wang.haogui.yuanda.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionConnectionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QuestionConnectionExample() {
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

        public Criteria andQuesionIdIsNull() {
            addCriterion("quesion_id is null");
            return (Criteria) this;
        }

        public Criteria andQuesionIdIsNotNull() {
            addCriterion("quesion_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuesionIdEqualTo(Integer value) {
            addCriterion("quesion_id =", value, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdNotEqualTo(Integer value) {
            addCriterion("quesion_id <>", value, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdGreaterThan(Integer value) {
            addCriterion("quesion_id >", value, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("quesion_id >=", value, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdLessThan(Integer value) {
            addCriterion("quesion_id <", value, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdLessThanOrEqualTo(Integer value) {
            addCriterion("quesion_id <=", value, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdIn(List<Integer> values) {
            addCriterion("quesion_id in", values, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdNotIn(List<Integer> values) {
            addCriterion("quesion_id not in", values, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdBetween(Integer value1, Integer value2) {
            addCriterion("quesion_id between", value1, value2, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("quesion_id not between", value1, value2, "quesionId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdIsNull() {
            addCriterion("answer_id is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIdIsNotNull() {
            addCriterion("answer_id is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerIdEqualTo(Integer value) {
            addCriterion("answer_id =", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdNotEqualTo(Integer value) {
            addCriterion("answer_id <>", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdGreaterThan(Integer value) {
            addCriterion("answer_id >", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("answer_id >=", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdLessThan(Integer value) {
            addCriterion("answer_id <", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdLessThanOrEqualTo(Integer value) {
            addCriterion("answer_id <=", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdIn(List<Integer> values) {
            addCriterion("answer_id in", values, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdNotIn(List<Integer> values) {
            addCriterion("answer_id not in", values, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdBetween(Integer value1, Integer value2) {
            addCriterion("answer_id between", value1, value2, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("answer_id not between", value1, value2, "answerId");
            return (Criteria) this;
        }

        public Criteria andCommentNumberIsNull() {
            addCriterion("comment_number is null");
            return (Criteria) this;
        }

        public Criteria andCommentNumberIsNotNull() {
            addCriterion("comment_number is not null");
            return (Criteria) this;
        }

        public Criteria andCommentNumberEqualTo(Integer value) {
            addCriterion("comment_number =", value, "commentNumber");
            return (Criteria) this;
        }

        public Criteria andCommentNumberNotEqualTo(Integer value) {
            addCriterion("comment_number <>", value, "commentNumber");
            return (Criteria) this;
        }

        public Criteria andCommentNumberGreaterThan(Integer value) {
            addCriterion("comment_number >", value, "commentNumber");
            return (Criteria) this;
        }

        public Criteria andCommentNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_number >=", value, "commentNumber");
            return (Criteria) this;
        }

        public Criteria andCommentNumberLessThan(Integer value) {
            addCriterion("comment_number <", value, "commentNumber");
            return (Criteria) this;
        }

        public Criteria andCommentNumberLessThanOrEqualTo(Integer value) {
            addCriterion("comment_number <=", value, "commentNumber");
            return (Criteria) this;
        }

        public Criteria andCommentNumberIn(List<Integer> values) {
            addCriterion("comment_number in", values, "commentNumber");
            return (Criteria) this;
        }

        public Criteria andCommentNumberNotIn(List<Integer> values) {
            addCriterion("comment_number not in", values, "commentNumber");
            return (Criteria) this;
        }

        public Criteria andCommentNumberBetween(Integer value1, Integer value2) {
            addCriterion("comment_number between", value1, value2, "commentNumber");
            return (Criteria) this;
        }

        public Criteria andCommentNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_number not between", value1, value2, "commentNumber");
            return (Criteria) this;
        }

        public Criteria andAgreementNumberIsNull() {
            addCriterion("agreement_number is null");
            return (Criteria) this;
        }

        public Criteria andAgreementNumberIsNotNull() {
            addCriterion("agreement_number is not null");
            return (Criteria) this;
        }

        public Criteria andAgreementNumberEqualTo(Integer value) {
            addCriterion("agreement_number =", value, "agreementNumber");
            return (Criteria) this;
        }

        public Criteria andAgreementNumberNotEqualTo(Integer value) {
            addCriterion("agreement_number <>", value, "agreementNumber");
            return (Criteria) this;
        }

        public Criteria andAgreementNumberGreaterThan(Integer value) {
            addCriterion("agreement_number >", value, "agreementNumber");
            return (Criteria) this;
        }

        public Criteria andAgreementNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("agreement_number >=", value, "agreementNumber");
            return (Criteria) this;
        }

        public Criteria andAgreementNumberLessThan(Integer value) {
            addCriterion("agreement_number <", value, "agreementNumber");
            return (Criteria) this;
        }

        public Criteria andAgreementNumberLessThanOrEqualTo(Integer value) {
            addCriterion("agreement_number <=", value, "agreementNumber");
            return (Criteria) this;
        }

        public Criteria andAgreementNumberIn(List<Integer> values) {
            addCriterion("agreement_number in", values, "agreementNumber");
            return (Criteria) this;
        }

        public Criteria andAgreementNumberNotIn(List<Integer> values) {
            addCriterion("agreement_number not in", values, "agreementNumber");
            return (Criteria) this;
        }

        public Criteria andAgreementNumberBetween(Integer value1, Integer value2) {
            addCriterion("agreement_number between", value1, value2, "agreementNumber");
            return (Criteria) this;
        }

        public Criteria andAgreementNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("agreement_number not between", value1, value2, "agreementNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreementNumberIsNull() {
            addCriterion("disagreement_number is null");
            return (Criteria) this;
        }

        public Criteria andDisagreementNumberIsNotNull() {
            addCriterion("disagreement_number is not null");
            return (Criteria) this;
        }

        public Criteria andDisagreementNumberEqualTo(Integer value) {
            addCriterion("disagreement_number =", value, "disagreementNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreementNumberNotEqualTo(Integer value) {
            addCriterion("disagreement_number <>", value, "disagreementNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreementNumberGreaterThan(Integer value) {
            addCriterion("disagreement_number >", value, "disagreementNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreementNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("disagreement_number >=", value, "disagreementNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreementNumberLessThan(Integer value) {
            addCriterion("disagreement_number <", value, "disagreementNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreementNumberLessThanOrEqualTo(Integer value) {
            addCriterion("disagreement_number <=", value, "disagreementNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreementNumberIn(List<Integer> values) {
            addCriterion("disagreement_number in", values, "disagreementNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreementNumberNotIn(List<Integer> values) {
            addCriterion("disagreement_number not in", values, "disagreementNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreementNumberBetween(Integer value1, Integer value2) {
            addCriterion("disagreement_number between", value1, value2, "disagreementNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreementNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("disagreement_number not between", value1, value2, "disagreementNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberIsNull() {
            addCriterion("click_number is null");
            return (Criteria) this;
        }

        public Criteria andClickNumberIsNotNull() {
            addCriterion("click_number is not null");
            return (Criteria) this;
        }

        public Criteria andClickNumberEqualTo(Integer value) {
            addCriterion("click_number =", value, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberNotEqualTo(Integer value) {
            addCriterion("click_number <>", value, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberGreaterThan(Integer value) {
            addCriterion("click_number >", value, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("click_number >=", value, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberLessThan(Integer value) {
            addCriterion("click_number <", value, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberLessThanOrEqualTo(Integer value) {
            addCriterion("click_number <=", value, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberIn(List<Integer> values) {
            addCriterion("click_number in", values, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberNotIn(List<Integer> values) {
            addCriterion("click_number not in", values, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberBetween(Integer value1, Integer value2) {
            addCriterion("click_number between", value1, value2, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("click_number not between", value1, value2, "clickNumber");
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