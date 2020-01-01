package wang.haogui.yuanda.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wang.haogui.yuanda.model.Article;
import wang.haogui.yuanda.service.ArticleService;
import wang.haogui.yuanda.service.search.ArticleIndexTemplate;
import wang.haogui.yuanda.service.search.SearchServiceImpl;
import wang.haogui.yuanda.utils.APIResult;

import java.awt.print.Book;
import java.io.IOException;
import java.util.Map;

/**
 * @author whg
 * @date 2019/12/24 10:49
 **/
@RestController
@RequestMapping("/esArticle")
public class TestController {

    @Autowired
    private RestClient client;

    @Autowired
    private SearchServiceImpl searchService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ModelMapper modelMapper;


    /**
     * 添加ES对象, Book的ID就是ES中存储的document的ID，所以最好不要为空，自定义生成的ID太浮夸
     *
     * @return ResponseEntity
     * @throws IOException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody Article article) throws IOException {
        // 构造HTTP请求，第一个参数是请求方法，第二个参数是服务器的端点，host默认是http://localhost:9200，
        // endpoint直接指定为index/type的形式
        Request request = new Request("POST", new StringBuilder("/yuanda/article/").
                append(article.getArticleId()).toString());
        // 设置其他一些参数比如美化json
        request.addParameter("pretty", "true");

        //1、使用JSONObject
        String string = JSONObject.toJSONString(article);

        System.out.println(string);
        // 设置请求体并指定ContentType，不指定回乱码
        request.setEntity(new NStringEntity(string,ContentType.APPLICATION_JSON));

        // 发送HTTP请求
        Response response = client.performRequest(request);

        // 获取响应体, id: AWXvzZYWXWr3RnGSLyhH
        String responseBody = EntityUtils.toString(response.getEntity());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public APIResult deleteById(@PathVariable(value = "id") int id){
        searchService.deleteById(id+"");
        return APIResult.genSuccessApiResponse("已经发送删除请求");
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @RequestMapping("/search/{id}")
    public APIResult searchById(@PathVariable("id") int id){
        ArticleIndexTemplate articleIndexTemplate = searchService.searchAritcleById(id);
        if(articleIndexTemplate != null){
            return APIResult.genSuccessApiResponse(articleIndexTemplate);
        }
        return APIResult.genFailApiResponse400("没有此文章");
    }

    @RequestMapping("/create/{id}")
    public APIResult indexById(@PathVariable("id") int id){
        searchService.index(id);
        return APIResult.genSuccessApiResponse("进行了操作");
    }

    @RequestMapping("/update/{id}")
    public APIResult updateById(@PathVariable("id") Integer id){
        Article article = articleService.selectArticleId(id);
        ArticleIndexTemplate articleIndexTemplate = new ArticleIndexTemplate();
        modelMapper.map(article,articleIndexTemplate);
        articleIndexTemplate.setArticleTitle("测试更新");
        searchService.updateForCover(id+"",articleIndexTemplate);
        return APIResult.genSuccessApiResponse("");
    }

    @RequestMapping("/updateSomething")
    public APIResult updateBySomething(@RequestBody JSONObject jsonObject){
        Object articleId = jsonObject.get("articleId");
//        System.out.println("json:" + jsonObject.toJSONString());
        searchService.updateSomethingById(articleId+"",jsonObject);
        return APIResult.genSuccessApiResponse("");
    }


    @RequestMapping(value = "/go", method = RequestMethod.GET)
    public ResponseEntity<String> go() {
        return new ResponseEntity<>("go", HttpStatus.OK);
    }


    /**
     * 同步执行HTTP请求
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/es", method = RequestMethod.GET)
    public ResponseEntity<String> getEsInfo() throws IOException {
        // 构造HTTP请求，第一个参数是请求方法，第二个参数是服务器的端点，host默认是http://localhost:9200
        Request request = new Request("GET", "/");
//        // 设置其他一些参数比如美化json
//        request.addParameter("pretty", "true");

//        // 设置请求体
//        request.setEntity(new NStringEntity("{\"json\":\"text\"}", ContentType.APPLICATION_JSON));

//        // 还可以将其设置为String，默认为ContentType为application/json
//        request.setJsonEntity("{\"json\":\"text\"}");

        /*
        performRequest是同步的，将阻塞调用线程并在请求成功时返回Response，如果失败则抛出异常
        内部属性可以取出来通过下面的方法
         */
        Response response = client.performRequest(request);
//        // 获取请求行
//        RequestLine requestLine = response.getRequestLine();
//        // 获取host
//        HttpHost host = response.getHost();
//        // 获取状态码
//        int statusCode = response.getStatusLine().getStatusCode();
//        // 获取响应头
//        Header[] headers = response.getHeaders();
        // 获取响应体
        String responseBody = EntityUtils.toString(response.getEntity());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }


    /**
     * 异步执行HTTP请求
     *
     * @return
     */
    @RequestMapping(value = "/es/asyn", method = RequestMethod.GET)
    public ResponseEntity<String> asynchronous() {
        Request request = new Request(
                "GET",
                "/");
        client.performRequestAsync(request, new ResponseListener() {
            @Override
            public void onSuccess(Response response) {
                System.out.println("异步执行HTTP请求并成功");
            }

            @Override
            public void onFailure(Exception exception) {
                System.out.println("异步执行HTTP请求并失败");
            }
        });
        return null;
    }

    /**
     * 并行异步执行HTTP请求
     */
    @RequestMapping(value = "/ps", method = RequestMethod.POST)
    public void parallAsyn(@RequestBody Book[] documents) {
//        final CountDownLatch latch = new CountDownLatch(documents.length);
//        for (int i = 0; i < documents.length; i++) {
//            Request request = new Request("PUT", "/posts/doc/" + i);
//            //let's assume that the documents are stored in an HttpEntity array
//            request.setEntity(documents[i]);
//            client.performRequestAsync(
//                    request,
//                    new ResponseListener() {
//                        @Override
//                        public void onSuccess(Response response) {
//
//                            latch.countDown();
//                        }
//
//                        @Override
//                        public void onFailure(Exception exception) {
//
//                            latch.countDown();
//                        }
//                    }
//            );
//        }
//        latch.await();
    }


    /**
     * 根据id获取ES对象
     *
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getBookById(@PathVariable("id") String id) {
        Request request = new Request("GET", new StringBuilder("/book/book/").
                append(id).toString());
        // 添加json返回优化
        request.addParameter("pretty", "true");
        Response response = null;
        String responseBody = null;
        try {
            // 执行HHTP请求
            response = client.performRequest(request);
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            return new ResponseEntity<>("can not found the book by your id", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

//    /**
//     * 根据id更新Book
//     *
//     * @param id
//     * @param book
//     * @return
//     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<String> updateBook(@PathVariable("id") String id, @RequestBody Book book) throws IOException {
//        // 构造HTTP请求
//        Request request = new Request("POST", new StringBuilder("/book/book/").
//                append(id).append("/_update").toString());
//        request.addParameter("pretty", "true");
//
//        // 将数据丢进去，这里一定要外包一层“doc”，否则内部不能识别
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("doc", new JSONObject(book));
//        request.setEntity(new NStringEntity(jsonObject.toString()));
//
//        // 执行HTTP请求
//        Response response = client.performRequest(request);
//
//        // 获取返回的内容
//        String responseBody = EntityUtils.toString(response.getEntity());
//
//        return new ResponseEntity<>(responseBody, HttpStatus.OK);
//    }

    /**
     * 使用脚本更新Book
     * @param id
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/update2/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateBook2(@PathVariable("id") String id, @RequestParam("name") String name) throws IOException {
        // 构造HTTP请求
        Request request = new Request("POST", new StringBuilder("/book/book/").
                append(id).append("/_update").toString());
        request.addParameter("pretty", "true");

        JSONObject jsonObject = new JSONObject();
        // 创建脚本语言，如果是字符变量，必须加单引号
        StringBuilder op1 = new StringBuilder("ctx._source.name=").append("'" + name + "'");
        jsonObject.put("script", op1);

        request.setEntity(new NStringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON));

        // 执行HTTP请求
        Response response = client.performRequest(request);

        // 获取返回的内容
        String responseBody = EntityUtils.toString(response.getEntity());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteById(@PathVariable("id") String id) throws IOException {
        Request request = new Request("DELETE", new StringBuilder("/book/book/").append(id).toString());
        request.addParameter("pretty", "true");
        // 执行HTTP请求
        Response response = client.performRequest(request);
        // 获取结果
        String responseBody = EntityUtils.toString(response.getEntity());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
