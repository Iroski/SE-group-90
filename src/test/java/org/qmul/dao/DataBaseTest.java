package org.qmul.dao;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.qmul.model.dao.base.DataBase;
import org.qmul.model.dao.base.DataItem;
import org.qmul.model.dao.entity.User;

import java.util.ArrayList;
import java.util.HashMap;

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
        tableName = User.class.getName();
        db.addTable(User.class);
    }

    @Test
    public void test02Insert() {
        HashMap<Long, DataItem> myItems = new HashMap<>();
        HashMap<String, String> args = new HashMap<>();
        for (long i = 0; i < 200; ++i) {
            args.put("id", String.valueOf(i));
            ArrayList<DataItem> results = db.query(tableName, args);
            if (results.size() > 0)
                continue;
            User user = new User(i,"927986413@qq.com",String.valueOf(i % 20),"1","1","1",1.0,1,1);
            myItems.put(i, user);
            db.insert(tableName, user);
        }
    }

    @Test
    public void test03Delete() {
        db.delete(tableName, 5);
        HashMap<String, String> args = new HashMap<>();
        args.put("id", "5");
        ArrayList<DataItem> results = db.query(tableName, args);
        assertEquals(results.size(), 0);
    }

    @Test
    public void test04Update() {
        User new_user = new User(50,"123", "sb","1","1","1",1.0,1,1);
        db.update(tableName, 50, new_user);
        HashMap<String, String> args = new HashMap<>();
        args.put("id", "50");
        ArrayList<DataItem> results = db.query(tableName, args);
        for (DataItem result: results)
            assertEquals(result, new_user);
    }

    @Test
    public void test05DeleteTable() {
        db.delete(tableName, 25);
        HashMap<String, String> args = new HashMap<>();
        args.put("id", "25");
        ArrayList<DataItem> results = db.query(tableName, args);
        assertEquals(results.size(), 0);
    }
}
