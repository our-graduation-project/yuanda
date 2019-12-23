package wang.haogui.yuanda.service.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.haogui.yuanda.YuandaApplication;
import wang.haogui.yuanda.common.CheckEnum;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.model.Answer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 向清润
 * @createTime 2019.12.23.15:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YuandaApplication.class)
class AnswerServiceImplTest {

    @Autowired
    AnswerServiceImpl answerService;

    @Test
    void addAnswer() {
        Answer answer = new Answer(11,3,CheckEnum.HVAEDNOCHECK.getStatus(),10,new Date(),10,(byte)1,"李阿明",false,1,1,"今天能做完吗","aaa.sda","当然能够写完啊");
        boolean b = answerService.addAnswer(answer);
        System.out.println(b);
    }

    @Test
    void addBatchAnswer()  {
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Answer answer = new Answer(11+i,3+i,CheckEnum.HVAEDNOCHECK.getStatus(),10,new Date(),10,(byte)1,"李阿明",false,1,1,"今天能做完吗"+i,"aaa.sda"+i,"当然能够写完啊"+i);
            answers.add(answer);
        }
        boolean b = false;
        try {
            b = answerService.addBatchAnswer(answers);
        }catch (Exception e){
            System.out.println("hhhhhh");
        }


        System.out.println(b);
    }

    @Test
    void deleteAnswer() {
        boolean b = answerService.deleteAnswer(3);
        System.out.println(b);
    }

    @Test
    void selectAnswerByQuestionId() {
        PageInfo<Answer> pageInfo = answerService.selectAnswerByQuestionId(1, 1, 10, "", null);
        List<Answer> list = pageInfo.getList();
        for (Answer a :
                list) {
            System.out.println(a.getAnswerId()+":"+a.getAgreeNumber());
        }
    }

    @Test
    void selectAnswerByUserId() {
        PageInfo<Answer> pageInfo = answerService.selectAnswerByUserId(1, 1, 10, "agree_number", OrderEnum.ASC);
        List<Answer> list = pageInfo.getList();
        for (Answer a :
                list) {
            System.out.println(a.getAnswerId()+":"+a.getAgreeNumber());
        }
    }

    @Test
    void selectAnswers() {
        PageInfo<Answer> pageInfo = answerService.selectAnswers( 1, 10, "agree_number", OrderEnum.ASC);
        List<Answer> list = pageInfo.getList();
        for (Answer a :
                list) {
            System.out.println(a.getAnswerId()+":"+a.getAgreeNumber());
        }
    }

    @Test
    void updateCheckStatus(){
        List<Integer> ids = new ArrayList<>();
        for (int i = 3; i <= 10; i++) {
            ids.add(i);
        }
        boolean b = answerService.updateCheckStatus(ids, CheckEnum.CHECKFAILL);
        System.out.println(b);
    }
}