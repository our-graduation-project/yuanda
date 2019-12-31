package wang.haogui.yuanda.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.model.Article;
import wang.haogui.yuanda.service.ArticleService;
import wang.haogui.yuanda.service.impl.ArticleServiceImpl;
import wang.haogui.yuanda.utils.APIResult;
import wang.haogui.yuanda.utils.CommonUtils;

import java.util.*;

/**
 * @author whg
 * @date 2019/12/24 15:17
 **/
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @RequestMapping("/admin/pageArticleList")
    @ResponseBody
    public APIResult pageArticleList(@RequestBody Map map){

        APIResult apiResult = articleList(map);
        return apiResult;
    }



    @RequestMapping("/admin/articleList")
    @ResponseBody
    public APIResult articleList(@RequestParam Map<String,Object> map){
        if(map == null){
            return APIResult.genFailApiResponse500("传入数据为空");
        }
        Integer limit = Integer.parseInt(map.get("limit").toString());
        Integer page = Integer.parseInt(map.get("page").toString());
        String order = (String) map.get("order");
        OrderEnum orderEnum = OrderEnum.DESC;
        if(order != null){
            orderEnum = CommonUtils.isOrderEnum(order);
        }
        String orderName = (String) map.get("orderName");
        PageInfo pageInfo = articleService.selectArticleByPage(page, limit, orderName, orderEnum);
        if(pageInfo == null){
            APIResult apiResult = APIResult.genFailApiResponse500("查询失败");
        }
        APIResult apiResult = APIResult.genSuccessApiResponse("查询成功",pageInfo);
        return apiResult;
    }

    @GetMapping("/articles/info/{id}")
    @ResponseBody
    public APIResult articleDetail(@PathVariable("id") Integer id){
        Article article = articleService.selectArticleId(id);
        return APIResult.genSuccessApiResponse(article);
    }

    @RequestMapping("/articles/save")
    public APIResult articleSave(@RequestBody Article article){
        if(article != null){
            article.setArticleId(0);
        }
        article.setCreateTime(new Date());
        article.setAuthorId(0);
        article.setCheckStatus((byte)0);
        boolean isSuccess = articleService.addArticle(article);

        if(isSuccess){
            return new APIResult(isSuccess,200);
        }
        return new APIResult(isSuccess,400);
    }

    @RequestMapping("/articles/changeStatus")
    public APIResult changeStatus(@RequestBody Map<String,Object> map){
        List list = (List) map.get("list");
//        List<Integer> list = Arrays.asList(object);
        Integer status = Integer.parseInt(map.get("status").toString());
        System.out.println("status : " + status + " list:" + list);
        Boolean aBoolean = articleService.changeCheckStatusByList(list, status);

        return new APIResult(aBoolean);
    }

    @RequestMapping("/admin/selectArticleByLabelId")
    @ResponseBody
    public APIResult selectArticleByLabelId(@RequestBody Map map){
        if(map == null){
            return APIResult.genFailApiResponse500("传入数据为空");
        }
        int page = (int) map.get("page");
        int limit = (int) map.get("limit");
        int labelId = (int) map.get("label");
        String order = (String) map.get("order");
        String orderName = (String) map.get("orderName");
        OrderEnum orderEnum = CommonUtils.isOrderEnum(order);
        PageInfo pageInfo = articleService.selectArticleByLabelId(labelId, page, limit, orderName, orderEnum);

        if(pageInfo == null){
            return APIResult.genFailApiResponse500("查询失败");
        }else {
            return APIResult.genSuccessApiResponse("查询成功",pageInfo);
        }


    }
}
