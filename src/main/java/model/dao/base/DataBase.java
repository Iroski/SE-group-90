package model.dao.base;

import model.exception.database.*;
import org.reflections.Reflections;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
        // Use reflections to find the subclasses of DataItem and create data table.
        File tableDir = tablesDirPath.toFile();
        Reflections reflections = new Reflections("model.dao.entity");
        Set<Class<? extends DataItem>> subClasses = reflections.getSubTypesOf(DataItem.class);
        for (Class<? extends DataItem> subClass : subClasses) {
            String tableName = subClass.getSimpleName();
            String fileName = tableName + ".json";
            Path filePath = Paths.get(tableDir.getPath(), fileName);
            DataTable dataTable = new DataTable(filePath, subClass);
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

    public void update(String tableName, DataItem item) throws NotInit, TableNotExists, InvalidDataItem, DataItemNotExists {
        checkInit();
        checkTable(tableName);
        tables.get(tableName).update(item);
    }

}
