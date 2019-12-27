package wang.haogui.yuanda.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class LabelConnection implements Serializable {


    public LabelConnection() {
    }

    public LabelConnection(Integer labelId, Integer connectId, Boolean connectType, Boolean isDeleted) {
        this.labelId = labelId;
        this.connectId = connectId;
        this.connectType = connectType;
        this.isDeleted = isDeleted;
    }

    @ApiModelProperty(value = "本表id")
    private Integer id;

    @ApiModelProperty(value = "标签的id")
    private Integer labelId;

    @ApiModelProperty(value = "关联的文章或问题的id")
    private Integer connectId;

    @ApiModelProperty(value = "0表示文章，1表示问题")
    private Boolean connectType;

    private String remark;

    private String remark2;

    @ApiModelProperty(value = " 0表示存在，1表示删除")
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getConnectId() {
        return connectId;
    }

    public void setConnectId(Integer connectId) {
        this.connectId = connectId;
    }

    public Boolean getConnectType() {
        return connectType;
    }

    public void setConnectType(Boolean connectType) {
        this.connectType = connectType;
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
        sb.append(", id=").append(id);
        sb.append(", labelId=").append(labelId);
        sb.append(", connectId=").append(connectId);
        sb.append(", connectType=").append(connectType);
        sb.append(", remark=").append(remark);
        sb.append(", remark2=").append(remark2);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}