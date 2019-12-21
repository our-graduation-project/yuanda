package wang.haogui.yuanda.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    @ApiModelProperty(value = "用户年龄")
    private Integer userAge;

    @ApiModelProperty(value = "日期")
    private Date userBrithday;

    @ApiModelProperty(value = "0表示男，1表示女")
    private Integer userSex;

    @ApiModelProperty(value = "头像图片路径")
    private String userPicture;

    @ApiModelProperty(value = "用户背景图片")
    private String userBackground;

    @ApiModelProperty(value = "别名")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    private String telphone;

    @ApiModelProperty(value = "用户积分")
    private Integer userIntegral;

    @ApiModelProperty(value = "最后登陆时间")
    private Date lastTime;

    @ApiModelProperty(value = "用户描述")
    private String userDescript;

    @ApiModelProperty(value = "发表的文章数目")
    private Integer articleNumber;

    @ApiModelProperty(value = "用户回答的问题数目")
    private Integer answerNumber;

    @ApiModelProperty(value = "用户提出问题的数目")
    private Integer questionNumber;

    @ApiModelProperty(value = "额外字段1")
    private String remark;

    @ApiModelProperty(value = "额外字段2")
    private String remark2;

    @ApiModelProperty(value = "用未读消息的数量")
    private Integer unreadMessageNumber;

    @ApiModelProperty(value = " 0表示存在，1表示删除")
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Date getUserBrithday() {
        return userBrithday;
    }

    public void setUserBrithday(Date userBrithday) {
        this.userBrithday = userBrithday;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public String getUserBackground() {
        return userBackground;
    }

    public void setUserBackground(String userBackground) {
        this.userBackground = userBackground;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public Integer getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(Integer userIntegral) {
        this.userIntegral = userIntegral;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getUserDescript() {
        return userDescript;
    }

    public void setUserDescript(String userDescript) {
        this.userDescript = userDescript;
    }

    public Integer getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(Integer articleNumber) {
        this.articleNumber = articleNumber;
    }

    public Integer getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(Integer answerNumber) {
        this.answerNumber = answerNumber;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
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

    public Integer getUnreadMessageNumber() {
        return unreadMessageNumber;
    }

    public void setUnreadMessageNumber(Integer unreadMessageNumber) {
        this.unreadMessageNumber = unreadMessageNumber;
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
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", userAge=").append(userAge);
        sb.append(", userBrithday=").append(userBrithday);
        sb.append(", userSex=").append(userSex);
        sb.append(", userPicture=").append(userPicture);
        sb.append(", userBackground=").append(userBackground);
        sb.append(", nickName=").append(nickName);
        sb.append(", email=").append(email);
        sb.append(", telphone=").append(telphone);
        sb.append(", userIntegral=").append(userIntegral);
        sb.append(", lastTime=").append(lastTime);
        sb.append(", userDescript=").append(userDescript);
        sb.append(", articleNumber=").append(articleNumber);
        sb.append(", answerNumber=").append(answerNumber);
        sb.append(", questionNumber=").append(questionNumber);
        sb.append(", remark=").append(remark);
        sb.append(", remark2=").append(remark2);
        sb.append(", unreadMessageNumber=").append(unreadMessageNumber);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}