package org.qmul.dao;

import org.junit.Test;
import org.qmul.model.dao.base.DataItem;
import org.qmul.model.dao.base.DataTable;
import org.qmul.model.dao.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Yubo Wang
 * @date ：Created in 2021 2021/4/3 21:00
 * @description：
 * @modified By：
 * @version:
 */
public class DaoTest {
    @Test
    public void testDataTable() {
        DataTable dt = new DataTable("test_table", "./test_table", User.class);
        for (int i = 0; i < 200; ++i) {
            User user = new User(i,"927986413@qq.com","2","1","1","1",1.0,1,1);
            dt.insert(user);
        }
        dt.writeToFile();
        dt.readFromFile();
        HashMap<Long, DataItem> items = dt.getItems();
        for (Map.Entry<Long, DataItem> entry: items.entrySet()) {
            System.out.println(entry);
        }
    }
}
