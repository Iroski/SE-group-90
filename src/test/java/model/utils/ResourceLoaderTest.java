package model.utils;

import model.dao.base.DataHouse;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author ：Yubo Wang
 * @date ：2021-04-10 16:35
 * @description：
 * @modified By：
 * @version:
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class ResourceLoaderTest {
    static DataHouse db;
    static String tableName;
    @BeforeClass
    public static void datatableInit() {
        db = DataHouse.getInstance();
        db.init("src/test/resources/database");
    }
    @Test
    public void test01ResLoader() {
        ResourceLoader.staticVideoLoader("src/test/resources/view/videos");
        ResourceLoader.staticDefaultProfilePhotoLoader("src/test/resources/view/images/default/profilephoto");
    }
}
