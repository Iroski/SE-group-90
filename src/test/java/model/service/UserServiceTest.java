package model.service;

import model.dao.base.DataBase;
import model.dao.entity.User;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import model.service.UserService;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/5 20:44
 * @description:
 * @modifiedBy By:
 * @version :
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
    static DataBase db;
    static String tableName;
    @BeforeClass
    public static void datatableInit() {
        db = DataBase.getInstance();
        db.init("src/test/resources/database");
    }

    @Test
    public void SaveUser01(){
        User user = new User(1117,"927986413@qq.com",String.valueOf(1111 % 20),"1","1","1",1.0,1,1);
        UserService userService=new UserService();
        System.out.println(userService.saveUser(user));
    }
}
