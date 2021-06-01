package model.service;

import model.dao.base.DataHouse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/7 9:59
 * @description:
 * @modifiedBy By:
 * @version :
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class VideoTest {
    static DataHouse db;
    static String tableName;
    @BeforeClass
    public static void datatableInit() {
        db = DataHouse.getInstance();
        db.init("src/test/resources/database");
    }
    @Test
    public void test01getAllVideos(){
        Assert.assertEquals((new VideoService().getAllVideos()).getCode(), 200);
    }

    @Test
    public void test02getRandomVideo(){
        Assert.assertEquals((new VideoService().getRandomVideosWithNum(0)).getCode(), 400);
        Assert.assertEquals((new VideoService().getRandomVideosWithNum(1)).getCode(), 200);
        Assert.assertEquals((new VideoService().getRandomVideosWithNum(2)).getCode(), 200);
        Assert.assertEquals((new VideoService().getRandomVideosWithNum(3)).getCode(), 400);
    }
    @Test
    public void test03BlurSearch() {
        Assert.assertEquals((new VideoService().blurSearchByName("qaq")).getCode(), 4045);
        Assert.assertEquals((new VideoService().blurSearchByName("cam")).getCode(), 200);
    }
}
