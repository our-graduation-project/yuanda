package wang.haogui.yuanda.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.mapper.AnswerMapper;
import wang.haogui.yuanda.model.Answer;
import wang.haogui.yuanda.model.AnswerExample;
import wang.haogui.yuanda.model.Question;
import wang.haogui.yuanda.model.QuestionExample;
import wang.haogui.yuanda.service.AnswerService;
import wang.haogui.yuanda.utils.CommonUtils;
import wang.haogui.yuanda.utils.LogUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 向清润
 * @createTime 2019.12.23.12:02
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    @Resource(type = AnswerMapper.class)
    AnswerMapper answerMapper;




    /**
     * 添加回答
     *
     * @param answer 回答数据
     * @return 是否成功
     */
    @Override
    public boolean addAnswer(Answer answer) {

        int len = 0;
        len = answerMapper.insert(answer);
        if(len > 0){
            return true;
        }
        return false;
    }

    /**
     * 批量增加回答
     *
     * @param answers 回答的信息数据
     * @return 是否成功
     */
    @Override
    public boolean addBatchAnswer(List<Answer> answers) {
        int len = 0;
        len = answerMapper.addBatchAnswer(answers);
        if(len == answers.size()){
            return true;
        }else {
            LogUtils.getDBLogger().info("数据库回答批量增加失败");
            throw new RuntimeException("数据库回答批量增加失败");
        }



    }

    /**
     * 删除回答
     *
     * @param answerId 回答的Id
     * @return 是否成功
     */
    @Override
    public boolean deleteAnswer(int answerId) {
        int len = 0;
        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        answer.setIsDeleted(true);
        len = answerMapper.updateByPrimaryKeySelective(answer);
        if(len > 0){
            return true;
        }
        return false;
    }


    /**
     * 通用查询
     * @param page 当前页码
     * @param limit 页码大小
     * @param answerExample 条件
     * @return 查询出来的结果
     */
    private PageInfo<Answer> select(int page,int limit,AnswerExample answerExample){

        PageHelper.startPage(page,limit);
        List<Answer> answers = answerMapper.selectByExample(answerExample);
        PageInfo<Answer> pageInfo = new PageInfo(answers,5);

        return pageInfo;
    }

    /**
     * 按照问题查询回答
     *
     * @param questionId 问题的id
     * @param page       当前页面
     * @param limit      页面大小
     * @param order      排序列
     * @param orderEnum  升序或者降序
     * @return 查询出来的分页回答
     */
    @Override
    public PageInfo<Answer> selectAnswerByQuestionId(int questionId, int page, int limit, String order, OrderEnum orderEnum) {
        AnswerExample answerExample = new AnswerExample();
        answerExample.or().andIsDeletedEqualTo(false).andQuestionIdEqualTo(questionId);

        if(order!=null&&!"".equals(order)){
            String str = CommonUtils.orderStr(order, orderEnum);
            System.out.println(str);
            answerExample.setOrderByClause(str);
        }
        PageInfo<Answer> pageInfo = select(page, limit, answerExample);
        return pageInfo;
    }

    /**
     * 按照用户id查询回答
     *
     * @param userId    用户的id
     * @param page      当前页面
     * @param limit     页面大小
     * @param order     排序列
     * @param orderEnum 升序或者降序
     * @return 查询出来的分页回答
     */
    @Override
    public PageInfo<Answer> selectAnswerByUserId(int userId, int page, int limit, String order, OrderEnum orderEnum) {
        AnswerExample answerExample = new AnswerExample();
        answerExample.or().andIsDeletedEqualTo(false).andAuthorIdEqualTo(userId);

        if(order!=null&&!"".equals(order)){
            String str = CommonUtils.orderStr(order, orderEnum);
            answerExample.setOrderByClause(str);
        }
        PageInfo<Answer> pageInfo = select(page, limit, answerExample);
        return pageInfo;
    }

    /**
     * 查询所有未删除回答
     *
     * @param page      当前页面
     * @param limit     页面大小
     * @param order     排序列
     * @param orderEnum 升序或者降序
     * @return 查询出来的分页回答
     */
    @Override
    public PageInfo<Answer> selectAnswers(int page, int limit, String order, OrderEnum orderEnum) {
        AnswerExample answerExample = new AnswerExample();
        answerExample.or().andIsDeletedEqualTo(false);

        if(order!=null&&!"".equals(order)){
            String str = CommonUtils.orderStr(order, orderEnum);
            answerExample.setOrderByClause(str);
        }
        PageInfo<Answer> pageInfo = select(page, limit, answerExample);
        return pageInfo;
    }
}
