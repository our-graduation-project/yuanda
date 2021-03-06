package wang.haogui.yuanda.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wang.haogui.yuanda.model.Answer;
import wang.haogui.yuanda.model.AnswerExample;

import java.util.List;

@Mapper
public interface AnswerMapper {
    long countByExample(AnswerExample example);

    int deleteByExample(AnswerExample example);

    int deleteByPrimaryKey(Integer answerId);

    int insert(Answer record);

    int insertSelective(Answer record);

    List<Answer> selectByExampleWithBLOBs(AnswerExample example);

    List<Answer> selectByExample(AnswerExample example);

    Answer selectByPrimaryKey(Integer answerId);

    int updateByExampleSelective(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByExampleWithBLOBs(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByExample(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKeyWithBLOBs(Answer record);

    int updateByPrimaryKey(Answer record);

    /**
     * 用于增减问题的评论数
     * @param record
     * @return
     */
    int updateForCommentNumber(Answer record);

    int addBatchAnswer(List<Answer> answers);

    int updateCheckStatusPass(List<Integer> answerId);

    int updateCheckStatusFail(List<Integer> answerId);

    int deleteAnswers(List<Integer> answerIds);

    int deleteAnswersbyQuestionId(List<Integer> questionIds);

    int decreaseCommentNumberByPrimaryKey(@Param("answerId") Integer answerId, @Param("number") int number);
}