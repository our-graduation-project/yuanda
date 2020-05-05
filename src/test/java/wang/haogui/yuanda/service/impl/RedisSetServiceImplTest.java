package wang.haogui.yuanda.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.haogui.yuanda.YuandaApplication;
import wang.haogui.yuanda.utils.RedisKeyUtil;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author whg
 * @date 2019/12/28 18:23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YuandaApplication.class)
class RedisSetServiceImplTest {

    @Autowired
    private RedisSetServiceImpl redisSetService;

    @Test
    void add() {
    }

    @Test
    void add1() {
    }

    @Test
    void get() {
//        System.out.println(RedisKeyUtil.getLabelKey());
//        Set set = redisSetService.get(RedisKeyUtil.getLabelKey());
//        set.forEach(data->{
//            System.out.println("data = " + data);
//        });
    }

    @Test
    void isMember() {

    }

    @Test
    void scard() {
    }

    @Test
    void pop() {
    }

    @Test
    void pop1() {
    }
}