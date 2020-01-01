package wang.haogui.yuanda.jsoup;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.nio.cs.ext.MacArabic;
import wang.haogui.yuanda.model.Article;
import wang.haogui.yuanda.service.RedisSetService;
import wang.haogui.yuanda.service.search.ArticleIndexTemplate;
import wang.haogui.yuanda.utils.RedisKeyUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 *
 * @author whg
 * @date 2019/12/27 16:40
 **/
@Component
public class CSDNGetData {

    @Autowired
    private RedisSetService redisSetService;

    //存放要爬的首页name,href
    private static Map<String,String> map = new HashMap<>();

    //没有加入articleList集合的url，set集合
//    private static Set<String> notAddListSet = ConcurrentHashMap.<String> newKeySet();,用redis代替

    //加入articleList集合的url，set集合
//    private static Set<String> addListSet =  ConcurrentHashMap.<String> newKeySet();,用redis代替

    //flag 为true进行消费，为false则停止
    private static Boolean flag = true;

    //用于存放存入redis的article信息
    private JSONObject articleToRedis = new JSONObject();

    //用于存放存入redis的label信息
    private JSONObject labelToRedis = new JSONObject();

    {
        articleToRedis.put("checkStatus",(byte)1);//0表示为系统爬取的数据
        articleToRedis.put("authorId",0);
        labelToRedis.put("isDeleted",0);
    }

    public static void main(String[] args) throws IOException {
//        getIndex();
//        getArticleByUrl("https://blog.csdn.net/ThinkWon/article/details/103715146");
//        startGetData();
//        getArticleByUrl("https://blog.csdn.net/m0_37609579/article/details/103133245");
    }


    /**
     * map里面key为名称，value为html链接
     * @return
     * @throws IOException
     */
    public Map getIndex(){
        String URL="https://www.csdn.net/";
        //先调用下忽略https证书的再请求才可以
        HttpsUrlValidator.retrieveResponseFromServer(URL);
        Document document= null;
        try {
            document = Jsoup.connect(URL).get();
        } catch (IOException e) {
            System.out.println("页面链接数据初始化失败，请更换cookie");
            e.printStackTrace();
        }
        //存放要爬的首页，href，name
//        Map<String,String> map = new HashMap<>();
        Elements navs = document.select("#nav > div > div > ul > li");
        System.out.println("nav.size:" + navs.size());
        for (int i = 0; i < navs.size(); i++) {
            Elements select = navs.get(i).select("li> a");
            map.put(select.text(),select.attr("href"));
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();

            if (value.indexOf("http") < 0) {
                entry.setValue("https://www.csdn.net" + value);
            }
        }
        map.forEach((key,value)->{
            System.out.println("label:" + key + ",href :" + value);
        });
        return map;
    }

    /**
     * 从navs的一条url中获得其文章的url并加入set集合
     * @param url
     * @return
     * @throws IOException
     */
    public void getUrlSet(String url) {
        Document document= null;
        try {
            //先调用下忽略https证书的再请求才可以
            HttpsUrlValidator.retrieveResponseFromServer(url);
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Elements select = document.select("ul#feedlist_id>li.clearfix");
        for (int i = 0; i < select.size(); i++) {
            Elements select1 = select.get(i).select(" div > div.title > h2 > a");
            String href = select1.attr("href");
//            System.out.println("href:" + href + "    -----  title  " + select1.text());
//            if(!addListSet.contains(href)){
//                notAddListSet.add(href);
//                System.out.println("成功将href:" + href + "    -----  title 加入notAddListSet队列 ");
//            }
            if(!redisSetService.isMember(RedisKeyUtil.getHaveAddRedisArticleKey(),href)){//判断文章的href是否被爬取过
                redisSetService.add(RedisKeyUtil.getNotAddRedisArticleKey(),href);
                System.out.println("成功将href:" + href + "    -----  title 加入notAddArticleSet队列 ");
            }
            System.out.println("--------------------------------------------------------------------------------");


        }

    }

    /**
     * 给定文章的url，随后爬取里面的内容,cookie的值需要改变
     * @param url
     * @return
     * @throws IOException
     */
    public void getArticleByUrl(String url){

        //url为空不逊要解析
        if(url.equals("") || url == null){
//            notAddListSet.remove(url);
//            if(redisSetService.srem(RedisKeyUtil.getNotAddRedisArticleKey(),url)){
//                System.out.println("从NotAddArticleKey中成功移除" + url);
//                return;
//            }
//            System.out.println("从NotAddArticleKey中移除" + url + " 失败");
            return;
        }
        //先将其从NotAddArticleKey中移除，防止多个线程从里面重复拿出,！redis中那到时已经移除，所以这一步不需要了
//        synchronized (flag){
////            notAddListSet.remove(url);
//            redisSetService.pop(RedisKeyUtil.getNotAddRedisArticleKey(),url);
//            System.out.println("-----------解析的url：" + url);
//        }
        //先调用下忽略https证书的再请求才可以
        HttpsUrlValidator.retrieveResponseFromServer(url);
        Document document= null;
        try {
            document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36")
                    .cookie("cookie","uuid_tt_dd=10_19119688160-1577626812548-860852; dc_session_id=10_1577626812548.376406; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_19119688160-1577626812548-860852!1788*1*PC_VC!5744*1*qq_39151754; acw_tc=2760823c15776268149666416ef07cfe2ff21fb3223448ec5c83abd25b9e64; Hm_lvt_2bd8228890893cb2a76137edfa16da78=1577590850,1577626816; Hm_ct_2bd8228890893cb2a76137edfa16da78=6525*1*10_19119688160-1577626812548-860852; __yadk_uid=wdNTt7jff0bMnzvHZ7wPReWjH8Qv17LK; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1577703393,1577703466,1577703549,1577757599; announcement=%257B%2522isLogin%2522%253Afalse%252C%2522announcementUrl%2522%253A%2522https%253A%252F%252Fblog.csdn.net%252Fblogdevteam%252Farticle%252Fdetails%252F103603408%2522%252C%2522announcementCount%2522%253A0%252C%2522announcementExpire%2522%253A3600000%257D; TY_SESSION_ID=e02800dc-b6b2-4279-b0fe-021c054cb7ce; firstDie=1; acw_sc__v2=5e0ab539e1cef77d080cdb33b601b6a80ce97aeb; dc_tos=q3cvg6; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1577760055; c-login-auto=9")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("发生错误");
            return;
        }
        Elements select = document.select("#content_views");
        Elements select1 = document.select("#mainBox > main > div.blog-content-box > div > div > div.article-title-box > h1");

//        System.out.println(document.html());
        Elements top = document.select("#mainBox > main > div.blog-content-box > div > div > div.article-info-box > div.article-bar-top");
        Elements agreementNumber = document.select("#article_content > div.more-toolbox > div > ul > li.tool-item.tool-active.is-like > a > span.count");
        String authorName = top.select("a.follow-nickName").text();
        String time = top.select("span.time").text();
        Elements clickNumber = top.select("span.read-count");
        //#mainBox > main > div.blog-content-box > div > div > div.article-info-box > div.slide-content-box > div:nth-child(2)
        Elements label = document.select("#mainBox > main > div.blog-content-box > div > div > div.article-info-box > div.slide-content-box > div.tags-box.artic-tag-box > a");
        String labelName = label.text();
        System.out.println("labelName:" + labelName);
        String[] s = labelName.split(" ");

        //将标签加入redis
        for (int i = 0; i < s.length; i++) {
            labelToRedis.put("labelName",s[i]);
            System.out.println("加入标签库的标签为" + labelToRedis.toJSONString());

            if(redisSetService.isMember(RedisKeyUtil.getHaveAddLabelKey(),labelToRedis.toJSONString())){//加入过数据库,则跳过
                continue;
            }
            //没有加入过数据库则加入到getNotAddLabelKey中
            redisSetService.add(RedisKeyUtil.getNotAddLabelKey(),labelToRedis.toJSONString());
        }

        articleToRedis.put("labelName",s);
        articleToRedis.put("articleTitle",select1.text());
        articleToRedis.put("articleContent",select.html()+"<br/>此文章转自"+url);

        articleToRedis.put("authorName",authorName);

        String time1 = time.substring(time.lastIndexOf("于")+1);
        articleToRedis.put("createTime",parseTime(time1));
        if(!agreementNumber.text().equals("")){
            articleToRedis.put("agreementNumber",Integer.parseInt(agreementNumber.text()));
        }else{
            articleToRedis.put("agreementNumber",0);
//            article.setAgreementNumber(0);
        }

        String trim = clickNumber.text().trim();
        System.out.println(trim);
        articleToRedis.put("clickNumber",Integer.parseInt(trim.substring(trim.lastIndexOf(" ")+1)));
        if (articleToRedis.get("articleContent") == null || articleToRedis.get("articleContent").equals("")) {
            //若解析失败则将其重新放入notAddArticleSet中
            redisSetService.add(RedisKeyUtil.getNotAddRedisArticleKey(),url);
            return ;
        }

        //将查出来的文章加入redis
        // ，并将url从notAddListSet移除，加入addListSet
        synchronized (flag){
            //将url存入getHaveAddRedisArticleKey中
            redisSetService.add(RedisKeyUtil.getHaveAddRedisArticleKey(),url);
            //将文章详情加入到redis中
            redisSetService.add(RedisKeyUtil.getArticleKey(),articleToRedis.toJSONString());
        }

//        redisSetService.get(RedisKeyUtil.getArticleKey()).forEach(date->{
//            System.out.println(date);
//        });

//        articleList.add(jsonObject);
        System.out.println("----------------article---加入list成功" );
        System.out.println(articleToRedis);
    }

    /**
     * 解析csdn上面的时间
     * @param time
     * @return
     */
    public static Date parseTime(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 启动这个线程开始获得数据
     * @throws IOException
     */
    public void startGetData() {

        //先将map初始化
        getIndex();
        //这个线程负责把url加入到set中去，相当于生产者
        Thread product = new Thread(()->{
            while (true){
                map.forEach((key,value)->{
                    //依次将所有的url加入相应的队列中
                    getUrlSet(value);
                });
                try {
                    Thread.sleep(1000000);
                    if(redisSetService.scard(RedisKeyUtil.getNotAddRedisArticleKey())>300){//存在了300个没有解析则休息休息
                        Thread.sleep(10000000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(!flag){
                    break;
                }
            }
        });
        product.start();

        List<Thread> list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            Thread thread = getConsumer();
            list.add(thread);
            thread.start();
            System.out.println("拥有生产者");
        }
//        for (int i = 0; i < 3; i++) {
//            try {
//                list.get(i).join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }

    /**
     * 产生一个个uri的线程
     * @return
     */
    public Thread getConsumer(){

        return new Thread(()->{
            String url = null;
            while(true){
                //如果队列为空则先等一等
                if(redisSetService.scard(RedisKeyUtil.getNotAddRedisArticleKey()) == 0){
                    try {
                        Thread.sleep(100);
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("消费者开始跑了");
                //notAddListSet中有数据则拿出来,进行获取article
                synchronized (flag){//加锁防止重复拿
                    url = redisSetService.pop(RedisKeyUtil.getNotAddRedisArticleKey()).toString();
                }
                getArticleByUrl(url);
                if(!flag){
                    System.out.println(Thread.currentThread().getName() + "停止运行");
                    break;
                }
            }
        });
    }

}
