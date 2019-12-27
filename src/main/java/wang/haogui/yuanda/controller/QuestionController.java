package wang.haogui.yuanda.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.haogui.yuanda.common.CheckEnum;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.model.Question;
import wang.haogui.yuanda.service.impl.QuestionServiceImpl;
import wang.haogui.yuanda.utils.APIResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author 向清润
 * @createTime 2019.12.23.16:38
 */
@Controller
public class QuestionController {
    @Autowired
    QuestionServiceImpl questionService;


    /**
     * 查询问题列表
     * @param map 传入的数据
     * @return 问题列表
     */
    @RequestMapping("/admin/selectQuestionList")
    @ResponseBody
    public APIResult selectQuestionList(@RequestParam Map map){

        int page = Integer.valueOf((String) map.get("page"));
        int limit = Integer.valueOf((String) map.get("limit"));
        String order = (String) map.get("order");
        String orderName = (String) map.get("orderName");
        OrderEnum orderEnum = null;
        if( "desc".equals(order)){
            orderEnum =OrderEnum.DESC;
        }else {
            orderEnum =OrderEnum.ASC;
        }

        PageInfo<Question> pageInfo = questionService.selectQuestions(page, limit, "check_status", orderEnum);
        if(pageInfo!=null &&pageInfo.getList()!=null&&pageInfo.getList().size()==0){
           return APIResult.genSuccessApiResponse("查询失败",null);
        }

      return APIResult.genSuccessApiResponse("查询成功",pageInfo);


    }

    /**
     * 审核
     * @param map 传入的信息（id以及是否通过审核）
     * @return 处理之后的信息
     */
    @RequestMapping("/admin/updateCheckStatus")
    @ResponseBody
    public APIResult updateCheckStatus(@RequestBody Map map){

        List<Integer> questionIds = (List<Integer>) map.get("questionIds");

        //System.out.println(questionIds.get(0));
        int state = (int) map.get("state");
        if(questionIds == null|| questionIds.size() == 0||map.get("state")==null||state == 0){
            return APIResult.genFailApiResponse500("fail");
        }
        CheckEnum status = CheckEnum.CHECKFAILL;
        if((byte)state == CheckEnum.CHECKPASS.getStatus()){
            status = CheckEnum.CHECKPASS;
        }


        boolean result = questionService.updateCheckStatus(questionIds, status);

        if(result){
            return APIResult.genSuccessApiResponse("success");

        }else {
            return APIResult.genFailApiResponse500("fail");
        }


    }


    /**
     * 批量删除问题
     * @param map 传过来的问题id
     * @return 执行之后的信息
     */
    @RequestMapping("/admin/deleteQuestion")
    @ResponseBody
    public APIResult deleteQuestion(@RequestBody Map map){

        List<Integer> questionIds = (List<Integer>) map.get("questionIds");
        for (int i = 0; i < questionIds.size(); i++) {
            System.out.println(questionIds.get(i));
        }
        boolean result = questionService.deleteQuestion(questionIds);
        if(result){
            return APIResult.genSuccessApiResponse("success");
        }else {
            return APIResult.genFailApiResponse500("fail");
        }


    }

    /**
     * 增加问题
     * @param question 问题的信息
     * @return 处理后的信息
     */
    @RequestMapping("/admin/addQuestion")
    @ResponseBody
    public APIResult addQuestion(@RequestBody Question question){

        boolean result = questionService.addQuestion(question);
        if(result){
            return APIResult.genSuccessApiResponse("success");
        }else {
            return APIResult.genSuccessApiResponse("success");
        }


    }

}
