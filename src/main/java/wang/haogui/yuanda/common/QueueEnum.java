package wang.haogui.yuanda.common;


/**
 * 消息队列枚举，里面存了交换机名称，路由，队列名称
 * @author whg
 * @date 2019/12/11 11:21
 **/
public enum QueueEnum {

    /**
     * 邮件消息通知队列
     */
    QUEUE_SENT_EAILL("yuanda_direct", "yuanda_sent_email", "yuanda_sent_email"),

    /**
     * 消息通知队列
     */
    QUEUE_SENT_NOTIFICATION("yuanda_notification_direct", "yuanda_sent_notification", "yuanda_sent_notification"),

    /**
     * 注册码发送队列
     */
    QUEUE_SENT_REGISTER_CODE("yuanda_register_direct","yuanda_sent_register","yuanda_sent_register"),

    /**
     * esarticle的发送队列
     */
    QUEUE_SENT_ES_ARTICLE_DATA("yuanda_es_article_direct","yuanda_sent_es_article","yuanda_sent_es_article");

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


    public String getExchange() {
        return exchange;
    }

    public String getName() {
        return name;
    }

    public String getRouteKey() {
        return routeKey;
    }
}
