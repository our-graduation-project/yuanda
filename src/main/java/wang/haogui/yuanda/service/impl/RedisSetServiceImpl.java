package wang.haogui.yuanda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.service.RedisSetService;

import java.util.List;
import java.util.Set;

/**
 * @author whg
 * @date 2019/12/28 16:51
 **/
@Service
public class RedisSetServiceImpl implements RedisSetService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 使其不序列化 不然会在key前面加东西
     * @param redisTemplate
     */
    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();//序列化为String
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);//序列化为Json
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        this.redisTemplate = redisTemplate;
    }
    /**
     * 存储数据
     *
     * @param key
     * @param value
     */
    @Override
    public void add(String key, Object value) {
        redisTemplate.opsForSet().add(key,value);
    }

    @Override
    public void add(String key, List list) {
        redisTemplate.opsForSet().add(key,list);
    }

    /**
     * 获取set所有数据
     *
     * @param key
     * @return
     */
    @Override
    public Set get(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 确定value是不是属于key的集合中
     * @param key
     * @param value
     * @return
     */
    public Boolean isMember(String key,String value){
        return redisTemplate.opsForSet().isMember(key,value);
    }

    /**
     * 获得set里面的数量
     * 对应redis中的命令  SCARD myset
     * @param key
     * @return
     */
    public Long scard(String key){
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 删除并获取set中的一个元素
     * @param key
     * @return
     */
    public Object pop(String key){
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 从key的set中删除value，返回值代表删除是否成功
     * @param key
     * @param value
     * @return
     */
    public Long pop(String key,Object value){
        return redisTemplate.opsForSet().remove(key,value);
    }

    /**
     * 从key中删除某一个元素
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Boolean srem(String key, Object value) {
        return redisTemplate.opsForSet().remove(key,value) > 0 ? true : false;
    }

    /**
     * 将a中的数据以入b中(a中的数据会消失)
     * @param a
     * @param b
     * @return
     */
    public Boolean removeAToB(String a,String b){
        //将a加入b
        Long aLong = redisTemplate.opsForSet().unionAndStore(b, a, b);
        if(aLong == 0){
            return false;
        }
        //删除a
        List pop = clear(a);
        if(pop.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * 清空key
     * @param key
     * @return
     */
    public List clear(String key){
        List pop = redisTemplate.opsForSet().pop(key, scard(key));
        return pop;
    }

}
