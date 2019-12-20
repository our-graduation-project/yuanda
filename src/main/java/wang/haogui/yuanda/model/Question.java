package wang.haogui.yuanda.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class Question implements Serializable {
    @ApiModelProperty(value = "问题id")
    private Integer questionId;

    @ApiModelProperty(value = "问题的标题")
    private String questionTitle;

    @ApiModelProperty(value = "问题详情描述")
    private String questionDescript;

    @ApiModelProperty(value = "回答的数量")
    private Integer answerNumber;

    @ApiModelProperty(value = "关注数")
    private Integer followNumber;

    @ApiModelProperty(value = "热度")
    private Integer hot;

    @ApiModelProperty(value = "是否通过审核，0表示未通过审核，1表示通过审核,2表示未通过审核")
    private Byte checkStatus;

    @ApiModelProperty(value = "是否删除，0表示未删除")
    private Byte isDeleted;

    @ApiModelProperty(value = "备用字段1")
    private String remark;

    @ApiModelProperty(value = "备用字段2")
    private String remark2;

    @ApiModelProperty(value = "发出提问的人的名字")
    private String author;

    @ApiModelProperty(value = "浏览次数")
    private Integer clickNumber;

    @ApiModelProperty(value = "发表问题的时间")
    private Date createTime;

    @ApiModelProperty(value = "作者id")
    private Integer authorId;

    private static final long serialVersionUID = 1L;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionDescript() {
        return questionDescript;
    }

    public void setQuestionDescript(String questionDescript) {
        this.questionDescript = questionDescript;
    }

    public Integer getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(Integer answerNumber) {
        this.answerNumber = answerNumber;
    }

    public Integer getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(Integer followNumber) {
        this.followNumber = followNumber;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Byte getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Byte checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(Integer clickNumber) {
        this.clickNumber = clickNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", questionId=").append(questionId);
        sb.append(", questionTitle=").append(questionTitle);
        sb.append(", questionDescript=").append(questionDescript);
        sb.append(", answerNumber=").append(answerNumber);
        sb.append(", followNumber=").append(followNumber);
        sb.append(", hot=").append(hot);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", remark=").append(remark);
        sb.append(", remark2=").append(remark2);
        sb.append(", author=").append(author);
        sb.append(", clickNumber=").append(clickNumber);
        sb.append(", createTime=").append(createTime);
        sb.append(", authorId=").append(authorId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}