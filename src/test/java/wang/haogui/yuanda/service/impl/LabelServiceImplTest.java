package wang.haogui.yuanda.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.haogui.yuanda.YuandaApplication;
import wang.haogui.yuanda.model.Label;
import wang.haogui.yuanda.service.LabelService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author whg
 * @date 2019/12/23 14:44
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YuandaApplication.class)
class LabelServiceImplTest {

    @Autowired
    private LabelService labelService;

//    @Test
//    void addLabel() {
//        Label label = new Label("生活",false);
//        Assert.assertTrue(labelService.addLabel(label));
//    }

    @Test
    void deleteLabel() {
        try {
            Assert.assertTrue(labelService.deleteLabel(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void selectLabel(){
        labelService.selectLabel(0, 5).getList()
                .forEach(
                        label-> System.out.println(label.getLabelName())
                );
    }
}