package wang.haogui.yuanda.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticleExample() {
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

        public Criteria andArticleIdIsNull() {
            addCriterion("article_id is null");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNotNull() {
            addCriterion("article_id is not null");
            return (Criteria) this;
        }

        public Criteria andArticleIdEqualTo(Integer value) {
            addCriterion("article_id =", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotEqualTo(Integer value) {
            addCriterion("article_id <>", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThan(Integer value) {
            addCriterion("article_id >", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_id >=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThan(Integer value) {
            addCriterion("article_id <", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThanOrEqualTo(Integer value) {
            addCriterion("article_id <=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdIn(List<Integer> values) {
            addCriterion("article_id in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotIn(List<Integer> values) {
            addCriterion("article_id not in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdBetween(Integer value1, Integer value2) {
            addCriterion("article_id between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("article_id not between", value1, value2, "articleId");
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

        public Criteria andArticleTitleIsNull() {
            addCriterion("article_title is null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIsNotNull() {
            addCriterion("article_title is not null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleEqualTo(String value) {
            addCriterion("article_title =", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotEqualTo(String value) {
            addCriterion("article_title <>", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThan(String value) {
            addCriterion("article_title >", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThanOrEqualTo(String value) {
            addCriterion("article_title >=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThan(String value) {
            addCriterion("article_title <", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThanOrEqualTo(String value) {
            addCriterion("article_title <=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLike(String value) {
            addCriterion("article_title like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotLike(String value) {
            addCriterion("article_title not like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIn(List<String> values) {
            addCriterion("article_title in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotIn(List<String> values) {
            addCriterion("article_title not in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleBetween(String value1, String value2) {
            addCriterion("article_title between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotBetween(String value1, String value2) {
            addCriterion("article_title not between", value1, value2, "articleTitle");
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

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
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

        public Criteria andHotNumberIsNull() {
            addCriterion("hot_number is null");
            return (Criteria) this;
        }

        public Criteria andHotNumberIsNotNull() {
            addCriterion("hot_number is not null");
            return (Criteria) this;
        }

        public Criteria andHotNumberEqualTo(Integer value) {
            addCriterion("hot_number =", value, "hotNumber");
            return (Criteria) this;
        }

        public Criteria andHotNumberNotEqualTo(Integer value) {
            addCriterion("hot_number <>", value, "hotNumber");
            return (Criteria) this;
        }

        public Criteria andHotNumberGreaterThan(Integer value) {
            addCriterion("hot_number >", value, "hotNumber");
            return (Criteria) this;
        }

        public Criteria andHotNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("hot_number >=", value, "hotNumber");
            return (Criteria) this;
        }

        public Criteria andHotNumberLessThan(Integer value) {
            addCriterion("hot_number <", value, "hotNumber");
            return (Criteria) this;
        }

        public Criteria andHotNumberLessThanOrEqualTo(Integer value) {
            addCriterion("hot_number <=", value, "hotNumber");
            return (Criteria) this;
        }

        public Criteria andHotNumberIn(List<Integer> values) {
            addCriterion("hot_number in", values, "hotNumber");
            return (Criteria) this;
        }

        public Criteria andHotNumberNotIn(List<Integer> values) {
            addCriterion("hot_number not in", values, "hotNumber");
            return (Criteria) this;
        }

        public Criteria andHotNumberBetween(Integer value1, Integer value2) {
            addCriterion("hot_number between", value1, value2, "hotNumber");
            return (Criteria) this;
        }

        public Criteria andHotNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("hot_number not between", value1, value2, "hotNumber");
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

        public Criteria andRecommendNumberIsNull() {
            addCriterion("recommend_number is null");
            return (Criteria) this;
        }

        public Criteria andRecommendNumberIsNotNull() {
            addCriterion("recommend_number is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendNumberEqualTo(Integer value) {
            addCriterion("recommend_number =", value, "recommendNumber");
            return (Criteria) this;
        }

        public Criteria andRecommendNumberNotEqualTo(Integer value) {
            addCriterion("recommend_number <>", value, "recommendNumber");
            return (Criteria) this;
        }

        public Criteria andRecommendNumberGreaterThan(Integer value) {
            addCriterion("recommend_number >", value, "recommendNumber");
            return (Criteria) this;
        }

        public Criteria andRecommendNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("recommend_number >=", value, "recommendNumber");
            return (Criteria) this;
        }

        public Criteria andRecommendNumberLessThan(Integer value) {
            addCriterion("recommend_number <", value, "recommendNumber");
            return (Criteria) this;
        }

        public Criteria andRecommendNumberLessThanOrEqualTo(Integer value) {
            addCriterion("recommend_number <=", value, "recommendNumber");
            return (Criteria) this;
        }

        public Criteria andRecommendNumberIn(List<Integer> values) {
            addCriterion("recommend_number in", values, "recommendNumber");
            return (Criteria) this;
        }

        public Criteria andRecommendNumberNotIn(List<Integer> values) {
            addCriterion("recommend_number not in", values, "recommendNumber");
            return (Criteria) this;
        }

        public Criteria andRecommendNumberBetween(Integer value1, Integer value2) {
            addCriterion("recommend_number between", value1, value2, "recommendNumber");
            return (Criteria) this;
        }

        public Criteria andRecommendNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("recommend_number not between", value1, value2, "recommendNumber");
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

        public Criteria andPictureSrcIsNull() {
            addCriterion("picture_src is null");
            return (Criteria) this;
        }

        public Criteria andPictureSrcIsNotNull() {
            addCriterion("picture_src is not null");
            return (Criteria) this;
        }

        public Criteria andPictureSrcEqualTo(String value) {
            addCriterion("picture_src =", value, "pictureSrc");
            return (Criteria) this;
        }

        public Criteria andPictureSrcNotEqualTo(String value) {
            addCriterion("picture_src <>", value, "pictureSrc");
            return (Criteria) this;
        }

        public Criteria andPictureSrcGreaterThan(String value) {
            addCriterion("picture_src >", value, "pictureSrc");
            return (Criteria) this;
        }

        public Criteria andPictureSrcGreaterThanOrEqualTo(String value) {
            addCriterion("picture_src >=", value, "pictureSrc");
            return (Criteria) this;
        }

        public Criteria andPictureSrcLessThan(String value) {
            addCriterion("picture_src <", value, "pictureSrc");
            return (Criteria) this;
        }

        public Criteria andPictureSrcLessThanOrEqualTo(String value) {
            addCriterion("picture_src <=", value, "pictureSrc");
            return (Criteria) this;
        }

        public Criteria andPictureSrcLike(String value) {
            addCriterion("picture_src like", value, "pictureSrc");
            return (Criteria) this;
        }

        public Criteria andPictureSrcNotLike(String value) {
            addCriterion("picture_src not like", value, "pictureSrc");
            return (Criteria) this;
        }

        public Criteria andPictureSrcIn(List<String> values) {
            addCriterion("picture_src in", values, "pictureSrc");
            return (Criteria) this;
        }

        public Criteria andPictureSrcNotIn(List<String> values) {
            addCriterion("picture_src not in", values, "pictureSrc");
            return (Criteria) this;
        }

        public Criteria andPictureSrcBetween(String value1, String value2) {
            addCriterion("picture_src between", value1, value2, "pictureSrc");
            return (Criteria) this;
        }

        public Criteria andPictureSrcNotBetween(String value1, String value2) {
            addCriterion("picture_src not between", value1, value2, "pictureSrc");
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