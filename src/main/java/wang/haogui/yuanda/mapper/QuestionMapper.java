package wang.haogui.yuanda.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wang.haogui.yuanda.model.Question;
import wang.haogui.yuanda.model.QuestionExample;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionMapper {
    long countByExample(QuestionExample example);

    int deleteByExample(QuestionExample example);

    int deleteByPrimaryKey(Integer questionId);

    int insert(Question record);

    int insertSelective(Question record);

    List<Question> selectByExample(QuestionExample example);

    Question selectByPrimaryKey(Integer questionId);

    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    int addBatchQuestion(List<Question> questions);

    List<Question> selectByTypeName(Map map);

    List<Question> selectByTypeId(Map map);

    int updateCheckStatusPass(List<Integer> questionIds);

    int updateCheckStatusFail(List<Integer> questionIds);

    int deleteQuestion(List<Integer> questionIds);

}