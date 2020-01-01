package wang.haogui.yuanda.component;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.haogui.yuanda.utils.LogUtils;


/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 16:33 2019/12/31
 * @Modifued By:
 */
@Component
public class SendNotificationQueue {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 将消息发送到对应的交换机中
     * @param exchangeName
     * @param routeKey
     * @param message
     */
    public void sendMessage(String exchangeName, String routeKey, Message message){
        //给延迟队列发送消息
        System.out.println("队列："+exchangeName+"++++"+routeKey+"++++"+message);
        amqpTemplate.convertAndSend(exchangeName, routeKey, message);
        LogUtils.getTimeOutTaskLogger().info("添加" + message + "到消息队列中去");
    }

    public Message setMessage(String json){
        MessageProperties messageProperties = new MessageProperties();
        Message message = new Message(json.getBytes(), messageProperties);
        //消息持久化
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        return message;
    }
}