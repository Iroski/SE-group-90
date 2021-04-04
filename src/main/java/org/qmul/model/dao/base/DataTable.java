package org.qmul.model.dao.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.qmul.model.exception.BaseException;
import org.qmul.model.exception.InvalidDataItem;
import org.qmul.model.exception.RedundancyDataItem;

import java.io.*;
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
    String name;
    String path;
    Class itemClass;
    @NonNull private HashMap<Long, DataItem> items;

    public DataTable(String name, String path, Class itemClass) {
        setName(name);
        setItemClass(itemClass);
        setPath(path);
        items = new HashMap<>();
    }

    public ArrayList<DataItem> query(HashMap<String, String> args) throws BaseException {
        return null;
    }

    public void insert(DataItem item) throws InvalidDataItem {
        if (item.getClass() != this.itemClass)
            throw new InvalidDataItem("Wrong data item type is inserted!");
        if (items.getOrDefault(item.getId(), null) != null)
            throw new RedundancyDataItem("Data item has exists!");
        items.put(item.getId(), item);
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
