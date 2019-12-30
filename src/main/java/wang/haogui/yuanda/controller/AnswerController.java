package wang.haogui.yuanda.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.haogui.yuanda.common.CheckEnum;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.model.Answer;
import wang.haogui.yuanda.model.Question;
import wang.haogui.yuanda.service.impl.AnswerServiceImpl;
import wang.haogui.yuanda.utils.APIResult;
import wang.haogui.yuanda.utils.CommonUtils;
import wang.haogui.yuanda.utils.power.AntMatcher;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 所有关于回答的操作
 * @author
 * @createTime 2019.12.24.11:25
 */
@Controller
public class AnswerController {
    @Autowired
    AnswerServiceImpl answerService;




    /**
     * 前台根据id分页查询所有回答啊
     * @param map 传入的信息
     * @return 返回的信息
     */
    @RequestMapping("/admin/pageSelectAnswersByQuestionId")
    @ResponseBody
    public APIResult pageSelectAnswersByQuestionId(@RequestBody Map map){
        return selectAnswersByQuestionId(map);

    }

    /**
     * 分页查询所有回答啊
     * @param map 传入的信息
     * @return 返回的信息
     */
    @RequestMapping("/admin/selectAnswerList")
    @ResponseBody
    public APIResult selectAnswerList(@RequestParam Map map){
        if( map.get("quesionId") == null){
            int page = Integer.valueOf((String) map.get("page"));
            int limit = Integer.valueOf((String) map.get("limit"));
            String order = (String) map.get("order");
            String orderName = (String) map.get("orderName");

            OrderEnum orderEnum = CommonUtils.isOrderEnum(order);

            if(orderName == null||"".equals(orderName)){
                orderName = "check_status";
            }

            PageInfo<Answer> pageInfo = answerService.selectAnswers(page, limit, orderName, orderEnum);

            if(pageInfo!=null &&pageInfo.getList()!=null&&pageInfo.getList().size()==0){
                return APIResult.genFailApiResponse500("查询失败");
            }

            return APIResult.genSuccessApiResponse("查询成功",pageInfo);
        }else {

            return selectAnswersByQuestionId(map);
        }


    }


    /**
     * 根据问题的id分页查询问题的所有回答
     * @param map 传入的信息
     * @return 查询出来的结果
     */
    @RequestMapping("/admin/selectAnswersByQuestionId")
    @ResponseBody
    public APIResult selectAnswersByQuestionId(@RequestParam Map map){
        int page = Integer.valueOf((String) map.get("page"));
        int limit = Integer.valueOf((String) map.get("limit"));
        int questionId = Integer.valueOf((String) map.get("questionId"));
        String order = (String) map.get("order");
        String orderName = (String) map.get("orderName");

        OrderEnum orderEnum =CommonUtils.isOrderEnum(order);
        PageInfo<Answer> answerPageInfo = answerService.selectAnswerByQuestionId(questionId, page, limit, orderName, orderEnum);
        if(answerPageInfo!=null ){
            System.out.println(answerPageInfo.getList().size());
            return APIResult.genSuccessApiResponse("查询成功",answerPageInfo);
        }
        return APIResult.genSuccessApiResponse("查询失败");

    }


    /**
     * 审核回答
     * @param map 传入的数据
     * @return 返回的数据
     */
    @RequestMapping("/admin/updateAnswerCheckStatus")
    @ResponseBody
    public APIResult updateAnswerCheckStatus(@RequestBody Map map){
        System.out.println("开始updateAnswerCheckStatus");
        List<Integer> answerids = (List<Integer>) map.get("answerIds");

        if(answerids == null|| answerids.size() == 0||map.get("state")==null|| map.get("state") == null){

            return APIResult.genFailApiResponse500("fail");
        }
        int answerState = (int) map.get("state");
        CheckEnum answerStatus = CheckEnum.CHECKFAILL;
        if((byte)answerState == CheckEnum.CHECKPASS.getStatus()){
            answerStatus = CheckEnum.CHECKPASS;
        }

        boolean result = answerService.updateCheckStatus(answerids, answerStatus);
        if(result){
            return APIResult.genSuccessApiResponse("updateSuccess");

        }else {
            return APIResult.genFailApiResponse500("updateFail");
        }
    }


    /**
     * 增加回答
     * @param map 回答的具体内容
     * @return 保存之后的信息
     */
    @RequestMapping("/admin/saveAnswer")
    @ResponseBody
    public APIResult saveAnswer(@RequestBody Map map){
        if(map == null||map.get("content") == null||map.get("isNoName") == null){
            return APIResult.genFailApiResponse500("传入数据为空");
        }
        //Answer answer = new Answer(0,0,(byte)0,0,new Date(),1,(byte)map.get("isNoName"),);

        Answer answer = new Answer();
        boolean b = answerService.addAnswer(answer);
        if(b){
            return APIResult.genSuccessApiResponse("保存成功");
        }
        return APIResult.genFailApiResponse500("操作失败");

    }


    /**
     * 批量删除回答
     * @param map 传过来的问题id
     * @return 执行之后的信息
     */
    @RequestMapping("/admin/deleteAnswer")
    @ResponseBody
    public APIResult deleteAnswer(@RequestBody Map map){

        List<Integer> answerIds = (List<Integer>) map.get("answerIds");

        boolean result = answerService.deleteAnswers(answerIds);
        if(result){
            return APIResult.genSuccessApiResponse("deleteSuccess");
        }else {
            return APIResult.genFailApiResponse500("deleteFail");
        }


    }

}
