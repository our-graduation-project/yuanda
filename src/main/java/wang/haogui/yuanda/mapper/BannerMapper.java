package wang.haogui.yuanda.mapper;

import org.apache.ibatis.annotations.Param;
import wang.haogui.yuanda.model.Banner;
import wang.haogui.yuanda.model.BannerExample;

import java.util.List;

public interface BannerMapper {
    long countByExample(BannerExample example);

    int deleteByExample(BannerExample example);

    int deleteByPrimaryKey(Integer bannerId);

    int insert(Banner record);

    int insertSelective(Banner record);

    List<Banner> selectByExample(BannerExample example);

    Banner selectByPrimaryKey(Integer bannerId);

    int updateByExampleSelective(@Param("record") Banner record, @Param("example") BannerExample example);

    int updateByExample(@Param("record") Banner record, @Param("example") BannerExample example);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);
}