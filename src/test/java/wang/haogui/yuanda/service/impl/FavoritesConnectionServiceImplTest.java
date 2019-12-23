package wang.haogui.yuanda.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.haogui.yuanda.YuandaApplication;
import wang.haogui.yuanda.mapper.FavoritesMapper;
import wang.haogui.yuanda.model.FavoritesConnection;
import wang.haogui.yuanda.service.FavoritesConnectionService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 10:48 2019/12/23
 * @Modifued By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YuandaApplication.class)
class FavoritesConnectionServiceImplTest {

    @Autowired
    private FavoritesConnectionService favoritesConnectionService;

    @Test
    void addRecordToFavorites() {
        FavoritesConnection favoritesConnection = new FavoritesConnection();
        favoritesConnection.setAuthor("zzz");
        favoritesConnection.setContentTitle("123");
        favoritesConnection.setFavoritesId(1);
        favoritesConnection.setIsDeleted(false);
        favoritesConnection.setConnectionId(1);
        favoritesConnection.setType((byte) 0);
        boolean b = favoritesConnectionService.addRecordToFavorites(favoritesConnection);
        System.out.println(b);

    }

    @Test
    void delRecordToFavorites() {
        boolean b = favoritesConnectionService.delRecordToFavorites(1);
        System.out.println(b);
    }

    @Test
    void selectRecordForFavorites() {
        List<FavoritesConnection> favoritesConnections = favoritesConnectionService.selectRecordForFavorites(1);
        System.out.println(favoritesConnections);
    }
}