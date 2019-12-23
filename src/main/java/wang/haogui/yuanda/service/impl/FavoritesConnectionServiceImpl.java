package wang.haogui.yuanda.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.mapper.FavoritesConnectionMapper;
import wang.haogui.yuanda.mapper.FavoritesMapper;
import wang.haogui.yuanda.model.Favorites;
import wang.haogui.yuanda.model.FavoritesConnection;
import wang.haogui.yuanda.model.FavoritesConnectionExample;
import wang.haogui.yuanda.model.FavoritesExample;
import wang.haogui.yuanda.service.FavoritesConnectionService;

import java.util.List;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 9:26 2019/12/23
 * @Modifued By:
 */
@Service
public class FavoritesConnectionServiceImpl implements FavoritesConnectionService {

    @Autowired
    private FavoritesConnectionMapper favoritesConnectionMapper;

    @Autowired
    private FavoritesMapper favoritesMapper;

    /**
     * 向某一个收藏夹中增加一条记录（表示收藏夹收藏了一篇文章 或者收藏了一个回答）
     *
     * @param favoritesConnection
     * @return
     */
    @Override
    public boolean addRecordToFavorites(FavoritesConnection favoritesConnection) {
        //先向收藏夹中增加一条记录
        int insert = favoritesConnectionMapper.insert(favoritesConnection);
        if(insert <= 0){
            return false;
        }

        //在把该收藏夹中收藏数量加一
        Favorites favorites = favoritesMapper.selectByPrimaryKey(favoritesConnection.getFavoritesId());
        favorites.setFavoritesNumber(favorites.getFavoritesNumber()+1);
        int i = favoritesMapper.updateByPrimaryKeySelective(favorites);
        if(i <= 0){
            return false;
        }
        return true;
    }

    /**
     * 向某一个收藏夹中删除(修改is_delete 状态)一条记录（表示收藏
     * 夹收删除一篇收藏的文章 或者收藏了一个收藏的回答）
     *
     * @param id 此id表示favoritesConnection表的主键ID
     * @return
     */
    @Override
    public boolean delRecordToFavorites(int id) {

        FavoritesConnection favoritesConnection = favoritesConnectionMapper.selectByPrimaryKey(id);
        favoritesConnection.setIsDeleted(true);
        int i = favoritesConnectionMapper.updateByPrimaryKey(favoritesConnection);
        return i > 0;
    }

    /**
     * 查询某一个收藏夹中所有收藏的内容
     *
     * @param favoritesId 此favoritesId表示某一个收藏夹的主键ID
     * @return
     */
    @Override
    public List<FavoritesConnection> selectRecordForFavorites(int favoritesId) {
        FavoritesConnectionExample favoritesConnectionExample = new FavoritesConnectionExample();
        favoritesConnectionExample.or().andFavoritesIdEqualTo(favoritesId).andIsDeletedEqualTo(false);
        List<FavoritesConnection> favoritesConnections = favoritesConnectionMapper.selectByExample(favoritesConnectionExample);
        return favoritesConnections;
    }
}