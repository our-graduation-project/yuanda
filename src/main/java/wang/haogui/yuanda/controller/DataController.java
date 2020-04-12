package wang.haogui.yuanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.haogui.yuanda.jsoup.CSDNGetData;
import wang.haogui.yuanda.jsoup.MoveDataToDB;
import wang.haogui.yuanda.utils.APIResult;

/**
 * @author whg
 * @date 2019/12/28 17:37
 **/
@Controller
public class DataController {

    @Autowired
    private CSDNGetData csdnGetData;

    @Autowired
    MoveDataToDB moveDataToDB;

    @RequestMapping("/jsoup/getData")
    @ResponseBody
    public APIResult getData(@RequestParam(value = "cookie",required = false) String cookie){
        System.out.println("cookie = " + cookie);
        if(cookie != null && !cookie.equals("")){
            CSDNGetData.setCookie(cookie);
        }else{
            CSDNGetData.setCookie("uuid_tt_dd=10_19119688160-1577626812548-860852; dc_session_id=10_1577626812548.376406; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_19119688160-1577626812548-860852!1788*1*PC_VC!5744*1*qq_39151754; Hm_lvt_2bd8228890893cb2a76137edfa16da78=1577590850,1577626816; Hm_ct_2bd8228890893cb2a76137edfa16da78=6525*1*10_19119688160-1577626812548-860852; __yadk_uid=wdNTt7jff0bMnzvHZ7wPReWjH8Qv17LK; __gads=ID=18936a22be0a0b96:T=1578360710:S=ALNI_Maax6HRdqr3527X2iEd31MgL5ZB1A; Hm_lvt_e5ef47b9f471504959267fd614d579cd=1580283088; Hm_ct_e5ef47b9f471504959267fd614d579cd=6525*1*10_19119688160-1577626812548-860852; _ga=GA1.2.54762122.1581769750; Hm_lvt_55ed22f8aada3acf4a2b8d34b34da51b=1582427952; Hm_ct_55ed22f8aada3acf4a2b8d34b34da51b=6525*1*10_19119688160-1577626812548-860852; Hm_ct_41964c6af812818be984f801361d537f=6525*1*10_19119688160-1577626812548-860852; Hm_lvt_41964c6af812818be984f801361d537f=1584338738,1584338759; Hm_lvt_7a55576465fd255135675045604b87b1=1584339675; Hm_ct_7a55576465fd255135675045604b87b1=6525*1*10_19119688160-1577626812548-860852; dc_sid=247a1e8c8ac9aa4d33a36529a2a3d222; TY_SESSION_ID=2f795ecc-e8a8-44ea-a058-52b0069ea7ea; c_ref=https%3A//www.google.com/; _gid=GA1.2.225003792.1586584895; c-toolbar-writeguide=1; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1586584894,1586585139,1586587075,1586657955; announcement=%257B%2522isLogin%2522%253Afalse%252C%2522announcementUrl%2522%253A%2522https%253A%252F%252Fblog.csdn.net%252Fblogdevteam%252Farticle%252Fdetails%252F105203745%2522%252C%2522announcementCount%2522%253A0%252C%2522announcementExpire%2522%253A3600000%257D; _gat_gtag_UA_127895514_1=1; utm_source=distribute.pc_feed.164484; dc_tos=q8nl4k; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1586657973; c-login-auto=28");
        }
        csdnGetData.startGetData();
        return APIResult.genSuccessApiResponse("已经开始获取数据");
    }

    /***
     * 把reids数据转移到es和mysql
     * @return
     */
    @RequestMapping("/jsoup/moveData")
    @ResponseBody
    public APIResult setRedisDataToDatabase(){
        moveDataToDB.getDataInRedis();
        return APIResult.genSuccessApiResponse("已经开始转移数据");
    }

    @RequestMapping("/jsoup/stopGetData")
    @ResponseBody
    public APIResult stopGetData(){
        csdnGetData.stopGetDate();
        return APIResult.genSuccessApiResponse("已经停止获取数据");
    }
}
