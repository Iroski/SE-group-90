package model.dao;

import model.dao.base.DataHouse;
import model.entity.Coach;
import model.entity.User;
import model.entity.Video;
import model.exception.database.RedundancyDataItem;
import model.utils.ResourceLoader;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.List;

/**
 * @author ：Yubo Wang
 * @date ：2021-05-20 15:04
 * @description：
 * @modified By：
 * @version:
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class BlurSearchTest {

    static DataHouse db;
    static String tableName;

    @BeforeClass
    public static void datatableInit() {
        db = DataHouse.getInstance();
        db.init("src/test/resources/database");
        ResourceLoader.staticVideoLoader("src/test/resources/view/videos");
    }


    @Test
    public void test02Insert() {
        tableName = Coach.class.getSimpleName();
//        System.out.println(tableName);
        for (long i = 0; i < 2; ++i) {
            Coach coach = new Coach("hly_y","male", 1, 1, 1, "hly", "sleeping", "null", null);
            try {
                coach  = (Coach) db.insert(tableName, coach);
            } catch (RedundancyDataItem e) {
                System.out.println(coach);
            }
        }
    }

    @Test
    public void test03VideoBlurSearch(){
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("HAS", "videoName`cam");
        List<Video> results = (List<Video>)db.query(Video.class.getSimpleName(), arguments);
        for (Video result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void test04CoachBlurSearch(){
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("HAS", "name`y_y");
        List<Coach> results = (List<Coach>)db.query(Coach.class.getSimpleName(), arguments);
        for (Coach result : results) {
            System.out.println(result);
        }
    }
}
