package wang.haogui.yuanda.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wang.haogui.yuanda.model.Article;
import wang.haogui.yuanda.model.ArticleExample;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {
    long countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    List<Article> selectByExampleWithBLOBs(ArticleExample example);

    List<Article> selectByExample(ArticleExample example);

    Article selectByPrimaryKey(Integer articleId);

    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    int updateForCommentNumber(Article record);

    /**
     * 将list中的数据的status变为传入的status
     * @param list
     * @param status
     * @return
     */
    int updateCheckStatusByList(@Param("list") List list,@Param("status") Integer status);

    List<Article> selectArticleByLabelId(Map map);

    /**
     * 批量插入文章，id在传入的list中
     * @param articles
     * @return
     */
    int batchInsert(List<Article> articles);

    /**
     * 减少这篇文章的评论数
     * @param articleId
     * @param number
     * @return
     */
    int decreaseCommentNumberByPrimaryKey(@Param("articleId") Integer articleId,@Param("number") int number);

    /**
     * 通过用户id，文章id 更新文章信息
     * 只能更新 时间，内容，作者，标题
     * @param article
     * @return
     */
    int updateArticleByIds(Article article);
}