package model.service;

import model.dao.base.DataHouse;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author ：Yubo Wang
 * @date ：2021-05-21 19:20
 * @description：
 * @modified By：
 * @version:
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaticImageTest {
    static DataHouse db;

    @BeforeClass
    public static void datatableInit() {
        db = DataHouse.getInstance();
        db.init("src/test/resources/database");
    }
    @Test
    public void test01Premium() {
        System.out.println(new StaticImageService().getDefaultProfilePhotos().getObject());
    }
}
