package model.dao;

import model.dao.base.DataBase;
import model.entity.User;

import java.util.HashMap;
import java.util.List;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/5 23:54
 * @description:
 * @modifiedBy By:
 * @version :
 */public class UserDao {
    String tableName;
    DataBase db;

    public UserDao(){
        tableName="User";
        db=DataBase.getInstance();
    }

    public List<User> getAllUser(){
        return (List<User>)db.query(tableName,new HashMap<String, String>());
    }

    public void saveUser(User newUser){
        db.insert("User",newUser);
    }

    public void updateUser(User user){
        db.update("User",user);
    }

}
