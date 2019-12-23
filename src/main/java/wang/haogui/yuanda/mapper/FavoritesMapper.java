package wang.haogui.yuanda.mapper;

import org.apache.ibatis.annotations.Param;
import wang.haogui.yuanda.model.Favorites;
import wang.haogui.yuanda.model.FavoritesExample;

import java.util.List;

public interface FavoritesMapper {
    long countByExample(FavoritesExample example);

    int deleteByExample(FavoritesExample example);

    int deleteByPrimaryKey(Integer favoritesId);

    int insert(Favorites record);

    int insertSelective(Favorites record);

    List<Favorites> selectByExample(FavoritesExample example);

    Favorites selectByPrimaryKey(Integer favoritesId);

    int updateByExampleSelective(@Param("record") Favorites record, @Param("example") FavoritesExample example);

    int updateByExample(@Param("record") Favorites record, @Param("example") FavoritesExample example);

    int updateByPrimaryKeySelective(Favorites record);

    int updateByPrimaryKey(Favorites record);
}