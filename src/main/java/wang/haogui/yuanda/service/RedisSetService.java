package wang.haogui.yuanda.service;

import java.util.List;
import java.util.Set;

/**
 * @author whg
 * @date 2019/12/28 16:51
 **/
public interface RedisSetService {

    /**
     * 存储数据
     */
    void add(String key, Object value);

    void add(String key, List list);

    /**
     * 获取数据
     * @return
     */
    Set get(String key);

    Boolean isMember(String key,String value);

    Long scard(String key);

    Object pop(String key);

    Long pop(String key,Object value);

    /**
     * 从key中删除某一个元素
     * @param key
     * @param value
     * @return
     */
    Boolean srem(String key,Object value);

    /**
     * 将A的数据移入B
     * @param a
     * @param b
     * @return
     */
    Boolean removeAToB(String a, String b);

    /**
     * 清空key
     * @param key
     * @return
     */
    List clear(String key);
}
