package wang.haogui.yuanda.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Favorites implements Serializable {
    @ApiModelProperty(value = "收藏夹id")
    private Byte favoritesId;

    @ApiModelProperty(value = "收藏夹名")
    private String favoritesName;

    @ApiModelProperty(value = "收藏夹里面收藏的数量")
    private Integer favoritesNumber;

    @ApiModelProperty(value = "备用字段1")
    private String remark;

    @ApiModelProperty(value = "备用字段2")
    private String remark2;

    @ApiModelProperty(value = "拥有者id")
    private Integer userId;

    private static final long serialVersionUID = 1L;

    public Byte getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(Byte favoritesId) {
        this.favoritesId = favoritesId;
    }

    public String getFavoritesName() {
        return favoritesName;
    }

    public void setFavoritesName(String favoritesName) {
        this.favoritesName = favoritesName;
    }

    public Integer getFavoritesNumber() {
        return favoritesNumber;
    }

    public void setFavoritesNumber(Integer favoritesNumber) {
        this.favoritesNumber = favoritesNumber;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", favoritesId=").append(favoritesId);
        sb.append(", favoritesName=").append(favoritesName);
        sb.append(", favoritesNumber=").append(favoritesNumber);
        sb.append(", remark=").append(remark);
        sb.append(", remark2=").append(remark2);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}