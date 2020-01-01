package wang.haogui.yuanda.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.haogui.yuanda.common.QueueEnum;
import wang.haogui.yuanda.service.MessageService;
import wang.haogui.yuanda.utils.LogUtils;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 16:54 2019/12/31
 * @Modifued By:
 */
@Component
public class HandlerNotificationQueue {
    @Autowired
    private MessageService  messageService;

    @RabbitHandler
    @RabbitListener(queues = "yuanda_sent_notification")
    public void handle(Message message) {
        byte[] body = message.getBody();
        String s = new String(body);
        JSONObject jsonObject = JSON.parseObject(s);
        wang.haogui.yuanda.model.Message message1 = new wang.haogui.yuanda.model.Message();
        message1.setMessageContent(jsonObject.get("messageContent").toString());
        message1.setMessageType(Byte.parseByte(jsonObject.get("messageType").toString()));
        message1.setMessageResourceId(Integer.parseInt(jsonObject.get("messageResourceId").toString()));
        message1.setMessageTarget(Integer.parseInt(jsonObject.get("messageTarget").toString()));
        message1.setIsRead((byte) 0);
        message1.setIsDeleted(false);
        boolean b = messageService.addMessage(message1);
        //如果插入数据库失败
        if(!b){
            LogUtils.getDBLogger().info("对" + jsonObject.get("messageTarget") + "发送通知失败！"
            +"消息内容"+s);
        }
        LogUtils.getTimeOutTaskLogger().info("对" + jsonObject.get("messageTarget") + "发送了条通知！");
    }

}