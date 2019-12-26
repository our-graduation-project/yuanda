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
import wang.haogui.yuanda.utils.power.AntMatcher;

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
            OrderEnum orderEnum = null;
            if( "asc".equals(order)){
                orderEnum =OrderEnum.ASC;
            }else {
                orderEnum =OrderEnum.DESC;
            }
            PageInfo<Answer> pageInfo = answerService.selectAnswers(page, limit, "check_status", orderEnum);

            if(pageInfo!=null &&pageInfo.getList()!=null&&pageInfo.getList().size()==0){
                return APIResult.genSuccessApiResponse("查询失败",null);
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

        OrderEnum orderEnum = null;
        if( "asc".equals(order)){
            orderEnum =OrderEnum.ASC;
        }else {
            orderEnum =OrderEnum.DESC;
        }
        PageInfo<Answer> answerPageInfo = answerService.selectAnswerByQuestionId(questionId, page, limit, "check_status", orderEnum);
        if(answerPageInfo!=null &&answerPageInfo.getList()!=null&&answerPageInfo.getList().size()==0){
            return APIResult.genSuccessApiResponse("查询失败",null);
        }
        return APIResult.genSuccessApiResponse("查询成功",answerPageInfo);
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
        int answerState = (int) map.get("state");
        if(answerids == null|| answerids.size() == 0||map.get("state")==null||answerState == 0){
            System.out.println("if");
            return APIResult.genFailApiResponse500("fail");
        }
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
