package wang.haogui.yuanda.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.haogui.yuanda.YuandaApplication;
import wang.haogui.yuanda.model.Favorites;
import wang.haogui.yuanda.service.FavoritesService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 10:49 2019/12/23
 * @Modifued By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YuandaApplication.class)
class FavoritesServiceImplTest {
    @Autowired
    private FavoritesService favoritesService;

    @Test
    void addFavorites() {
        Favorites favorites = new Favorites();
        favorites.setIsDeleted(false);
        favorites.setFavoritesNumber(0);
        favorites.setFavoritesName("ZZZ 的收藏夹");
        favorites.setUserId(1);
        favoritesService.addFavorites(favorites);
    }

    @Test
    void delFavorties() {
        favoritesService.delFavorties(1);
    }

    @Test
    void selectFavoritesByUserId() {
        List<Favorites> favorites = favoritesService.selectFavoritesByUserId(1);
        System.out.println(favorites.get(0));
    }

    @Test
    void editFavorites() {
        Favorites favorites = new Favorites();
        favorites.setIsDeleted(false);
        favorites.setFavoritesNumber(0);
        favorites.setFavoritesName("的收藏夹");
        favorites.setUserId(1);
        favorites.setFavoritesId(1);
        boolean b = favoritesService.editFavorites(favorites);
        System.out.println(b);

    }
}