package wang.haogui.yuanda.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class FavoritesConnection implements Serializable {
    @ApiModelProperty(value = "本表的id")
    private Integer id;

    @ApiModelProperty(value = "收藏夹id")
    private Integer favoritesId;

    @ApiModelProperty(value = "文章或者问题的id")
    private Integer connectionId;

    @ApiModelProperty(value = "connection类型，0表示文章，1表示问题")
    private Byte type;

    @ApiModelProperty(value = "收藏的标题")
    private String contentTitle;

    @ApiModelProperty(value = "作者名")
    private String author;

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

    public Integer getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(Integer favoritesId) {
        this.favoritesId = favoritesId;
    }

    public Integer getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Integer connectionId) {
        this.connectionId = connectionId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
        sb.append(", favoritesId=").append(favoritesId);
        sb.append(", connectionId=").append(connectionId);
        sb.append(", type=").append(type);
        sb.append(", contentTitle=").append(contentTitle);
        sb.append(", author=").append(author);
        sb.append(", remark=").append(remark);
        sb.append(", remark2=").append(remark2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}