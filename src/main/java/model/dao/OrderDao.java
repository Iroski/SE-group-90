package model.dao;

import model.dao.base.DataHouse;
import model.dao.base.DataItem;
import model.entity.Order;

import java.util.HashMap;
import java.util.List;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/13 20:44
 * @description:
 * @modifiedBy By:
 * @version :
 */
public class OrderDao {
    String tableName;
    DataHouse db;

    public OrderDao(){
        tableName="Order";
        db= DataHouse.getInstance();
    }

    public List<Order> getAllOrder(){
        return (List<Order>)db.query(tableName,new HashMap<String, String>());
    }

    public DataItem saveOrder(Order order){
        return db.insert(tableName,order);
    }

    public void updateOrder(Order order){
        db.update(tableName,order);
    }
}
