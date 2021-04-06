package model.dao;

import common.CommunicationStatus;
import model.dao.base.DataBase;
import model.dao.entity.ReturnEntity;
import model.dao.entity.User;
import model.exception.database.DataItemNotExists;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
        db.update("User",user.getId(),user);
    }

}
