package wang.haogui.yuanda.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class Banner implements Serializable {
    @ApiModelProperty(value = "id")
    private Integer bannerId;

    @ApiModelProperty(value = "图片路径")
    private String bannerPic;

    @ApiModelProperty(value = "广告内容")
    private String bannerContent;

    @ApiModelProperty(value = "广告标题")
    private String bannerTitle;

    @ApiModelProperty(value = "生效时间")
    private Date bannerCreateTime;

    @ApiModelProperty(value = "失效时间")
    private Date bannerFailureTime;

    @ApiModelProperty(value = "0表示存在，1表示删除")
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerPic() {
        return bannerPic;
    }

    public void setBannerPic(String bannerPic) {
        this.bannerPic = bannerPic;
    }

    public String getBannerContent() {
        return bannerContent;
    }

    public void setBannerContent(String bannerContent) {
        this.bannerContent = bannerContent;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    public Date getBannerCreateTime() {
        return bannerCreateTime;
    }

    public void setBannerCreateTime(Date bannerCreateTime) {
        this.bannerCreateTime = bannerCreateTime;
    }

    public Date getBannerFailureTime() {
        return bannerFailureTime;
    }

    public void setBannerFailureTime(Date bannerFailureTime) {
        this.bannerFailureTime = bannerFailureTime;
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
        sb.append(", bannerId=").append(bannerId);
        sb.append(", bannerPic=").append(bannerPic);
        sb.append(", bannerContent=").append(bannerContent);
        sb.append(", bannerTitle=").append(bannerTitle);
        sb.append(", bannerCreateTime=").append(bannerCreateTime);
        sb.append(", bannerFailureTime=").append(bannerFailureTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}