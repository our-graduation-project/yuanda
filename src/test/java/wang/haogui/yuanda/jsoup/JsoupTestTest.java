package wang.haogui.yuanda.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.haogui.yuanda.YuandaApplication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author whg
 * @date 2019/12/27 12:09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YuandaApplication.class)
class JsoupTestTest {




    @Test
    void getZhiHuData() throws IOException {







//
//        String URL="https://www.zhihu.com/hot";
//        Document document= Jsoup.connect(URL)
//                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
//                .ignoreContentType(true).c.get();




        //System.out.println(document);
        //在下载的document里进行检索的语句
        //#TopstoryContent > div > div > div.HotList-list
        //#TopstoryContent > div > div > div.HotList-list > section:nth-child(1)
//        Elements elements=document.select("#TopstoryContent")
//                .select("div")
//                .select("div")
//                .select("div.HotList-list")
//                .select("HotItem");

//        Elements elements = document.getElementsByClass("HotItem-title");
//        System.out.println("获取" + elements.size() + "," + elements.html() );
//        System.out.println(document.html());
//        for (int i = 0; i < elements.size(); i++) {
//            System.out.println("come in");
//            //#TopstoryContent > div > div > div.HotList-list > section:nth-child(1) > div.HotItem-content > a > h2
//            Element element = elements.get(i);
//            String h2 = element.text();
//            System.out.println("h2 = " + h2);
//        }
    }
}