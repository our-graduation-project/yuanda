package wang.haogui.yuanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.haogui.yuanda.model.Comment;
import wang.haogui.yuanda.service.CommentService;
import wang.haogui.yuanda.utils.APIResult;
import wang.haogui.yuanda.utils.TokenUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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


      @RequestMapping(value = "user/loadcomment")
      public APIResult loadComment(@RequestBody Map map){
            if(map.get("commentType") == null){
                  return APIResult.genFailApiResponse400("加载评论失败！");
            }
            if(map.get("commentTargetId") == null){
                  return APIResult.genFailApiResponse400("加载评论失败！");
            }
            byte commentType = Byte.parseByte(map.get("commentType").toString());
            Integer commentTargetId = Integer.parseInt(map.get("commentTargetId").toString());
            Comment comment = new Comment();
            comment.setCommentTargetId(commentTargetId);
            comment.setCommentType(commentType);
            List<Comment> comments = commentService.selectComment(comment);
            return APIResult.genSuccessApiResponse(comments);
      }

      @PostMapping("user/addcomment")
      public APIResult addComment(@RequestBody Map map, HttpServletRequest request){
            if(map.get("commentTargetId") != null || map.get("commentContent") != null ||
                     map.get("commentType") != null || map.get("parentId") != null){
                  Comment comments = new Comment();
                  comments.setCommentNumber(0);
                  comments.setParentId((Integer) map.get("parentId"));
                  //此部分为用户的  获取token是输入
//                  Cookie[] cookies = request.getCookies();
//                  Cookie cookie = null;
//                  for (Cookie c:cookies) {
//                        if("user".equals(c.getName())){
//                              cookie = c;
//                        }
//                  }
//                  String string = cookie.getValue().toString();
//                  int userId = (int) TokenUtil.getTokenValue(string, "userId");
//                  String userName = (String) TokenUtil.getTokenValue(string,"userName");
//                  String userPicture = (String)TokenUtil.getTokenValue(string,"userPicture");
                  comments.setCommentResourceName("1");
                  comments.setCommentResourcePicture("1");
                  comments.setCommentResourceId(1);
                  comments.setCommentTargetId((Integer) map.get("commentTargetId"));
                  comments.setCommentType(Byte.parseByte(map.get("commentType").toString()));
                  comments.setIsDeleted(false);
                  comments.setCreateDate(new Date());
                  comments.setCommentContent((String) map.get("commentContent"));
                  boolean flag ;
                  switch (Byte.parseByte(map.get("commentType").toString())){
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


      @RequestMapping("user/delcomment")
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