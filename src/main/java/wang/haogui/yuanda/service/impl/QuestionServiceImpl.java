package wang.haogui.yuanda.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.common.CheckEnum;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.mapper.QuestionMapper;
import wang.haogui.yuanda.model.Question;
import wang.haogui.yuanda.model.QuestionExample;
import wang.haogui.yuanda.service.QuestionService;
import wang.haogui.yuanda.utils.CommonUtils;
import wang.haogui.yuanda.utils.LogUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 有关于所有问题的业务操作
 * @author
 * @createTime 2019.12.21.10:48
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    @Resource(type = QuestionMapper.class)
    QuestionMapper questionMapper;
    /**
     * 增加问题的操作
     *
     * @param question 问题数据
     * @return 是否增加成功
     */
    @Override
    public boolean addQuestion(Question question) {
        int len = 0;
        len = questionMapper.insert(question);
        if(len >= 0 ){
            return true;
        }else {
            return false;
        }

    }

    /**
     * 批量增加问题的操作
     *
     * @param questions 问题数据列表
     * @return 是否增加成功
     */
    @Override
    public boolean addBatchQuestion(List<Question> questions) {
        int len = 0;
        len = questionMapper.addBatchQuestion(questions);
        if(len == questions.size()){
            return  true;
        }else {
            LogUtils.getDBLogger().info("数据库回答批量增加失败");
            throw new RuntimeException("数据库回答批量增加失败");

        }

    }

    /**
     * 根据问题Id查询问题
     *
     * @param questionId 问题的id
     * @return 该问题的数据
     */
    @Override
    public Question selectQuestionById(int questionId) {
        Question question = questionMapper.selectByPrimaryKey(questionId);
        if(question!=null&&question.getQuestionTitle()!=null){
            return question;
        }else {
            return null;
        }

    }





    /**
     * 通用查询
     * @param page 当前页码
     * @param limit 页码大小
     * @param questionExample 条件
     * @return 查询出来的结果
     */
    private PageInfo<Question> select(int page,int limit,QuestionExample questionExample){

        PageHelper.startPage(page,limit);
        List<Question> questions = questionMapper.selectByExample(questionExample);
        PageInfo<Question> pageInfo = new PageInfo(questions,5);

        return pageInfo;
    }


    /**
     * 根据 用户id分页查询问题
     *
     * @param userId 用户id
     * @param page   当前页码
     * @param limit  页面大小
     * @param order  排序列
     * @param orderEnum    是否升序
     * @return 查询出来的问题列表
     */
    @Override
    public PageInfo<Question> selectQuestionByUerId(int userId, int page, int limit, String order, OrderEnum orderEnum) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.or().andAuthorIdEqualTo(userId).andIsDeletedEqualTo(false);

        if(order!=null&&!"".equals(order)){
            String str = CommonUtils.orderStr(order, orderEnum);
            questionExample.setOrderByClause(str);
        }

        PageInfo<Question> pageInfo = select(page, limit, questionExample);
        return pageInfo;
    }

    /**
     * 根据问题名称分页查询问题
     *
     * @param name      问题名称
     * @param page      当前页码
     * @param limit     页面大小
     * @param order     排序列
     * @param orderEnum 是否升序
     * @return 查询出来的问题列表
     */
    @Override
    public PageInfo<Question> selectQuestionByName(String name, int page, int limit, String order, OrderEnum orderEnum) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.or().andQuestionTitleEqualTo(name).andIsDeletedEqualTo(false);

        if(order!=null&&!"".equals(order)){
            String str = CommonUtils.orderStr(order, orderEnum);
            questionExample.setOrderByClause(str);
        }
        PageInfo<Question> pageInfo = select(page, limit, questionExample);
        return pageInfo;
    }

    /**
     * 根据标签类别查询问题
     *
     * @param typeId    标签的id
     * @param page      当前页码
     * @param limit     页面大小
     * @param order     排序列
     * @param orderEnum 是否升序
     * @return 查询出来的问题
     */
    @Override
    public PageInfo<Question> selectQuestionByTypeId(int typeId, int page, int limit, String order, OrderEnum orderEnum) {
        if(typeId != 0){
            Map map = new HashMap();
            map.put("typeId",typeId);

            String str = null;
        if(order!=null&&!"".equals(order)){
           str  = CommonUtils.orderStr(order, orderEnum);
        }
            map.put("str",str);
        PageHelper.startPage(page,limit);
            List<Question> questions = questionMapper.selectByTypeId(map);
            PageInfo<Question> pageInfo = new PageInfo(questions,5);

        return pageInfo;
        }else {
            return  null;
        }

    }

    /**
     * 根据标签类别查询问题
     *
     * @param typeName  标签名
     * @param page      当前页码
     * @param limit     页面大小
     * @param order     排序列
     * @param orderEnum 是否升序
     * @return 查询出来的问题
     */
    @Override
    public PageInfo<Question> selectQuestionByTypeName(String typeName, int page, int limit, String order, OrderEnum orderEnum) {
        if(typeName == null&&"".equals(typeName)) {
        }
            Map map = new HashMap();
            map.put("typeName",typeName);

            String str = null;
            if(order!=null){
                str  = CommonUtils.orderStr(order, orderEnum);
            }
            map.put("str",str);
            PageHelper.startPage(page,limit);
            List<Question> questions = questionMapper.selectByTypeName(map);
            PageInfo<Question> pageInfo = new PageInfo(questions,5);

            return pageInfo;

    }

    /**
     * 分页查询所有未删除的数据
     *
     * @param page      当前页码
     * @param limit     页面大小
     * @param order     排序列
     * @param orderEnum 是否升序
     * @return 分页之后的数据
     */
    @Override
    public PageInfo<Question> selectQuestions(int page, int limit, String order, OrderEnum orderEnum) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.or().andIsDeletedEqualTo(false);

        if(order!=null&&!"".equals(order)){
            String str = CommonUtils.orderStr(order, orderEnum);
            questionExample.setOrderByClause(str);
        }
        PageInfo<Question> pageInfo = select(page, limit, questionExample);
        return pageInfo;
    }

    /**
     * 分页查询所有删除的数据
     *
     * @param page      当前页码
     * @param limit     页面大小
     * @param order     排序列
     * @param orderEnum 是否升序
     * @return 分页之后的数据
     */
    @Override
    public PageInfo<Question> selectDeleteQuestions(int page, int limit, String order, OrderEnum orderEnum) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.or().andIsDeletedEqualTo(true);

        if(order!=null){
            String str = CommonUtils.orderStr(order, orderEnum);
            questionExample.setOrderByClause(str);
        }
        PageInfo<Question> pageInfo = select(page, limit, questionExample);
        return pageInfo;
    }

    /**
     * 删除一个问题
     *
     * @param questionId 问题的Id
     * @return 是否成功
     */
    @Override
    public boolean deleteQuestion(int questionId) {
        Question question = new Question();
        question.setQuestionId(questionId);
        question.setIsDeleted(true);
        int i = questionMapper.updateByPrimaryKeySelective(question);
        if (i > 0){
            return true;
        }else {
            return false;
        }


    }

    /**
     * 批量审核
     *
     * @param questionIds 需要审核的问题的Id
     * @return 返回是否成功
     */
    @Override
    public boolean updateCheckStatus(List<Integer> questionIds,CheckEnum checkEnum) {
        int i = 0;
        if(checkEnum == CheckEnum.CHECKPASS){
            i = questionMapper.updateCheckStatusPass(questionIds);
        }else {
            i = questionMapper.updateCheckStatusFail(questionIds);
        }
        if(i == questionIds.size()){
            return true;
        }else {
            LogUtils.getDBLogger().info("数据库问题批量修改失败");
            throw new RuntimeException("数据库问题批量修改失败");
        }

    }


}
