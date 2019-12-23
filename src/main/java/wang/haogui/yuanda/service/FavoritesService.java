package wang.haogui.yuanda.service;

import wang.haogui.yuanda.model.Favorites;

import java.util.List;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 11:39 2019/12/21
 * @Modifued By:
 */
public interface FavoritesService {

    /**
     * 新建一个收藏夹
     * @param favorites
     * @return
     */
    boolean addFavorites(Favorites favorites);


    /**
     * 删除一个收藏夹
     * @param favoritesId
     * @return
     */
    boolean delFavorties(int favoritesId);


    /**
     * 查询一个用户的所有收藏夹
     * @param userId
     * @return
     */
    List<Favorites> selectFavoritesByUserId(int userId);


    /**
     * 修改一个收藏夹的信息
     * @param favorites
     * @return
     */
    boolean editFavorites(Favorites favorites);


}
