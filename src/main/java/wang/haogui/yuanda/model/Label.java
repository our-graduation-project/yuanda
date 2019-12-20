package wang.haogui.yuanda.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Label implements Serializable {
    @ApiModelProperty(value = "主键")
    private Integer labelId;

    @ApiModelProperty(value = "标签名称")
    private String labelName;

    private String remark;

    private String remark2;

    private static final long serialVersionUID = 1L;

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
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
        sb.append(", labelId=").append(labelId);
        sb.append(", labelName=").append(labelName);
        sb.append(", remark=").append(remark);
        sb.append(", remark2=").append(remark2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}