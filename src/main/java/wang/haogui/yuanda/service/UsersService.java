package wang.haogui.yuanda.service;

import com.github.pagehelper.PageInfo;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.model.Users;

import java.util.List;

public interface UsersService {

    /**
     * 登录用户
     * @param users
     * @return
     */
    Users loginUser(Users users);

    /**
     * 增加用户
     * @param users
     * @return
     */
    int addUser(Users users);

    /**
     * 批量增加用户
     * @param users
     * @return
     */
    boolean addBatchUsers(List<Users> users);

    /**
     * 修改用户
     * @param users
     * @return
     */
    boolean updateUser(Users users);

    /**
     * 删除用户
     * @param userId
     * @param isDeleted
     * @return
     */
    int updateisDeleted(Integer userId,Boolean isDeleted);


    /**
     * 通过邮箱修改密码
     * @param
     * @param users
     * @return
     */
    int updatePasswordByMailbox(Users users);
    /**
     * 根据ID删除用户
     * @param userId
     * @return
     */
    boolean deleteUser(Integer userId);

    /**
     * 查询所有用户
     * @param page
     * @param limit
     * @return
     */
    PageInfo<Users> searchUsers(int page,int limit);

    /**
     * 根据ID查询用户
     * @param userId
     * @return
     */
    Users searchUsersByUserId(Integer userId);



    /**
     * 根据ID查询用户
     * @param userId
     * @return
     */
    PageInfo<Users> searchUsersById(int page, int limit, Integer userId, String order, OrderEnum orderEnum);

    /**
     *根据名称查询用户
     * @param page
     * @param limit
     * @param userName
     * @return
     */
    PageInfo<Users> searchUsersByName(int page,int limit,String userName);

    /**
     *根据邮箱查询用户
     * @param page
     * @param limit
     * @param email
     * @return
     */
    PageInfo<Users> searchUsersByEmail(int page,int limit,String email);

}
