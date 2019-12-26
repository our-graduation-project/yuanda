package wang.haogui.yuanda.service;

import com.github.pagehelper.PageInfo;
import wang.haogui.yuanda.common.CheckEnum;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.model.Article;

import java.util.List;

/**
 * @author whg
 * @date 2019/12/21 10:23
 **/
public interface ArticleService {

    /**
     * 添加文章
     * @param article
     * @return
     */
    boolean addArticle(Article article);

    /**
     * 通过id删除文章
     * @param articleId
     * @return
     */
    boolean deleteArticleById(int articleId);

    /**
     * 通过文章中的id，更新文章
     * @param aritcle
     * @return
     */
    boolean updateAritcle(Article aritcle);

    /**
     * 通过用户id查询文章,
     * order为排序的字段名，若为null 则不需要排序
     * OrderEnum，为排序的枚举
     * @param userId
     * @return
     */
    PageInfo selectAritcleByUserId(int userId, int page, int limit, String order, OrderEnum orderEnum);

    /**
     * 通过文章标题查询文章,
     * order为排序的字段名，若为null 则不需要排序
     * OrderEnum，为排序的枚举
     * @param title
     * @param page
     * @param limit
     * @param order
     * @param orderEnum
     * @return
     */
    PageInfo selectArticleByTitle(String title, int page, int limit, String order, OrderEnum orderEnum);

    /**
     * 通过labelId查询文章
     * order为排序的字段名，若为null 则不需要排序
     * OrderEnum，为排序的枚举
     * @param labelId
     * @param page
     * @param limit
     * @param order
     * @param orderEnum
     * @return
     */
    PageInfo selectArticleByLabelId(int labelId, int page, int limit, String order, OrderEnum orderEnum);

    /**
     * 通过labelName查询文章
     * order为排序的字段名，若为null 则不需要排序
     * OrderEnum，为排序的枚举
     * @param labelName
     * @param page
     * @param limit
     * @param order
     * @param orderEnum
     * @return
     */
    PageInfo selectArticleByLabelName(String labelName, int page, int limit, String order, OrderEnum orderEnum);

    /**
     * 通过文章id改变某篇文章的状态吗
     * @param id
     * @param checkEnum
     * @return
     */
    Boolean changeCheckStuatsById(int id, CheckEnum checkEnum);

    /**
     * 通过分页信息查询文章信息
     * @param page
     * @param limit
     * @param order
     * @param orderEnum
     * @return
     */
    PageInfo selectArticleByPage(int page, int limit, String order, OrderEnum orderEnum);

    /**
     * 通过id查询文章
     * @param id
     * @return
     */
    Article selectArticleId(Integer id);

    /**
     * 通过文章id将其状态码全部改为status
     * @param list
     * @param status
     * @return
     */
    Boolean changeCheckStatusByList(List list, Integer status);
}
