package model.dao;

import model.dao.base.DataHouse;
import model.entity.User;
import model.exception.database.RedundancyDataItem;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author :Yubo Wang
 * @date :2021-04-04 16:34
 * @description:
 * @modified By:
 * @version:
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class DataHouseTest {
    static DataHouse db;
    static String tableName;

    @BeforeClass
    public static void datatableInit() {
        db = DataHouse.getInstance();
        db.init("src/test/resources/database");
    }

    @Test
    public void test01AddTable() {
        tableName = User.class.getSimpleName();
    }

    @Test
    public void test02Insert() {
        ArrayList<User> users = new ArrayList<>();
        for (long i = 0; i < 200; ++i) {
            HashMap<String, String> m_args = new HashMap<>();
            m_args.put("email", i + "testaccount@qq.com");
            List<User> m_result = ((List<User>) db.query("User", m_args));
            if (m_result.size() > 0) {
                for (User user : m_result)
                    db.delete(tableName, user.getId());
            }
            User user = new User((i + "testaccount@qq.com"),String.valueOf(i % 20),"1","1","1", null, 1.0,1,1, null, null);
            users.add(user);
            try {
                user = (User) db.insert(tableName, user);
                HashMap<String, String> args = new HashMap<>();
                args.put("email", i + "testaccount@qq.com");
                User result = ((List<User>) db.query(tableName, args)).get(0);
                assertEquals(result, user);
            } catch (RedundancyDataItem e) {
                e.printStackTrace();
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
        User user = new User(("testaccount@qq.com"),"20","1","1","1", null, 1.0,1,1, null, null);
        user = (User) db.insert(tableName, user);

        HashMap<String, String> args = new HashMap<>();
        args.put("id", String.valueOf(user.getId()));
        List<?> results = db.query(tableName, args);

        assertEquals(1, results.size());
        user = (User) results.get(0);
        user.setAge(80);
        db.update(tableName, user);
        results = db.query(tableName, args);
        for (Object result: results)
            assertEquals(((User)result).getAge(), Integer.valueOf(80));
    }
}
