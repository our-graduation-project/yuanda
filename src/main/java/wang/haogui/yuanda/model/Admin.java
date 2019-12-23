package wang.haogui.yuanda.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Admin implements Serializable {
    @ApiModelProperty(value = "管理员id")
    private Integer adminId;

    @ApiModelProperty(value = "管理员真实名字")
    private String adminName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "别名")
    private String nickname;

    @ApiModelProperty(value = "用户密码")
    private String adminPassword;

    @ApiModelProperty(value = "权力,0表示一般管理员，1表示super管理员")
    private Byte right;

    @ApiModelProperty(value = "0表示男，1表示女")
    private Boolean sex;

    @ApiModelProperty(value = "管理员电话号码")
    private String phone;

    @ApiModelProperty(value = "0表示没有删除，1表示删除")
    private Boolean isDeleted;

    public Admin() {
    }

    public Admin(Integer adminId, String email, String adminPassword, Boolean isDeleted) {
        this.adminId = adminId;
        this.email = email;
        this.adminPassword = adminPassword;
        this.isDeleted = isDeleted;
    }


    public Admin(String email, String adminPassword, Byte right, Boolean isDeleted) {
        this.email = email;
        this.adminPassword = adminPassword;
        this.right = right;
        this.isDeleted = isDeleted;
    }

    public Admin(Integer adminId, Byte right) {
        this.adminId = adminId;
        this.right = right;
    }

    public Admin(String email, String adminPassword) {
        this.email = email;
        this.adminPassword = adminPassword;
    }

    private static final long serialVersionUID = 1L;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Byte getRight() {
        return right;
    }

    public void setRight(Byte right) {
        this.right = right;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        sb.append(", adminId=").append(adminId);
        sb.append(", adminName=").append(adminName);
        sb.append(", email=").append(email);
        sb.append(", nickname=").append(nickname);
        sb.append(", adminPassword=").append(adminPassword);
        sb.append(", right=").append(right);
        sb.append(", sex=").append(sex);
        sb.append(", phone=").append(phone);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}