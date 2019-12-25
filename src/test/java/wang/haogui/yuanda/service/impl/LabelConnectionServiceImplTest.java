package wang.haogui.yuanda.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.haogui.yuanda.YuandaApplication;
import wang.haogui.yuanda.model.LabelConnection;
import wang.haogui.yuanda.service.LabelConnectionService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author whg
 * @date 2019/12/23 14:51
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YuandaApplication.class)
class LabelConnectionServiceImplTest {

    @Autowired
    private LabelConnectionService labelConnectionService;

//    @Test
//    void addLabelConnection() {
//        LabelConnection labelConnection = new LabelConnection(1,1,false,false);
//        LabelConnection labelConnection2 = new LabelConnection(1,2,true,false);
//        Assert.assertTrue(labelConnectionService.addLabelConnection(labelConnection2));
//    }

    @Test
    void deleteLabelConnection() {
        Assert.assertTrue(labelConnectionService.deleteLabelConnection(1));
    }

    @Test
    void deleteBatchByLabelId(){
        Assert.assertTrue(labelConnectionService.deleteBatchByLabelId(1));
    }
}