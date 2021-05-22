package model.service;

import model.dao.base.DataHouse;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/6 23:57
 * @description:
 * @modifiedBy By:Yubo Wang
 * @version :
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class CoachTest {
    static DataHouse db;
    static String tableName;
    @BeforeClass
    public static void datatableInit() {
        db = DataHouse.getInstance();
        db.init("src/test/resources/database");
    }
//    @Test
//    public void test01GetEmptyTable(){
//        System.out.println("------------1");
//        ArrayList<Coach> coaches=new ArrayList<>();
//        coaches.add(new Coach("a","a",1,1,1,",",",",",",new ArrayList<Long>()));
//        coaches.add(new Coach("b","a",1,1,1,",",",",",",new ArrayList<Long>()));
//        System.out.println(new CoachService().saveCoaches(coaches));
//   }
//
//    @Test
//    public void test02getAllCoaches(){
//        System.out.println("------------2");
//        System.out.println(new CoachService().getAllCoaches());
//    }
//
//    @Test
//    public void test03getCoachById(){
//        System.out.println("------------3");
//        System.out.println(new CoachService().getCoachById((long) 0));
//        System.out.println(new CoachService().getCoachById((long) 10));
//    }
//
//    @Test
//    public void test04updateCoach(){
//        System.out.println("------------4");
//        ArrayList<Long> timeList=new ArrayList<>();
//        timeList.add((long)123);
//        timeList.add((long)1213);
//        timeList.add((long)1123);
//        Coach coach=new Coach((long)0,"a","a",1,1,1,",",",",",",timeList);
//        Coach coach1=new Coach((long)10,"a","a",1,1,1,",",",",",",new ArrayList<Long>());
//        System.out.println(new CoachService().updateCoach(coach));
//        System.out.println(new CoachService().updateCoach(coach1));
//    }
//
//    @Test
//    public void test05getReservedTimeById(){
//        System.out.println("------------5");
//        System.out.println(new CoachService().getReservedTimeById(0));
//        System.out.println(new CoachService().getReservedTimeById(1));
//        System.out.println(new CoachService().getReservedTimeById(20));
//    }
//
//    @Test
//    public void test06setReservedTimeById(){
//        System.out.println("------------6");
//        ArrayList<Long> timeList=new ArrayList<>();
//        timeList.add((long)4);
//        timeList.add((long)44);
//        timeList.add((long)444);
//        System.out.println(new CoachService().setReservedTimeById(0,timeList));
//        System.out.println(new CoachService().setReservedTimeById(1,timeList));
//        System.out.println(new CoachService().setReservedTimeById(20,timeList));
//        System.out.println(new CoachService().getReservedTimeById(1));
//        System.out.println(new CoachService().getReservedTimeById(2));
//        System.out.println(new CoachService().getReservedTimeById(20));
//    }
    @Test
    public void test07BlurSearch() {
        System.out.println("-----------7");
        System.out.println(new CoachService().blurSearchByName("hl"));
        System.out.println(new CoachService().blurSearchByName("ly"));
        System.out.println(new CoachService().blurSearchByName("hly"));
        System.out.println(new CoachService().blurSearchByName("zyb"));
    }
}
