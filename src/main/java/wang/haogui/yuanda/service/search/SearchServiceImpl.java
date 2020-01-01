package wang.haogui.yuanda.service.search;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.search.Search;
import io.swagger.models.auth.In;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import wang.haogui.yuanda.mapper.ArticleMapper;
import wang.haogui.yuanda.mapper.LabelConnectionMapper;
import wang.haogui.yuanda.mapper.LabelMapper;
import wang.haogui.yuanda.model.*;
import wang.haogui.yuanda.service.ArticleService;
import wang.haogui.yuanda.utils.LogUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author whg
 * @date 2019/12/23 19:56
 **/
@Service
public class SearchServiceImpl{

    private static final String INDEX_NAME = "yuanda";

    private static final String INDEX_TYPE = "article";

    private static JSONObject actionToEs = new JSONObject();

    private static JSONObject articleToEs;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private LabelConnectionMapper labelConnectionMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestClient esClient;

    @Autowired
    private RestHighLevelClient highClient;

    @Autowired
    private ObjectMapper objectMapper;

    {
        articleToEs = new JSONObject();
        articleToEs.put("_index","yuanda");
        articleToEs.put("_type","article");
    }

    /**
     * 索引目标文章
     *
     * @param articleId
     */
    public void index(int articleId) {

        //先在es中查询是否有此篇文章
        ArticleIndexTemplate articleIndexTemplate1 = searchAritcleById(articleId);

        //没有则加入
        if(articleIndexTemplate1 == null){
            Article article = articleMapper.selectByPrimaryKey(articleId);

            if(article == null){
                LogUtils.getDBLogger().error("index article {} doesn't exits" + articleId);
                return;
            }

            ArticleIndexTemplate articleIndexTemplate = new ArticleIndexTemplate();
            //将article里面的数据映射到articleIndexTemplate
            modelMapper.map(article,articleIndexTemplate);
            List<Label> labels = labelMapper.selectLabelNameByConnectionId(article.getArticleId(), 0);

            List list = new ArrayList<String>();
            labels.forEach(label -> {
                list.add(label.getLabelName());
            });
            articleIndexTemplate.setLabelName(list);
            //如果es中没有create，加入es中
            try {
                add(articleIndexTemplate);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 通过文章id搜索ArticleIndexTemplate
     * @param articleId
     * @return
     */
    public ArticleIndexTemplate searchAritcleById(int articleId){
        Request request = new Request("GET",new StringBuilder("/" + INDEX_NAME + "/" + INDEX_TYPE + "/").
                append(articleId).toString());
        request.addParameter("pretty", "true");
        Response response = null;
        String responseBody = null;
        JSONObject jsonObject = null;
        try {
            response = esClient.performRequest(request);
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            System.out.println("没有找到文章-----------------------------------------");
//            e.printStackTrace();
            System.out.println("-----------------------------------------");
            LogUtils.getBussinessLogger().error("没有找到id为：{} 的文章" + articleId);
            return null;
        }
        jsonObject = JSONObject.parseObject(responseBody);
        Object source = jsonObject.get("_source");
        System.out.println("source = " + source.toString());
        ArticleIndexTemplate articleIndexTemplate = JSON.parseObject(source.toString(), ArticleIndexTemplate.class);
        return articleIndexTemplate;
    }

    /**
     * 添加一个es数据
     * @param articleIndexTemplate
     * @return
     */
    private void add(ArticleIndexTemplate articleIndexTemplate) throws IOException {

        // 构造HTTP请求，第一个参数是请求方法，第二个参数是服务器的端点，host默认是http://localhost:9200，
        // endpoint直接指定为index/type的形式
        Request request = new Request("POST", new StringBuilder("/" + INDEX_NAME + "/" + INDEX_TYPE + "/").
                append(articleIndexTemplate.getArticleId()).toString());
        // 设置其他一些参数比如美化json
        request.addParameter("pretty", "true");
        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
        String string = JSONObject.toJSONString(articleIndexTemplate);
        System.out.println("sent = " + string);
        request.setEntity(new NStringEntity(string,ContentType.APPLICATION_JSON));

        LogUtils.getPlatformLogger().info("create index with article" + articleIndexTemplate.getArticleId());

        // 发送HTTP请求
//            Response response = esClient.performRequest(request);
        sentRequestAsync(request,articleIndexTemplate.getArticleId()+"","add");

    }

    /**
     * 通过索引文档的id覆盖跟新
     * @param esId
     * @param articleIndexTemplate
     * @return
     */
    public void updateForCover(String esId, ArticleIndexTemplate articleIndexTemplate){

        Request request = new Request("PUT", new StringBuilder("/" + INDEX_NAME + "/" + INDEX_TYPE + "/").
                append(esId).toString());

        // 设置其他一些参数比如美化json
        request.addParameter("pretty", "true");
        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
        String string = JSONObject.toJSONString(articleIndexTemplate);
        System.out.println("sent = " + string);
        request.setEntity(new NStringEntity(string,ContentType.APPLICATION_JSON));
        sentRequestAsync(request,esId,"update");
    }

    /**
     * 通过esId,和要跟新的数据进行局部更新
     * @param esId
     * @param jsonObject
     */
    public void updateSomethingById(String esId, JSONObject jsonObject){
        Request request = new Request("POST", new StringBuilder("/" + INDEX_NAME + "/" + INDEX_TYPE + "/").
                append(esId).append("/_update").toString());
        String string = jsonObject.toJSONString();
        String update = "{\"doc\":" +  string + "}";
        System.out.println("update:" + update);
        request.addParameter("pretty", "true");
        request.setEntity(new NStringEntity(update,ContentType.APPLICATION_JSON));
        sentRequestAsync(request,esId,"update");
    }


    /**
     * 通过id删除某条数据
     * @param esId
     */
    public void deleteById(String esId){
        Request request = new Request("DELETE", new StringBuilder("/" + INDEX_NAME + "/" + INDEX_TYPE + "/").
                append(esId).toString());
//        request.setEntity(new NStringEntity(update,ContentType.APPLICATION_JSON));
        sentRequestAsync(request,esId,"delete");
    }

//    public List<Article> searchArticleBySomething(){
//
//    }

    /**
     * 通过id删除文章
     * @param esId
     */
    public void delete(int esId){
        Request request = new Request("DELETE",new StringBuilder("/" + INDEX_NAME + "/" + INDEX_TYPE + "/").
                append(esId).toString());
        sentRequestAsync(request,esId+"","delete");
    }


    /**
     * 此方法一般用于给es增删数据
     * 执行异步操作，request 为要发送的请求，esId为要操作的id，operate为要进行的操作(方便日志记录)
     * @param request
     * @param esId
     * @param operate
     */
    private void sentRequestAsync(Request request,String esId,String operate){
        esClient.performRequestAsync(request, new ResponseListener() {
            @Override
            public void onSuccess(Response response) {
                if(response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED ||
                        response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    LogUtils.getPlatformLogger().info(operate + "article id: {} doc success" + esId);
                }
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                LogUtils.getPlatformLogger().error(operate + "article id: {} ,failed exception:{}"
                        + esId + e.getMessage());
            }
        });
    }


    /**
     * 批量插入
     * @param toEs
     */
    public void bulkInsert(List<JSONObject> toEs) {
        Request request = new Request("POST", new StringBuilder("/" + INDEX_NAME + "/" + INDEX_TYPE + "/")
                .append("_bulk").toString());
        StringBuffer createString = new StringBuffer();
        for (int i = 0; i < toEs.size(); i++) {
            Object articleId = toEs.get(i).get("articleId");
            String createToEs = getCreateToEs(articleId);
            createString.append(createToEs);
            createString.append(toEs.get(i).toJSONString()).append("\n");
            System.out.println("将要插入es的数据" + createString);
        }
        System.out.println("-------------------"+createString.toString());
        request.addParameter("pretty", "true");
        request.setEntity(new NStringEntity(createString.toString(),ContentType.APPLICATION_JSON));
        sentRequestAsync(request,"bulk","create");
    }


    /**
     * 根据标题搜索文章
     * @param title
     * @return
     */
    public List<Article> searchArticleByTitle(String title){
        Request request = new Request("POST", new StringBuilder("/" + INDEX_NAME + "/" + INDEX_TYPE + "/")
                .append("_search").toString());
        String queryBody = "{\"query\":{\"match\":" + "{\"articleTitle\":\"" + title +"\"}" + "}}";
        System.out.println(queryBody);
        request.addParameter("pretty", "true");
        request.setEntity(new NStringEntity(queryBody.toString(),ContentType.APPLICATION_JSON));
        String responseContent = "";
        try {
            Response response = esClient.performRequest(request);
            responseContent = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            System.out.println("搜索出现问题");
            e.printStackTrace();
        }
        if(responseContent == ""){
            return null;
        }
//        System.out.println(responseContent);
        JSONObject jsonObject = JSONObject.parseObject(responseContent);
        JSONObject hitsJson = JSONObject.parseObject(jsonObject.get("hits").toString());
        JSONArray hits1 = hitsJson.getJSONArray("hits");
        List<Article> list = new ArrayList<>();
        for (int i = 0; i < hits1.size(); i++) {
            list.add(JSONObject.parseObject(JSON.parseObject(hits1.get(i).toString()).get("_source").toString(),Article.class));
        }

        return list;
    }

    /**
     * 用HighClient进行查询
     * @param title
     */
    public void searchArticleByHighClient(String title){
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder articleTitle = QueryBuilders.matchQuery("articleTitle", title)
                .prefixLength(3).maxExpansions(10);

        sourceBuilder.query(articleTitle);
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(INDEX_TYPE);
        request.source(sourceBuilder);
//        try {
//            SearchResponse response = highClient.search(request, RequestOptions.DEFAULT);
//            SearchHits hits = response.getHits();
//            for (SearchHit hit:hits) {
//                Map tempSource = hit.getSourceAsMap();
//                tempSource.forEach((key,value)->{
//                    System.out.println("key = " + key + "value:" + value);
//                });
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    /**
     * 创建bulk所需要的语句
     * @param id
     * @return
     */
    public String getCreateToEs(Object id){
        articleToEs.put("_id",id.toString());
        actionToEs.put("create",articleToEs);
        System.out.println(actionToEs.toJSONString());
        return actionToEs.toJSONString() + "\n";
    }







}
