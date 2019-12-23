package wang.haogui.yuanda.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnswerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AnswerExample() {
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

        public Criteria andAgreeNumberIsNull() {
            addCriterion("agree_number is null");
            return (Criteria) this;
        }

        public Criteria andAgreeNumberIsNotNull() {
            addCriterion("agree_number is not null");
            return (Criteria) this;
        }

        public Criteria andAgreeNumberEqualTo(Integer value) {
            addCriterion("agree_number =", value, "agreeNumber");
            return (Criteria) this;
        }

        public Criteria andAgreeNumberNotEqualTo(Integer value) {
            addCriterion("agree_number <>", value, "agreeNumber");
            return (Criteria) this;
        }

        public Criteria andAgreeNumberGreaterThan(Integer value) {
            addCriterion("agree_number >", value, "agreeNumber");
            return (Criteria) this;
        }

        public Criteria andAgreeNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("agree_number >=", value, "agreeNumber");
            return (Criteria) this;
        }

        public Criteria andAgreeNumberLessThan(Integer value) {
            addCriterion("agree_number <", value, "agreeNumber");
            return (Criteria) this;
        }

        public Criteria andAgreeNumberLessThanOrEqualTo(Integer value) {
            addCriterion("agree_number <=", value, "agreeNumber");
            return (Criteria) this;
        }

        public Criteria andAgreeNumberIn(List<Integer> values) {
            addCriterion("agree_number in", values, "agreeNumber");
            return (Criteria) this;
        }

        public Criteria andAgreeNumberNotIn(List<Integer> values) {
            addCriterion("agree_number not in", values, "agreeNumber");
            return (Criteria) this;
        }

        public Criteria andAgreeNumberBetween(Integer value1, Integer value2) {
            addCriterion("agree_number between", value1, value2, "agreeNumber");
            return (Criteria) this;
        }

        public Criteria andAgreeNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("agree_number not between", value1, value2, "agreeNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreeNumberIsNull() {
            addCriterion("disagree_number is null");
            return (Criteria) this;
        }

        public Criteria andDisagreeNumberIsNotNull() {
            addCriterion("disagree_number is not null");
            return (Criteria) this;
        }

        public Criteria andDisagreeNumberEqualTo(Integer value) {
            addCriterion("disagree_number =", value, "disagreeNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreeNumberNotEqualTo(Integer value) {
            addCriterion("disagree_number <>", value, "disagreeNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreeNumberGreaterThan(Integer value) {
            addCriterion("disagree_number >", value, "disagreeNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreeNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("disagree_number >=", value, "disagreeNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreeNumberLessThan(Integer value) {
            addCriterion("disagree_number <", value, "disagreeNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreeNumberLessThanOrEqualTo(Integer value) {
            addCriterion("disagree_number <=", value, "disagreeNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreeNumberIn(List<Integer> values) {
            addCriterion("disagree_number in", values, "disagreeNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreeNumberNotIn(List<Integer> values) {
            addCriterion("disagree_number not in", values, "disagreeNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreeNumberBetween(Integer value1, Integer value2) {
            addCriterion("disagree_number between", value1, value2, "disagreeNumber");
            return (Criteria) this;
        }

        public Criteria andDisagreeNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("disagree_number not between", value1, value2, "disagreeNumber");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNull() {
            addCriterion("check_status is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("check_status is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(Byte value) {
            addCriterion("check_status =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(Byte value) {
            addCriterion("check_status <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(Byte value) {
            addCriterion("check_status >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("check_status >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(Byte value) {
            addCriterion("check_status <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(Byte value) {
            addCriterion("check_status <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<Byte> values) {
            addCriterion("check_status in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<Byte> values) {
            addCriterion("check_status not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(Byte value1, Byte value2) {
            addCriterion("check_status between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("check_status not between", value1, value2, "checkStatus");
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

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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

        public Criteria andIsNoNameIsNull() {
            addCriterion("is_no_name is null");
            return (Criteria) this;
        }

        public Criteria andIsNoNameIsNotNull() {
            addCriterion("is_no_name is not null");
            return (Criteria) this;
        }

        public Criteria andIsNoNameEqualTo(Byte value) {
            addCriterion("is_no_name =", value, "isNoName");
            return (Criteria) this;
        }

        public Criteria andIsNoNameNotEqualTo(Byte value) {
            addCriterion("is_no_name <>", value, "isNoName");
            return (Criteria) this;
        }

        public Criteria andIsNoNameGreaterThan(Byte value) {
            addCriterion("is_no_name >", value, "isNoName");
            return (Criteria) this;
        }

        public Criteria andIsNoNameGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_no_name >=", value, "isNoName");
            return (Criteria) this;
        }

        public Criteria andIsNoNameLessThan(Byte value) {
            addCriterion("is_no_name <", value, "isNoName");
            return (Criteria) this;
        }

        public Criteria andIsNoNameLessThanOrEqualTo(Byte value) {
            addCriterion("is_no_name <=", value, "isNoName");
            return (Criteria) this;
        }

        public Criteria andIsNoNameIn(List<Byte> values) {
            addCriterion("is_no_name in", values, "isNoName");
            return (Criteria) this;
        }

        public Criteria andIsNoNameNotIn(List<Byte> values) {
            addCriterion("is_no_name not in", values, "isNoName");
            return (Criteria) this;
        }

        public Criteria andIsNoNameBetween(Byte value1, Byte value2) {
            addCriterion("is_no_name between", value1, value2, "isNoName");
            return (Criteria) this;
        }

        public Criteria andIsNoNameNotBetween(Byte value1, Byte value2) {
            addCriterion("is_no_name not between", value1, value2, "isNoName");
            return (Criteria) this;
        }

        public Criteria andAutherNameIsNull() {
            addCriterion("auther_name is null");
            return (Criteria) this;
        }

        public Criteria andAutherNameIsNotNull() {
            addCriterion("auther_name is not null");
            return (Criteria) this;
        }

        public Criteria andAutherNameEqualTo(String value) {
            addCriterion("auther_name =", value, "autherName");
            return (Criteria) this;
        }

        public Criteria andAutherNameNotEqualTo(String value) {
            addCriterion("auther_name <>", value, "autherName");
            return (Criteria) this;
        }

        public Criteria andAutherNameGreaterThan(String value) {
            addCriterion("auther_name >", value, "autherName");
            return (Criteria) this;
        }

        public Criteria andAutherNameGreaterThanOrEqualTo(String value) {
            addCriterion("auther_name >=", value, "autherName");
            return (Criteria) this;
        }

        public Criteria andAutherNameLessThan(String value) {
            addCriterion("auther_name <", value, "autherName");
            return (Criteria) this;
        }

        public Criteria andAutherNameLessThanOrEqualTo(String value) {
            addCriterion("auther_name <=", value, "autherName");
            return (Criteria) this;
        }

        public Criteria andAutherNameLike(String value) {
            addCriterion("auther_name like", value, "autherName");
            return (Criteria) this;
        }

        public Criteria andAutherNameNotLike(String value) {
            addCriterion("auther_name not like", value, "autherName");
            return (Criteria) this;
        }

        public Criteria andAutherNameIn(List<String> values) {
            addCriterion("auther_name in", values, "autherName");
            return (Criteria) this;
        }

        public Criteria andAutherNameNotIn(List<String> values) {
            addCriterion("auther_name not in", values, "autherName");
            return (Criteria) this;
        }

        public Criteria andAutherNameBetween(String value1, String value2) {
            addCriterion("auther_name between", value1, value2, "autherName");
            return (Criteria) this;
        }

        public Criteria andAutherNameNotBetween(String value1, String value2) {
            addCriterion("auther_name not between", value1, value2, "autherName");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Boolean value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Boolean value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Boolean value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Boolean value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Boolean> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Boolean> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
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

        public Criteria andAuthorIdIsNull() {
            addCriterion("author_id is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIdIsNotNull() {
            addCriterion("author_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorIdEqualTo(Integer value) {
            addCriterion("author_id =", value, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdNotEqualTo(Integer value) {
            addCriterion("author_id <>", value, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdGreaterThan(Integer value) {
            addCriterion("author_id >", value, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("author_id >=", value, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdLessThan(Integer value) {
            addCriterion("author_id <", value, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdLessThanOrEqualTo(Integer value) {
            addCriterion("author_id <=", value, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdIn(List<Integer> values) {
            addCriterion("author_id in", values, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdNotIn(List<Integer> values) {
            addCriterion("author_id not in", values, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdBetween(Integer value1, Integer value2) {
            addCriterion("author_id between", value1, value2, "authorId");
            return (Criteria) this;
        }

        public Criteria andAuthorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("author_id not between", value1, value2, "authorId");
            return (Criteria) this;
        }

        public Criteria andCheckAdminIdIsNull() {
            addCriterion("check_admin_id is null");
            return (Criteria) this;
        }

        public Criteria andCheckAdminIdIsNotNull() {
            addCriterion("check_admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andCheckAdminIdEqualTo(Integer value) {
            addCriterion("check_admin_id =", value, "checkAdminId");
            return (Criteria) this;
        }

        public Criteria andCheckAdminIdNotEqualTo(Integer value) {
            addCriterion("check_admin_id <>", value, "checkAdminId");
            return (Criteria) this;
        }

        public Criteria andCheckAdminIdGreaterThan(Integer value) {
            addCriterion("check_admin_id >", value, "checkAdminId");
            return (Criteria) this;
        }

        public Criteria andCheckAdminIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_admin_id >=", value, "checkAdminId");
            return (Criteria) this;
        }

        public Criteria andCheckAdminIdLessThan(Integer value) {
            addCriterion("check_admin_id <", value, "checkAdminId");
            return (Criteria) this;
        }

        public Criteria andCheckAdminIdLessThanOrEqualTo(Integer value) {
            addCriterion("check_admin_id <=", value, "checkAdminId");
            return (Criteria) this;
        }

        public Criteria andCheckAdminIdIn(List<Integer> values) {
            addCriterion("check_admin_id in", values, "checkAdminId");
            return (Criteria) this;
        }

        public Criteria andCheckAdminIdNotIn(List<Integer> values) {
            addCriterion("check_admin_id not in", values, "checkAdminId");
            return (Criteria) this;
        }

        public Criteria andCheckAdminIdBetween(Integer value1, Integer value2) {
            addCriterion("check_admin_id between", value1, value2, "checkAdminId");
            return (Criteria) this;
        }

        public Criteria andCheckAdminIdNotBetween(Integer value1, Integer value2) {
            addCriterion("check_admin_id not between", value1, value2, "checkAdminId");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNull() {
            addCriterion("check_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("check_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("check_time =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("check_time <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("check_time >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("check_time >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("check_time <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("check_time <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("check_time in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("check_time not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("check_time between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("check_time not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNull() {
            addCriterion("question_id is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNotNull() {
            addCriterion("question_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdEqualTo(Integer value) {
            addCriterion("question_id =", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotEqualTo(Integer value) {
            addCriterion("question_id <>", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThan(Integer value) {
            addCriterion("question_id >", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("question_id >=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThan(Integer value) {
            addCriterion("question_id <", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThanOrEqualTo(Integer value) {
            addCriterion("question_id <=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIn(List<Integer> values) {
            addCriterion("question_id in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotIn(List<Integer> values) {
            addCriterion("question_id not in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdBetween(Integer value1, Integer value2) {
            addCriterion("question_id between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("question_id not between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionTitileIsNull() {
            addCriterion("question_titile is null");
            return (Criteria) this;
        }

        public Criteria andQuestionTitileIsNotNull() {
            addCriterion("question_titile is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionTitileEqualTo(String value) {
            addCriterion("question_titile =", value, "questionTitile");
            return (Criteria) this;
        }

        public Criteria andQuestionTitileNotEqualTo(String value) {
            addCriterion("question_titile <>", value, "questionTitile");
            return (Criteria) this;
        }

        public Criteria andQuestionTitileGreaterThan(String value) {
            addCriterion("question_titile >", value, "questionTitile");
            return (Criteria) this;
        }

        public Criteria andQuestionTitileGreaterThanOrEqualTo(String value) {
            addCriterion("question_titile >=", value, "questionTitile");
            return (Criteria) this;
        }

        public Criteria andQuestionTitileLessThan(String value) {
            addCriterion("question_titile <", value, "questionTitile");
            return (Criteria) this;
        }

        public Criteria andQuestionTitileLessThanOrEqualTo(String value) {
            addCriterion("question_titile <=", value, "questionTitile");
            return (Criteria) this;
        }

        public Criteria andQuestionTitileLike(String value) {
            addCriterion("question_titile like", value, "questionTitile");
            return (Criteria) this;
        }

        public Criteria andQuestionTitileNotLike(String value) {
            addCriterion("question_titile not like", value, "questionTitile");
            return (Criteria) this;
        }

        public Criteria andQuestionTitileIn(List<String> values) {
            addCriterion("question_titile in", values, "questionTitile");
            return (Criteria) this;
        }

        public Criteria andQuestionTitileNotIn(List<String> values) {
            addCriterion("question_titile not in", values, "questionTitile");
            return (Criteria) this;
        }

        public Criteria andQuestionTitileBetween(String value1, String value2) {
            addCriterion("question_titile between", value1, value2, "questionTitile");
            return (Criteria) this;
        }

        public Criteria andQuestionTitileNotBetween(String value1, String value2) {
            addCriterion("question_titile not between", value1, value2, "questionTitile");
            return (Criteria) this;
        }

        public Criteria andAuthorPictureIsNull() {
            addCriterion("author_picture is null");
            return (Criteria) this;
        }

        public Criteria andAuthorPictureIsNotNull() {
            addCriterion("author_picture is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorPictureEqualTo(String value) {
            addCriterion("author_picture =", value, "authorPicture");
            return (Criteria) this;
        }

        public Criteria andAuthorPictureNotEqualTo(String value) {
            addCriterion("author_picture <>", value, "authorPicture");
            return (Criteria) this;
        }

        public Criteria andAuthorPictureGreaterThan(String value) {
            addCriterion("author_picture >", value, "authorPicture");
            return (Criteria) this;
        }

        public Criteria andAuthorPictureGreaterThanOrEqualTo(String value) {
            addCriterion("author_picture >=", value, "authorPicture");
            return (Criteria) this;
        }

        public Criteria andAuthorPictureLessThan(String value) {
            addCriterion("author_picture <", value, "authorPicture");
            return (Criteria) this;
        }

        public Criteria andAuthorPictureLessThanOrEqualTo(String value) {
            addCriterion("author_picture <=", value, "authorPicture");
            return (Criteria) this;
        }

        public Criteria andAuthorPictureLike(String value) {
            addCriterion("author_picture like", value, "authorPicture");
            return (Criteria) this;
        }

        public Criteria andAuthorPictureNotLike(String value) {
            addCriterion("author_picture not like", value, "authorPicture");
            return (Criteria) this;
        }

        public Criteria andAuthorPictureIn(List<String> values) {
            addCriterion("author_picture in", values, "authorPicture");
            return (Criteria) this;
        }

        public Criteria andAuthorPictureNotIn(List<String> values) {
            addCriterion("author_picture not in", values, "authorPicture");
            return (Criteria) this;
        }

        public Criteria andAuthorPictureBetween(String value1, String value2) {
            addCriterion("author_picture between", value1, value2, "authorPicture");
            return (Criteria) this;
        }

        public Criteria andAuthorPictureNotBetween(String value1, String value2) {
            addCriterion("author_picture not between", value1, value2, "authorPicture");
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