package org.qmul.dao;

import org.junit.Test;
import org.qmul.model.dao.base.DataItem;
import org.qmul.model.dao.entity.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ：Yubo Wang
 * @date ：2021-04-04 14:58
 * @description：
 * @modified By：
 * @version:
 */
public class DataTableTest {
    @Test
    public void testQuery() {
        DataTable dt = new org.qmul.model.dao.base.DataTable("test_table", "./test_table", User.class);
        for (int i = 0; i < 200; ++i) {
            User user = new User(i,"927986413@qq.com","2","1","1","1",1.0,1,1);
            dt.insert(user);
        }
        HashMap<String, String> args = new HashMap<>();
        args.put("email", "927986413@qq.com");
        args.put("id", "1");
        ArrayList<DataItem> results = dt.query(args);
        for (DataItem result: results)
            System.out.println(result);
//        dt.writeToFile();
//        dt.readFromFile();
//        HashMap<Long, DataItem> items = dt.getItems();
//        for (Map.Entry<Long, DataItem> entry: items.entrySet()) {
//            System.out.println(entry);
//        }
    }

    @Test
    public void testWriteReadFile() {

    }
}
