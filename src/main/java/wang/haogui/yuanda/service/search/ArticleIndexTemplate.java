package wang.haogui.yuanda.service.search;

import java.util.Date;
import java.util.List;

/**
 * 索引结构模板
 * @author whg
 * @date 2019/12/23 19:32
 **/

public class ArticleIndexTemplate {


    private long articleId;


    private String articleTitle;


    private String articleContent;


    private Integer agreementNumber;


    private Integer recommendNumber;


    private Date createTime;


    private Date updateTime;


    private Integer hotNumber;


    private List<String> labelName;

    private int clickNumber;


    public ArticleIndexTemplate() {
    }

    public ArticleIndexTemplate(long articleId, String articleTitle, String articleContent, Integer agreementNumber, Integer recommendNumber, Date createTime, Date updateTime, Integer hotNumber, List<String> labelName, int clickNumber) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.agreementNumber = agreementNumber;
        this.recommendNumber = recommendNumber;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.hotNumber = hotNumber;
        this.labelName = labelName;
        this.clickNumber = clickNumber;
    }

    public int getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(int clickNumber) {
        this.clickNumber = clickNumber;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Integer getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(Integer agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public Integer getRecommendNumber() {
        return recommendNumber;
    }

    public void setRecommendNumber(Integer recommendNumber) {
        this.recommendNumber = recommendNumber;
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

    public Integer getHotNumber() {
        return hotNumber;
    }

    public void setHotNumber(Integer hotNumber) {
        this.hotNumber = hotNumber;
    }

    public List<String> getLabelName() {
        return labelName;
    }

    public void setLabelName(List<String> labelName) {
        this.labelName = labelName;
    }


    @Override
    public String toString() {
        return "ArticleIndexTemplate{" +
                "articleId=" + articleId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", agreementNumber=" + agreementNumber +
                ", recommendNumber=" + recommendNumber +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", hotNumber=" + hotNumber +
                ", labelName=" + labelName +
                ", clickNumber=" + clickNumber +
                '}';
    }
}
