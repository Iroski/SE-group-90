package model.service;

import model.dao.base.DataHouse;
import model.entity.Tag;
import model.entity.Video;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/7 9:29
 * @description:
 * @modifiedBy By:
 * @version :
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class TagTest {

    static DataHouse db;
    static String tableName;
    @BeforeClass
    public static void datatableInit() {
        db = DataHouse.getInstance();
        db.init("src/test/resources/database");
    }
    @Test
    public void test01saveEmptyTable(){
        Assert.assertEquals(40022, new TagService().saveTag(new Tag("123",new ArrayList<Video>())));
        Assert.assertEquals(40022, new TagService().saveTag(new Tag("123",new ArrayList<Video>())));
        Assert.assertEquals(40022, new TagService().saveTag(new Tag("123",new ArrayList<Video>())));
    }

    @Test
    public void test02getAllTags(){
        Assert.assertEquals(200, new TagService().getAllTagsName().getCode());
    }

    @Test
    public void test04getVideosBytag(){
        Assert.assertEquals(200, new TagService().getVideosByTag("123").getCode());
        Assert.assertEquals(200, new TagService().getVideosByTag("123").getCode());
        Assert.assertEquals(4046, new TagService().getVideosByTag("1123").getCode());
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
}
