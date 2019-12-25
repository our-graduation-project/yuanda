package wang.haogui.yuanda.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.mapper.AdminMapper;
import wang.haogui.yuanda.model.Admin;
import wang.haogui.yuanda.model.AdminExample;
import wang.haogui.yuanda.service.AdminService;
import wang.haogui.yuanda.utils.LogUtils;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper mapper;

    /**
     * 管理员登录
     * @param admin
     * @return
     */
    @Override
    public Admin loginAdmin(Admin admin) {
        AdminExample adminExample=new AdminExample();
        adminExample.or().andEmailEqualTo(admin.getEmail()).
                andAdminPasswordEqualTo(admin.getAdminPassword());
        List<Admin> admins = mapper.selectByExample(adminExample);
        if (admins!=null&&admins.size()==1){
            return admins.get(0);
        }
        return null;
    }

    /**
     * 增加管理员
     * @param admin
     * @return
     */
    @Override
    public int addAdmin(Admin admin) {
        return mapper.insert(admin);
    }

    /**
     * 批量增加管理员
     * @param admins
     * @return
     */
    @Override
    public boolean addBatchAdmin(List<Admin> admins) {
        int i =0;
        i=mapper.addBatchAdmin(admins);
        if (i==admins.size()){
            return true;
        }else {
            LogUtils.getDBLogger().info("数据库回答批量增加失败");
            throw new RuntimeException("数据库回答批量增加失败");
        }
    }

    /**
     * 修改管理员
     * @param admin
     * @return
     */
    @Override
    public boolean updateUser(Admin admin) {
        int i = mapper.updateByPrimaryKeySelective(admin);
        return i>0?true:false;
    }

    /**
     * 根据ID修改权限
     * @param adminId
     * @param right
     * @return
     */
    @Override
    public int updateRight(Integer adminId, Byte right) {
        int i=0;
        Admin admin=searchAdminByAdminId(adminId);
        if (admin!=null){
            admin.setRight(right);
            i = mapper.updateByPrimaryKey(admin);
        }
        return i;
    }

    /**
     * 根据ID修改状态
     * @param adminId
     * @param isDeleted
     * @return
     */
    @Override
    public int updateisDeleted(Integer adminId, Boolean isDeleted) {
        int i=0;
        Admin admin=searchAdminByAdminId(adminId);
        if (admin!=null){
            admin.setIsDeleted(isDeleted);
            i =mapper.updateByPrimaryKey(admin);
        }
        return i;
    }

    /**
     * 根据邮箱修改密码
     * @param admin
     * @return
     */
    @Override
    public int updatePasswordByMailbox(Admin admin) {
        AdminExample adminExample=new AdminExample();
        adminExample.or().andEmailEqualTo(admin.getEmail());
        return mapper.updateByExampleSelective(admin,adminExample);
    }

    /**
     * 根据ID删除管理员
     * @param adminId
     * @return
     */
    @Override
    public boolean deleteAdmin(Integer adminId) {
        return mapper.deleteByPrimaryKey(adminId)>0?true:false;
    }

    /**
     * 查询所有管理员
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageInfo<Admin> searchAdmins(int page, int limit) {
        AdminExample adminExample=new AdminExample();
        adminExample.or();
        PageHelper.startPage(page, limit);
        List<Admin> admins = mapper.selectByExample(adminExample);
        PageInfo pageInfo=new PageInfo(admins,3);
        return pageInfo;
    }

    @Override
    public Admin searchAdminByAdminId(Integer adminId) {
        Admin admin = mapper.selectByPrimaryKey(adminId);
        return admin;
    }

    /**
     * 根据ID查询管理员
     * @param page
     * @param limit
     * @param adminId
     * @param order
     * @param orderEnum
     * @return
     */
    @Override
    public PageInfo<Admin> searchAdminById(int page, int limit, Integer adminId, String order, OrderEnum orderEnum) {
        AdminExample adminExample=new AdminExample();
        //根据ID查询存在的管理员
        adminExample.or().andAdminIdEqualTo(adminId).andIsDeletedEqualTo(false);
        if (order != null) {
            String str = null;
            str = "`" + order + "` ";
            if (orderEnum != null) {
                str += orderEnum.getName();
            } else {
                str += OrderEnum.DESC.getName();
            }
            adminExample.setOrderByClause(str);
        }
        PageHelper.startPage(page,limit);
        List<Admin> admins = mapper.selectByExample(adminExample);
        PageInfo pageInfo=new PageInfo(admins,3);
        return pageInfo;
    }

    /**
     * 根据name查询管理员
     * @param page
     * @param limit
     * @param adminName
     * @return
     */
    @Override
    public PageInfo<Admin> searchAdminByName(int page, int limit, String adminName) {
        AdminExample adminExample=new AdminExample();
        adminExample.or().andAdminNameEqualTo(adminName);
        PageHelper.startPage(page, limit);
        List<Admin> admins = mapper.selectByExample(adminExample);
        PageInfo pageInfo=new PageInfo(admins,3);
        return pageInfo;
    }

    /**
     * 根据邮箱查询用户
     * @param page
     * @param limit
     * @param email
     * @return
     */
    @Override
    public PageInfo<Admin> searchAdminByEmail(int page, int limit, String email) {
        AdminExample adminExample=new AdminExample();
        adminExample.or().andEmailEqualTo(email);
        PageHelper.startPage(page, limit);
        List<Admin> admins = mapper.selectByExample(adminExample);
        PageInfo pageInfo=new PageInfo(admins,3);
        return pageInfo;
    }
}
