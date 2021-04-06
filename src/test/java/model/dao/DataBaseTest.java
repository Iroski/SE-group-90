package model.dao;

import static org.junit.Assert.*;

import model.exception.database.RedundancyDataItem;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import model.dao.base.DataBase;
import model.dao.entity.User;

import java.util.HashMap;
import java.util.List;

/**
 * @author ：Yubo Wang
 * @date ：2021-04-04 16:34
 * @description：
 * @modified By：
 * @version:
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class DataBaseTest {
    static DataBase db;
    static String tableName;

    @BeforeClass
    public static void datatableInit() {
        db = DataBase.getInstance();
        db.init("src/test/resources/database");
    }

    @Test
    public void test01AddTable() {
        tableName = User.class.getSimpleName();
    }

    @Test
    public void test02Insert() {
        for (long i = 0; i < 200; ++i) {
            User user = new User("927986413@qq.com",String.valueOf(i % 20),"1","1","1",1.0,1,1);
            try {
                user = (User) db.insert(tableName, user);
            } catch (RedundancyDataItem e) {
                System.out.println(user);
            }
        }
    }

    @Test
    public void test03Delete() {
        db.delete(tableName, 5);
        HashMap<String, String> args = new HashMap<>();
        args.put("id", "5");
        List<?> results = db.query(tableName, args);
        assertEquals(results.size(), 0);
    }

    @Test
    public void test04Update() {
        HashMap<String, String> args = new HashMap<>();
        args.put("id", "50");
        List<?> results = db.query(tableName, args);
        User user;
        System.out.println(results.size());
        user = (User) results.get(0);
        user.setAge(80);
        db.update(tableName, user);
        results = db.query(tableName, args);
        for (Object result: results)
            assertEquals(((User)result).getAge(), Integer.valueOf(80));
    }

    @Test
    public void test05DeleteTable() {
        db.delete(tableName, 25);
        HashMap<String, String> args = new HashMap<>();
        args.put("id", "25");
        List<?> results = db.query(tableName, args);
        assertEquals(results.size(), 0);
    }
}
