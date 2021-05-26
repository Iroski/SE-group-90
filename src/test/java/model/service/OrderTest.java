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

//    @Test
//    public void test01Premium() {
//        System.out.println(System.currentTimeMillis());
//        System.out.println(DateUtils.timeStampToDate(System.currentTimeMillis()));
//        new AccountService().createAccountForDeletedInfo();
//        //new AccountService().setPremium("userTest2", PremiumType.MONTH_PREMIUM.getType(),1);
//        Order order=new Order("userTest2",0,(long)0,PremiumType.YEAR_PREMIUM.getType(),1,
//                BigDecimal.ZERO, OrderStatus.NOT_PAYED.getCode(),System.currentTimeMillis());
//        OrderService orderService=new OrderService();
//        System.out.println(orderService.createPremiumOrder("userTest2",order));
//        System.out.println(orderService.payPremiumOrder("userTest2",order));
//        Account account=(Account)(new AccountService().getAccount("userTest2").getObject());
//        long premiumEndTime=account.getPremiumEndTime();
//        System.out.println(premiumEndTime);
//        System.out.println(DateUtils.timeStampToDate(premiumEndTime*1000));




        //System.out.println(DateUtils.timeStampToDate((System.currentTimeMillis()/1000+2592000)*1000));
//        System.out.println("current: "+System.currentTimeMillis()/1000);
//        System.out.println(new AccountService().getAccount("userTest2").getObject());
//        System.out.println( new AccountService().getAccount("userTest2").getObject());
//        new AccountService().setPremium("userTest2", PremiumType.YEAR_PREMIUM.getType(),1);
//        System.out.println( new AccountService().getAccount("userTest2").getObject());
//


//        System.out.println("------------1");
//        Order order = new Order("userTest1", OrderType.PREMIUM_ORDER.getCode(), null, PremiumType.MONTH_PREMIUM.getType(), (long) 10000, new BigDecimal("10"), OrderStatus.NOT_PAYED.getCode(), System.currentTimeMillis());
//        System.out.println(order);
//        Order returnOrder = (Order) new OrderService().createPremiumOrder("userTest1", order).getObject();
//        System.out.println(returnOrder);
//        System.out.println(new OrderService().payPremiumOrder("userTest1", returnOrder));
//        System.out.println(new OrderService().getOrdersByName("userTest1"));
//
//        order = new Order("userTest1", OrderType.PREMIUM_ORDER.getCode(), null, PremiumType.MONTH_PREMIUM.getType(), (long) 10000, new BigDecimal("10000"), OrderStatus.NOT_PAYED.getCode(), System.currentTimeMillis());
//        returnOrder = (Order) new OrderService().createPremiumOrder("userTest1", order).getObject();
//        System.out.println(new OrderService().payPremiumOrder("userTest1", returnOrder));
//        System.out.println(new OrderService().getOrdersByName("userTest1"));
//
//        order = new Order("userTest1", OrderType.PREMIUM_ORDER.getCode(), null, PremiumType.MONTH_PREMIUM.getType(), (long) 10000, new BigDecimal("10"), OrderStatus.NOT_PAYED.getCode(), System.currentTimeMillis());
//        returnOrder = (Order) new OrderService().createPremiumOrder("userTest1", order).getObject();
//        System.out.println(new OrderService().cancelPremiumOrder(returnOrder));
//        System.out.println(new OrderService().getOrdersByName("userTest1"));
//
//        System.out.println(new AccountService().getAccountByUsername("userTest1"));
//    }
//
//    @Test
//    public void test02LiveLesson(){
//        System.out.println("-----------2");
//        long currentTime=System.currentTimeMillis();
//        Order order=new Order("userTest1",OrderType.LIVELESSON_ORDER.getCode(),currentTime,null,null,new BigDecimal("10"),OrderStatus.NOT_PAYED.getCode(),System.currentTimeMillis());
//        LiveLesson liveLesson=new LiveLesson("userTest1","aaa",(long)4444, LiveLessonStatus.NOT_PAYED.getCode(),currentTime);
//        Order returnOrder= (Order) new OrderService().createLiveLessonOrder("userTest1",order,liveLesson).getObject();
//        System.out.println(returnOrder);
//        System.out.println(new OrderService().createLiveLessonOrder("userTest1",order,liveLesson));
//
//        System.out.println("-----------2.1 pay without free");
//        System.out.println(new OrderService().payLiveLessonOrder("userTest1",returnOrder,liveLesson,new AtomicBoolean(false)));
//        System.out.println(new AccountService().getAccountByUsername("userTest1"));
//        System.out.println(new OrderService().getOrdersByName("userTest1"));
//        System.out.println("-----------3.01 cancel after pay");
//        System.out.println(new OrderService().cancelLiveLessonOrderAfterPay("userTest1",liveLesson));
//        System.out.println(new AccountService().getAccountByUsername("userTest1"));
//        System.out.println(new OrderService().getOrdersByName("userTest1"));
//
//        currentTime=System.currentTimeMillis();
//         order=new Order("userTest1",OrderType.LIVELESSON_ORDER.getCode(),currentTime,null,null,new BigDecimal("10"),OrderStatus.NOT_PAYED.getCode(),System.currentTimeMillis());
//         liveLesson=new LiveLesson("userTest1","aaa",(long)4444, LiveLessonStatus.NOT_PAYED.getCode(),currentTime);
//         returnOrder= (Order) new OrderService().createLiveLessonOrder("userTest1",order,liveLesson).getObject();
//        System.out.println(returnOrder);
//        System.out.println(new OrderService().createLiveLessonOrder("userTest1",order,liveLesson));
//
//        System.out.println("-----------2.2 pay with free");
//        System.out.println(new OrderService().payLiveLessonOrder("userTest1",returnOrder,liveLesson,new AtomicBoolean(true)));
//        System.out.println(new AccountService().getAccountByUsername("userTest1"));
//        System.out.println(new OrderService().getOrdersByName("userTest1"));
//
//    }
//
//    @Test
//    public void test03CancelLiveLesson(){
//        System.out.println("-----------3");
//        long currentTime=System.currentTimeMillis();
//        Order order=new Order("userTest1",OrderType.LIVELESSON_ORDER.getCode(),currentTime,null,null,new BigDecimal("10"),OrderStatus.NOT_PAYED.getCode(),System.currentTimeMillis());
//        LiveLesson liveLesson=new LiveLesson("userTest1","aaa",(long)4444, LiveLessonStatus.NOT_PAYED.getCode(),currentTime);
//        Order returnOrder= (Order) new OrderService().createLiveLessonOrder("userTest1",order,liveLesson).getObject();
//        System.out.println(returnOrder);
//        System.out.println(new OrderService().createLiveLessonOrder("userTest1",order,liveLesson));
//        System.out.println(new CoachService().getCoachByName("aaa"));
//
//        System.out.println("-----------3.1 cancel before pay");
//        System.out.println(new OrderService().cancelLiveLessonOrderBeforePay("userTest1",liveLesson));
//        System.out.println(new AccountService().getAccountByUsername("userTest1"));
//        System.out.println(new OrderService().getOrdersByName("userTest1"));
//    }
}
