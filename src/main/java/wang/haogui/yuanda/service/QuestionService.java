package wang.haogui.yuanda.service;

import com.github.pagehelper.PageInfo;
import wang.haogui.yuanda.common.CheckEnum;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.model.Question;

import java.util.List;

/**
 * @author 向清润
 * @createTime 2019.12.21.9:54
 */
public interface QuestionService {


    /**
     *增加问题的操作
     * @param question 问题数据
     * @return 是否增加成功
     */
    boolean addQuestion(Question question);


    /**
     * 批量增加问题的操作
     * @param questions 问题数据列表
     * @return 是否增加成功
     */
    boolean addBatchQuestion(List<Question> questions);


    /**
     * 根据问题Id查询用户
     * @param questionId 问题的id
     * @return 该问题的数据
     */
    Question selectQuestionById(int questionId);

    /**
     * 根据 用户id分页查询问题
     * @param userId 用户id
     * @param page 当前页码
     * @param limit 页面大小
     * @param order 排序列
     * @param orderEnum 是否升序
     * @return 查询出来的问题列表
     */
    PageInfo<Question> selectQuestionByUerId(int userId, int page, int limit, String order, OrderEnum orderEnum);


    /**
     * 根据问题名称分页查询问题
     * @param name 问题名称
     * @param page 当前页码
     * @param limit 页面大小
     * @param order 排序列
     * @param orderEnum 是否升序
     * @return 查询出来的问题列表
     */
    PageInfo<Question> selectQuestionByName(String name, int page, int limit, String order, OrderEnum orderEnum);


    /**
     * 根据标签类别查询问题
     * @param typeId 标签的id
     * @param page 当前页码
     * @param limit 页面大小
     * @param order 排序列
     * @param orderEnum 是否升序
     * @return 查询出来的问题
     */
    PageInfo<Question> selectQuestionByTypeId(int typeId, int page, int limit, String order, OrderEnum orderEnum);


    /**
     * 根据标签类别查询问题
     * @param typeName 标签名
     * @param page 当前页码
     * @param limit 页面大小
     * @param order 排序列
     * @param orderEnum 是否升序
     * @return 查询出来的问题
     */
    PageInfo<Question> selectQuestionByTypeName(String typeName, int page, int limit, String order, OrderEnum orderEnum);


    /**
     * 分页查询所有未删除的数据
     * @param page 当前页码
     * @param limit 页面大小
     * @param order 排序列
     * @param orderEnum 是否升序
     * @return 分页之后的数据
     */
    PageInfo<Question> selectQuestions(int page, int limit, String order, OrderEnum orderEnum);

    /**
     * 分页查询所有删除的数据
     * @param page 当前页码
     * @param limit 页面大小
     * @param order 排序列
     * @param orderEnum 是否升序
     * @return 分页之后的数据
     */
    PageInfo<Question> selectDeleteQuestions(int page, int limit, String order, OrderEnum orderEnum);



    /**
     * 批量删除
     * @param questionIds 问题的Id
     * @return 是否成功
     */
    boolean deleteQuestion(List<Integer> questionIds);


    /**
     * 批量审核
     * @param questionIds 需要审核的问题的Id
     * @return 返回是否成功
     */
    boolean updateCheckStatus(List<Integer> questionIds, CheckEnum checkEnum);

    /**
     * 改变问题的数据，比如热度，浏览数等
     * @param questionId 问题id
     * @param dateName 数据的名字
     * @param inOrDe 增加或减少数量
     * @return 操作是否成功
     */
    boolean updateDate(int questionId, String dateName, int inOrDe);

}
