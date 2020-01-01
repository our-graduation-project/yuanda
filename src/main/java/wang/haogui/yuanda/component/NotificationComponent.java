package wang.haogui.yuanda.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.haogui.yuanda.common.QueueEnum;
import wang.haogui.yuanda.model.Message;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 18:33 2019/12/31
 * @Modifued By:
 */
@Component
public class NotificationComponent {
    @Autowired
    private SendNotificationQueue sendNotificationQueue;


    public void sendNotification(Message message){
        Object json = JSON.toJSON(message);
        System.out.println("json = " + json);
        org.springframework.amqp.core.Message messageObj = sendNotificationQueue.setMessage(json.toString());
        System.out.println(messageObj);
        sendNotificationQueue.sendMessage(QueueEnum.QUEUE_SENT_NOTIFICATION.getExchange(),
                QueueEnum.QUEUE_SENT_NOTIFICATION.getRouteKey(),messageObj);
    }

}