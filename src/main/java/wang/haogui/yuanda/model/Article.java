package wang.haogui.yuanda.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    @ApiModelProperty(value = "文章id")
    private Integer articleId;

    @ApiModelProperty(value = "审核时间")
    private Date checkTime;

    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "是否审核，0表示未审核，1表示审核通过,2表示审核未通过")
    private Byte checkStatus;

    @ApiModelProperty(value = "浏览次数")
    private Integer clickNumber;

    @ApiModelProperty(value = "热度")
    private Integer hotNumber;

    @ApiModelProperty(value = "点赞数")
    private Integer agreementNumber;

    @ApiModelProperty(value = "不赞同数")
    private Integer disagreementNumber;

    @ApiModelProperty(value = "评论数")
    private Integer commentNumber;

    @ApiModelProperty(value = "推荐度")
    private Integer recommendNumber;

    @ApiModelProperty(value = "备用字段")
    private String remark;

    @ApiModelProperty(value = "备用字段2")
    private String remark2;

    @ApiModelProperty(value = "审核人的id")
    private Integer checkAdminId;

    @ApiModelProperty(value = "图片路径")
    private String pictureSrc;

    @ApiModelProperty(value = " 是否删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "文章内容")
    private String articleContent;

    private static final long serialVersionUID = 1L;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Byte getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Byte checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(Integer clickNumber) {
        this.clickNumber = clickNumber;
    }

    public Integer getHotNumber() {
        return hotNumber;
    }

    public void setHotNumber(Integer hotNumber) {
        this.hotNumber = hotNumber;
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

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Integer getRecommendNumber() {
        return recommendNumber;
    }

    public void setRecommendNumber(Integer recommendNumber) {
        this.recommendNumber = recommendNumber;
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

    public Integer getCheckAdminId() {
        return checkAdminId;
    }

    public void setCheckAdminId(Integer checkAdminId) {
        this.checkAdminId = checkAdminId;
    }

    public String getPictureSrc() {
        return pictureSrc;
    }

    public void setPictureSrc(String pictureSrc) {
        this.pictureSrc = pictureSrc;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", checkTime=").append(checkTime);
        sb.append(", articleTitle=").append(articleTitle);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", author=").append(author);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", clickNumber=").append(clickNumber);
        sb.append(", hotNumber=").append(hotNumber);
        sb.append(", agreementNumber=").append(agreementNumber);
        sb.append(", disagreementNumber=").append(disagreementNumber);
        sb.append(", commentNumber=").append(commentNumber);
        sb.append(", recommendNumber=").append(recommendNumber);
        sb.append(", remark=").append(remark);
        sb.append(", remark2=").append(remark2);
        sb.append(", checkAdminId=").append(checkAdminId);
        sb.append(", pictureSrc=").append(pictureSrc);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", articleContent=").append(articleContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}