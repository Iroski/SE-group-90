package model.dao.base;

import model.exception.database.*;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ：Yubo Wang
 */
public class DataBase {
    private Path tablesDirPath;
    private HashMap<String, DataTable> tables;

    private static boolean isInit = false;
    private static DataBase instance = new DataBase();

    private DataBase() {
        tables = new HashMap<>();
    }

    private void initTables() {
        File tableDir = tablesDirPath.toFile();
        File[] tableFiles = tableDir.listFiles();
        for (File tableFile: tableFiles) {
            System.out.println(tableFile);
            String fileName = tableFile.getName();
            String tableName = fileName.split("\\.")[0];
            DataTable dataTable = new DataTable(Path.of(tableFile.getPath()));
            tables.put(tableName, dataTable);
        }
    }

    private void checkInit() throws NotInit {
        if (!isInit)
            throw new NotInit("Please init database first!");
    }

    private void checkTable(String tableName) throws TableNotExists {
        if (!tables.containsKey(tableName))
            throw new TableNotExists(String.format("Table %s not exists!", tableName));
    }

    public void init(String tablesDirPath) {
        this.tablesDirPath = Path.of(tablesDirPath);
        initTables();
        isInit = true;
    }

    public static DataBase getInstance() {
        return instance;
    }

    public void addTable(Class<?> itemClass) throws NotInit, TableHaveExists, InvalidDataItem {
        checkInit();
        if (!DataItem.class.isAssignableFrom(itemClass)) {
            throw new InvalidDataItem(String.format("Class %s is not inherited from %s!", itemClass.getClass(), DataItem.class.getClass()));
        }
        String tableName = itemClass.getName();
        if (tables.containsKey(tableName))
            throw new TableHaveExists(String.format("Table %s has exist!", tableName));
        Path newTablePath = tablesDirPath.resolve(itemClass.getSimpleName() + ".json");
        DataTable newTable = new DataTable(newTablePath, itemClass);
        tables.put(itemClass.getName(), newTable);
    }

    public void delTable(String tableName) throws NotInit, TableNotExists {
        checkInit();
        checkTable(tableName);
        tables.get(tableName).deleteFile();
        tables.remove(tableName);
    }

    public void flush(String tableName) throws NotInit, TableNotExists {
        checkInit();
        checkTable(tableName);
        tables.get(tableName).flush();
    }

    public DataItem insert(String tableName, DataItem item) throws NotInit, TableNotExists, InvalidDataItem {
        checkInit();
        checkTable(tableName);
        return tables.get(tableName).insert(item);
    }

    public void delete(String tableName, long itemId) throws NotInit, TableNotExists {
        checkInit();
        checkTable(tableName);
        tables.get(tableName).delete(itemId);
    }

    public List<?> query(String tableName, HashMap<String, String> arguments) throws NotInit, TableNotExists, InvalidArgument {
        checkInit();
        checkTable(tableName);
        return tables.get(tableName).query(arguments);
    }

    public void update(String tableName, DataItem item) throws NotInit, TableNotExists, InvalidDataItem,DataItemNotExists {
        checkInit();
        checkTable(tableName);
        tables.get(tableName).update(item);
    }


}