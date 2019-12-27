package wang.haogui.yuanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.haogui.yuanda.model.Comment;
import wang.haogui.yuanda.service.CommentService;
import wang.haogui.yuanda.utils.APIResult;

import java.util.List;
import java.util.Map;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 20:22 2019/12/23
 * @Modifued By:
 */

@RestController
public class CommentController {

      @Autowired
      private CommentService commentService;


      @RequestMapping(value = "loadcomment")
      public APIResult loadComment(@RequestBody Map map){
            if(map.get("commentType") == null){
                  return APIResult.genFailApiResponse400("加载评论失败！");
            }
            Integer commentType = Integer.parseInt(map.get("commentType").toString());
            List<Comment> comments ;
            switch (commentType){
                  case 0:
                        if(map.get("commentTargetId") == null){
                              return APIResult.genFailApiResponse400("加载评论失败！");
                        }
                        int articleId = Integer.parseInt(map.get("commentTargetId").toString());
                        comments = commentService.selectCommentByArticle(articleId);
                        break;
                  case 1:
                        if(map.get("commentTargetId") == null){
                              return APIResult.genFailApiResponse400("加载评论失败！");
                        }
                        int answerId = Integer.parseInt(map.get("commentTargetId").toString());
                        comments = commentService.selectCommentByAnswer(answerId);
                        break;
                  case 2:
                        if(map.get("commentTargetId") == null){
                              return APIResult.genFailApiResponse400("加载评论失败！");
                        }
                        int commentId = Integer.parseInt(map.get("commentTargetId").toString());
                        comments = commentService.selectCommentByCommentId(commentId);
                        break;
                  default:
                        return APIResult.genFailApiResponse400("加载评论失败");
            }

            return APIResult.genSuccessApiResponse(comments);
      }

      @PostMapping("addcomment")
      public APIResult addComment(@RequestBody Map map){
            if(map.get("commentId") != null || map.get("commentContent") != null){
                  Byte commentType = (Byte) map.get("commentTYpe");
                  Comment comments = new Comment();
                  comments.setCommentId((Integer) map.get("commentId"));
                  comments.setCommentNumber(0);
                  comments.setCommentResourceId((Integer) map.get("commentResourceId"));
                  comments.setCommentTargetId((Integer) map.get("commentTargetId"));
                  comments.setCommentType((Byte) map.get("commentType"));
                  comments.setIsDeleted(false);
                  comments.setCommentContent((String) map.get("commentContent"));
                  boolean flag ;
                  switch (commentType){
                        case 0:
                              flag = commentService.addCommentForArticle(comments);
                              break;
                        case 1:
                              flag = commentService.addCommentForAnswer(comments);
                              break;
                        case 2:
                              flag = commentService.addCommentForComment(comments);
                              break;
                        default:
                              return APIResult.genFailApiResponse400("添加评论失败");
                  }

                  if(flag){
                        return APIResult.genSuccessApiResponse("添加评论成功");
                  }
            }

            return APIResult.genFailApiResponse400("添加评论失败");
      }


      @RequestMapping("delcomment")
      public APIResult delComment(@RequestBody Map map){
            if(map.get("commentId") != null){

                  boolean flag = commentService.delComment((Integer) map.get("commentId"));
                  if(flag){
                        return APIResult.genSuccessApiResponse("删除评论成功");
                  }

            }
            return APIResult.genFailApiResponse400("删除评论失败");

      }

}