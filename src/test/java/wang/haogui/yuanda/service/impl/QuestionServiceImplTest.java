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
import wang.haogui.yuanda.model.Question;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 向清润
 * @createTime 2019.12.21.11:47
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YuandaApplication.class)
class QuestionServiceImplTest {

    @Autowired
    QuestionServiceImpl questionService;

    @Test
    void addQuestion() {
        Question question = new Question("这个能写完吗？","aaaaaaaaaaa",3,4,22,(byte)1,true,"张老三",1,new Date(),2);

        boolean b = questionService.addQuestion(question);
        System.out.println(b);
    }

    @Test
    void addBatchQuestion() {

        List<Question> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            Question question = new Question("这个能写完吗?","aaaaaaaaaaa",4+i,5+i,22+i,(byte)1,true,"张老三",1+i,new Date(),2);

            list.add(question);
        }
        boolean b = questionService.addBatchQuestion(list);
        System.out.println(b);
    }

    @Test
    void selectQuestionById() {
        Question question = questionService.selectQuestionById(6);
        System.out.println(question);
    }

    @Test
    void selectQuestionByUerId() {
        PageInfo<Question> pageInfo = questionService.selectQuestionByUerId(2, 1, 5, "create_time", OrderEnum.ASC);
        List<Question> list =pageInfo.getList();
        for (Question question:
             list) {
            System.out.println(question.getCreateTime());

        }
    }

    @Test
    void selectQuestionByName() {
        PageInfo<Question> pageInfo = questionService.selectQuestionByName("这个能写完吗?", 1, 3, "hot", OrderEnum.ASC);
        List<Question> list = pageInfo.getList();
        for (Question q:
             list) {
            System.out.println(q.getHot());

        }
    }

    @Test
    void selectQuestionByTypeId() {
        PageInfo<Question> pageInfo = questionService.selectQuestionByTypeId(1, 1, 5, "hot", OrderEnum.DESC);
        List<Question> list = pageInfo.getList();
        for (Question q:
                list) {
            System.out.println(q.getQuestionId()+":"+q.getHot());

        }
    }

    @Test
    void selectQuestionByTypeName() {
        PageInfo<Question> pageInfo = questionService.selectQuestionByTypeName("学习", 1, 5, "hot", OrderEnum.DESC);
        List<Question> list = pageInfo.getList();
        for (Question q:
                list) {
            System.out.println(q.getQuestionId()+":"+q.getHot());

        }
    }

    @Test
    void selectQuestions() {
        PageInfo<Question> pageInfo = questionService.selectQuestions(1, 10, "hot", OrderEnum.DESC);
        List<Question> list = pageInfo.getList();
        for (Question q:
                list) {
            System.out.println(q.getQuestionId()+":"+q.getHot());

        }
    }

    @Test
    void deleteQuestions() {
        boolean b = questionService.deleteQuestion(1);
        System.out.println(b);
    }

    @Test
    void updateCheckStatus(){
        List<Integer> ids = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ids.add(i);
        }
        boolean b = questionService.updateCheckStatus(ids, CheckEnum.CHECKFAILL);
        System.out.println(b);
    }
}