package wang.haogui.yuanda.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.model.Label;
import wang.haogui.yuanda.service.LabelService;
import wang.haogui.yuanda.utils.APIResult;

import java.util.Map;

/**
 * @author whg
 * @date 2019/12/25 21:55
 **/
@RestController
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping("/admin/labelList")
    public APIResult labelList(@RequestParam Map map){
        Integer limit = Integer.parseInt(map.get("limit").toString());
        Integer page = Integer.parseInt(map.get("page").toString());
        PageInfo pageInfo = labelService.selectLabel(page, limit);
        APIResult apiResult = APIResult.genSuccessApiResponse(pageInfo);
        return apiResult;
    }

    @RequestMapping("/labelDelete/{id}")
    public APIResult labelDelete(@PathVariable("id") Integer id){
        Boolean isSuccess = labelService.deleteLabel(id);
        return new APIResult(isSuccess);
    }

    @RequestMapping("/label/save")
    public APIResult labelAdd(@RequestBody Label label){
        label.setIsDeleted(false);
        Boolean isSuccess = labelService.addLabel(label);
        return new APIResult(isSuccess);
    }
}
