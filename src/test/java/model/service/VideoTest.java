package model.service;

import model.dao.base.DataHouse;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/7 9:59
 * @description:
 * @modifiedBy By:
 * @version :
 */
public class VideoTest {
    static DataHouse db;
    static String tableName;
    @BeforeClass
    public static void datatableInit() {
        db = DataHouse.getInstance();
        db.init("src/test/resources/database");
    }
//    @Test
//    public void test01getAllVideos(){
//        System.out.println("------------1");
//        System.out.println(new VideoService().getAllVideos());
//    }
//
//    @Test
//    public void test02getRandomVideo(){
//        System.out.println("------------2");
//        System.out.println(new VideoService().getRandomVideosWithNum(0));
//        System.out.println(new VideoService().getRandomVideosWithNum(1));
//        System.out.println(new VideoService().getRandomVideosWithNum(2));
//        System.out.println(new VideoService().getRandomVideosWithNum(3));
//    }
    @Test
    public void test03BlurSearch() {
        System.out.println("-----------3");
        System.out.println(new VideoService().blurSearchByName("qaq"));
        System.out.println(new VideoService().blurSearchByName("cam"));
    }
}
