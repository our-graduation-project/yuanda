package wang.haogui.yuanda.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.mapper.FavoritesMapper;
import wang.haogui.yuanda.model.Favorites;
import wang.haogui.yuanda.model.FavoritesExample;
import wang.haogui.yuanda.service.FavoritesService;

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

        return insert > 0;
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
        return i > 0;
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
        return i > 0;
    }
}