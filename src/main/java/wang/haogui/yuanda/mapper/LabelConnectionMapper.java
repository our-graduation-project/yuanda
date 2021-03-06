package wang.haogui.yuanda.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wang.haogui.yuanda.model.LabelConnection;
import wang.haogui.yuanda.model.LabelConnectionExample;

import java.util.List;

@Mapper
public interface LabelConnectionMapper {
    long countByExample(LabelConnectionExample example);

    int deleteByExample(LabelConnectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LabelConnection record);

    int insertSelective(LabelConnection record);

    List<LabelConnection> selectByExample(LabelConnectionExample example);

    LabelConnection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LabelConnection record, @Param("example") LabelConnectionExample example);

    int updateByExample(@Param("record") LabelConnection record, @Param("example") LabelConnectionExample example);

    int updateByPrimaryKeySelective(LabelConnection record);

    int updateByPrimaryKey(LabelConnection record);

    /**
     * 通过标签id软删除，与标签相关的全部联系记录
     * @param labelConnection
     * @return
     */
    int deleteBatchByLabelId(LabelConnection labelConnection);

    /**
     * 批量插入
     * @param labelConnectionList
     * @return
     */
    int insertBatch(List<LabelConnection> labelConnectionList);
}