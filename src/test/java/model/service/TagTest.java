package model.service;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import model.dao.base.DataBase;
import model.entity.Coach;
import model.entity.Tag;
import model.entity.Video;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;
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
//
//    static DataBase db;
//    static String tableName;
//    @BeforeClass
//    public static void datatableInit() {
//        db = DataBase.getInstance();
//        db.init("src/test/resources/database");
//    }
//    @Test
//    public void test01saveEmptyTable(){
//        System.out.println("------------1");
//        System.out.println(new TagService().saveTag(new Tag("123",new ArrayList<Video>())));
//        System.out.println(new TagService().saveTag(new Tag("123",new ArrayList<Video>())));
//        System.out.println(new TagService().saveTag(new Tag("345",new ArrayList<Video>())));
//    }
//
//    @Test
//    public void test02getAllTags(){
//        System.out.println("------------2");
//        System.out.println(new TagService().getAllTagsName());
//    }
//
//    @Test
//    public void test03UpdateVideoTag(){
//        System.out.println("------------3");
//        Video video1=new Video((long)0,"!23","zyb",(long)111,(long)1,new ArrayList<String>(Collections.singletonList("123")),"123","123");
//        Video video2=new Video("456","zyb",(long)111,(long)1,new ArrayList<String>(),"123","123");
//        Video video3=new Video("not_insert","zyb",(long)111,(long)1,new ArrayList<String>(),"123","123");
//        System.out.println(new VideoService().saveVideo(video1));
//        System.out.println(new VideoService().saveVideo(video2));
//        System.out.println(new TagService().updateVideoTag("123",video1,"add"));
//        System.out.println(new TagService().updateVideoTag("123",video1,"add"));
//        System.out.println(new TagService().updateVideoTag("123",video1,"remove"));
//        System.out.println(new TagService().updateVideoTag("123",video1,"remove"));
//        System.out.println(new TagService().updateVideoTag("123",video1,"wogiao"));
//        System.out.println(new TagService().updateVideoTag("123",video3,"remove"));
//        System.out.println(new TagService().updateVideoTag("123",video1,"add"));
//        System.out.println(new TagService().updateVideoTag("123",video2,"add"));
//        System.out.println(new TagService().updateVideoTag("345",video1,"add"));
//        System.out.println(new TagService().updateVideoTag("1123",video1,"add"));
//
//    }

//    @Test
//    public void test04getVideosBytag(){
//        System.out.println("------------4");
//        System.out.println(new TagService().getVideosByTag("123"));
//        System.out.println(new TagService().getVideosByTag("345"));
//        System.out.println(new TagService().getVideosByTag("1123"));
//    }

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
}
