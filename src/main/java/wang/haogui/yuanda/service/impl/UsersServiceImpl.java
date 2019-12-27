package wang.haogui.yuanda.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.common.IsDeletedEnum;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.mapper.UsersMapper;
import wang.haogui.yuanda.model.Users;
import wang.haogui.yuanda.model.UsersExample;
import wang.haogui.yuanda.service.UsersService;
import wang.haogui.yuanda.utils.LogUtils;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;

    /**
     * 用户登录
     *
     * @param users
     * @return
     */
    @Override
    public Users loginUser(Users users) {
        UsersExample usersExample = new UsersExample();
        usersExample.or().andEmailEqualTo(users.getEmail())
                .andUserPasswordEqualTo(users.getUserPassword());
        List<Users> user = usersMapper.selectByExample(usersExample);
        if (user != null && user.size() == 1) {
            return user.get(0);
        }
        return null;
    }

    /**
     * 用户增加
     *
     * @param users
     * @return
     */
    @Override
    public int addUser(Users users) {
        return usersMapper.insert(users);
    }

    /**
     * 批量增加用户
     *
     * @param users
     * @return
     */
    @Override
    public boolean addBatchUsers(List<Users> users) {
        int i = 0;
        i = usersMapper.addBatchUsers(users);
        if (i == users.size()) {
            return true;
        } else {
            LogUtils.getDBLogger().info("数据库回答批量增加失败");
            throw new RuntimeException("数据库回答批量增加失败");
        }
    }

    /**
     * 修改用户
     *
     * @param users
     * @return
     */
    @Override
    public boolean updateUser(Users users) {
        int i = usersMapper.updateByPrimaryKeySelective(users);
        return i > 0 ? true : false;
    }


    /**
     * 删除用户
     *
     * @param userId
     * @param isDeleted
     * @return
     */
    @Override
    public int updateisDeleted(Integer userId, Boolean isDeleted) {
        int i = 0;
        Users users = searchUsersByUserId(userId);
        if (users != null) {
            users.setIsDeleted(isDeleted);
            i = usersMapper.updateByPrimaryKey(users);
        }
        return i;
    }

    /**
     * 根据邮箱修改密码
     *
     * @param users
     * @return
     */
    @Override
    public int updatePasswordByMailbox(Users users) {
        UsersExample usersExample = new UsersExample();
        usersExample.or().andEmailEqualTo(users.getEmail());
        return usersMapper.updateByExampleSelective(users, usersExample);
    }

    /**
     * 根据ID删除用户
     *
     * @param userId
     * @return
     */
    @Override
    public boolean deleteUser(Integer userId) {
        return usersMapper.deleteByPrimaryKey(userId) > 0 ? true : false;
    }

    /**
     * 查询所有用户
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageInfo<Users> searchUsers(int page, int limit) {
        UsersExample usersExample = new UsersExample();
        usersExample.or();
        PageHelper.startPage(page, limit);
        List<Users> users = usersMapper.selectByExample(usersExample);
        PageInfo pageInfo = new PageInfo(users, 3);
        return pageInfo;
    }

    @Override
    public Users searchUsersByUserId(Integer userId) {
        Users users = usersMapper.selectByPrimaryKey(userId);
        return users;
    }

    /**
     * 根据ID查询用户
     *
     * @param page      当前页码
     * @param limit     页面大小
     * @param userId    用户ID
     * @param order     排序列
     * @param orderEnum 是否升序
     * @return
     */
    @Override
    public PageInfo<Users> searchUsersById(int page, int limit, Integer userId, String order, OrderEnum orderEnum) {
        UsersExample usersExample = new UsersExample();
        //根据用户ID查询存在的用户
        usersExample.or().andUserIdEqualTo(userId).andIsDeletedEqualTo(false);
        if (order != null) {
            String str = null;
            str = "`" + order + "` ";
            if (orderEnum != null) {

                str += orderEnum.getName();
            } else {
                str += OrderEnum.DESC.getName();
            }
            usersExample.setOrderByClause(str);
        }
        PageHelper.startPage(page, limit);
        List<Users> users = usersMapper.selectByExample(usersExample);
        PageInfo pageInfo = new PageInfo(users, 3);
        return pageInfo;
    }

    /**
     * 根据用户名查询用户
     *
     * @param page
     * @param limit
     * @param userName
     * @return
     */
    @Override
    public PageInfo<Users> searchUsersByName(int page, int limit, String userName) {
        UsersExample usersExample = new UsersExample();
        usersExample.or().andUserNameEqualTo(userName);
        PageHelper.startPage(page, limit);
        List<Users> users = usersMapper.selectByExample(usersExample);
        PageInfo pageInfo = new PageInfo(users, 3);
        return pageInfo;
    }

    /**
     * 根据邮箱查询用户
     *
     * @param page
     * @param limit
     * @param email
     * @return
     */
    @Override
    public PageInfo<Users> searchUsersByEmail(int page, int limit, String email) {
        UsersExample usersExample = new UsersExample();
        usersExample.or().andEmailEqualTo(email);
        PageHelper.startPage(page, limit);
        List<Users> users = usersMapper.selectByExample(usersExample);
        PageInfo pageInfo = new PageInfo(users, 3);
        return pageInfo;
    }

    /**
     * 通过用户ID修改状态码
     *
     * @param id
     * @param isDeletedEnum
     * @return
     */
    @Override
    public Boolean changeIsDeletedById(int id, IsDeletedEnum isDeletedEnum) {
        Users users = new Users();
        users.setIsDeleted(isDeletedEnum.getStatus());
        users.setUserId(id);
        int i = usersMapper.updateByPrimaryKeySelective(users);
        return i > 0 ? true : false;
    }

    /**
     * 软删除用户
     *
     * @param list
     * @param status
     * @return
     */
    @Override
    public Boolean changeIsDeletedByList(List list, Boolean status) {
        int i = usersMapper.updateIsDeletedByList(list, status);
        return i > 0 ? true : false;
    }
}
