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
import wang.haogui.yuanda.service.impl.LabelServiceImpl;
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
    @Autowired
    LabelServiceImpl labelService;


    /**
     * 通用数据查询
     * @param page 查询页码
     * @param limit 页码大小
     * @param orderName 需要排序的名字
     * @param orderEnum 升序或降序
     * @return 查询出来的数值
     */
    public APIResult selectQuestionList(int page,int limit,String orderName,OrderEnum orderEnum){

        PageInfo<Question> pageInfo = questionService.selectQuestions(page, limit, orderName, orderEnum);
        if(pageInfo!=null &&pageInfo.getList()!=null&&pageInfo.getList().size()==0){
           return APIResult.genSuccessApiResponse("查询失败",null);
        }

      return APIResult.genSuccessApiResponse("查询成功",pageInfo);


    }

    /**
     * 将升序降序转化为枚举类
     * @param order 传入的升序降序标记
     * @return 枚举类的升序降序标记
     */
    private OrderEnum isOrderEnum(String order){
        OrderEnum orderEnum = null;
        if( "desc".equals(order)){
            orderEnum =OrderEnum.DESC;
        }else {
            orderEnum =OrderEnum.ASC;
        }
        return orderEnum;
    }

    /**
     * 后台查询数据查询问题列表
     * @param map 传入的数据
     * @return 问题列表
     */
    @RequestMapping("/admin/selectQuestionList")
    @ResponseBody
    private APIResult adminSelectQuestion(@RequestParam Map map){
        int page = Integer.valueOf((String) map.get("page"));
        int limit = Integer.valueOf((String) map.get("limit"));
        String order = (String) map.get("order");
        String orderName = (String) map.get("orderName");

        OrderEnum orderEnum = isOrderEnum(order);

        if(orderName == null||"".equals(orderName)){
            orderName = "check_status";
        }
        return selectQuestionList(page,limit,orderName,orderEnum);
    }


    /**
     * 前台查询数据查询问题列表
     * @param map 传入的数据
     * @return 查询出来的数据
     */
    @RequestMapping("/admin/pageSelectQuestions")
    @ResponseBody
    private APIResult pageSelectQuestions(@RequestBody Map map){
        int page =(int)map.get("page");
        int limit = (int)map.get("limit");
        String order = (String) map.get("order");
        String orderName = (String) map.get("orderName");
       OrderEnum orderEnum = isOrderEnum(order);

        if(orderName == null||"".equals(orderName)){
            orderName = "check_status";
        }
        return selectQuestionList(page,limit,orderName,orderEnum);
    }

    /**
     * 根据标签查询问题
     * @param map 传入的数据
     * @return 返回的查询数据
     */
    @RequestMapping("/admin/pageSelectQuestionsByType")
    @ResponseBody
    public APIResult pageSelectQuestionsByType(@RequestBody Map map){
        int page =(int)map.get("page");
        int limit = (int)map.get("limit");
        String order = (String) map.get("order");
        String orderName = (String) map.get("orderName");
        int label = (int) map.get("label");
        OrderEnum orderEnum = isOrderEnum(order);
        System.out.println(orderEnum.getName());
        PageInfo<Question> pageInfo = questionService.selectQuestionByTypeId(label, page, limit, orderName, orderEnum);
        if(pageInfo!=null &&pageInfo.getList()!=null&&pageInfo.getList().size()==0){
            return APIResult.genFailApiResponse500("查询失败");
        }
        return APIResult.genSuccessApiResponse("查询成功",pageInfo);
    }


    /**
     * 根据id查询单个问题的数据
     * @param map
     * @return 查询出来的问题数据
     */
    @RequestMapping("/admin/questionById")
    @ResponseBody
    public APIResult questionById(@RequestBody Map map){
        int questionId = Integer.valueOf((String) map.get("questionId"));
        Question question = questionService.selectQuestionById(questionId);
        if(question!=null){
            return APIResult.genSuccessApiResponse("查询成功",question);
        }else {
            return APIResult.genFailApiResponse500("查询失败");
        }

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
