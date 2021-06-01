package model.service;

import model.dao.base.DataHouse;
import model.entity.Account;
import model.entity.Order;
import model.entity.User;
import model.enumPackage.OrderStatus;
import model.enumPackage.PremiumType;
import model.service.premium.MonthPremium;
import model.utils.DateUtils;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/14 16:36
 * @description:
 * @modifiedBy By:
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderTest {
    static DataHouse db;

    @BeforeClass
    public static void datatableInit() {
        db = DataHouse.getInstance();
        db.init("src/test/resources/database");
    }

    @Test
    public void test01Premium() {
        System.out.println(DateUtils.timeStampToDate((System.currentTimeMillis()/1000+2592000)*1000));
        System.out.println("current: "+System.currentTimeMillis()/1000);
        System.out.println(new AccountService().getAccount("userTest2").getObject());
        System.out.println( new AccountService().getAccount("userTest2").getObject());
        new AccountService().setPremium("userTest2", PremiumType.YEAR_PREMIUM.getType(),1);
        System.out.println( new AccountService().getAccount("userTest2").getObject());
    }
}
