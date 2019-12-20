package wang.haogui.yuanda.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.service.RedisStringService;

import java.util.concurrent.TimeUnit;

/**
 * 这里操作的都是String
 * @author whg
 * @date 2019/12/12 21:55
 **/
@Service
public class RedisStringServiceImpl implements RedisStringService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 存储数据
     *
     * @param key
     * @param value
     */
    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取数据
     *
     * @param key
     */
    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 设置超期时间
     *
     * @param key
     * @param expire
     */
    @Override
    public boolean expire(String key, long expire) {
        return stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 删除数据
     *
     * @param key
     */
    @Override
    public void remove(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 自增操作
     *
     * @param key
     * @param delta 自增步长
     */
    @Override
    public Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key,delta);
    }
}
