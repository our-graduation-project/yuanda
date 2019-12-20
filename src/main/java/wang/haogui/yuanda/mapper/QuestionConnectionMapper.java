package wang.haogui.yuanda.mapper;

import org.apache.ibatis.annotations.Param;
import wang.haogui.yuanda.model.QuestionConnection;
import wang.haogui.yuanda.model.QuestionConnectionExample;

import java.util.List;

public interface QuestionConnectionMapper {
    long countByExample(QuestionConnectionExample example);

    int deleteByExample(QuestionConnectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QuestionConnection record);

    int insertSelective(QuestionConnection record);

    List<QuestionConnection> selectByExample(QuestionConnectionExample example);

    QuestionConnection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QuestionConnection record, @Param("example") QuestionConnectionExample example);

    int updateByExample(@Param("record") QuestionConnection record, @Param("example") QuestionConnectionExample example);

    int updateByPrimaryKeySelective(QuestionConnection record);

    int updateByPrimaryKey(QuestionConnection record);
}