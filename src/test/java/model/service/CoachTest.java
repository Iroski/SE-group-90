package model.service;

import model.dao.base.DataHouse;
import model.entity.Coach;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

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

    @Test
    public void test02getAllCoaches(){
        Assert.assertEquals(200, new CoachService().getAllCoaches().getCode());
    }

    @Test
    public void test03getCoachById(){
        Assert.assertEquals(200, new CoachService().getCoachById((long) 0).getCode());
        Assert.assertEquals(200, new CoachService().getCoachById((long) 10).getCode());
    }

    @Test
    public void test04updateCoach(){
        ArrayList<Long> timeList=new ArrayList<>();
        timeList.add((long)123);
        timeList.add((long)1213);
        timeList.add((long)1123);
        Coach coach=new Coach((long)0,"a","a",1,1,1,",",",",",",timeList);
        Coach coach1=new Coach((long)10,"a","a",1,1,1,",",",",",",new ArrayList<Long>());
        Assert.assertEquals(200, new CoachService().updateCoach(coach));
        Assert.assertEquals(200, new CoachService().updateCoach(coach1));
    }

    @Test
    public void test05getReservedTimeById(){
        Assert.assertEquals(200, new CoachService().getReservedTimeById(0).getCode());
        Assert.assertEquals(200, new CoachService().getReservedTimeById(1).getCode());
        Assert.assertEquals(4044, new CoachService().getReservedTimeById(20).getCode());
    }

    @Test
    public void test06setReservedTimeById(){
        ArrayList<Long> timeList=new ArrayList<>();
        timeList.add((long)4);
        timeList.add((long)44);
        timeList.add((long)444);
        Assert.assertEquals(200, new CoachService().setReservedTimeById(0,timeList));
        Assert.assertEquals(200, new CoachService().setReservedTimeById(1,timeList));
        Assert.assertEquals(4044, new CoachService().setReservedTimeById(20,timeList));
        Assert.assertEquals(200, new CoachService().getReservedTimeById(1).getCode());
        Assert.assertEquals(200, new CoachService().getReservedTimeById(2).getCode());
        Assert.assertEquals(4044, new CoachService().getReservedTimeById(20).getCode());
    }
    @Test
    public void test07BlurSearch() {
        Assert.assertEquals(200, new CoachService().blurSearchByName("hl").getCode());
        Assert.assertEquals(200, new CoachService().blurSearchByName("ly").getCode());
        Assert.assertEquals(200, new CoachService().blurSearchByName("hly").getCode());
        Assert.assertEquals(4044, new CoachService().blurSearchByName("zyb").getCode());
    }
}
