package wang.haogui.yuanda.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author whg
 * @date 2019/12/27 15:40
 **/
public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println("说什么\n呢");

//        String ck = "_zap=cfe2e35a-6788-4992-9109-9cf18a5ea458; _xsrf=PuCpfNoYKmwnCTLEWx2s0StIsdipk22Z; d_c0=\"AHBjzvER3Q-PTmrjCHbzg4aJ0y6sKmlVOl0=|1565313800\"; q_c1=a2ae2077b3ef4c05a7fa5395635cdedd|1575019494000|1567670052000; __utmz=51854390.1576634516.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmv=51854390.100-1|2=registration_date=20170804=1^3=entry_date=20170804=1; __utma=51854390.2055005628.1576634516.1576634516.1576652320.2; tst=h; tshl=science; Hm_lvt_98beee57fd2ef70ccdd5ca52b9740c49=1576744719,1577413986,1577419860,1577430599; capsion_ticket=\"2|1:0|10:1577431613|14:capsion_ticket|44:MjI2Yjk1ZDNlZjEwNDhmM2FmNmI4ZWM3NTU2MzA2MDQ=|d4edebbec29739c657d96f06165d65d05ae5ac147cb035e73fafbd5b1e734e5d\"; z_c0=\"2|1:0|10:1577431719|4:z_c0|92:Mi4xaEpyMkRRQUFBQUFBY0dQTzhSSGREeVlBQUFCZ0FsVk5wd0R6WGdDZnhoXzN3SXlLQTgxNE81ZWdXelJCSFh1cnpB|c145ad869502cb8553b10b015ce9844aa2c03f6b4944e9a076423ca289959c5b\"; Hm_lpvt_98beee57fd2ef70ccdd5ca52b9740c49=1577431722; KLBRSID=ed2ad9934af8a1f80db52dcb08d13344|1577431723|1577428871";
//        String[] cks = ck.split(";");
//        Map map = new HashMap();
//        for (String c:cks) {
//            String[] kv = c.trim().split("=");
//            String k = kv[0];
//            String v = kv[1];
//            map.put(k,v);
//
//        }
//        Map heads = new HashMap();
//        heads.put("authority","www.zhihu.com");
//        heads.put("method","GET");
//        heads.put("path","/hot?list=science");
//        heads.put("scheme","https");
//        heads.put("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//        heads.put("accept-encoding","gzip, deflate, br");
//        heads.put("accept-language","zh-CN,zh;q=0.9");
//        heads.put("cache-control","no-cache");
//        heads.put("cookie","_zap=cfe2e35a-6788-4992-9109-9cf18a5ea458; _xsrf=PuCpfNoYKmwnCTLEWx2s0StIsdipk22Z; d_c0=\"AHBjzvER3Q-PTmrjCHbzg4aJ0y6sKmlVOl0=|1565313800\"; q_c1=a2ae2077b3ef4c05a7fa5395635cdedd|1575019494000|1567670052000; __utmz=51854390.1576634516.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmv=51854390.100-1|2=registration_date=20170804=1^3=entry_date=20170804=1; __utma=51854390.2055005628.1576634516.1576634516.1576652320.2; tst=h; tshl=science; Hm_lvt_98beee57fd2ef70ccdd5ca52b9740c49=1576744719,1577413986,1577419860,1577430599; capsion_ticket=\"2|1:0|10:1577431613|14:capsion_ticket|44:MjI2Yjk1ZDNlZjEwNDhmM2FmNmI4ZWM3NTU2MzA2MDQ=|d4edebbec29739c657d96f06165d65d05ae5ac147cb035e73fafbd5b1e734e5d\"; z_c0=\"2|1:0|10:1577431719|4:z_c0|92:Mi4xaEpyMkRRQUFBQUFBY0dQTzhSSGREeVlBQUFCZ0FsVk5wd0R6WGdDZnhoXzN3SXlLQTgxNE81ZWdXelJCSFh1cnpB|c145ad869502cb8553b10b015ce9844aa2c03f6b4944e9a076423ca289959c5b\"; KLBRSID=ed2ad9934af8a1f80db52dcb08d13344|1577431747|1577428871; tgw_l7_route=e2ca88f7b4ad1bb6affd1b65f8997df3; Hm_lpvt_98beee57fd2ef70ccdd5ca52b9740c49=1577432608");
//        heads.put("pragma","no-cache");
//        heads.put("upgrade-insecure-requests","1");
//
//
//        String URL="https://www.zhihu.com/hot?list=science";
//        Document document= Jsoup.connect(URL)
////                .headers(heads)
//                .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36")
//                .ignoreContentType(true)
//                .cookie("Cookie","d_c0=\"AFBAQf6XlgqPTtYdnN-RzRPzpjaWuytWSLo=|1474684865\"; _za=fb1a1ef3-9484-4225-8849-9fab90e0feba; _zap=7cdbe920-a6de-40f5-b7b2-af7821420a3e; nweb_qa=heifetz; _xsrf=fa2a707345fb80b139e35af06eb4e9d0; _ga=GA1.2.1704872840.1490540507; capsion_ticket=\"2|1:0|10:1490871560|14:capsion_ticket|44:ODMyNWY4MzMwZjA1NDUzYWI0NGZiNjFlNzNiZjllNWY=|7e45182e5ee7a4d1f38ce1cc0d42545fd5216f4f3291865bc77cbea893abad68\"; aliyungf_tc=AQAAAPgLXlk4mQgABdv7ccQfshtJdRmP; q_c1=2878e530c4594d11b86b945817daeda5|1490947400000|1490947400000; r_cap_id=\"Y2E5Zjc1MDM4MjQzNDgyNGEzZjMwNjQ2MDc4OGZlMWY=|1490947400|fbcc43997eb568bb122dd768bb6ac62ab8b1c471\"; cap_id=\"OGIyZmQxZjQyZjNlNDllODhmMGE3ODAzOGMzODc0OTU=|1490947400|98209fe4abe8cadf0f672b5ff0c58223efce859e\"; l_n_c=1; __utma=51854390.1704872840.1490540507.1490942040.1490947224.2; __utmb=51854390.0.10.1490947224; __utmc=51854390; __utmz=51854390.1490942040.1.1.utmcsr=zhihu.com|utmccn=(referral)|utmcmd=referral|utmcct=/topic/19550517/followers; __utmv=51854390.100--|2=registration_date=20151203=1^3=entry_date=20151203=1; "
//                        + "z_c0=\"2|1:0|10:1577431719|4:z_c0|92:Mi4xaEpyMkRRQUFBQUFBY0dQTzhSSGREeVlBQUFCZ0FsVk5wd0R6WGdDZnhoXzN3SXlLQTgxNE81ZWdXelJCSFh1cnpB|c145ad869502cb8553b10b015ce9844aa2c03f6b4944e9a076423ca289959c5b\"")
//                .get();
//
//

//        heads.put("","");
//        heads.put("","");
//        heads.put("","");
//        heads.put("","");


//        String text = document.text();
//        System.out.println(text);


    }
}
