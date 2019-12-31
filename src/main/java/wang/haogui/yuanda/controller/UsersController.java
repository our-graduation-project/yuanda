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
import wang.haogui.yuanda.model.Users;
import wang.haogui.yuanda.service.UsersService;
import wang.haogui.yuanda.utils.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private RegisterComponent registerComponent;


    /**
     * 用户登录
     *
     * @param page
     * @param limit
     * @param map
     * @param response
     * @return
     */
    @RequestMapping("persons/user/loginUser")
    @ResponseBody
    public APIResult loginUser(@RequestParam(value = "page", defaultValue = "0") int page
            , @RequestParam(value = "limit", defaultValue = "3") int limit,
                               @RequestBody Map map, HttpServletResponse response, HttpServletRequest request) {
        System.out.println("用户登录");
        Logger logger = LogUtils.getBussinessLogger();
        Users users = new Users();
        users.setEmail(map.get("email").toString());
        users.setUserPassword(MD5Utils.StringInMd5(map.get("userPassword").toString()));
        System.out.println(map.get("email").toString() + "-------" + MD5Utils.StringInMd5(map.get("userPassword").toString()));
        Users login = usersService.loginUser(users);
        if (login != null) {
            Map map1 = new HashMap();
            map1.put("userId", login.getUserId());
            map1.put("userName", login.getUserName());
            map1.put("userPicture", login.getUserPicture());
            map1.put("right", "users");
            String s = TokenUtil.becomeToken(map1);
            Cookie token = new Cookie("token", s);
            token.setPath(request.getContextPath() + "/");
            response.addCookie(token);
            logger.info("getBussissnessLogger===用户登录");
            LogUtils.getControllerLogger().info("getControllerLogger===用户登录成功");
            return new APIResult(true);
        } else {
            LogUtils.getControllerLogger().info("getControllerLogger===用户登录失败");
            PageInfo<Users> email = usersService.searchUsersByEmail(page, limit, users.getEmail());
            if (email == null) {
                return new APIResult("邮箱不存在！", false, 200);
            }
            return new APIResult(false);
        }
    }

    /**
     * 发送验证码到邮箱
     *
     * @param users
     * @return
     */
    @RequestMapping("persons/user/forgetPassWord")
    @ResponseBody
    public APIResult forgetPassWord(@RequestBody Users users) {
        String s = registerComponent.sendCodeToEmail(users.getEmail());
        APIResult apiResult = new APIResult(true, 200);
        apiResult.setMessage("5分钟内验证码有效");
        return apiResult;
    }

    /**
     * 修改密码
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping("persons/user/updatepassword")
    @ResponseBody
    public APIResult updatePassWord(@RequestBody JSONObject jsonObject) {
        String verificationCode = (String) jsonObject.get("verificationCode");
        String email = jsonObject.get("email").toString();
        String password = MD5Utils.StringInMd5(jsonObject.get("password").toString());
        String codeByEmail = registerComponent.getCodeByEmail(email);
        if (codeByEmail == null) {
            APIResult apiResult = new APIResult(false, 200);
            apiResult.setMessage("验证码不正确");
            return apiResult;
        }
        if (!codeByEmail.equals(verificationCode)) {
            APIResult apiResult = new APIResult(false, 200);
            apiResult.setMessage("验证码不正确");
            return apiResult;
        }
        System.out.println("password" + password);
        Users users = new Users();
        users.setEmail(email);
        users.setUserPassword(password);
        int i = usersService.updatePasswordByMailbox(users);
        boolean isFlag = false;
        if (i > 0) {
            isFlag = true;
        }
        APIResult apiResult = new APIResult(isFlag, 200);
        return apiResult;
    }


    /**
     * 用户注册
     *
     * @param users
     * @return
     */
    @RequestMapping("persons/user/RegisterUsers")
    @ResponseBody
    public APIResult RegisterUsers(@RequestBody(required = false) Users users) {
        if (users == null || users.getUserName() == null || users.getTelphone() == null ||
                users.getUserPassword() == null || users.getEmail() == null || users.getIsDeleted() == null
                || users.getUserSex() == null) {
            return new APIResult("参数有误", false, 400);
        }
        users.setUserPassword(MD5Utils.StringInMd5(users.getUserPassword()));
        int i = usersService.addUser(users);
        System.out.println("--------" + i);
        boolean isFlag = false;
        if (i > 0) {
            isFlag = true;
        }
        return new APIResult(isFlag, 200);
    }

    /**
     * 加载所有用户
     *
     * @return
     */
    @RequestMapping("admin/user/loadUsers")
    @ResponseBody
    public APIResult searchUsers(@RequestParam Map map) {
        System.out.println("加载用户");
        int page = Integer.valueOf((String) map.get("page"));
        int limit = Integer.valueOf((String) map.get("limit"));
        PageInfo<Users> pageInfo = usersService.searchUsers(page, limit);
        System.out.println("pageInfo" + pageInfo);
        return new APIResult("加载用户成功！", true, 200, pageInfo);
    }

    /**
     * 增加用户
     *
     * @param users
     * @return
     */
    @RequestMapping("admin/user/addUsers")
    @ResponseBody
    public APIResult addUsers(@RequestBody(required = false) Users users) {
        if (users == null || users.getUserName() == null || users.getTelphone() == null ||
                users.getUserPassword() == null || users.getEmail() == null || users.getIsDeleted() == null
                || users.getUserSex() == null) {
            return new APIResult("参数有误", false, 400);
        }
        users.setUserPassword(MD5Utils.StringInMd5(users.getUserPassword()));
        int i = usersService.addUser(users);
        System.out.println("--------" + i);
        boolean isFlag = false;
        if (i > 0) {
            isFlag = true;
        }
        return new APIResult(isFlag, 200);
    }

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @GetMapping("/admin/user/info/{id}")
    @ResponseBody
    public APIResult searchUserById(@PathVariable("id") Integer id) {
        Users users = usersService.searchUsersByUserId(id);
        return APIResult.genSuccessApiResponse(users);
    }

    /**
     * 删除用户
     *
     * @param map
     * @return
     */
    @RequestMapping("admin/user/changeIsDeleted")
    public APIResult changeIsDeleted(@RequestBody Map<String, Object> map) {
        List list = (List) map.get("list");
        Boolean status = Boolean.parseBoolean(map.get("status").toString());
        System.out.println("status : " + status + " list:" + list);
        Boolean aBoolean = usersService.changeIsDeletedByList(list, status);
        return new APIResult(aBoolean);
    }

    /**
     * 后台修改用户
     *
     * @param users
     * @return
     */
    @RequestMapping("admin/user/editUsers")
    @ResponseBody
    public APIResult updateUsers(@RequestBody(required = false) Users users) {
        if (users == null || users.getUserName() == null || users.getTelphone() == null ||
                users.getEmail() == null || users.getIsDeleted() == null || users.getUserSex() == null) {
            return new APIResult("参数有误", false, 400);
        }
        System.out.println("users" + users);
        boolean b = usersService.updateUser(users);
        if (b) {
            return APIResult.genSuccessApiResponse("修改成功！");
        } else {
            return APIResult.genFailApiResponse500("修改失败！");
        }
    }

    /**
     * 根据用户编号查询用户
     *
     * @param
     * @return
     */
    @RequestMapping("persons/user/searchUserByUserId")
    @ResponseBody
    public APIResult searchUserByUserId(HttpServletRequest request) {
        int tokenId = CommonUtils.getTokenId(request);
        System.out.println("tokenValue = " + tokenId);
        Users users = usersService.searchUsersByUserId(tokenId);
        APIResult apiResult = new APIResult();
        apiResult.setData(users);
        return apiResult;
    }

    /**
     * 前台修改用户
     *
     * @param users
     * @return
     */
    @RequestMapping("persons/user/edituser")
    @ResponseBody
    public APIResult editUsers(@RequestBody(required = false) Users users) {
        if (users == null || users.getUserName() == null || users.getTelphone() == null
                || users.getEmail() == null || users.getUserDescript() == null ||
                users.getUserBrithday() == null || users.getUserSex() == null||
                users.getUserPicture()==null||users.getUserBackground()==null) {
            return new APIResult("参数有误", false, 400);
        }
        System.out.println("users" + users);
        boolean b = usersService.updateUser(users);
        if (b) {
            return APIResult.genSuccessApiResponse("修改成功！");
        } else {
            return APIResult.genFailApiResponse500("修改失败！");
        }
    }

    /**
     * 跳入个人中心
     *
     * @return
     */
    @RequestMapping("persons/user/forPerson")
    public String forPerson() {
        return "person/person.html";
    }
}


