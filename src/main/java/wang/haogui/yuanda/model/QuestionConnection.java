package wang.haogui.yuanda.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class QuestionConnection implements Serializable {
    @ApiModelProperty(value = "问题联系表id")
    private Integer id;

    @ApiModelProperty(value = "问题id")
    private Integer quesionId;

    @ApiModelProperty(value = "回答id")
    private Integer answerId;

    @ApiModelProperty(value = "评论数")
    private Integer commentNumber;

    @ApiModelProperty(value = "赞同数")
    private Integer agreementNumber;

    @ApiModelProperty(value = "反对数")
    private Integer disagreementNumber;

    @ApiModelProperty(value = "浏览数")
    private Integer clickNumber;

    @ApiModelProperty(value = "备用字段1")
    private String remark;

    @ApiModelProperty(value = "备用字段2")
    private String remark2;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuesionId() {
        return quesionId;
    }

    public void setQuesionId(Integer quesionId) {
        this.quesionId = quesionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Integer getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(Integer agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public Integer getDisagreementNumber() {
        return disagreementNumber;
    }

    public void setDisagreementNumber(Integer disagreementNumber) {
        this.disagreementNumber = disagreementNumber;
    }

    public Integer getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(Integer clickNumber) {
        this.clickNumber = clickNumber;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", quesionId=").append(quesionId);
        sb.append(", answerId=").append(answerId);
        sb.append(", commentNumber=").append(commentNumber);
        sb.append(", agreementNumber=").append(agreementNumber);
        sb.append(", disagreementNumber=").append(disagreementNumber);
        sb.append(", clickNumber=").append(clickNumber);
        sb.append(", remark=").append(remark);
        sb.append(", remark2=").append(remark2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}