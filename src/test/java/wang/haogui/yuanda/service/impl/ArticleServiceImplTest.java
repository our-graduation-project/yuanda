package wang.haogui.yuanda.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import wang.haogui.yuanda.YuandaApplication;
import wang.haogui.yuanda.common.CheckEnum;
import wang.haogui.yuanda.model.Article;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author whg
 * @date 2019/12/23 10:10
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YuandaApplication.class)
class ArticleServiceImplTest {

    @Autowired
    private ArticleServiceImpl articleService;

    @Test
    void addArticle() {
        Article article = new Article();
        article.setAgreementNumber(10);
        article.setArticleContent("测试");
        article.setArticleTitle("测试");
        article.setAuthorName("whg");
        article.setAuthorPicture("/images/1.png");
        article.setAuthorId(1);
        article.setCreateTime(new Date());
        article.setCheckStatus((byte) 0);
        article.setIsDeleted(false);
        System.out.println(articleService.addArticle(article));
    }

    @Test
    void deleteArticleById() {
        Assert.assertTrue(articleService.deleteArticleById(1));
    }

    @Test
    void changeCheckStuatsById(){
        Assert.assertTrue(articleService.changeCheckStuatsById(1,CheckEnum.CHECKPASS));
    }
}