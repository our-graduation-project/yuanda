package wang.haogui.yuanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.haogui.yuanda.jsoup.CSDNGetData;
import wang.haogui.yuanda.utils.APIResult;

/**
 * @author whg
 * @date 2019/12/28 17:37
 **/
@Controller
public class DataController {

    @Autowired
    private CSDNGetData csdnGetData;

    @RequestMapping("/jsoup/getData")
    @ResponseBody
    public APIResult getData(){
        csdnGetData.startGetData();
        return APIResult.genSuccessApiResponse("已经开始获取数据");
    }

    public APIResult setRedisDataToDatabase(){

        return null;
    }
}
