package wang.haogui.yuanda.confign;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wang.haogui.yuanda.common.QueueEnum;

/**
 * @author whg
 * @date 2019/12/11 11:32
 **/
@Configuration
public class RabbitMqConfig {

    /**
     * 邮件发送消息实际消费队列所绑定的交换机
     */
    @Bean
    DirectExchange emailDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_SENT_EAILL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 发送邮箱实际消费队列
     */
    @Bean
    public Queue emailQueue() {
        return new Queue(QueueEnum.QUEUE_SENT_EAILL.getName());
    }

    /**
     * 将发送邮箱队列绑定到交换机
     */
    @Bean
    Binding orderBinding(DirectExchange emailDirect,Queue emailQueue){
        return BindingBuilder
                .bind(emailQueue)
                .to(emailDirect)
                .with(QueueEnum.QUEUE_SENT_EAILL.getRouteKey());
    }


    /**
     * 注册码邮件发送消息实际消费队列所绑定的交换机
     */
    @Bean
    DirectExchange registerEmailDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_SENT_REGISTER_CODE.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 注册码邮件发送消息实际消费队列
     */
    @Bean
    public Queue registerEmailQueue() {
        return new Queue(QueueEnum.QUEUE_SENT_REGISTER_CODE.getName());
    }

    /**
     * 注册码邮件队列绑定到交换机
     */
    @Bean
    Binding registerBinding(DirectExchange registerEmailDirect,Queue registerEmailQueue){
        return BindingBuilder
                .bind(registerEmailQueue)
                .to(registerEmailDirect)
                .with(QueueEnum.QUEUE_SENT_REGISTER_CODE.getRouteKey());
    }

    /**
     * 通知发送消息实际消费队列所绑定的交换机
     */
    @Bean
    DirectExchange notificationDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_SENT_NOTIFICATION.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 通知发送消息实际消费队列
     */
    @Bean
    public Queue notificationQueue() {
        return new Queue(QueueEnum.QUEUE_SENT_NOTIFICATION.getName());
    }

    /**
     * 通知队列绑定到交换机
     */
    @Bean
    Binding notificationBinding(DirectExchange notificationDirect,Queue notificationQueue){
        return BindingBuilder
                .bind(notificationQueue)
                .to(notificationDirect)
                .with(QueueEnum.QUEUE_SENT_NOTIFICATION.getRouteKey());
    }


}
