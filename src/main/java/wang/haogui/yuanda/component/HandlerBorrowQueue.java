package wang.haogui.yuanda.component;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import wang.haogui.yuanda.utils.LogUtils;
import wang.haogui.yuanda.utils.SendMail;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 监听book_manager_sent_email 队列是否有内容
 * @author whg
 * @date 2019/12/11 14:48
 **/
@Component
public class HandlerBorrowQueue {

    @RabbitHandler
    @RabbitListener(queues = "yuanda_sent_email")
    public void handle(Message message) {
        byte[] body = message.getBody();
        String s = new String(body);
        JSONObject jsonObject = JSON.parseObject(s);
        StringBuffer sb = new StringBuffer();
        String time = jsonObject.get("shouldReturnTime").toString();
        Date date = new Date(time);
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = format0.format(date);
        sb.append("尊敬的" + jsonObject.get("userName") + "您借的书" + jsonObject.get("bookName") + "到期日期为：" + time + "请尽快还书");
        SendMail.sendMail("图书馆催还信息",sb.toString(),jsonObject.get("mailbox").toString());
        LogUtils.getTimeOutTaskLogger().info("对" + jsonObject.get("name") + "发送了一封催还邮件");
    }
}
