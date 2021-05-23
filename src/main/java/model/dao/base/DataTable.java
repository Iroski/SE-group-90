package model.dao.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.Setter;
import model.exception.database.DataItemNotExists;
import model.exception.database.InvalidArgument;
import model.exception.database.InvalidDataItem;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.*;

/**
 * @author ：Yubo Wang
 */

@Setter
class DataTable {
    private Path path;
    private Class<?> itemClass;
    private HashMap<Long, DataItem> items;
    private Map<String, Field> itemClassFieldMap;
    private Map<String, Method> itemClassMethodMap;
    private Long curPK;

    private void queryArgsCheck(HashMap<String, String> arguments) throws InvalidArgument {
        for (Map.Entry<String, String> argument: arguments.entrySet()) {
            if (argument.getKey() == "HAS")
                continue;
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

    protected DataTable(Path path, Class<?> itemClass) {
        setItemClass(itemClass);
        setPath(path);
        items = new HashMap<>();
        itemClassMethodMap = new HashMap<>();
        itemClassFieldMap = new HashMap<>();
        curPK = Long.valueOf(0);
        readFromFile();
        getItemClassInfo();
    }

    protected void flush() {
        writeToFile();
    }

    protected DataItem insert(DataItem item) throws InvalidDataItem {
        if (item == null || item.getClass() != this.itemClass)
            throw new InvalidDataItem("Wrong data item type is inserted!");
        item.setId(curPK);
        ++curPK;
        items.put(item.getId(), item);
        flush();
        return item;
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

        if (arguments.containsKey("HAS")) {
            results = blurSearch(arguments.get("HAS"), results);
            arguments.remove("HAS");
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

    private ArrayList<DataItem> blurSearch(String args, ArrayList<DataItem> results) throws InvalidArgument{
        try {
            String []arg = args.split("`");
            String argName = arg[0];
            String value = arg[1];

            String getMethodName = "get" + argName.substring(0,1).toUpperCase() + argName.substring(1);
            Method getArgMethod = itemClassMethodMap.get(getMethodName);
            ArrayList<DataItem> tmpResults = new ArrayList<>();
            for (DataItem result : results) {
                String argValue = String.valueOf(getArgMethod.invoke(result));
                if (argValue.contains(value))
                    tmpResults.add(result);
            }
            results = tmpResults;
            return results;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new InvalidArgument("Wrong \"HAS\" order's value:" + args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    protected void update(DataItem item) throws DataItemNotExists, InvalidDataItem {
        if (item.getClass() != this.itemClass)
            throw new InvalidDataItem("Wrong data item type is inserted!");
        if (!items.containsKey(item.getId()))
            throw new DataItemNotExists("Data item not exists!");
        items.put(item.getId(), item);
        flush();
    }

    private void writeToFile(){
        try {
            File file = path.toFile();
            if (!file.exists())
                file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            // write headers
            bw.write("Current pk:" + curPK);
            bw.write(System.getProperty("line.separator"));
            List<DataItem> listOfItems = new ArrayList<>();
            int cnt = 1;
            // write contents
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
            if (!file.exists()) {
                file.createNewFile();
                writeToFile();
                return;
            }
            // Read file headers
            BufferedReader br= new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String curPk = line.split(":")[1];
            curPK = Long.valueOf(curPk);
            // Read file contents
            items.clear();
            while ((line = br.readLine()) != null) {
                List<?> results = JSONObject.parseArray(line, itemClass);
                for (Object result : results) {
                    DataItem dataItem = (DataItem) result;
                    items.put(dataItem.getId(), dataItem);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
