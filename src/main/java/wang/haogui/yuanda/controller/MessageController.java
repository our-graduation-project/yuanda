package wang.haogui.yuanda.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wang.haogui.yuanda.common.QueueEnum;
import wang.haogui.yuanda.component.NotificationComponent;
import wang.haogui.yuanda.component.SendNotificationQueue;
import wang.haogui.yuanda.model.Message;
import wang.haogui.yuanda.service.MessageService;
import wang.haogui.yuanda.utils.APIResult;
import wang.haogui.yuanda.utils.TokenUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 20:44 2019/12/30
 * @Modifued By:
 */

@RestController
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private NotificationComponent notificationComponent;


    @RequestMapping("user/loadmessage")
    public APIResult loadMessage(@RequestParam(value = "page",defaultValue = "0") int page
            , @RequestParam(value = "limit",defaultValue = "10") int limit, HttpServletRequest request){
        //此部分为用户的  获取token是输入
//        Cookie[] cookies = request.getCookies();
//        Cookie cookie = null;
//        for (Cookie c:cookies) {
//               if("user".equals(c.getName())){
//                   cookie = c;
//               }
//        }
//       String string = cookie.getValue().toString();
//      int userId = (int) TokenUtil.getTokenValue(string, "userId");
       int userId = 2;
      //查询已读的消息
      PageInfo pageInfoIsRead = messageService.searchMessage(userId, 1, page, limit);

      //查询未读消息
      PageInfo pageInfoNotRead = messageService.searchMessage(userId, 0, page, limit);

        Map map = new HashMap();
        map.put("pageInfoIsRead",pageInfoIsRead);
        map.put("pageInfoNotRead",pageInfoNotRead);

        System.out.println(map);

        return APIResult.genSuccessApiResponse(map);

    }

    @RequestMapping("user/loadnotreadmessage")
    public APIResult loadNotReadMessage(@RequestBody Map map, HttpServletRequest request){
        //此部分为用户的  获取token是输入
//        Cookie[] cookies = request.getCookies();
//        Cookie cookie = null;
//        for (Cookie c:cookies) {
//               if("user".equals(c.getName())){
//                   cookie = c;
//               }
//        }
//       String string = cookie.getValue().toString();
//      int userId = (int) TokenUtil.getTokenValue(string, "userId");
        int userId = 2;
        int page = 0;
        if(map.get("page") != null){
            page = Integer.parseInt(map.get("page").toString());
        }
        int limit = 10;
        if(map.get("limit") != null){
            limit = Integer.parseInt(map.get("limit").toString());
        }

        //查询未读消息
        PageInfo pageInfoNotRead = messageService.searchMessage(userId, 0, page, limit);

        return APIResult.genSuccessApiResponse(pageInfoNotRead);

    }

    @RequestMapping("user/loadisreadmessage")
    public APIResult loadIsReadMessage(@RequestBody Map map, HttpServletRequest request){
        //此部分为用户的  获取token是输入
//        Cookie[] cookies = request.getCookies();
//        Cookie cookie = null;
//        for (Cookie c:cookies) {
//               if("user".equals(c.getName())){
//                   cookie = c;
//               }
//        }
//       String string = cookie.getValue().toString();
//      int userId = (int) TokenUtil.getTokenValue(string, "userId");
        int userId = 2;
        int page = 0;
        if(map.get("page") != null){
            page = Integer.parseInt(map.get("page").toString());
        }
        int limit = 10;
        if(map.get("limit") != null){
            limit = Integer.parseInt(map.get("limit").toString());
        }

        //查询未读消息
        PageInfo pageInfoIsRead = messageService.searchMessage(userId, 1, page, limit);

        return APIResult.genSuccessApiResponse(pageInfoIsRead);

    }

    @RequestMapping("user/addmessage")
    public APIResult addMessage(@RequestBody Map map){
        if(map.get("messageContent") == null && map.get("messageResourceId") == null
                && map.get("messageTarget") == null && map.get("messageType") == null){
            return APIResult.genFailApiResponse400("添加通知是发生参数错误");
        }

        Message message = new Message();
        message.setMessageContent((String) map.get("messageContent"));
        message.setMessageType(Byte.parseByte(map.get("messageType").toString()));
        message.setMessageResourceId(Integer.parseInt(map.get("messageResourceId").toString()));
        message.setMessageTarget(Integer.parseInt(map.get("messageTarget").toString()));
        message.setIsRead((byte) 0);
        message.setIsDeleted(false);

        notificationComponent.sendNotification(message);

        return APIResult.genSuccessApiResponse("发送通知成功");


    }

    @RequestMapping("user/updatemessage")
    public APIResult updateMessage(@RequestBody Map map){
        if(map.get("messageId") == null){
            return APIResult.genFailApiResponse400("更新通知是发生参数错误");
        }

        boolean b = messageService.updateMessage((Integer) map.get("messageId"));

        if(b){
            return APIResult.genSuccessApiResponse("修改通知成功");
        }

        return APIResult.genFailApiResponse400("修改通知失败");

    }

    @RequestMapping("user/delmessage")
    public APIResult delMessage(@RequestBody Map map){
        if(map.get("messageId") == null){
            return APIResult.genFailApiResponse400("更新通知是发生参数错误");
        }

        boolean b = messageService.delMessage((Integer) map.get("messageId"));

        if(b){
            return APIResult.genSuccessApiResponse("删除通知成功");
        }

        return APIResult.genFailApiResponse400("删除通知失败");

    }


}