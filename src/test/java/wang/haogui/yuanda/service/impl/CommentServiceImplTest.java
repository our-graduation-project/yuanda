package wang.haogui.yuanda.service.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.haogui.yuanda.YuandaApplication;
import wang.haogui.yuanda.model.Comment;
import wang.haogui.yuanda.service.CommentService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 10:49 2019/12/23
 * @Modifued By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YuandaApplication.class)
class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;
    @Test
    void addCommentForArticle() {
        Comment comment = new Comment();
        comment.setCommentContent("yiqihapi");
        comment.setCommentResourceId(1);
        comment.setCommentResourcePicture("1");
        comment.setCommentNumber(0);
        comment.setIsDeleted(false);
        comment.setCommentResourceName("zzz");
        comment.setCommentTargetId(1);
        comment.setCommentType((byte) 0);
        boolean b = commentService.addCommentForArticle(comment);
        System.out.println(b);
    }

    @Test
    void addCommentForAnswer() {
        Comment comment = new Comment();
        comment.setCommentContent("yiqihapi");
        comment.setCommentResourceId(1);
        comment.setCommentResourcePicture("1");
        comment.setCommentNumber(0);
        comment.setIsDeleted(false);
        comment.setCommentResourceName("zzz");
        comment.setCommentTargetId(1);
        comment.setCommentType((byte) 1);
        boolean b = commentService.addCommentForAnswer(comment);
        System.out.println(b);
    }

    @Test
    void addCommentForComment() {
        Comment comment = new Comment();
        comment.setCommentContent("yiqihapi");
        comment.setCommentResourceId(1);
        comment.setCommentResourcePicture("1");
        comment.setCommentNumber(0);
        comment.setIsDeleted(false);
        comment.setCommentResourceName("zzzz");
        comment.setCommentTargetId(1);
        comment.setCommentType((byte) 2);
        boolean b = commentService.addCommentForComment(comment);
        System.out.println(b);
    }

    @Test
    void selectCommentByArticle() {
        PageInfo pageInfo = commentService.selectCommentByArticle(1, 1, 2, null, null);
        System.out.println(pageInfo);
        System.out.println(pageInfo.getList().get(0));
    }

    @Test
    void selectCommentByAnswer() {
        PageInfo pageInfo = commentService.selectCommentByAnswer(1, 1, 2, null, null);
        System.out.println(pageInfo);
        System.out.println(pageInfo.getList().get(0));
    }

    @Test
    void selectCommentByComment() {
        PageInfo pageInfo = commentService.selectCommentByCommentId(1, 1, 2, null, null);
        System.out.println(pageInfo);
        System.out.println(pageInfo.getList().get(0));
    }

    @Test
    void delComment(){
        commentService.delComment(1);
    }
}