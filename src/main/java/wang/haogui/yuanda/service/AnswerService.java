package wang.haogui.yuanda.service;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.common.CheckEnum;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.model.Answer;

import java.util.List;

/**
 * @author 向清润
 * @createTime 2019.12.23.11:25
 */
public interface AnswerService {

    /**
     * 添加回答
     * @param answer 回答数据
     * @return 是否成功
     */
    boolean addAnswer(Answer answer);

    /**
     * 批量增加回答
     * @param answers 回答的信息数据
     * @return 是否成功
     */
    boolean addBatchAnswer(List<Answer> answers) throws Exception;

    /**
     * 删除回答
     * @param answerIds 回答的Id
     * @return 是否成功
     */
    boolean deleteAnswers(List<Integer> answerIds);


    /**
     * 按照问题的ID批量删除回答
     * @param questionIds 问题的Id
     * @return 是否成功
     */
    boolean deleteAnswersbyQuestionId(List<Integer> questionIds);


    /**
     * 按照问题查询回答
     * @param questionId 问题的id
     * @param page 当前页面
     * @param limit 页面大小
     * @param order 排序列
     * @param orderEnum 升序或者降序
     * @return 查询出来的分页回答
     */
    PageInfo<Answer> selectAnswerByQuestionId(int questionId, int page, int limit, String order, OrderEnum orderEnum);


    /**
     * 按照用户id查询回答
     * @param userId 问题的id
     * @param page 当前页面
     * @param limit 页面大小
     * @param order 排序列
     * @param orderEnum 升序或者降序
     * @return 查询出来的分页回答
     */
    PageInfo<Answer> selectAnswerByUserId(int userId, int page, int limit, String order, OrderEnum orderEnum);


    /**
     * 查询未删除回答
     * @param page 当前页面
     * @param limit 页面大小
     * @param order 排序列
     * @param orderEnum 升序或者降序
     * @return 查询出来的分页回答
     */
    PageInfo<Answer> selectAnswers(int page, int limit, String order, OrderEnum orderEnum);

    /**
     * 根据id查询回答
     * @param answerId 回答的id
     * @return 查询出来的数据
     */
    Answer selectAnswerById(int answerId);
    /**
     * 批量审核
     * @param answerId 需要审核的答案的Id
     * @return 返回是否成功
     */
    boolean updateCheckStatus(List<Integer> answerId, CheckEnum checkEnum);
}
