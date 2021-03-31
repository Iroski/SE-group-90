package com.se90.java.dao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.se90.java.entity.User;
import com.alibaba.fastjson.JSONArray;

import java.awt.desktop.ScreenSleepEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author     :YanBo Zhang
 * @date       :Created in 2021 2021/3/30 0:03
 * @description:
 * @modified By:
 * @version    :
 */public class UserDao {
    public static void main(String[]  args){
        User user1=new User("927986413@qq.com","1","1","1","1",1.0,1,1);
        User user2=new User("927986413@qq.com","1","1","1","1",1.0,1,1);
        ArrayList<User> users=new ArrayList<>();
        users.add(user1);
        users.add(user2);
        ArrayList<User> users1= (ArrayList<User>) new UserDao().readFromFile();
        users1.forEach(System.out::println);
    }

    public void writeToFile(ArrayList<User>  users){
        try{
            JSONArray jsonArray=JSONArray.parseArray(JSON.toJSONString(users));
            File file=new File("./data/user.json");
            if(!file.exists())
                if(!file.createNewFile())
                    throw new IOException();
            BufferedWriter bw=new BufferedWriter(new FileWriter(file));
            bw.write(jsonArray.toString());
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> readFromFile(){
        try{
            BufferedReader br=new BufferedReader(new FileReader("./data/user.json"));
            String data=br.readLine();
            return JSONObject.parseArray(data,User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
