package wang.haogui.yuanda.component;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.haogui.yuanda.utils.JMailUtils;
import wang.haogui.yuanda.utils.LogUtils;
import wang.haogui.yuanda.utils.SendMail;

/**
 *
 * @author whg
 * @date 2019/12/12 20:13
 **/
@Component
public class HandlerRegisterCodeQueue {


    @RabbitHandler
    @RabbitListener(queues = "yuanda_sent_register")
    public void handle(Message message) {
        byte[] body = message.getBody();
        String s = new String(body);
        JSONObject jsonObject = JSON.parseObject(s);
        StringBuffer sb = new StringBuffer();
        sb.append("这是源达发送的验证码：" + jsonObject.get("code") + "</br>5分钟后到期");
        JMailUtils.send(jsonObject.get("mailbox").toString(),"验证码",sb.toString());
        LogUtils.getTimeOutTaskLogger().info("对" + jsonObject.get("name") + "发送了注册码");
    }
}
