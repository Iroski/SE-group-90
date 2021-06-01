package model.service;

import model.dao.base.DataHouse;
import model.entity.Account;
import org.intellij.lang.annotations.JdkConstants;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/14 16:05
 * @description:
 * @modifiedBy By:
 * @version :
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class AccountTest {
    static DataHouse db;
    static String tableName;
    @BeforeClass
    public static void datatableInit() {
        db = DataHouse.getInstance();
        db.init("src/test/resources/database");
    }

    @Test
    public void test01GetUpdateAccount(){
        Account account1= (Account) new AccountService().getAccount("userTest").getObject();
        Assert.assertEquals(4042, new AccountService().getAccount("usertest").getCode());
        account1.setBalance(new BigDecimal("100"));
        account1.setPremiumLevel(2);
        account1.setPremiumEndTime((long)0);
        Assert.assertEquals(200, new AccountService().updateAccount(account1));

        Account account2= (Account) new AccountService().getAccount("userTest2").getObject();
        account2.setBalance(new BigDecimal("100"));
        account2.setPremiumLevel(1);
        account2.setPremiumEndTime(System.currentTimeMillis()+1000000000);
        Assert.assertEquals(200, new AccountService().updateAccount(account2));
    }
    @Test
    public void test02updateBalance(){
        Assert.assertEquals(200, new AccountService().updateBalance("userTest",new BigDecimal("1")));
        Assert.assertEquals(4042, new AccountService().updateBalance("userTest1",new BigDecimal("1")));
        Assert.assertEquals(4042, new AccountService().updateBalance("userTest1",new BigDecimal("100")));
        Assert.assertEquals(4042, new AccountService().updateBalance("userTest1",new BigDecimal("0")));
    }

    @Test
    public void test03IsPremium(){
        Assert.assertEquals(false, new AccountService().isAccountExist("userTest1"));
        Assert.assertEquals(true, new AccountService().isAccountExist("userTest"));
    }

    @Test
    public void test04IsPremium(){
        System.out.println(System.currentTimeMillis());
        Assert.assertEquals(4042, new AccountService().setPremium("userTest1",2,10000));
        Assert.assertEquals(200, new AccountService().setPremium("userTest2",1,10000));
    }
}
