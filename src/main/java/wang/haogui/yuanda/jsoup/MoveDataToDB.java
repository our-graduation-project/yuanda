package wang.haogui.yuanda.jsoup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.haogui.yuanda.model.Article;
import wang.haogui.yuanda.model.Label;
import wang.haogui.yuanda.model.LabelConnection;
import wang.haogui.yuanda.service.ArticleService;
import wang.haogui.yuanda.service.LabelConnectionService;
import wang.haogui.yuanda.service.LabelService;
import wang.haogui.yuanda.service.RedisSetService;
import wang.haogui.yuanda.service.search.SearchServiceImpl;
import wang.haogui.yuanda.utils.RedisKeyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 将redis中的数据转移到数据库中
 * @author whg
 * @date 2019/12/28 18:58
 **/
@Component
public class MoveDataToDB {

    private static JSONObject actionToEs = new JSONObject();

    private static JSONObject dataToEs;

    @Autowired
    private RedisSetService redisSetService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LabelService labelService;

    @Autowired
    private LabelConnectionService labelConnectionService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private SearchServiceImpl searchService;

    {
        dataToEs = new JSONObject();
        dataToEs.put("_index","yuanda");
        dataToEs.put("_type","article");
    }

    public void getDataInRedis(){
        Set set = redisSetService.get(RedisKeyUtil.getNotAddLabelKey());
        List<Label> list = new ArrayList();
        set.forEach(data->{
            list.add(JSON.parseObject(data.toString(), Label.class));
        });

        int number = labelService.addBatchLabel(list);
        //将数据移动到getHaveAddLabelKey
        if(number>0){
            redisSetService.removeAToB(RedisKeyUtil.getNotAddLabelKey(),RedisKeyUtil.getHaveAddLabelKey());
        }
        System.out.println("新增标签数量:" + number);

        Set articleSet = redisSetService.get(RedisKeyUtil.getArticleKey());
        if (articleSet == null || articleSet.isEmpty()){
            return;
        }
        List<Article> articleList = new ArrayList();
//        articleSet.forEach(data->{
////            System.out.println("data = " + data);
//            articleList.add(JSON.parseObject(data.toString(), Article.class));
//        });

        for (Object json :
                articleSet) {
            try{
                articleList.add(JSON.parseObject(json.toString().replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", ""), Article.class));
            }catch (Exception e){
                System.out.println("出现错误");
            }
        }

        articleList = articleService.addBatchAritcle(articleList);
        List<LabelConnection> labelConnectionList = new ArrayList<>();
        //将文章加入到es中去0
        List<Integer> toEs = new ArrayList<>();
        int i = 0;
        for (Object json :
                articleSet) {
            try{
                JSONObject jsonObject = JSONObject.parseObject(json.toString());
                jsonObject.put("articleId",articleList.get(i).getArticleId());
                //获得文章所拥有的id
                List labelNames = (List) jsonObject.get("labelName");
                for (Object labelName :
                        labelNames) {
                    List<Label> idByLabelName = labelService.getIdByLabelName(labelName.toString());
                    //标签不为空向标签联系表中加一条数据
                    if(!idByLabelName.isEmpty()){
                        labelConnectionList.add(new LabelConnection(idByLabelName.get(0).getLabelId(),articleList.get(i).getArticleId(),false,false));
                    }
                }
                toEs.add(articleList.get(i).getArticleId());
    //            System.out.println("jsonObject = " + jsonObject.toJSONString());
                i++;
            }catch (Exception e){

            }

        }

        //批量加入labelConnection
        int number2 = labelConnectionService.addBatch(labelConnectionList);
        //加入es
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        toEs.forEach(id->{
            searchService.index(id);
        });
//        searchService.bulkInsert(toEs);
        //清空Article
        List clear = redisSetService.clear(RedisKeyUtil.getArticleKey());
        if(clear != null && !clear.isEmpty()){
            System.out.println("清空redis里面的");
        }
//        executorService.shutdown();
//        System.out.println(number2);
//        //批量加入es
//        searchService.bulkInsert(toEs);

    }




}
