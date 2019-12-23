package wang.haogui.yuanda.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class Answer implements Serializable {
    @ApiModelProperty(value = "回答id")
    private Integer answerId;

    @ApiModelProperty(value = "同意数")
    private Integer agreeNumber;

    @ApiModelProperty(value = "不同意数")
    private Integer disagreeNumber;

    @ApiModelProperty(value = "是否通过审核，0表示没有审核，1表示通过审核,2表示审核不通过")
    private Byte checkStatus;

    @ApiModelProperty(value = "评论数目")
    private Integer commentNumber;

    @ApiModelProperty(value = "回答时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "浏览次数")
    private Integer clickNumber;

    @ApiModelProperty(value = "是否匿名，0表示不匿名，1表示匿名")
    private Byte isNoName;

    @ApiModelProperty(value = "回答问题的人的名字")
    private String autherName;

    @ApiModelProperty(value = "是否被删除，0表示未删除，1表示删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "备用字段2")
    private String remark2;

    @ApiModelProperty(value = "备用字段1")
    private String remark;

    @ApiModelProperty(value = "回答者id")
    private Integer authorId;

    @ApiModelProperty(value = "审核人的id")
    private Integer checkAdminId;

    @ApiModelProperty(value = "审核时间")
    private Date checkTime;

    @ApiModelProperty(value = "问题的id")
    private Integer questionId;

    @ApiModelProperty(value = "问题标题")
    private String questionTitile;

    @ApiModelProperty(value = "作者头像")
    private String authorPicture;

    @ApiModelProperty(value = "回答内容")
    private String answerContent;

    private static final long serialVersionUID = 1L;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getAgreeNumber() {
        return agreeNumber;
    }

    public void setAgreeNumber(Integer agreeNumber) {
        this.agreeNumber = agreeNumber;
    }

    public Integer getDisagreeNumber() {
        return disagreeNumber;
    }

    public void setDisagreeNumber(Integer disagreeNumber) {
        this.disagreeNumber = disagreeNumber;
    }

    public Byte getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Byte checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(Integer clickNumber) {
        this.clickNumber = clickNumber;
    }

    public Byte getIsNoName() {
        return isNoName;
    }

    public void setIsNoName(Byte isNoName) {
        this.isNoName = isNoName;
    }

    public String getAutherName() {
        return autherName;
    }

    public void setAutherName(String autherName) {
        this.autherName = autherName;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getCheckAdminId() {
        return checkAdminId;
    }

    public void setCheckAdminId(Integer checkAdminId) {
        this.checkAdminId = checkAdminId;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitile() {
        return questionTitile;
    }

    public void setQuestionTitile(String questionTitile) {
        this.questionTitile = questionTitile;
    }

    public String getAuthorPicture() {
        return authorPicture;
    }

    public void setAuthorPicture(String authorPicture) {
        this.authorPicture = authorPicture;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", answerId=").append(answerId);
        sb.append(", agreeNumber=").append(agreeNumber);
        sb.append(", disagreeNumber=").append(disagreeNumber);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", commentNumber=").append(commentNumber);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", clickNumber=").append(clickNumber);
        sb.append(", isNoName=").append(isNoName);
        sb.append(", autherName=").append(autherName);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", remark2=").append(remark2);
        sb.append(", remark=").append(remark);
        sb.append(", authorId=").append(authorId);
        sb.append(", checkAdminId=").append(checkAdminId);
        sb.append(", checkTime=").append(checkTime);
        sb.append(", questionId=").append(questionId);
        sb.append(", questionTitile=").append(questionTitile);
        sb.append(", authorPicture=").append(authorPicture);
        sb.append(", answerContent=").append(answerContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}