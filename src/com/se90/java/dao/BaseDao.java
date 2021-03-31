package com.se90.java.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.se90.java.entity.User;

import java.io.*;
import java.util.*;

/**
 * @author     :YanBo Zhang
 */
public class BaseDao {

    public static void main(String[] args){
        //使用示例
        User user1=new User("927986413@qq.com","2","1","1","1",1.0,1,1);
        User user2=new User("927986413@qq.com","1","1","1","1",1.0,1,1);
        ArrayList<User> users=new ArrayList<>();
        users.add(user1);
        users.add(user2);

        /*
        我这里想的是，你的类sql语句中from可以用这个read方法。只要我提供表名，从EntityType中提取保存路径与class就可以能找到对应的表
        类型的强制转换在service层实现。
        write写法类似。
         */
        String name="USER";
        new BaseDao().writeToFile(users,EntityType.USER.getFilePath());
        List<?> users1= new BaseDao().readFromFile(EntityType.valueOf(name).getFilePath(),EntityType.valueOf(name).getType());
        users1.forEach(System.out::println);
    }
    public String writeToFile(List<?> list,String path){
        try{
            File file=new File(path);
            if(!file.exists())
                if(!file.createNewFile())
                    throw new IOException();
            BufferedWriter bw=new BufferedWriter(new FileWriter(file));
            JSONArray.writeJSONString(bw,list);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
        return "success";
    }

    public List<?> readFromFile(String path, Class<?> type){
        try{
            BufferedReader br=new BufferedReader(new FileReader("./data/user.json"));
            String data=br.readLine();
            return JSONObject.parseArray(data,type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
