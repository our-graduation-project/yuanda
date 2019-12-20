package wang.haogui.yuanda.component;


import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.haogui.yuanda.common.QueueEnum;

/**
 * 将注册码发送到对应的交换机
 * @author whg
 * @date 2019/12/12 20:24
 **/
@Component
public class SendRegisterToQueue {

    @Autowired
    private SenderToQueue senderToQueue;

    /**
     * 传入注册码，邮箱地址
     * @param code
     */
    public void sendToQueue(String code, String email){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("mailbox",email);
        sendToQueue(jsonObject);
    }

    /**
     * 传入带有 注册码，邮箱地址的json
     * @param jsonObject
     */
    public void sendToQueue(JSONObject jsonObject){
        senderToQueue.sendMessage(QueueEnum.QUEUE_SENT_REGISTER_CODE.getExchange(),QueueEnum.QUEUE_SENT_REGISTER_CODE.getRouteKey(),setMessage(jsonObject.toJSONString()));
    }


    /**
     * 设置消息参数
     * @param json
     * @return
     */
    public Message setMessage(String json){
        MessageProperties messageProperties = new MessageProperties();
        Message message = new Message(json.getBytes(), messageProperties);
        //消息持久化
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        return message;
    }
}
