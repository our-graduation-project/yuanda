package wang.haogui.yuanda.service;

import com.github.pagehelper.PageInfo;
import wang.haogui.yuanda.model.Comment;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 11:22 2019/12/21
 * @Modifued By:
 */
public interface CommentService {

    /**
     * 在一篇文章下面添加评论
     * @param comment
     * @return
     */
    boolean addCommentForArticle(Comment comment);

    /**
     * 在一个回答下面添加评论
     * @param comment
     * @return
     */
    boolean addCommentForAnswer(Comment comment);

    /**
     * 在一该评论下面添加评论
     * @param comment
     * @return
     */
    boolean addCommentForComment(Comment comment);

    /**
     * 查询评论通过文章id
     *
     * @param articleId
     */
    PageInfo selectCommentByArticle(int articleId);

    /**
     * 查询评论通过文章id
     *
     * @param answerId
     */
    PageInfo selectCommentByAnswer(int answerId);

    /**
     * 查询评论通过文章id
     *
     * @param commentId
     */
    PageInfo selectCommentByComment(int commentId);

}
