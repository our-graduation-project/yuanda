package wang.haogui.yuanda.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.common.CommentTypeEnum;
import wang.haogui.yuanda.common.LogEnum;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.mapper.AnswerMapper;
import wang.haogui.yuanda.mapper.ArticleMapper;
import wang.haogui.yuanda.mapper.CommentMapper;
import wang.haogui.yuanda.model.*;
import wang.haogui.yuanda.service.CommentService;
import wang.haogui.yuanda.utils.CommonUtils;
import wang.haogui.yuanda.utils.LogUtils;

import java.util.List;

import static wang.haogui.yuanda.common.CommentTypeEnum.*;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 9:25 2019/12/23
 * @Modifued By:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private AnswerMapper answerMapper;

    /**
     * 在一篇文章下面添加评论
     *
     * @param comment
     * @return
     */
    @Override
    public boolean addCommentForArticle(Comment comment) {
        //先插入这条评论
        int insert = commentMapper.insert(comment);

        if(insert <= 0){
            return false;
        }

        //先通过id查询到这条文章
        Article article = articleMapper.selectByPrimaryKey(comment.getCommentTargetId());
        article.setCommentNumber(article.getCommentNumber()+1);
        //在将文章的评论数量加一
        int i = articleMapper.updateByPrimaryKey(article);

        if(i <= 0){
            return false;
        }


        return true;
    }

    /**
     * 在一个回答下面添加评论
     *
     * @param comment
     * @return
     */
    @Override
    public boolean addCommentForAnswer(Comment comment) {
        //先插入这条评论
        int insert = commentMapper.insert(comment);

        if(insert <= 0){
            return false;
        }

        //查询这个问题
        Answer answer = answerMapper.selectByPrimaryKey(comment.getCommentTargetId());
        //修改问题下的评论数量
        answer.setCommentNumber(answer.getCommentNumber()+1);
        int i = answerMapper.updateByPrimaryKey(answer);

        if(i <= 0){
            return false;
        }

        return true;
    }

    /**
     * 在一该评论下面添加评论
     *
     * @param comment
     * @return
     */
    @Override
    public boolean addCommentForComment(Comment comment) {
        //先插入这条评论
        int insert = commentMapper.insert(comment);

        if(insert <= 0){
            return false;
        }

        //查询这个评论
        Comment comment1 = commentMapper.selectByPrimaryKey(comment.getCommentTargetId());
        //修改评论下的评论数量
        comment1.setCommentNumber(comment1.getCommentNumber()+1);
        int i = commentMapper.updateByPrimaryKey(comment1);

        if(i <= 0){
            return false;
        }

        return true;
    }

    /**
     * 查询评论通过文章id
     * order为排序的字段名 若为null 则不需要排序
     * OrderEnum,为排序的枚举
     *
     * @param articleId
     * @param page
     * @param limit
     * @param order
     * @param orderEnum
     */
    @Override
    public PageInfo selectCommentByArticle(int articleId, int page, int limit, String order, OrderEnum orderEnum) {
        CommentExample commentExample = new CommentExample();

        commentExample.or().andCommentTargetIdEqualTo(articleId)
                .andCommentTypeEqualTo((byte)0).andIsDeletedEqualTo(false);
        //添加是否进行排序的查询
        if(order!=null){
            String str = CommonUtils.orderStr(order, orderEnum);
            commentExample.setOrderByClause(str);
        }

        PageHelper.startPage(page,limit);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        PageInfo pageInfo = new PageInfo(comments);

        return pageInfo;
    }

    /**
     * 查询评论通过问题id
     * order为排序的字段名 若为null 则不需要排序
     * OrderEnum,为排序的枚举
     *
     * @param answerId
     * @param page
     * @param limit
     * @param order
     * @param orderEnum
     */
    @Override
    public PageInfo selectCommentByAnswer(int answerId, int page, int limit, String order, OrderEnum orderEnum) {
        CommentExample commentExample = new CommentExample();

        commentExample.or().andCommentTargetIdEqualTo(answerId)
                .andCommentTypeEqualTo((byte)1).andIsDeletedEqualTo(false);
        //添加是否进行排序的查询
        if(order!=null){
            String str = CommonUtils.orderStr(order, orderEnum);
            commentExample.setOrderByClause(str);
        }

        PageHelper.startPage(page,limit);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        PageInfo pageInfo = new PageInfo(comments);

        return pageInfo;
    }

    /**
     * 查询评论通过评论id
     * order为排序的字段名 若为null 则不需要排序
     * OrderEnum,为排序的枚举
     *
     * @param commentId
     * @param page
     * @param limit
     * @param order
     * @param orderEnum
     */
    @Override
    public PageInfo selectCommentByCommentId(int commentId, int page, int limit, String order, OrderEnum orderEnum) {
        CommentExample commentExample = new CommentExample();

        commentExample.or().andCommentTargetIdEqualTo(commentId).andCommentTypeEqualTo((byte)2).andIsDeletedEqualTo(false);
        //添加是否进行排序的查询
        if(order!=null){
            String str = CommonUtils.orderStr(order, orderEnum);
            commentExample.setOrderByClause(str);
        }

        PageHelper.startPage(page,limit);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        PageInfo pageInfo = new PageInfo(comments);

        return pageInfo;
    }

    /**
     * 删除某一条评论 然后级联删除它下面的所有子评论
     *
     * @param commentId
     */
    @Override
    public boolean delComment(int commentId) {
        //先查询出这条评论下的所有子评论
        CommentExample commentExample = new CommentExample();
        commentExample.or().andCommentTargetIdEqualTo(commentId).andCommentTypeEqualTo(COMMENT.getCommentType()).andIsDeletedEqualTo(false);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        //查询出该条评论 再将该条评论加入到软删除子评论列表中
        Comment commentParent = commentMapper.selectByPrimaryKey(commentId);
        comments.add(commentParent);

        for(Comment comment:comments){
            comment.setIsDeleted(true);
        }
        //这里我们批量软删除这些评论(包括子评论和自己这条评论) 我们只需要根据这条评论的id来修改它的isDelete状态为true
        int i = commentMapper.updateBatchForComment(comments);

        if(i <= 0){
            LogUtils.getDBLogger().info("删除"+commentId+"子评论失败");
            throw new RuntimeException("删除子评论失败！");
        }

        //将父级的评论数量减一
        //先找到父级
        //0 代表文章  1 代表问题 2 表示评论
        switch (commentParent.getCommentType()){
            case 0:
                articleMapper.updateForCommentNumber(commentParent.getCommentTargetId());
                break;
            case 1:
                answerMapper.updateForCommentNumber(commentParent.getCommentTargetId());
                break;
            case 2:
                commentMapper.updateForCommentNumber(commentParent.getCommentTargetId());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + commentParent.getCommentType());
        }

        return true;
    }
}