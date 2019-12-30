package wang.haogui.yuanda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.mapper.AnswerMapper;
import wang.haogui.yuanda.mapper.ArticleMapper;
import wang.haogui.yuanda.mapper.CommentMapper;
import wang.haogui.yuanda.model.*;
import wang.haogui.yuanda.service.CommentService;
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
            LogUtils.getDBLogger().info("在文章下插入评论失败");
            throw new RuntimeException("在文章下插入评论失败！");
        }

        //先通过id查询到这条文章
        Article article = articleMapper.selectByPrimaryKey(comment.getCommentTargetId());
        article.setCommentNumber(article.getCommentNumber()+1);
        //在将文章的评论数量加一
        int i = articleMapper.updateByPrimaryKey(article);

        if(i <= 0){
            LogUtils.getDBLogger().info("在文章评论数增加失败");
            throw new RuntimeException("在文章评论数增加失败！");
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
            LogUtils.getDBLogger().info("在回答评论数增加失败");
            throw new RuntimeException("在回答评论数增加失败！");
        }

//        //查询这个问题
//        Answer answer = answerMapper.selectByPrimaryKey(comment.getCommentTargetId());
        //修改问题下的评论数量
        Answer answer = new Answer();
        answer.setAnswerId(comment.getCommentTargetId());
        answer.setCommentNumber(1);
        answer.setCommentNumber(answer.getCommentNumber()+1);
        int i = answerMapper.updateByPrimaryKey(answer);

        if(i <= 0){
            LogUtils.getDBLogger().info("在回答评论数增加失败");
            throw new RuntimeException("在回答评论数增加失败！");
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
            LogUtils.getDBLogger().info("在评论中增加评论失败");
            throw new RuntimeException("在评论中增加评论失败！");
        }

        //查询这个评论
//        Comment comment1 = commentMapper.selectByPrimaryKey(comment.getCommentTargetId());
        //修改评论下的评论数量
        Comment comment1 = new Comment();
        comment1.setCommentId(comment.getCommentTargetId());
        comment1.setCommentNumber(1);
        int i = commentMapper.updateForCommentNumber(comment1);

        if(i <= 0){
            LogUtils.getDBLogger().info("在评论的评论数增加失败");
            throw new RuntimeException("在评论的评论数增加失败！");
        }

        return true;
    }

    /**
     * 查询评论通过文章id
     * order为排序的字段名 若为null 则不需要排序
     * OrderEnum,为排序的枚举
     *
     * @param articleId
     */
    @Override
    public List<Comment> selectCommentByArticle(int articleId) {
        CommentExample commentExample = new CommentExample();

        commentExample.or().andCommentTargetIdEqualTo(articleId)
                .andCommentTypeEqualTo((byte)0).andIsDeletedEqualTo(false);

        List<Comment> comments = commentMapper.selectByExample(commentExample);

        return comments;
    }

    /**
     * 查询评论通过问题id
     * order为排序的字段名 若为null 则不需要排序
     * OrderEnum,为排序的枚举
     *
     * @param answerId
     */
    @Override
    public List<Comment> selectCommentByAnswer(int answerId) {
        CommentExample commentExample = new CommentExample();

        commentExample.or().andCommentTargetIdEqualTo(answerId)
                .andCommentTypeEqualTo((byte)1).andIsDeletedEqualTo(false);

        List<Comment> comments = commentMapper.selectByExample(commentExample);

        return comments;
    }

    /**
     * 查询评论通过评论id
     * order为排序的字段名 若为null 则不需要排序
     * OrderEnum,为排序的枚举
     *
     * @param commentId
     */
    @Override
    public List<Comment> selectCommentByParentId(List<Integer> commentId) {
        CommentExample commentExample = new CommentExample();

        commentExample.or().andParentIdIn(commentId).andCommentTypeEqualTo((byte)2)
                .andIsDeletedEqualTo(false);

        List<Comment> comments = commentMapper.selectByExample(commentExample);

        return comments;
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

        //将父级的评论数量减一 （这里我将-1作为commentNumber表示为评论数减1的标志）
        //先找到父级
        //0 代表文章  1 代表问题 2 表示评论
        switch (commentParent.getCommentType()){
            case 0:
                Article article = new Article();
                article.setArticleId(commentParent.getCommentTargetId());
                article.setCommentNumber(-1);
                articleMapper.updateForCommentNumber(article);
                break;
            case 1:
                Answer answer = new Answer();
                answer.setAnswerId(commentParent.getCommentTargetId());
                answer.setCommentNumber(-1);
                answerMapper.updateForCommentNumber(answer);
                break;
            case 2:
                Comment comment = new Comment();
                comment.setCommentId(commentParent.getCommentTargetId());
                comment.setCommentNumber(-1);
                commentMapper.updateForCommentNumber(comment);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + commentParent.getCommentType());
        }

        return true;
    }


    /**
     *  查询一个文章或者回答的所有评论
     *
     * @param comment
     */
    @Override
    public List<Comment> selectComment(Comment comment) {
        System.out.println(comment);
        List<Comment> comments = commentMapper.selectComment(comment);
        return comments;
    }
}