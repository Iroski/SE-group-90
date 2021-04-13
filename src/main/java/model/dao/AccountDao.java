package model.dao;

import model.dao.base.DataHouse;
import model.entity.Account;

import java.util.HashMap;
import java.util.List;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/13 20:40
 * @description:
 * @modifiedBy By:
 * @version :
 */
public class AccountDao {
    String tableName;
    DataHouse db;

    public AccountDao(){
        tableName="Account";
        db= DataHouse.getInstance();
    }

    public List<Account> getAllAccount(){
        return (List<Account>)db.query(tableName,new HashMap<String, String>());
    }

    public void saveAccount(Account account){
        db.insert(tableName,account);
    }

    public void updateAccount(Account account){
        db.update(tableName,account);
    }
}
