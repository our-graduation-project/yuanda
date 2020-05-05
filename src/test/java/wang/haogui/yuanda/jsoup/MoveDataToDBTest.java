package wang.haogui.yuanda.jsoup;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.haogui.yuanda.YuandaApplication;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author whg
 * @date 2019/12/28 19:35
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YuandaApplication.class)
class MoveDataToDBTest {

    @Autowired
    MoveDataToDB moveDataToDB;

    @Test
    void getDataInRedis() {
        moveDataToDB.getDataInRedis();
    }
}