package wang.haogui.yuanda.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wang.haogui.yuanda.model.Label;
import wang.haogui.yuanda.model.LabelExample;

import java.util.List;

@Mapper
public interface LabelMapper {
    long countByExample(LabelExample example);

    int deleteByExample(LabelExample example);

    int deleteByPrimaryKey(Integer labelId);

    int insert(Label record);

    int insertSelective(Label record);

    List<Label> selectByExample(LabelExample example);

    Label selectByPrimaryKey(Integer labelId);

    int updateByExampleSelective(@Param("record") Label record, @Param("example") LabelExample example);

    int updateByExample(@Param("record") Label record, @Param("example") LabelExample example);

    int updateByPrimaryKeySelective(Label record);

    int updateByPrimaryKey(Label record);

    /**
     * 通过connectionType，与connectionType，查询它所拥有的标签
     * @param connectId
     * @param connectionType
     * @return
     */
    List<Label> selectLabelNameByConnectionId(@Param("connectId") int connectId,@Param("connectionType") int connectionType);
}