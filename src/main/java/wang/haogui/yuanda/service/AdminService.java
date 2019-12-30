package wang.haogui.yuanda.service;

import com.github.pagehelper.PageInfo;
import wang.haogui.yuanda.common.IsDeletedEnum;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.model.Admin;

import java.util.List;

public interface AdminService {
    /**
     * 登录管理员
     * @param admin
     * @return
     */
    Admin loginAdmin(Admin admin);

    /**
     * 增加管理员
     * @param admin
     * @return
     */
    int addAdmin(Admin admin);

    /**
     * 批量增加管理员
     * @param admins
     * @return
     */
    boolean addBatchAdmin(List<Admin> admins);

    /**
     * 修改管理员
     * @param admin
     * @return
     */
    boolean updateUser(Admin admin);

    /**
     * 根据ID修改权限
     * @param adminId
     * @param right
     * @return
     */
    int updateRight(Integer adminId, Byte right);

//    /**
//     * 根据ID修改状态
//     * @param adminId
//     * @param isDeleted
//     * @return
//     */
//    int updateisDeleted(Integer adminId, Boolean isDeleted);

    /**
     * 通过邮箱修改密码
     * @param
     * @param admin
     * @return
     */
    int updatePasswordByMailbox(Admin admin);

    /**
     * 根据ID删除管理员
     * @param adminId
     * @return
     */
    boolean deleteAdmin(Integer adminId);

    /**
     * 查询所有管理员
     * @param page
     * @param limit
     * @return
     */
    PageInfo<Admin> searchAdmins(int page, int limit);

    /**
     * 根据ID查询管理员
     * @param adminId
     * @return
     */
    Admin searchAdminByAdminId(Integer adminId);


    /**
     * 根据ID查询管理员
     * @param adminId
     * @return
     */
    PageInfo<Admin> searchAdminById(int page, int limit, Integer adminId, String order, OrderEnum orderEnum);

    /**
     *根据名称查询管理员
     * @param page
     * @param limit
     * @param adminName
     * @return
     */
    PageInfo<Admin> searchAdminByName(int page, int limit, String adminName);

    /**
     *根据名称查询管理员
     * @param page
     * @param limit
     * @param email
     * @return
     */
    PageInfo<Admin> searchAdminByEmail(int page,int limit,String email);

    /**
     * 通过管理员id改变状态码
     * @param id
     * @param isDeletedEnum
     * @return
     */
    Boolean changeIsDeletedById(int id, IsDeletedEnum isDeletedEnum);

    /**
     * 通过管理员ID将其状态码全部改为false
     * @param list
     * @param status
     * @return
     */
    Boolean changeIsDeletedByList(List list, Boolean status);
}
