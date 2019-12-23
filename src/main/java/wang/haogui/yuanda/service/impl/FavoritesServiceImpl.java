package wang.haogui.yuanda.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.mapper.FavoritesMapper;
import wang.haogui.yuanda.model.Favorites;
import wang.haogui.yuanda.model.FavoritesExample;
import wang.haogui.yuanda.service.FavoritesService;
import wang.haogui.yuanda.utils.LogUtils;

import java.util.List;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 9:28 2019/12/23
 * @Modifued By:
 */

@Service
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    private FavoritesMapper favoritesMapper;
    /**
     * 新建一个收藏夹
     *
     * @param favorites
     * @return
     */
    @Override
    public boolean addFavorites(Favorites favorites) {

        int insert = favoritesMapper.insert(favorites);
        if(insert <= 0){
            LogUtils.getDBLogger().info("新建收藏夹失败");
            throw new RuntimeException("新建收藏夹失败！");
        }

        return true;
    }

    /**
     * 删除一个收藏夹
     *
     * @param favoritesId
     * @return
     */
    @Override
    public boolean delFavorties(int favoritesId) {
        Favorites favorites = new Favorites();
        favorites.setIsDeleted(true);
        favorites.setFavoritesId(favoritesId);
        int i = favoritesMapper.updateByPrimaryKeySelective(favorites);
        if(i <= 0){
            LogUtils.getDBLogger().info("删除收藏夹失败");
            throw new RuntimeException("删除收藏夹失败！");
        }
        return true;
    }

    /**
     * 查询一个用户的所有收藏夹
     *
     * @param userId
     * @return
     */
    @Override
    public List<Favorites> selectFavoritesByUserId(int userId) {

        FavoritesExample favoritesExample = new FavoritesExample();
        favoritesExample.or().andUserIdEqualTo(userId).andIsDeletedEqualTo(false);
        List<Favorites> favorites = favoritesMapper.selectByExample(favoritesExample);
        return favorites;
    }

    /**
     * 修改一个收藏夹的信息
     *
     * @param favorites
     * @return
     */
    @Override
    public boolean editFavorites(Favorites favorites) {
        int i = favoritesMapper.updateByPrimaryKey(favorites);
        if(i <= 0){
            LogUtils.getDBLogger().info("修改收藏夹失败");
            throw new RuntimeException("修改收藏夹失败！");
        }

        return true;
    }
}