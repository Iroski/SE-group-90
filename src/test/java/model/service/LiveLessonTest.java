package model.service;

import model.dao.base.DataBase;
import model.entity.LiveLesson;
import model.entity.LiveLessonTable;
import model.entity.User;
import model.enumPackage.LiveLessonStatus;
import model.enumPackage.LiveSessionTimeType;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/6 23:32
 * @description:
 * @modifiedBy By:
 * @version :
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class LiveLessonTest {
//    static DataBase db;
//    static String tableName;
//    @BeforeClass
//    public static void datatableInit() {
//        db = DataBase.getInstance();
//        db.init("src/test/resources/database");
//    }
//
//    @Test
//    public void test01GetEmptyTable(){
//        System.out.println("------------1");
//        LiveLessonTable liveLessonTable=new LiveLessonTable("userTest", new ArrayList<LiveLesson>());
//        System.out.println(new LiveLessonService().getLiveLessonTableByUsername("userTest"));
//        System.out.println(new LiveLessonService().getLiveLessonTableByUsername("userTest500"));
//    }
//
//    @Test
//    public void test02UpdateTable(){
//        System.out.println("------------2");
//        LiveLessonTable liveLessonTable= (LiveLessonTable) new LiveLessonService().getLiveLessonTableByUsername("userTest").getObject();
//        liveLessonTable.getLessonList().add(new LiveLesson("userTest","asd",(long)1,1,(long)1));
//        liveLessonTable.getLessonList().add(new LiveLesson("userTest","asd", Long.MAX_VALUE,1,(long)1));
//        System.out.println(new LiveLessonService().updateLiveLessonTable(liveLessonTable));
//        LiveLessonTable liveLessonTable1=new LiveLessonTable("userTest111", new ArrayList<LiveLesson>());
//        System.out.println(new LiveLessonService().updateLiveLessonTable(liveLessonTable1));
//    }
//
//    @Test
//    public void test03getTableByUsername(){
//        System.out.println("------------3");
//        System.out.println(new LiveLessonService().getLiveLessonTableByUsername("userTest"));
//        System.out.println(new LiveLessonService().getLiveLessonTableByUsername("ab"));
//    }
//
//    @Test
//    public void test04getLiveLessonsByTimeCondition(){
//        System.out.println("------------4");
//        System.out.println(new LiveLessonService().getLiveLessonsByTimeCondition("userTest2", LiveSessionTimeType.NOT_START.getDescription()));
//        System.out.println(new LiveLessonService().getLiveLessonsByTimeCondition("userTest", LiveSessionTimeType.IS_END.getDescription()));
//        System.out.println(new LiveLessonService().getLiveLessonsByTimeCondition("A", LiveSessionTimeType.NOT_START.getDescription()));
//        System.out.println(new LiveLessonService().getLiveLessonsByTimeCondition("A", LiveSessionTimeType.IS_END.getDescription()));
//        System.out.println(new LiveLessonService().getLiveLessonsByTimeCondition("userTest2", "wdnmd"));
//    }
}
