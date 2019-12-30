package wang.haogui.yuanda.service;

import wang.haogui.yuanda.model.Comment;

import java.util.List;

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
     * order为排序的字段名 若为null 则不需要排序
     * OrderEnum,为排序的枚举
     * @param articleId
     */
    List<Comment> selectCommentByArticle(int articleId);

    /**
     * 查询评论通过文章id
     *  order为排序的字段名 若为null 则不需要排序
     *  OrderEnum,为排序的枚举
     * @param answerId
     */
    List<Comment> selectCommentByAnswer(int answerId);

    /**
     * 查询评论通过文章id
     *  order为排序的字段名 若为null 则不需要排序
     *  OrderEnum,为排序的枚举
     * @param commentId
     */
    List<Comment> selectCommentByParentId(List<Integer> commentId);

    /**
     *  删除某一条评论 然后级联删除它下面的所有子评论
     *
     * @param commentId
     */
    boolean delComment(int commentId);

    /**
     *  查询一个文章或者回答的所有评论
     *
     * @param comment
     */
    List<Comment> selectComment(Comment comment);

}
