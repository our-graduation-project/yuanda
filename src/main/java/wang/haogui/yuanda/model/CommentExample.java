package wang.haogui.yuanda.model;

import java.util.ArrayList;
import java.util.List;

public class CommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommentExample() {
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

        public Criteria andCommentIdIsNull() {
            addCriterion("comment_id is null");
            return (Criteria) this;
        }

        public Criteria andCommentIdIsNotNull() {
            addCriterion("comment_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommentIdEqualTo(Integer value) {
            addCriterion("comment_id =", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotEqualTo(Integer value) {
            addCriterion("comment_id <>", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThan(Integer value) {
            addCriterion("comment_id >", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_id >=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThan(Integer value) {
            addCriterion("comment_id <", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThanOrEqualTo(Integer value) {
            addCriterion("comment_id <=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdIn(List<Integer> values) {
            addCriterion("comment_id in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotIn(List<Integer> values) {
            addCriterion("comment_id not in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdBetween(Integer value1, Integer value2) {
            addCriterion("comment_id between", value1, value2, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_id not between", value1, value2, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentContentIsNull() {
            addCriterion("comment_content is null");
            return (Criteria) this;
        }

        public Criteria andCommentContentIsNotNull() {
            addCriterion("comment_content is not null");
            return (Criteria) this;
        }

        public Criteria andCommentContentEqualTo(String value) {
            addCriterion("comment_content =", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentNotEqualTo(String value) {
            addCriterion("comment_content <>", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentGreaterThan(String value) {
            addCriterion("comment_content >", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentGreaterThanOrEqualTo(String value) {
            addCriterion("comment_content >=", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentLessThan(String value) {
            addCriterion("comment_content <", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentLessThanOrEqualTo(String value) {
            addCriterion("comment_content <=", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentLike(String value) {
            addCriterion("comment_content like", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentNotLike(String value) {
            addCriterion("comment_content not like", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentIn(List<String> values) {
            addCriterion("comment_content in", values, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentNotIn(List<String> values) {
            addCriterion("comment_content not in", values, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentBetween(String value1, String value2) {
            addCriterion("comment_content between", value1, value2, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentNotBetween(String value1, String value2) {
            addCriterion("comment_content not between", value1, value2, "commentContent");
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

        public Criteria andCommentTargetIdIsNull() {
            addCriterion("comment_target_id is null");
            return (Criteria) this;
        }

        public Criteria andCommentTargetIdIsNotNull() {
            addCriterion("comment_target_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommentTargetIdEqualTo(Integer value) {
            addCriterion("comment_target_id =", value, "commentTargetId");
            return (Criteria) this;
        }

        public Criteria andCommentTargetIdNotEqualTo(Integer value) {
            addCriterion("comment_target_id <>", value, "commentTargetId");
            return (Criteria) this;
        }

        public Criteria andCommentTargetIdGreaterThan(Integer value) {
            addCriterion("comment_target_id >", value, "commentTargetId");
            return (Criteria) this;
        }

        public Criteria andCommentTargetIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_target_id >=", value, "commentTargetId");
            return (Criteria) this;
        }

        public Criteria andCommentTargetIdLessThan(Integer value) {
            addCriterion("comment_target_id <", value, "commentTargetId");
            return (Criteria) this;
        }

        public Criteria andCommentTargetIdLessThanOrEqualTo(Integer value) {
            addCriterion("comment_target_id <=", value, "commentTargetId");
            return (Criteria) this;
        }

        public Criteria andCommentTargetIdIn(List<Integer> values) {
            addCriterion("comment_target_id in", values, "commentTargetId");
            return (Criteria) this;
        }

        public Criteria andCommentTargetIdNotIn(List<Integer> values) {
            addCriterion("comment_target_id not in", values, "commentTargetId");
            return (Criteria) this;
        }

        public Criteria andCommentTargetIdBetween(Integer value1, Integer value2) {
            addCriterion("comment_target_id between", value1, value2, "commentTargetId");
            return (Criteria) this;
        }

        public Criteria andCommentTargetIdNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_target_id not between", value1, value2, "commentTargetId");
            return (Criteria) this;
        }

        public Criteria andCommentResourceIdIsNull() {
            addCriterion("comment_resource_id is null");
            return (Criteria) this;
        }

        public Criteria andCommentResourceIdIsNotNull() {
            addCriterion("comment_resource_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommentResourceIdEqualTo(Integer value) {
            addCriterion("comment_resource_id =", value, "commentResourceId");
            return (Criteria) this;
        }

        public Criteria andCommentResourceIdNotEqualTo(Integer value) {
            addCriterion("comment_resource_id <>", value, "commentResourceId");
            return (Criteria) this;
        }

        public Criteria andCommentResourceIdGreaterThan(Integer value) {
            addCriterion("comment_resource_id >", value, "commentResourceId");
            return (Criteria) this;
        }

        public Criteria andCommentResourceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_resource_id >=", value, "commentResourceId");
            return (Criteria) this;
        }

        public Criteria andCommentResourceIdLessThan(Integer value) {
            addCriterion("comment_resource_id <", value, "commentResourceId");
            return (Criteria) this;
        }

        public Criteria andCommentResourceIdLessThanOrEqualTo(Integer value) {
            addCriterion("comment_resource_id <=", value, "commentResourceId");
            return (Criteria) this;
        }

        public Criteria andCommentResourceIdIn(List<Integer> values) {
            addCriterion("comment_resource_id in", values, "commentResourceId");
            return (Criteria) this;
        }

        public Criteria andCommentResourceIdNotIn(List<Integer> values) {
            addCriterion("comment_resource_id not in", values, "commentResourceId");
            return (Criteria) this;
        }

        public Criteria andCommentResourceIdBetween(Integer value1, Integer value2) {
            addCriterion("comment_resource_id between", value1, value2, "commentResourceId");
            return (Criteria) this;
        }

        public Criteria andCommentResourceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_resource_id not between", value1, value2, "commentResourceId");
            return (Criteria) this;
        }

        public Criteria andCommentResourceNameIsNull() {
            addCriterion("comment_resource_name is null");
            return (Criteria) this;
        }

        public Criteria andCommentResourceNameIsNotNull() {
            addCriterion("comment_resource_name is not null");
            return (Criteria) this;
        }

        public Criteria andCommentResourceNameEqualTo(String value) {
            addCriterion("comment_resource_name =", value, "commentResourceName");
            return (Criteria) this;
        }

        public Criteria andCommentResourceNameNotEqualTo(String value) {
            addCriterion("comment_resource_name <>", value, "commentResourceName");
            return (Criteria) this;
        }

        public Criteria andCommentResourceNameGreaterThan(String value) {
            addCriterion("comment_resource_name >", value, "commentResourceName");
            return (Criteria) this;
        }

        public Criteria andCommentResourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("comment_resource_name >=", value, "commentResourceName");
            return (Criteria) this;
        }

        public Criteria andCommentResourceNameLessThan(String value) {
            addCriterion("comment_resource_name <", value, "commentResourceName");
            return (Criteria) this;
        }

        public Criteria andCommentResourceNameLessThanOrEqualTo(String value) {
            addCriterion("comment_resource_name <=", value, "commentResourceName");
            return (Criteria) this;
        }

        public Criteria andCommentResourceNameLike(String value) {
            addCriterion("comment_resource_name like", value, "commentResourceName");
            return (Criteria) this;
        }

        public Criteria andCommentResourceNameNotLike(String value) {
            addCriterion("comment_resource_name not like", value, "commentResourceName");
            return (Criteria) this;
        }

        public Criteria andCommentResourceNameIn(List<String> values) {
            addCriterion("comment_resource_name in", values, "commentResourceName");
            return (Criteria) this;
        }

        public Criteria andCommentResourceNameNotIn(List<String> values) {
            addCriterion("comment_resource_name not in", values, "commentResourceName");
            return (Criteria) this;
        }

        public Criteria andCommentResourceNameBetween(String value1, String value2) {
            addCriterion("comment_resource_name between", value1, value2, "commentResourceName");
            return (Criteria) this;
        }

        public Criteria andCommentResourceNameNotBetween(String value1, String value2) {
            addCriterion("comment_resource_name not between", value1, value2, "commentResourceName");
            return (Criteria) this;
        }

        public Criteria andCommentResourcePictureIsNull() {
            addCriterion("comment_resource_picture is null");
            return (Criteria) this;
        }

        public Criteria andCommentResourcePictureIsNotNull() {
            addCriterion("comment_resource_picture is not null");
            return (Criteria) this;
        }

        public Criteria andCommentResourcePictureEqualTo(String value) {
            addCriterion("comment_resource_picture =", value, "commentResourcePicture");
            return (Criteria) this;
        }

        public Criteria andCommentResourcePictureNotEqualTo(String value) {
            addCriterion("comment_resource_picture <>", value, "commentResourcePicture");
            return (Criteria) this;
        }

        public Criteria andCommentResourcePictureGreaterThan(String value) {
            addCriterion("comment_resource_picture >", value, "commentResourcePicture");
            return (Criteria) this;
        }

        public Criteria andCommentResourcePictureGreaterThanOrEqualTo(String value) {
            addCriterion("comment_resource_picture >=", value, "commentResourcePicture");
            return (Criteria) this;
        }

        public Criteria andCommentResourcePictureLessThan(String value) {
            addCriterion("comment_resource_picture <", value, "commentResourcePicture");
            return (Criteria) this;
        }

        public Criteria andCommentResourcePictureLessThanOrEqualTo(String value) {
            addCriterion("comment_resource_picture <=", value, "commentResourcePicture");
            return (Criteria) this;
        }

        public Criteria andCommentResourcePictureLike(String value) {
            addCriterion("comment_resource_picture like", value, "commentResourcePicture");
            return (Criteria) this;
        }

        public Criteria andCommentResourcePictureNotLike(String value) {
            addCriterion("comment_resource_picture not like", value, "commentResourcePicture");
            return (Criteria) this;
        }

        public Criteria andCommentResourcePictureIn(List<String> values) {
            addCriterion("comment_resource_picture in", values, "commentResourcePicture");
            return (Criteria) this;
        }

        public Criteria andCommentResourcePictureNotIn(List<String> values) {
            addCriterion("comment_resource_picture not in", values, "commentResourcePicture");
            return (Criteria) this;
        }

        public Criteria andCommentResourcePictureBetween(String value1, String value2) {
            addCriterion("comment_resource_picture between", value1, value2, "commentResourcePicture");
            return (Criteria) this;
        }

        public Criteria andCommentResourcePictureNotBetween(String value1, String value2) {
            addCriterion("comment_resource_picture not between", value1, value2, "commentResourcePicture");
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

        public Criteria andCommentTypeIsNull() {
            addCriterion("comment_type is null");
            return (Criteria) this;
        }

        public Criteria andCommentTypeIsNotNull() {
            addCriterion("comment_type is not null");
            return (Criteria) this;
        }

        public Criteria andCommentTypeEqualTo(Byte value) {
            addCriterion("comment_type =", value, "commentType");
            return (Criteria) this;
        }

        public Criteria andCommentTypeNotEqualTo(Byte value) {
            addCriterion("comment_type <>", value, "commentType");
            return (Criteria) this;
        }

        public Criteria andCommentTypeGreaterThan(Byte value) {
            addCriterion("comment_type >", value, "commentType");
            return (Criteria) this;
        }

        public Criteria andCommentTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("comment_type >=", value, "commentType");
            return (Criteria) this;
        }

        public Criteria andCommentTypeLessThan(Byte value) {
            addCriterion("comment_type <", value, "commentType");
            return (Criteria) this;
        }

        public Criteria andCommentTypeLessThanOrEqualTo(Byte value) {
            addCriterion("comment_type <=", value, "commentType");
            return (Criteria) this;
        }

        public Criteria andCommentTypeIn(List<Byte> values) {
            addCriterion("comment_type in", values, "commentType");
            return (Criteria) this;
        }

        public Criteria andCommentTypeNotIn(List<Byte> values) {
            addCriterion("comment_type not in", values, "commentType");
            return (Criteria) this;
        }

        public Criteria andCommentTypeBetween(Byte value1, Byte value2) {
            addCriterion("comment_type between", value1, value2, "commentType");
            return (Criteria) this;
        }

        public Criteria andCommentTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("comment_type not between", value1, value2, "commentType");
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