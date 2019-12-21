package wang.haogui.yuanda.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Message implements Serializable {
    @ApiModelProperty(value = "消息id")
    private Integer messageId;

    private String messageContent;

    @ApiModelProperty(value = "发送者的id，0表示系统信息，即系统发送的通知,其余为用户之间发送的消息")
    private Integer messageResourceId;

    @ApiModelProperty(value = "接收者id")
    private Integer messageTarget;

    @ApiModelProperty(value = "消息类型0表示系统消息，1表示用户交流信息")
    private Byte messageType;

    @ApiModelProperty(value = "是否已阅读，0表示没有阅读，1表示阅读")
    private Byte isRead;

    @ApiModelProperty(value = "备用字段1")
    private String remark;

    @ApiModelProperty(value = "备用字段2")
    private String remark2;

    @ApiModelProperty(value = " 0表示存在，1表示删除")
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getMessageResourceId() {
        return messageResourceId;
    }

    public void setMessageResourceId(Integer messageResourceId) {
        this.messageResourceId = messageResourceId;
    }

    public Integer getMessageTarget() {
        return messageTarget;
    }

    public void setMessageTarget(Integer messageTarget) {
        this.messageTarget = messageTarget;
    }

    public Byte getMessageType() {
        return messageType;
    }

    public void setMessageType(Byte messageType) {
        this.messageType = messageType;
    }

    public Byte getIsRead() {
        return isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
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
        sb.append(", messageId=").append(messageId);
        sb.append(", messageContent=").append(messageContent);
        sb.append(", messageResourceId=").append(messageResourceId);
        sb.append(", messageTarget=").append(messageTarget);
        sb.append(", messageType=").append(messageType);
        sb.append(", isRead=").append(isRead);
        sb.append(", remark=").append(remark);
        sb.append(", remark2=").append(remark2);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}