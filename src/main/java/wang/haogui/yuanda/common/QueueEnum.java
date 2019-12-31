package wang.haogui.yuanda.common;

import lombok.Getter;

/**
 * 消息队列枚举，里面存了交换机名称，路由，队列名称
 * @author whg
 * @date 2019/12/11 11:21
 **/
@Getter
public enum QueueEnum {

    /**
     * 消息通知队列
     */
    QUEUE_SENT_EAILL("yuanda_direct", "yuanda_sent_email", "yuanda_sent_email"),


    /**
     * 注册码发送队列
     */
    QUEUE_SENT_REGISTER_CODE("yuanda_register_direct","yuanda_sent_register","yuanda_sent_register"),

    /**
     * esarticle的发送队列
     */
    QUEUE_SENT_ES_ARTICLE_DATA("yuanda_es_article_direct","yuanda_sent_es_article","yuanda_sent_es__article");

    /**
     * 交换名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
