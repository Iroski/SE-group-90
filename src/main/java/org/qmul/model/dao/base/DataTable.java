package org.qmul.model.dao.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.qmul.model.exception.database.DataItemNotExists;
import org.qmul.model.exception.database.InvalidArgument;
import org.qmul.model.exception.database.InvalidDataItem;
import org.qmul.model.exception.database.RedundancyDataItem;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Yubo Wang
 * @date ：Created in 2021 2021/4/3 21:00
 * @description：
 * @modified By：
 * @version:
 */
@Getter
@Setter
public class DataTable {
    private String name;
    private String path;
    private Class itemClass;
    private HashMap<Long, DataItem> items;

    private void queryArgsCheck(HashMap<String, String> arguments) throws InvalidArgument {
        for (Map.Entry<String, String> argument: arguments.entrySet()) {
            try {
                Field f = itemClass.getDeclaredField(argument.getKey());
            } catch (NoSuchFieldException e) {
                throw new InvalidArgument(String.format("DataItem do not have %s field.", argument.getKey()));
            }
        }
    }

    public DataTable(String name, String path, Class itemClass) {
        setName(name);
        setItemClass(itemClass);
        setPath(path);
        items = new HashMap<>();
    }

    public void insert(@NotNull DataItem item) throws InvalidDataItem {
        if (item.getClass() != this.itemClass)
            throw new InvalidDataItem("Wrong data item type is inserted!");
        if (items.getOrDefault(item.getId(), null) != null)
            throw new RedundancyDataItem("Data item has exists!");
        items.put(item.getId(), item);
    }

    public void delete(long itemId) {
        if (!items.containsKey(itemId))
            return;
        items.remove(itemId);
    }

    public ArrayList<DataItem> query(HashMap<String, String> arguments) throws InvalidArgument{
        queryArgsCheck(arguments);
        ArrayList<DataItem> results = new ArrayList<>();
        if (arguments.containsKey("id") && items.containsKey(arguments.get("id"))) {
            results.add(items.get(arguments.get("id")));
        } else {
            for (Map.Entry<Long, DataItem> entry: items.entrySet()) {
                results.add(entry.getValue());
            }
        }
        String get = "get";
        for (Map.Entry<String, String> argument : arguments.entrySet()) {
            ArrayList<DataItem> tmpResults = new ArrayList<>();
            try {
                String argName = argument.getKey();
                String getMethodName = get + argName.substring(0,1).toUpperCase() + argName.substring(1);
                Method getArgMethod = itemClass.getMethod(getMethodName);
                for (DataItem result : results) {
                    String argValue = (String) getArgMethod.invoke(result);
                    if (argValue.equals(argument.getValue()))
                        tmpResults.add(result);
                }
            } catch (NoSuchMethodException |  IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            results = tmpResults;
        }
        return results;
    }

    public void update(long itemId, DataItem item) throws DataItemNotExists, InvalidDataItem {
        if (item.getClass() != this.itemClass)
            throw new InvalidDataItem("Wrong data item type is inserted!");
        if (!items.containsKey(itemId))
            throw new DataItemNotExists("Data item not exists!");
        items.put(itemId, item);
    }

    public void writeToFile(){
        try {
            File file = new File(path);
            if (!file.exists())
                file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
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
        } finally {
        }
    }

    public void readFromFile() {
        try{
            BufferedReader br=new BufferedReader(new FileReader(path));
            String line;
            items.clear();
            while ((line = br.readLine()) != null) {
                List<DataItem> res = JSONObject.parseArray(line, itemClass);
                res.forEach(item->items.put(item.getId(), item));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
