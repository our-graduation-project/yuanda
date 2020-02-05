package wang.haogui.yuanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.management.snmp.jvminstr.NotificationTargetImpl;
import wang.haogui.yuanda.component.NotificationComponent;
import wang.haogui.yuanda.model.Favorites;
import wang.haogui.yuanda.model.FavoritesConnection;
import wang.haogui.yuanda.model.Message;
import wang.haogui.yuanda.service.FavoritesConnectionService;
import wang.haogui.yuanda.service.FavoritesService;
import wang.haogui.yuanda.utils.APIResult;
import wang.haogui.yuanda.utils.CookieUtil;
import wang.haogui.yuanda.utils.TokenUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    private NotificationComponent notificationComponent;

    @Autowired
    private FavoritesConnectionService favoritesConnectionService;

    @GetMapping(value = "user/loadallfavorites")
    public APIResult loadAllFavorites(HttpServletRequest request){
        //通过token获取当前用户的id

        Integer userId = (Integer) CookieUtil.getCookiesValueByName(request, "userId");
        List<Favorites> favorites = favoritesService.selectFavoritesByUserId(userId);
        System.out.println("favorites = " + favorites);
        return APIResult.genSuccessApiResponse(favorites);
    }

    @RequestMapping("user/addfavorites")
    public APIResult addFavorites(@RequestBody Map map, HttpServletRequest request){
        Favorites favorites = new Favorites();
        if(map.get("addFavoritesName") == null){
            return APIResult.genFailApiResponse400("未输入收藏夹名。");
        }
        String addFavoritesName = (String) map.get("addFavoritesName");
        Integer userId = (Integer) CookieUtil.getCookiesValueByName(request, "userId");
//        通过token获取当前用户的id
        favorites.setFavoritesName(addFavoritesName);
        //默认创建收藏夹是收藏数量为0
        favorites.setFavoritesNumber(0);
        //默认创建藏夹为启用
        favorites.setIsDeleted(false);
        favorites.setUserId(userId);

        boolean b = favoritesService.addFavorites(favorites);
        if(b){
            return APIResult.genSuccessApiResponse("收藏成功！");
        }
        return APIResult.genFailApiResponse400("收藏失败");
    }

    @RequestMapping("user/searchfavoritdetailbyid")
    public APIResult searchFavoritDetailById(@RequestBody Map map){
        if(map.get("favoritesId") == null){
            return APIResult.genFailApiResponse400("未输入收藏夹id。");
        }
        Integer id = Integer.parseInt(map.get("favoritesId").toString());

        Favorites favorites = favoritesService.selectFavoritesById(id);

        List<FavoritesConnection> favoritesConnections = favoritesConnectionService.selectRecordForFavorites(id);

        Map map1 = new HashMap();

        map1.put("favorites",favorites);

        map1.put("favoritesConnections",favoritesConnections);

        return APIResult.genSuccessApiResponse(map1);

    }


    @RequestMapping("user/editfavorites")
    public APIResult editFavorites(@RequestBody Map map){
        if(map.get("favoritesId") == null){
            return APIResult.genFailApiResponse400("修改失败");
        }
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

    @RequestMapping("user/delfavorites")
    public APIResult delFavorites(@RequestBody Map map){
        if(map.get("favoritesId") == null){
            return APIResult.genFailApiResponse400("删除失败");
        }
        Integer favoritesId = Integer.parseInt(map.get("favoritesId").toString());
//        System.out.println("delfavorites"+favoritesId);
        boolean b = favoritesService.delFavorties(favoritesId);
        if(!b){
            return APIResult.genFailApiResponse400("删除失败");
        }
        return APIResult.genSuccessApiResponse("删除成功");
    }


    /**
     * 添加到收藏
     * @param map
     * @return
     */
    @RequestMapping("user/delfavoritesconnection")
    public APIResult delFavoritesConnection(@RequestBody Map map){
        if(map.get("id") == null){
            return APIResult.genFailApiResponse400("删除失败");
        }
        if(map.get("favoritesId") == null){
            return APIResult.genFailApiResponse400("删除失败");
        }
        if(map.get("favoritesNumber") == null){
            return APIResult.genFailApiResponse400("删除失败");
        }
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

    @RequestMapping("user/addfavoritesconnection")
    public APIResult addFavoritesconnection(@RequestBody FavoritesConnection favoritesConnection){

        System.out.println("addFavoritesconnection:"+favoritesConnection);

        favoritesConnection.setIsDeleted(false);

        boolean b = favoritesConnectionService.addRecordToFavorites(favoritesConnection);
        if(b){
//            Message message = new Message();
//            message.setMessageTarget();
////        message.setMessageTarget();
//            message.setMessageType((byte)0);
//            message.setIsRead((byte) 0);
//            message.setIsDeleted(false);
//            notificationComponent.sendNotification(message);
            return APIResult.genSuccessApiResponse("收藏成功");
        }

        return APIResult.genFailApiResponse401("收藏失败请重新尝试");
    }



}