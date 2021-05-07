package model.service;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/14 16:36
 * @description:
 * @modifiedBy By:
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderTest {
//    static DataHouse db;
//
//    @BeforeClass
//    public static void datatableInit() {
//        db = DataHouse.getInstance();
//        db.init("src/test/resources/database");
//    }
//
//    @Test
//    public void test01Premium() {
//        User user = new User("927986413@qq.com","userTest2","111111","1","1",1.0,1,1,new ArrayList<Long>(),new ArrayList<Long>());
//        System.out.println(new UserService().saveUser(user));
//        System.out.println("current: "+System.currentTimeMillis()/1000);
//        System.out.println((Account)new AccountService().getAccount(user.getName()).getObject());
//        SeasonPremium seasonPremium=new SeasonPremium();
//        seasonPremium.setPremium((Account) new AccountService().getAccount(user.getName()).getObject(),1);
//        System.out.println((Account) new AccountService().getAccount(user.getName()).getObject());
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
