package wang.haogui.yuanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wang.haogui.yuanda.model.Favorites;
import wang.haogui.yuanda.model.FavoritesConnection;
import wang.haogui.yuanda.service.FavoritesConnectionService;
import wang.haogui.yuanda.service.FavoritesService;
import wang.haogui.yuanda.utils.APIResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 9:35 2019/12/24
 * @Modifued By:
 */

@RestController
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private FavoritesConnectionService favoritesConnectionService;

    @GetMapping(value = "loadallfavorites")
    public APIResult loadAllFavorites(){
        //通过token获取当前用户的id
        int id = 1;
        List<Favorites> favorites = favoritesService.selectFavoritesByUserId(id);
        return APIResult.genSuccessApiResponse(favorites);
    }

    @RequestMapping("addfavorites")
    public APIResult addFavorites(@RequestBody Map map){
        Favorites favorites = new Favorites();
        String addFavoritesName = (String) map.get("addFavoritesName");
        int userId = 1;
        //通过token获取当前用户的id
        favorites.setFavoritesName(addFavoritesName);
        //默认创建收藏夹是收藏数量为0
        favorites.setFavoritesNumber(0);
        //默认创阿金收藏夹为启用
        favorites.setIsDeleted(false);
        favorites.setUserId(userId);

        boolean b = favoritesService.addFavorites(favorites);
        if(b){
            return APIResult.genSuccessApiResponse("成功！");
        }
        return APIResult.genFailApiResponse400("收藏夹失败");
    }

    @RequestMapping("searchfavoritdetailbyid")
    public APIResult searchFavoritDetailById(@RequestBody Map map){
        Integer id = Integer.parseInt(map.get("favoritesId").toString());

        Favorites favorites = favoritesService.selectFavoritesById(id);

        List<FavoritesConnection> favoritesConnections = favoritesConnectionService.selectRecordForFavorites(id);

        Map map1 = new HashMap();

        map1.put("favorites",favorites);

        map1.put("favoritesConnections",favoritesConnections);

        return APIResult.genSuccessApiResponse(map1);

    }


    @RequestMapping("editfavorites")
    public APIResult editFavorites(@RequestBody Map map){
        System.out.println(map);
        Integer favoritesId = Integer.parseInt(map.get("favoritesId").toString());
        String favoritesName = (String) map.get("editfavoritesName");
        Favorites favorites = new Favorites();
        favorites.setFavoritesName(favoritesName);
        favorites.setFavoritesId(favoritesId);
        boolean b = favoritesService.editFavorites(favorites);
        if(!b){
            return APIResult.genFailApiResponse400("修改失败");
        }
        return APIResult.genSuccessApiResponse("修改成功");
    }

    @RequestMapping("delfavorites")
    public APIResult delFavorites(@RequestBody Map map){
        Integer favoritesId = Integer.parseInt(map.get("favoritesId").toString());
        System.out.println("delfavorites"+favoritesId);
        boolean b = favoritesService.delFavorties(favoritesId);
        if(!b){
            return APIResult.genFailApiResponse400("删除失败");
        }
        return APIResult.genSuccessApiResponse("删除成功");
    }


    @RequestMapping("delfavoritesconnection")
    public APIResult delFavoritesConnection(@RequestBody Map map){
        Integer id = Integer.parseInt(map.get("id").toString());
        Integer favoritesId = Integer.parseInt(map.get("favoritesId").toString());
        Integer favoritesNumber = Integer.parseInt(map.get("favoritesNumber").toString());
        System.out.println("id"+id+"delFavoritesConnection！");
        //先删除该条收藏记录
        boolean b = favoritesConnectionService.delRecordToFavorites(id);
        if(!b){
            return APIResult.genFailApiResponse400("删除失败");
        }
        Favorites favorites = new Favorites();
        favorites.setFavoritesId(favoritesId);
        favorites.setFavoritesNumber(favoritesNumber-1);
        //修改该收藏夹的收藏数量
        boolean b1 = favoritesService.editFavorites(favorites);
        if(!b1){
            return APIResult.genFailApiResponse400("删除失败");
        }
        return APIResult.genSuccessApiResponse("删除成功");
    }


}