package wang.haogui.yuanda.mapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.haogui.yuanda.YuandaApplication;
import wang.haogui.yuanda.service.AdminService;

import java.sql.Driver;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author whg
 * @date 2020/4/5 14:47
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YuandaApplication.class)
class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    void decreaseCommentNumberByPrimaryKey() {
        int i = articleMapper.decreaseCommentNumberByPrimaryKey(512, 1);
        System.out.println("i = " + i);
    }

    void testJdbc(){

    }
}