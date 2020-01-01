package wang.haogui.yuanda.utils;

/**
 * @author whg
 * @date 2019/12/28 16:39
 **/
public class RedisKeyUtil {

    private static String SPLIT = ":";

    private static String BIZ_LIKE = "LIKE";

    private static String BIZ_DISLIKE = "DISLIKE";

    private static String LABEL_NAME_NOT_ADD_REDIS = "NOTADDLABELNAME";

    private static String LABEL_NAME_HAVE_ADD_REDIS = "HAVEADDLABELNAME";

    private static String ARTICLE_NAME_NOT_ADD_REDIS = "NOTADDARTICLENAME";

    private static String ARTICLE_NAME_HAVE_ADD_REDIS = "HAVEADDARTICLENAME";

    private static String ARTICLE_NAME = "ARTICLENAME";

    /**
     * 获得喜欢set的名称
     * @param entityType
     * @param entityId
     * @return
     */
    public static String getLikeKey(int entityType, int entityId){
        return BIZ_LIKE + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }

    /**
     * 获得不喜欢的set的key
     * @param entityType
     * @param entityId
     * @return
     */
    public static String getDisLikeKey(int entityType, int entityId){
        return BIZ_DISLIKE + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }

    /**
     * 获得还没有加入数据库的label的key
     * @return
     */
    public static String getNotAddLabelKey(){
        return LABEL_NAME_NOT_ADD_REDIS;
    }

    /**
     * 获得已经加入数据库的label的key
     * @return
     */
    public static String getHaveAddLabelKey(){
        return LABEL_NAME_HAVE_ADD_REDIS;
    }

    /**
     * 获得没有加入redis的article的key(里面存储文章的url)
     * @return
     */
    public static String getNotAddRedisArticleKey(){
        return ARTICLE_NAME_NOT_ADD_REDIS;
    }

    /**
     * 获得加入了redis的article的key(里面存储文章的url)
     * @return
     */
    public static String getHaveAddRedisArticleKey(){
        return ARTICLE_NAME_HAVE_ADD_REDIS;
    }

    /**
     * 获得存储Article详情的redisKey(存入数据库了的就会冲这里移除)
     * @return
     */
    public static String getArticleKey(){
        return ARTICLE_NAME;
    }
}
