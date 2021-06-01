package model.service;

import model.dao.base.DataHouse;
import model.entity.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/5 20:44
 * @description:
 * @modifiedBy By:
 * @version :
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
    static DataHouse db;
    static String tableName;
    @BeforeClass
    public static void datatableInit() {
        db = DataHouse.getInstance();
        db.init("src/test/resources/database");
    }

    @Test
    public void test01Signup(){
        User user = new User("927986413@qq.com","userTest","111111","1","1", null, 1.0,1,1,new ArrayList<Long>(),new ArrayList<Long>());
        Assert.assertEquals(4001, new UserService().saveUser(user));

    }

    @Test
    public void test02Login(){
        Assert.assertEquals(200, new UserService().login("userTest","111111").getCode());
    }

    @Test
    public void test03getUser(){
        Assert.assertEquals(4041, new UserService().getUser("000101").getCode());
    }

    @Test
    public void test04updateUser() {
        User user = new User( 1402L, "927986413@qq.com","userTest3","111111","1","1", null, 1.0,1,1,new ArrayList<Long>(),new ArrayList<Long>());
        Assert.assertEquals(4041, new UserService().updateUser(user));
        Assert.assertEquals(4041, new UserService().getUser(user.getName()).getCode());
    }

    @Test
    public void test05updateUser() {
        Assert.assertEquals(false, new UserService().isUserExist("userTest3"));
        Assert.assertEquals(false, new UserService().isUserExist("userTest4"));
    }
    @Test
    public void test06setAndGetHistory() {
        Assert.assertEquals(4041, new UserService().setHistoryByName("userTest1",(long)1));
        Assert.assertEquals(4041, new UserService().getHistoryByName("userTest1").getCode());
        Assert.assertEquals(4041, new UserService().setHistoryByName("userTest1",(long)1));
        Assert.assertEquals(4041, new UserService().getHistoryByName("userTest1").getCode());
        Assert.assertEquals(4041, new UserService().setHistoryByName("userTest1",(long)2));
        Assert.assertEquals(4041, new UserService().setHistoryByName("userTest1",(long)3));
        Assert.assertEquals(4041, new UserService().setHistoryByName("userTest1",(long)4));
        Assert.assertEquals(4041, new UserService().setHistoryByName("userTest1",(long)5));
        Assert.assertEquals(4041, new UserService().setHistoryByName("userTest1",(long)6));
        Assert.assertEquals(4041, new UserService().setHistoryByName("userTest1",(long)7));
        Assert.assertEquals(4041, new UserService().setHistoryByName("userTest0",(long)1));
        Assert.assertEquals(4041, new UserService().getHistoryByName("userTest1").getCode());
        Assert.assertEquals(4041, new UserService().getHistoryByName("userTest0").getCode());
    }
}
