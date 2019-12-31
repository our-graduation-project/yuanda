package wang.haogui.yuanda.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.component.RegisterComponent;
import wang.haogui.yuanda.model.Admin;
import wang.haogui.yuanda.model.Users;
import wang.haogui.yuanda.service.AdminService;
import wang.haogui.yuanda.utils.APIResult;
import wang.haogui.yuanda.utils.LogUtils;
import wang.haogui.yuanda.utils.MD5Utils;
import wang.haogui.yuanda.utils.TokenUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RegisterComponent registerComponent;

    /**
     * 管理员登录
     * @param page
     * @param limit
     * @param map
     * @param response
     * @return
     */
    @RequestMapping("admin/loginAdmin")
    @ResponseBody
    public APIResult loginAdmin(@RequestParam(value = "page",defaultValue = "0") int page
                               , @RequestParam(value = "limit",defaultValue = "3") int limit,
                               @RequestBody Map map, HttpServletResponse response){
        System.out.println("管理员登录");
        Logger logger = LogUtils.getBussinessLogger();
        Admin admin=new Admin();
        admin.setEmail(map.get("email").toString());
        admin.setAdminPassword(MD5Utils.StringInMd5(map.get("adminPassword").toString()));
        System.out.println(map.get("email").toString()+"-------"+MD5Utils.StringInMd5(map.get("adminPassword").toString()));
        Admin login = adminService.loginAdmin(admin);
        if (login!=null){
            Map map1=new HashMap();
            map1.put("adminId",login.getAdminId());
            map1.put("adminName",login.getAdminName());
            map1.put("right","admin");
            String s = TokenUtil.becomeToken(map1);
            Cookie token=new Cookie("token",s);
            response.addCookie(token);
            logger.info("getBussinessLogger===管理员登录");
            LogUtils.getControllerLogger().info("getControllerLogger===管理员登录成功");
            return new APIResult(true);
        }else {
            LogUtils.getControllerLogger().info("getControllerLogger===用户登录失败");
            PageInfo<Admin> email = adminService.searchAdminByEmail(page, limit, admin.getEmail());
            if (email==null){
                return new APIResult("邮箱不存在！",false,200);
            }
            return new APIResult(false);
        }
    }

    /**
     * 发送验证码到邮箱
     * @param admin
     * @return
     */
    @RequestMapping("admin/forgetPassWord")
    @ResponseBody
    public APIResult forgetPassWord(@RequestBody Admin admin){
        String s = registerComponent.sendCodeToEmail(admin.getEmail());
        APIResult apiResult=new APIResult(true,200);
        apiResult.setMessage("5分钟内验证码有效");
        return apiResult;
    }

    /**
     * 修改密码
     * @param jsonObject
     * @return
     */
    @RequestMapping("admin/updatepassword")
    @ResponseBody
    public APIResult updatePassWord(@RequestBody JSONObject jsonObject){
        String verificationCode=jsonObject.get("verificationCode").toString();
        String email =jsonObject.get("email").toString();
        String password =MD5Utils.StringInMd5(jsonObject.get("password").toString());
        String codeByEmail = registerComponent.getCodeByEmail(email);
        if (codeByEmail==null){
            APIResult apiResult=new APIResult(false,200);
            apiResult.setMessage("验证码不正确");
            return apiResult;
        }
        if (!codeByEmail.equals(verificationCode)){
            APIResult apiResult=new APIResult(false,200);
            apiResult.setMessage("验证码不正确");
            return apiResult;
        }
        Admin admin=new Admin();
        admin.setEmail(email);
        admin.setAdminPassword(password);
        int i = adminService.updatePasswordByMailbox(admin);
        boolean isFlag=false;
        if (i>0){
            isFlag=true;
        }
        APIResult apiResult=new APIResult(isFlag,200);
        return apiResult;
    }

    /**
     * 加载所有管理员
     * @return
     */
    @RequestMapping("admin/loadAdmin")
    @ResponseBody
    public APIResult searchAdmins(@RequestParam Map map){
        System.out.println("加载管理员");
        int page = Integer.valueOf((String) map.get("page"));
        int limit=10;
        PageInfo<Admin> pageInfo = adminService.searchAdmins(page, limit);
        System.out.println("pageInfo"+pageInfo);
        return new APIResult("加载管理员成功",true,200,pageInfo);
    }

    /**
     * 增加管理员
     * @param admin
     * @return
     */
    @RequestMapping("admin/addAdmin")
    @ResponseBody
    public APIResult addAdmin(@RequestBody(required=false) Admin admin){
        if(admin == null || admin.getAdminName()==null||admin.getAdminPassword()==null||
                admin.getSex()==null||admin.getRight()==null||admin.getPhone()==null||admin.getEmail()==null||admin.getIsDeleted()==null){
            return new APIResult("参数有误",false,400);
        }
        admin.setAdminPassword(MD5Utils.StringInMd5(admin.getAdminPassword()));
        int i = adminService.addAdmin(admin);
        boolean isFlag=false;
        if (i>0){
            isFlag=true;
        }
        return new APIResult(isFlag,200);
    }

    /**
     * 根据ID查询管理员
     * @param id
     * @return
     */
    @GetMapping("/admin/info/{id}")
    @ResponseBody
    public APIResult searchAdminById(@PathVariable("id") Integer id){
        Admin admin= adminService.searchAdminByAdminId(id);
        return APIResult.genSuccessApiResponse(admin);
    }


    /**
     * 删除管理员
     * @param map
     * @return
     */
    @RequestMapping("admin/changeIsDeleted")
    public APIResult changeIsDeleted(@RequestBody Map<String,Object> map){
        List list = (List) map.get("list");
        Boolean status = Boolean.parseBoolean(map.get("status").toString());
        System.out.println("status : " + status + " list:" + list);
        Boolean aBoolean = adminService.changeIsDeletedByList(list, status);
        return new APIResult(aBoolean);
    }

    /**
     * 修改管理员
     * @param admin
     * @return
     */
    @RequestMapping("admin/editAdmin")
    @ResponseBody
    public APIResult updateUsers(@RequestBody(required=false) Admin admin){
        if(admin == null ||admin.getAdminName()==null||admin.getRight()==null||
                admin.getPhone()==null ||admin.getEmail()==null||admin.getIsDeleted()==null||
                admin.getSex()==null){
            return new APIResult("参数有误",false,400);
        }
        System.out.println("admin"+admin);
        boolean b = adminService.updateUser(admin);
        if (b){
            return APIResult.genSuccessApiResponse("修改成功！");
        }else {
            return APIResult.genFailApiResponse500("修改失败！");
        }
    }

}
