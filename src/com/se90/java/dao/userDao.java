package com.se90.java.dao;
import com.alibaba.fastjson.JSONObject;
import com.se90.java.entity.User;
import net.sf.json.JSONArray;

import java.util.ArrayList;

/**
 * @author     :YanBo Zhang
 * @date       :Created in 2021 2021/3/30 0:03
 * @description:
 * @modified By:
 * @version    :
 */public class userDao {
    public static void main(String[]  args){
        User user1=new User("927986413@qq.com","1","1","1","1",1.0,1,1);
        User user2=new User("927986413@qq.com","1","1","1","1",1.0,1,1);
        ArrayList<User> users=new ArrayList<>();
        users.add(user1);
        users.add(user2);
        JSONArray jsonArray=new JSONArray();
        jsonArray=JSONArray.fromObject(users);
        System.out.println(jsonArray.toString());
    }

    public void readUser( ){
        User user1=new User("927986413@qq.com","1","1","1","1",1.0,1,1);
        User user2=new User("927986413@qq.com","1","1","1","1",1.0,1,1);
        ArrayList<User> users=new ArrayList<>();
        users.add(user1);
        users.add(user2);
        JSONArray jsonArray=new JSONArray();
        jsonArray=JSONArray.fromObject(users);
        System.out.println(jsonArray.toString());
    }
}
