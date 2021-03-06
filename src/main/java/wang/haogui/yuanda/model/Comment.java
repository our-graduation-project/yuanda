package wang.haogui.yuanda.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    @ApiModelProperty(value = "评论id")
    private Integer commentId;

    @ApiModelProperty(value = "评论内容")
    private String commentContent;

    @ApiModelProperty(value = "评论的这条评论的数目")
    private Integer commentNumber;

    @ApiModelProperty(value = "评论的目标id")
    private Integer commentTargetId;

    @ApiModelProperty(value = "评论者的id")
    private Integer commentResourceId;

    @ApiModelProperty(value = "评论者的名字")
    private String commentResourceName;

    @ApiModelProperty(value = "评论者的头像")
    private String commentResourcePicture;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "父级id")
    private Integer parentId;

    @ApiModelProperty(value = "评论对象的类型0表示文章，1表示回答，2表示评论")
    private Byte commentType;

    @ApiModelProperty(value = " 0表示存在，1表示删除")
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public void increaseCommentNumber(){
        this.commentNumber++;
    }

    public void decreaseCommentNumber(){
        this.commentNumber--;
    }

    public Integer getCommentTargetId() {
        return commentTargetId;
    }

    public void setCommentTargetId(Integer commentTargetId) {
        this.commentTargetId = commentTargetId;
    }

    public Integer getCommentResourceId() {
        return commentResourceId;
    }

    public void setCommentResourceId(Integer commentResourceId) {
        this.commentResourceId = commentResourceId;
    }

    public String getCommentResourceName() {
        return commentResourceName;
    }

    public void setCommentResourceName(String commentResourceName) {
        this.commentResourceName = commentResourceName;
    }

    public String getCommentResourcePicture() {
        return commentResourcePicture;
    }

    public void setCommentResourcePicture(String commentResourcePicture) {
        this.commentResourcePicture = commentResourcePicture;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Byte getCommentType() {
        return commentType;
    }

    public void setCommentType(Byte commentType) {
        this.commentType = commentType;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commentId=").append(commentId);
        sb.append(", commentContent=").append(commentContent);
        sb.append(", commentNumber=").append(commentNumber);
        sb.append(", commentTargetId=").append(commentTargetId);
        sb.append(", commentResourceId=").append(commentResourceId);
        sb.append(", commentResourceName=").append(commentResourceName);
        sb.append(", commentResourcePicture=").append(commentResourcePicture);
        sb.append(", createDate=").append(createDate);
        sb.append(", parentId=").append(parentId);
        sb.append(", commentType=").append(commentType);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}