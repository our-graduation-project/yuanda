package wang.haogui.yuanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.haogui.yuanda.component.NotificationComponent;
import wang.haogui.yuanda.component.SensitiveFilter;
import wang.haogui.yuanda.model.Answer;
import wang.haogui.yuanda.model.Article;
import wang.haogui.yuanda.model.Comment;
import wang.haogui.yuanda.model.Message;
import wang.haogui.yuanda.service.AnswerService;
import wang.haogui.yuanda.service.ArticleService;
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

      @Autowired
      private AnswerService answerService;

      @Autowired
      private ArticleService articleService;

      @Autowired
      private NotificationComponent notificationComponent;

      @Autowired
      private SensitiveFilter sensitiveFilter;

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
            System.out.println(comments);
            return APIResult.genSuccessApiResponse(comments);
      }

      @PostMapping("user/addcomment")
      public APIResult addComment(@RequestBody Map map, HttpServletRequest request){
            if(map.get("commentTargetId") != null || map.get("commentContent") != null ||
                     map.get("commentType") != null || map.get("parentId") != null){
                  Comment comments = new Comment();
                  comments.setCommentNumber(0);
                  comments.setParentId(Integer.parseInt(map.get("parentId").toString()));
                  //此部分为用户的  获取token是输入
                  Cookie[] cookies = request.getCookies();
                  Cookie cookie = null;
                  for (Cookie c:cookies) {
                        if("token".equals(c.getName())){
                              cookie = c;
                        }
                  }
                  String string = cookie.getValue().toString();
                  int userId = (int) TokenUtil.getTokenValue(string, "userId");
                  String userName = (String) TokenUtil.getTokenValue(string,"userName");
                  String userPicture = (String)TokenUtil.getTokenValue(string,"userPicture");
                  comments.setCommentResourceName(userName);
                  comments.setCommentResourcePicture(userPicture);
                  comments.setCommentResourceId(userId);
                  comments.setCommentTargetId(Integer.parseInt(map.get("commentTargetId").toString()));
                  comments.setCommentType(Byte.parseByte(map.get("commentType").toString()));
                  comments.setIsDeleted(false);
                  comments.setCreateDate(new Date());
                  comments.setCommentContent(sensitiveFilter.filter(map.get("commentContent").toString()));
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

                  if(comments.getCommentResourceId() != 0 || comments.getCommentResourceId() != null){
                        if(flag){
                              if(Byte.parseByte(map.get("commentType").toString()) == 2){
                                    //评论的类型是评论别人的评论成功后发送一条通知给被评论的人
                                    //先通过targetId查询到要发送给谁
                                    Comment comment = commentService.selectCommentById(Integer.parseInt(map.get("commentTargetId").toString()));
                                    Message message = new Message();
                                    message.setIsRead((byte) 0);
                                    Date date = new Date();
                                    message.setMessageTarget(comment.getCommentResourceId());
                                    message.setMessageResourceId(comments.getCommentResourceId());
                                    StringBuffer s = new StringBuffer();
                                    s.append(comment.getCommentResourceName());
                                    s.append("评论了你的评论：");
                                    s.append(comment.getCommentContent());
                                    s.append("ta说：");
                                    s.append((String) map.get("commentContent"));
                                    message.setMessageContent(s.toString());
                                    message.setMessageType((byte) 0);
                                    message.setIsDeleted(false);
                                    notificationComponent.sendNotification(message);
                              }else if(Byte.parseByte(map.get("commentType").toString()) == 1){
                                    //评论的类型是评论别人的回答成功后发送一条通知给被评论的人
                                    //先通过targetId查询到要发送给谁
                                    Answer answer = answerService.selectAnswerById(Integer.parseInt(map.get("commentTargetId").toString()));
                                    Message message = new Message();
                                    message.setIsRead((byte) 0);
                                    Date date = new Date();
                                    message.setMessageTarget(answer.getAuthorId());
                                    message.setMessageResourceId(comments.getCommentResourceId());
                                    StringBuffer s = new StringBuffer();
                                    s.append(answer.getAutherName());
                                    s.append("评论了你的回答：");
                                    s.append("ta说：");
                                    s.append((String) map.get("commentContent"));
                                    message.setMessageContent(s.toString());
                                    message.setMessageType((byte) 0);
                                    message.setIsDeleted(false);
                                    notificationComponent.sendNotification(message);

                              }else if(Byte.parseByte(map.get("commentType").toString()) == 0){
                                    //评论的类型是评论别人的文章成功后发送一条通知给被评论的人
                                    //先通过targetId查询到要发送给谁
                                    Article article = articleService.selectArticleId(Integer.parseInt(map.get("commentTargetId").toString()));
                                    Message message = new Message();
                                    message.setIsRead((byte) 0);
                                    Date date = new Date();
                                    message.setMessageTarget(article.getAuthorId());
                                    message.setMessageResourceId(comments.getCommentResourceId());
                                    StringBuffer s = new StringBuffer();
                                    //拼接通知内容
                                    s.append(article.getAuthorName());
                                    s.append("评论了你的文章：");
                                    s.append("ta说：");
                                    s.append((String) map.get("commentContent"));
                                    message.setMessageContent(s.toString());
                                    message.setMessageType((byte) 0);
                                    message.setIsDeleted(false);
                                    notificationComponent.sendNotification(message);
                              }
                        }
                  }
                  return APIResult.genSuccessApiResponse("添加评论成功");
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