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
import wang.haogui.yuanda.utils.APIResult;

import java.util.*;

/**
 * @author whg
 * @date 2019/12/24 15:17
 **/
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/admin/articleList")
    @ResponseBody
    public APIResult articleList(@RequestParam Map<String,Object> map){
        Integer limit = Integer.parseInt(map.get("limit").toString());
        Integer page = Integer.parseInt(map.get("page").toString());

        PageInfo pageInfo = articleService.selectArticleByPage(page, limit, null, OrderEnum.ASC);
        APIResult apiResult = APIResult.genSuccessApiResponse(pageInfo);
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
}
