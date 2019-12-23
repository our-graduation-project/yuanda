package wang.haogui.yuanda.service.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.haogui.yuanda.YuandaApplication;
import wang.haogui.yuanda.common.OrderEnum;
import wang.haogui.yuanda.model.Users;
import wang.haogui.yuanda.service.UsersService;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YuandaApplication.class)
class UsersServiceImplTest {

    @Autowired
    private UsersService usersService;
    @Test
    void loginUser() {
        Users users=new Users();
        users.setEmail("1761432189@qq.com");
        users.setUserPassword("123");
        Users loginUser = usersService.loginUser(users);
        System.out.println("--------"+loginUser);
    }

    @Test
    void addUser() {
        Users users=new Users();
        users.setUserId(2);
        users.setUserName("李雪");
        users.setUserPassword("123");
        users.setUserSex(0);
        users.setIsDeleted(true);
        int i = usersService.addUser(users);
        System.out.println(i);
    }

    @Test
    void updateUser() {
        System.out.println(usersService.updateUser(new Users(1,"meng","456",1,true)));
    }

    @Test
    void updateisDeleted() {
        int i = usersService.updateisDeleted(2, false);
        System.out.println(i);
    }

    @Test
    void updatePasswordByMailBox() {
        int i = usersService.updatePasswordByMailbox(new Users("456", "1761432189@qq.com"));
        System.out.println("------"+i);
    }

    @Test
    void deleteUser() {
        boolean b = usersService.deleteUser(1);
        System.out.println(b);
    }

    @Test
    void searchUsers() {
        PageInfo<Users> usersPageInfo = usersService.searchUsers(1, 3);
        System.out.println(usersPageInfo.toString());
    }

    @Test
    void searchUsersById() {
//        Users users=new Users();
//        usersService.searchUsersById(1,3,2,null, null);
    }

    @Test
    void searchUsersByUserId() {
        Users users = usersService.searchUsersByUserId(2);
        System.out.println(users);
    }
    @Test
    void searchUsersByName() {
        PageInfo<Users> users= usersService.searchUsersByName(1, 3, "李雪");
        System.out.println(users.toString());
    }
}