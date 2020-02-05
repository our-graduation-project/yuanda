package wang.haogui.yuanda.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.model.Article;
import wang.haogui.yuanda.model.Users;
import wang.haogui.yuanda.service.ArticleService;
import wang.haogui.yuanda.service.LikeService;
import wang.haogui.yuanda.service.impl.ArticleServiceImpl;
import wang.haogui.yuanda.service.impl.UsersServiceImpl;
import wang.haogui.yuanda.service.search.SearchServiceImpl;
import wang.haogui.yuanda.utils.APIResult;
import wang.haogui.yuanda.utils.CommonUtils;
import wang.haogui.yuanda.utils.TokenUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author whg
 * @date 2019/12/24 15:17
 **/
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UsersServiceImpl usersService;

    @Autowired
    private SearchServiceImpl searchService;

    @Autowired
    private LikeService likeService;

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
        List list = new ArrayList();
        list.add(article);
        likeService.getLikeAndDisLikeNumber(1,list);
        return APIResult.genSuccessApiResponse(article);
    }

    @RequestMapping("/articles/details")
    @ResponseBody
    public APIResult getArticleDetail(@RequestBody Map map){
        if(map.get("articleId") == null){
            return APIResult.genFailApiResponse400("参数有问题");
        }

        Article article = articleService.selectArticleId(Integer.parseInt(map.get("articleId").toString()));
        List list = new ArrayList();
        list.add(article);
        likeService.getLikeAndDisLikeNumber(1,list);
        int likeStatus = 0;
        if(map.get("userId") != null && !map.get("userId").toString().equals("")){
//            System.out.println("id" + map.get("userId"));
            likeStatus = likeService.getLikeStatus(Integer.parseInt(map.get("userId").toString()), 1, Integer.parseInt(map.get("articleId").toString()));
//            System.out.println("likeStatus = " + likeStatus);
        }
        list.add(likeStatus);
        return APIResult.genSuccessApiResponse(list);
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

    @RequestMapping("/admin/saveArticle")
    @ResponseBody
    public APIResult saveArticle(@RequestBody Map map, HttpServletRequest request){
        int tokenId = CommonUtils.getTokenId(request);
        Users user = usersService.searchUsersByUserId(tokenId);
        if(map.get("title")==null||"".equals(map.get("title"))||
                map.get("pictureStr")==null||"".equals(map.get("pictureStr"))||
                map.get("articleDescript")==null||"".equals(map.get("articleDescript"))){

            return APIResult.genFailApiResponse500("传入值为空");
        }
        Article article = new Article((String)map.get("title"),new Date(),user.getUserId(),(byte)0,1,1,1,0,0,0,(String)map.get("pictureStr"),false,user.getUserName(),user.getUserPicture(),(String)map.get("articleDescript"));

        boolean b = articleService.addArticle(article);
        if(b){
            return APIResult.genSuccessApiResponse("增加文章成功");
        }else {
            return APIResult.genFailApiResponse500("增加文章失败");
        }
    }


    /**
     * 找到于标题相匹配的文章
     * @param map
     * @return
     */
    @RequestMapping("/search")
    @ResponseBody
    public APIResult searchArticleByEs(@RequestBody Map<String,Object> map){
        Object title = map.get("title");
        System.out.println("title = " + title);
        List<Article> list = searchService.searchArticleByTitle(title.toString());
        likeService.getLikeAndDisLikeNumber(1,list);
        return APIResult.genSuccessApiResponse(list);
    }

    @RequestMapping("/articleLikeStatus")
    @ResponseBody
    public APIResult articleLikeStatus(@RequestBody Map<String,Object> map){
        if(map.get("userId") == null || map.get("articleId") == null || map.get("likeStatus") == null ){
            return APIResult.genFailApiResponse400("参数有误");
        }
        int userId = Integer.parseInt(map.get("userId").toString());
        int articleId = Integer.parseInt(map.get("articleId").toString());
        Article article = articleService.selectArticleId(articleId);
        List list = new ArrayList();
        if(map.get("likeStatus").toString().equals("1")){
            long like = likeService.like(userId, 1, articleId);
            like = like+article.getAgreementNumber();
            System.out.println("like = " + like);
            Long disLikeNumber = likeService.getDisLikeNumber(1, articleId);
            list.add(like);
            list.add(disLikeNumber+article.getDisagreementNumber());
            return APIResult.genSuccessApiResponse(list);
        }
        long l = likeService.disLike(userId, 1, articleId);
        l = l+article.getDisagreementNumber();
        System.out.println("l = " + l);
        list.add(likeService.getLikeNumber(1, articleId)+article.getAgreementNumber());
        list.add(l);
        return APIResult.genSuccessApiResponse(list);
    }
    /**
     * 查找一个用户的所有发表的文章
     *
     */
    @RequestMapping("/user/searcharticlebyuserid")
    @ResponseBody
    public APIResult searchArticleByUserId(@RequestBody Map<String,Object> map,HttpServletRequest request){

        //通过token获取userId
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (Cookie c:cookies) {
            if("token".equals(c.getName())){
                cookie = c;
            }
        }
        String string = cookie.getValue().toString();
        int userId = (int) TokenUtil.getTokenValue(string, "userId");
        System.out.println("blog"+userId);
        int page;
        int limit;
        String order;
        OrderEnum orderEnum = null;
        if(map.get("page") == null){
            page = 0;
        }else{
            page = (int) map.get("page");
        }

        if(map.get("limit") == null){
            limit = 10;
        }else{
            limit = (int) map.get("limit");
        }

        if(map.get("order") == null){
            order = null;
        }else{
            order = (String) map.get("order");
            String orderName = (String) map.get("orderName");
            orderEnum = CommonUtils.isOrderEnum(orderName);
        }

        PageInfo pageInfo = articleService.selectAritcleByUserId(userId, page, limit, order, orderEnum);
        return APIResult.genSuccessApiResponse(pageInfo);
    }

    @RequestMapping("user/delarticlebyarticleid")
    @ResponseBody
    public APIResult delArticleByArticleId(@RequestBody Map<String,Object> map){

        if(map.get("articleId") == null){
            return APIResult.genFailApiResponse401("删除失败");
        }
        int articleId = Integer.parseInt(map.get("articleId").toString());
        boolean b = articleService.deleteArticleById(articleId);

        if(b){
            return APIResult.genSuccessApiResponse("删除成功！");
        }
        return APIResult.genFailApiResponse401("删除失败");

    }
}
