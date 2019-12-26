package wang.haogui.yuanda.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wang.haogui.yuanda.model.FavoritesConnection;
import wang.haogui.yuanda.model.FavoritesConnectionExample;

import java.util.List;

@Mapper
public interface FavoritesConnectionMapper {
    long countByExample(FavoritesConnectionExample example);

    int deleteByExample(FavoritesConnectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FavoritesConnection record);

    int insertSelective(FavoritesConnection record);

    List<FavoritesConnection> selectByExample(FavoritesConnectionExample example);

    FavoritesConnection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FavoritesConnection record, @Param("example") FavoritesConnectionExample example);

    int updateByExample(@Param("record") FavoritesConnection record, @Param("example") FavoritesConnectionExample example);

    int updateByPrimaryKeySelective(FavoritesConnection record);

    int updateByPrimaryKey(FavoritesConnection record);
}