package wang.haogui.yuanda.service;

import wang.haogui.yuanda.model.FavoritesConnection;

import java.util.List;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 11:49 2019/12/21
 * @Modifued By:
 */
public interface FavoritesConnectionService {

    /**
     * 向某一个收藏夹中增加一条记录（表示收藏夹收藏了一篇文章 或者收藏了一个回答）
     *
     * @param favoritesConnection
     * @return
     */
    boolean addRecordToFavorites(FavoritesConnection favoritesConnection);

//    /**
//     * 收藏一篇文章
//     * @param favoritesConnection
//     * @return
//     */
//    boolean addArticleToFavorites(FavoritesConnection favoritesConnection);

    /**
     * 向某一个收藏夹中删除一条记录（表示收藏
     * 夹收删除一篇收藏的文章 或者收藏了一个收藏的回答）
     *
     * @param id 此id表示favoritesConnection表的主键ID
     * @return
     */
    boolean delRecordToFavorites(int id);

    /**
     * 查询某一个收藏夹中所有收藏的内容
     *
     * @param favoritesId 此favoritesId表示某一个收藏夹的主键ID
     * @return
     */
    List<FavoritesConnection> selectRecordForFavorites(int favoritesId);



}
