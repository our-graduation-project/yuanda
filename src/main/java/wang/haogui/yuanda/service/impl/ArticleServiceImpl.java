package wang.haogui.yuanda.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.common.CheckEnum;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.mapper.ArticleMapper;
import wang.haogui.yuanda.model.Article;
import wang.haogui.yuanda.model.ArticleExample;
import wang.haogui.yuanda.service.ArticleService;
import wang.haogui.yuanda.utils.CommonUtils;

import java.util.List;

/**
 * @author whg
 * @date 2019/12/21 11:23
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    @Override
    public boolean addArticle(Article article) {
        Integer articleId = article.getArticleId();
        if(articleId != null && articleId > 0){
            article.setArticleId(0);
        }
        return articleMapper.insert(article) > 0 ? true : false;
    }

    /**
     * 通过id删除文章,软删除
     *
     * @param articleId
     * @return
     */
    @Override
    public boolean deleteArticleById(int articleId) {
        Article article = new Article();
        article.setIsDeleted(true);
        article.setArticleId(articleId);

        return articleMapper.updateByPrimaryKeySelective(article) > 0 ? true : false;
    }

    /**
     * 通过文章中的id，更新文章
     *
     * @param aritcle
     * @return
     */
    @Override
    public boolean updateAritcle(Article aritcle) {
        if(aritcle.getArticleId() == null || aritcle.getArticleId() < 0){
            return false;
        }

        return articleMapper.updateByPrimaryKey(aritcle) > 0 ? true : false;
    }

    /**
     * 通过用户id查询文章,
     * order为排序的字段名，若为null 则不需要排序
     * OrderEnum，为排序的枚举
     *
     * @param userId
     * @param page
     * @param limit
     * @param order
     * @param orderEnum
     * @return
     */
    @Override
    public PageInfo selectAritcleByUserId(int userId, int page, int limit, String order, OrderEnum orderEnum) {
        ArticleExample articleExample = new ArticleExample();
        //查询用户没有被删除，且审核状态不为1的文章
        articleExample.or().andArticleIdEqualTo(userId)
                .andIsDeletedEqualTo(false)
                .andCheckStatusNotEqualTo(CheckEnum.CHECKFAILL.getStatus());

        //排序不为空则排序
        if(order != null){
            String s = CommonUtils.orderStr(order, orderEnum);
            articleExample.setOrderByClause(s);
        }

        PageHelper.startPage(page,limit);
        List<Article> articles = articleMapper.selectByExampleWithBLOBs(articleExample);
        PageInfo pageInfo = new PageInfo(articles,5);
        return pageInfo;
    }

    /**
     * 通过文章标题查询文章,
     * order为排序的字段名，若为null 则不需要排序
     * OrderEnum，为排序的枚举
     *
     * @param title
     * @param page
     * @param limit
     * @param order
     * @param orderEnum
     * @return
     */
    @Override
    public PageInfo selectArticleByTitle(String title, int page, int limit, String order, OrderEnum orderEnum) {

        return null;
    }

    /**
     * 通过labelId查询文章
     * order为排序的字段名，若为null 则不需要排序
     * OrderEnum，为排序的枚举
     *
     * @param labelId
     * @param page
     * @param limit
     * @param order
     * @param orderEnum
     * @return
     */
    @Override
    public PageInfo selectArticleByLabelId(int labelId, int page, int limit, String order, OrderEnum orderEnum) {

        return null;
    }

    /**
     * 通过labelName查询文章
     * order为排序的字段名，若为null 则不需要排序
     * OrderEnum，为排序的枚举
     *
     * @param labelName
     * @param page
     * @param limit
     * @param order
     * @param orderEnum
     * @return
     */
    @Override
    public PageInfo selectArticleByLabelName(String labelName, int page, int limit, String order, OrderEnum orderEnum) {
        return null;
    }

    /**
     * 通过文章id改变某篇文章的状态吗
     *
     * @param id
     * @param checkEnum
     * @return
     */
    @Override
    public Boolean changeCheckStuatsById(int id, CheckEnum checkEnum) {
        Article article = new Article();
        article.setCheckStatus(checkEnum.getStatus());
        article.setArticleId(id);
        return articleMapper.updateByPrimaryKeySelective(article) > 0 ? true : false;
    }


}
