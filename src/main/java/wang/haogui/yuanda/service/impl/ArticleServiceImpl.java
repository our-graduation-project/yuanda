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
import wang.haogui.yuanda.model.Question;
import wang.haogui.yuanda.model.QuestionExample;
import wang.haogui.yuanda.service.ArticleService;
import wang.haogui.yuanda.utils.CommonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 跟新文章 只更新内容，标题，图片，标签
     * 需要验证更新者是不是作者否则失败
     *
     * @param article
     * @return
     */
    @Override
    public boolean updateArticle(Article article) {
        if(article.getArticleId() == null || article.getArticleId() < 0){
            return false;
        }
        articleMapper.updateArticleByIds(article);
        return articleMapper.updateArticleByIds(article) > 0 ? true : false;
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
        articleExample.or().andAuthorIdEqualTo(userId)
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
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleTitleLike("%"+title+"%").andIsDeletedEqualTo(false);

        if(order!=null&&!"".equals(order)){
            String str = CommonUtils.orderStr(order, orderEnum);
            articleExample.setOrderByClause(str);
        }
        PageInfo<Article> pageInfo = select(page, limit, articleExample);
        return pageInfo;
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

        Map map = new HashMap<>();
        map.put("labelId",labelId);
        String orderStr = null;
        if(order != null&& !"".equals(order)){
            orderStr = CommonUtils.orderStr(order,orderEnum);
        }

        map.put("str",orderStr);
        PageHelper.startPage(page,limit);
        List<Article> articles = articleMapper.selectArticleByLabelId(map);
        PageInfo<Article> pageInfo = new PageInfo<>(articles,5);

        return pageInfo;
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

    /**
     * 通过分页信息查询文章信息
     *
     * @param page
     * @param limit
     * @param order
     * @param orderEnum
     * @return
     */
    @Override
    public PageInfo selectArticleByPage(int page, int limit, String order, OrderEnum orderEnum) {

        ArticleExample articleExample = new ArticleExample();

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
     * 通过id查询文章
     *
     * @param id
     * @return
     */
    @Override
    public Article selectArticleId(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        return article;
    }

    /**
     * 通过文章id将其状态码全部改为status
     *
     * @param list
     * @param status
     * @return
     */
    @Override
    public Boolean changeCheckStatusByList(List list, Integer status) {
        int i = articleMapper.updateCheckStatusByList(list, status);
        return i > 0 ? true : false;
    }

    /**
     * 批量增加入文章
     *
     * @param list
     * @return
     */
    @Override
    public List<Article> addBatchAritcle(List list) {
        if(list == null){
            return null;
        }
        Integer i = articleMapper.batchInsert(list);
        if(i > 0){
            return list;
        }
        return null;
    }

    /**
     * 通用查询
     * @param page 当前页码
     * @param limit 页码大小
     * @param articleExample 条件
     * @return 查询出来的结果
     */
    private PageInfo<Article> select(int page, int limit, ArticleExample articleExample){

        PageHelper.startPage(page,limit);
        List<Article> articles = articleMapper.selectByExample(articleExample);
        PageInfo<Article> pageInfo = new PageInfo(articles,5);

        return pageInfo;
    }

}
