package wang.haogui.yuanda.utils;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.Properties;

/**
 * 发送邮件工具类
 * @author whg
 * @date 2019/12/3 22:12
 **/
@Component
public class SendMail implements Serializable {

    private static Message message = null;

    private static Session session = null;

    static {
        //做链接前的准备工作  也就是参数初始化
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host","smtp.qq.com");//发送邮箱服务器
        properties.setProperty("mail.smtp.port","465");//发送端口
        properties.setProperty("mail.smtp.auth","true");//是否开启权限控制
        properties.setProperty("mail.debug","true");//true 打印信息到控制台
        properties.setProperty("mail.transport","smtp");//发送的协议是简单的邮件传输协议
        properties.setProperty("mail.smtp.ssl.enable","true");
        //建立两点之间的链接
//        System.out.println("执行了2");
        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //邮箱和其开启权限qq给你的码
                return new PasswordAuthentication("1547527394@qq.com","jhxoncskcrpjiede");
            }
        });
        //不会在控制台输出太多内容
        session.setDebug(false);
        //创建邮件对象
        message = new MimeMessage(session);
    }

    /**
     * 输入验证码和账户 进行邮件发送
     * @param content
     * @param account
     */
    public static void sendMail(String title, String content, String account){

        //设置发件人
        try {
            message.setFrom(new InternetAddress("1547527394@qq.com"));
            //设置收件人
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(account));//收件人
            //设置主题
            message.setSubject(title);
            //设置邮件正文  第二个参数是邮件发送的类型
            message.setContent(content,"text/html;charset=UTF-8");
            System.out.println("开始执行");
            //发送一封邮件
            Transport transport = session.getTransport();
            //发送邮件的账号密码
            transport.connect("1547527394@qq.com","wanghaogui1998");
            Transport.send(message);
            System.out.println("信息发送完成");
        } catch (MessagingException e) {
            e.printStackTrace();
        }finally {

        }

    }

    public static void main(String[] args) {
        sendMail("验证码","111212","1547527394@qq.com");
    }
}
