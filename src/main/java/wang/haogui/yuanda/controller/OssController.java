package wang.haogui.yuanda.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.haogui.yuanda.confign.OssCallbackResult;
import wang.haogui.yuanda.confign.OssPolicyResult;
import wang.haogui.yuanda.service.impl.OssServiceImpl;
import wang.haogui.yuanda.utils.APIResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author whg
 * @date 2019/12/16 16:31
 **/
@Controller
@Api(tags = "OssController", description = "Oss管理")
@RequestMapping("/aliyun/oss")
public class OssController {

    @Autowired
    private OssServiceImpl ossService;

    @ApiOperation(value = "oss上传签名生成")
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    @ResponseBody
    public APIResult policy() {
        OssPolicyResult result = ossService.policy();
        APIResult apiResult = new APIResult(true);
        apiResult.setData(result);
        return apiResult;
    }

    @ApiOperation(value = "oss上传成功回调")
    @RequestMapping(value = "callback", method = RequestMethod.POST)
    @ResponseBody
    public APIResult callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        APIResult apiResult = new APIResult(true);
        apiResult.setData(ossCallbackResult);
        return apiResult;
    }

}
