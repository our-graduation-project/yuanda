package wang.haogui.yuanda.service.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.haogui.yuanda.YuandaApplication;
import wang.haogui.yuanda.model.Admin;
import wang.haogui.yuanda.service.AdminService;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YuandaApplication.class)
class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;

    @Test
    void loginAdmin() {
        Admin admin=new Admin();
        admin.setEmail("1111111@qq.com");
        admin.setAdminPassword("102");
        Admin admin1 = adminService.loginAdmin(admin);
        System.out.println("------"+admin1);
    }

    @Test
    void addAdmin() {
        Admin admin=new Admin();
        Byte isDeleted=0;
        Byte right=0;
        admin.setAdminId(2);
        admin.setAdminName("张三");
        admin.setEmail("1761432189@qq.com");
        admin.setAdminPassword("456");
        admin.setIsDeleted(isDeleted);
        admin.setRight(right);
        int i = adminService.addAdmin(admin);
        System.out.println("-------"+i);
    }

    @Test
    void updateUser() {
        Byte isDeleted=1;
        boolean b = adminService.updateUser(new Admin(1, "1111111@qq.com", "789", isDeleted));
        System.out.println(b);
    }

    @Test
    void updateRight() {
        Byte right=0;
        System.out.println(adminService.updateRight(1,right));
    }

    @Test
    void updateisDeleted() {
        Byte isDeleted=0;
        System.out.println(adminService.updateisDeleted(1,isDeleted));
    }

    @Test
    void updatePasswordByMailbox() {
        System.out.println(adminService.updatePasswordByMailbox(new Admin("1111111@qq.com","102")));
    }

    @Test
    void deleteAdmin() {
        System.out.println(adminService.deleteAdmin(1));
    }

    @Test
    void searchAdmins() {
        PageInfo<Admin> adminPageInfo = adminService.searchAdmins(1, 3);
        System.out.println(adminPageInfo.toString());
    }

    @Test
    void searchAdminByAdminId() {
        Admin admin = adminService.searchAdminByAdminId(1);
        System.out.println(admin);
    }

    @Test
    void searchAdminById() {
    }

    @Test
    void searchAdminByName() {
        PageInfo<Admin> admin = adminService.searchAdminByName(1, 3, "李四");
        System.out.println(admin.toString());
    }
}