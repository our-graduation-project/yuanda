package wang.haogui.yuanda.utils;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
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

        //建立两点之间的链接
//        System.out.println("执行了2");
        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //邮箱和其开启权限qq给你的码
                return new PasswordAuthentication("1547527394@qq.com","nqbcobekxuzyhfjb");
            }
        });
        //不会在控制台输出太多内容
        session.setDebug(false);
        //创建邮件对象
        message = new MimeMessage(session);
    }


    public static void sendMail(){
        String smtp ="smtp.qq.com";//SMTP服务器地址
        String from = "1547527394@qq.com";
        String to = "1547527394@qq.com";
        String copyto="1547527394@qq.com,1547527394@qq.com";
        String subject = " 狗年大吉";
//        String content ="<h1>狗年大吉吧</h1>";
        MimeBodyPart img = new MimeBodyPart();

        String content ="<div style='color:red;font-size:18px;'>从163发来的邮件</div>我这里有一张自拍";
        //正文中的图片
        String imgFile="C:\\Users\\lenovo\\Pictures\\Saved Pictures\\1.jpg";
        //附件
        String filename = "C:\\Users\\lenovo\\Pictures\\Saved Pictures\\1.jpg";
        //163邮箱用户名就是去掉@163.com
        String username = "1547527394@qq.com";
        String password = "nqbcobekxuzyhfjb";

//        Mail.send(smtp,from,to,subject,content,username,password);
//        JMailUtils.sendAndCcWithFile(smtp,from,to,subject,content,imgFile,username,password,copyto,filename);
        JMailUtils.send(smtp,from,to,subject,content,username,password);

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
            transport.connect("1547527394@qq.com","nqbcobekxuzyhfjb");
            Transport.send(message);
            System.out.println("信息发送完成");
        } catch (MessagingException e) {
            e.printStackTrace();
        }finally {

        }

    }

    public static void main(String[] args) {
//        sendMail("验证码","111212","408164188@qq.com");
        sendMail();
    }
}
