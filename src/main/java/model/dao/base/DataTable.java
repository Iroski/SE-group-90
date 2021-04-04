package model.dao.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Setter;
import model.exception.database.DataItemNotExists;
import model.exception.database.InvalidArgument;
import model.exception.database.InvalidDataItem;
import model.exception.database.RedundancyDataItem;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.*;

/**
 * @author ：Yubo Wang
 * @date ：Created in 2021 2021/4/3 21:00
 * @description：
 * @modified By：
 * @version:
 */

@Setter
class DataTable {
    private Path path;
    private Class<?> itemClass;
    private HashMap<Long, DataItem> items;
    private Map<String, Field> itemClassFieldMap;
    private Map<String, Method> itemClassMethodMap;

    private void queryArgsCheck(HashMap<String, String> arguments) throws InvalidArgument {
        for (Map.Entry<String, String> argument: arguments.entrySet()) {
            if (itemClassFieldMap.containsKey(argument.getKey()))
                continue;
            throw new InvalidArgument(String.format("DataItem do not have %s field.", argument.getKey()));
        }
    }

    private void getItemClassInfo() {
        Class<?> tmpClass = itemClass;
        while (tmpClass != null && !tmpClass.getName().equalsIgnoreCase("java.lang.object")) {
            for(Field f : tmpClass.getDeclaredFields()) {
                itemClassFieldMap.put(f.getName(), f);
            }
            for (Method m : tmpClass.getDeclaredMethods()) {
                itemClassMethodMap.put(m.getName(), m);
            }
            tmpClass = tmpClass.getSuperclass();
        }
    }

    private void readItemClass() {
        try{
            File file = path.toFile();
            if (!file.exists())
                file.createNewFile();
            BufferedReader br= new BufferedReader(new FileReader(file));
            br.readLine();
            String line;
            items.clear();
            while ((line = br.readLine()) != null) {
                List<?> res = JSONObject.parseArray(line, itemClass);
                res.forEach(item->items.put(((DataItem)item).getId(), (DataItem) item));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected DataTable(Path path) {
        setPath(path);
        items = new HashMap<>();
        itemClassMethodMap = new HashMap<>();
        itemClassFieldMap = new HashMap<>();
        readFromFile();
        getItemClassInfo();
    }

    protected DataTable(Path path, Class<?> itemClass) {
        setItemClass(itemClass);
        setPath(path);
        items = new HashMap<>();
        itemClassMethodMap = new HashMap<>();
        itemClassFieldMap = new HashMap<>();
        readFromFile();
        getItemClassInfo();
    }

    protected boolean deleteFile() {
        File file = path.toFile();
        return file.delete();
    }

    protected void flush() {
        writeToFile();
    }

    protected void insert(DataItem item) throws InvalidDataItem {
        if (item == null || item.getClass() != this.itemClass)
            throw new InvalidDataItem("Wrong data item type is inserted!");
        if (items.getOrDefault(item.getId(), null) != null)
            throw new RedundancyDataItem("Data item has exists!");
        items.put(item.getId(), item);
        flush();
    }

    protected void delete(long itemId) {
        if (!items.containsKey(itemId))
            return;
        items.remove(itemId);
        flush();
    }

    protected List<?> query(HashMap<String, String> arguments) throws InvalidArgument{
        queryArgsCheck(arguments);
        ArrayList<DataItem> results = new ArrayList<>();
        if (arguments.containsKey("id") && items.containsKey(Long.valueOf(arguments.get("id")))) {
            results.add(items.get(Long.valueOf(arguments.get("id"))));
            return results;
        }

        for (Map.Entry<Long, DataItem> entry: items.entrySet()) {
            results.add(entry.getValue());
        }
        String get = "get";
        for (Map.Entry<String, String> argument : arguments.entrySet()) {
            ArrayList<DataItem> tmpResults = new ArrayList<>();
            try {
                String argName = argument.getKey();
                String getMethodName = get + argName.substring(0,1).toUpperCase() + argName.substring(1);
                Method getArgMethod = itemClassMethodMap.get(getMethodName);
                for (DataItem result : results) {
                    String argValue = String.valueOf(getArgMethod.invoke(result));
                    if (argValue.equals(argument.getValue()))
                        tmpResults.add(result);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            results = tmpResults;
        }
        return results;
    }

    protected void update(long itemId, DataItem item) throws DataItemNotExists, InvalidDataItem {
        if (item.getClass() != this.itemClass)
            throw new InvalidDataItem("Wrong data item type is inserted!");
        if (!items.containsKey(itemId))
            throw new DataItemNotExists("Data item not exists!");
        items.put(itemId, item);
        flush();
    }

    private void writeToFile(){
        try {
            File file = path.toFile();
            if (!file.exists())
                file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(itemClass.getName());
            bw.write(System.getProperty("line.separator"));
            List<DataItem> listOfItems = new ArrayList<>();
            int cnt = 1;
            for (Map.Entry<Long, DataItem> entry: items.entrySet()) {
                if ((cnt % 101) == 0) {
                    JSONArray.writeJSONString(bw, listOfItems);
                    bw.write(System.getProperty("line.separator"));
                    listOfItems.clear();
                    cnt = 0;
                }
                ++cnt;
                listOfItems.add(entry.getValue());
            }
            if (cnt != 0) {
                JSONArray.writeJSONString(bw, listOfItems);
                bw.write(System.getProperty("line.separator"));
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        try{
            File file = path.toFile();
            if (!file.exists())
                file.createNewFile();
            BufferedReader br= new BufferedReader(new FileReader(file));
            String line = br.readLine();
            if (itemClass == null) {
                itemClass = Class.forName(line);
            }
            items.clear();
            while ((line = br.readLine()) != null) {
                List<?> res = JSONObject.parseArray(line, itemClass);
                res.forEach(item->items.put(((DataItem)item).getId(), (DataItem) item));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
