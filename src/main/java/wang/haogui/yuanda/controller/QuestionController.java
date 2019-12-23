package wang.haogui.yuanda.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.model.Question;
import wang.haogui.yuanda.service.impl.QuestionServiceImpl;
import wang.haogui.yuanda.utils.APIResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 向清润
 * @createTime 2019.12.23.16:38
 */
@Controller
public class QuestionController {
    @Autowired
    QuestionServiceImpl questionService;

    @RequestMapping("/admin/selectQuestionList")
    @ResponseBody
public void selectQuestionList(HttpServletRequest request, HttpServletResponse response){

        int page = Integer.valueOf(request.getParameter("page"));
        int limit = Integer.valueOf(request.getParameter("limit"));
        String order = request.getParameter("order");
        String anEnum = request.getParameter("orderEnum");
        OrderEnum orderEnum = null;
        if( "asc".equals(anEnum)){
            orderEnum =OrderEnum.ASC;
        }else {
            orderEnum =OrderEnum.DESC;
        }

        PageInfo<Question> pageInfo = questionService.selectQuestions(page, limit, "hot", orderEnum);
        System.out.println(pageInfo.getList().get(0).getQuestionId()+":"+pageInfo.getSize());
        if(pageInfo!=null &&pageInfo.getList()!=null&&pageInfo.getList().size()==0){
           return null;
        }
        String data = JSONObject.toJSONString(pageInfo);
        System.out.println(data);
      response.wait();


    }

}
