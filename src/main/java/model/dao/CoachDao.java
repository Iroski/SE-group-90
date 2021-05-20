package model.dao;

import model.dao.base.DataHouse;
import model.entity.Coach;

import java.util.HashMap;
import java.util.List;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/6 13:34
 * @description:
 * @modifiedBy By:
 * @version :
 */
public class CoachDao {
    String tableName;
    DataHouse db;


    public CoachDao(){
        tableName="Coach";
        db= DataHouse.getInstance();
    }

    public List<Coach> getAllCoaches(){
        return (List<Coach>)db.query(tableName,new HashMap<>());
    }

    public List<Coach> blurSearch(String attName, String value) {
        String queryArg = attName + "`" + value;
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("HAS", queryArg);
        return (List<Coach>) db.query(tableName, arguments);
    }
    public void updateCoach(Coach coach){
        db.update(tableName,coach);
    }

    public void saveCoach(Coach coach){
        db.insert(tableName,coach);
    }


}
