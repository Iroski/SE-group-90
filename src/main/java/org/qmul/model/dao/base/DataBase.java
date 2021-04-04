package org.qmul.model.dao.base;

import org.qmul.model.exception.database.NotInit;

import java.io.File;
import java.util.HashMap;

/**
 * @author ：Yubo Wang
 * @date ：Created in 2021 2021/4/3 21:00
 * @description：
 * @modified By：
 * @version:
 */
public class DataBase {
    private String tablesDirPath;
    private HashMap<String, DataTable> tables;

    private static boolean isInit = false;
    private static DataBase instance = new DataBase();

    private DataBase() {
        tables = new HashMap<>();
    }



    private void initTables() {
        try {
            File tableDir = new File(tablesDirPath);
            File[] tableFiles = tableDir.listFiles();
            for (File tableFile: tableFiles) {
                String fileName = tableFile.getName();
                String tableName = fileName.split(".")[0];
                Class tableClass = Class.forName(tableName);
                DataTable dataTable = new DataTable(tableName,tableFile.getPath(), tableClass);
                tables.put(tableName, dataTable);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void checkInit() throws NotInit {
        if (!isInit)
            throw new NotInit("Please init database first!");
    }

    public void init(String tablesDirPath) {
        this.tablesDirPath = tablesDirPath;
        initTables();
        isInit = true;
    }

    public static DataBase getInstance() throws NotInit {
        return instance;
    }
}
