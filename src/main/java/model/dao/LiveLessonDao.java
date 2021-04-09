package model.dao;

import model.dao.base.DataHouse;
import model.entity.LiveLessonTable;

import java.util.HashMap;
import java.util.List;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/5 23:59
 * @description:
 * @modifiedBy By:
 * @version :
 */public class LiveLessonDao {
    String tableName;
    DataHouse db;


    public LiveLessonDao(){
        tableName="LiveLessonTable";
        db= DataHouse.getInstance();
    }

    public void saveLiveLessonTable(LiveLessonTable liveLessonTable){
        db.insert(tableName,liveLessonTable);
    }

    public void updateLiveLessonTable(LiveLessonTable liveLessonTable){
        db.update(tableName,liveLessonTable);
    }

    public List<LiveLessonTable> getAllLiveLessonTable(){
        return (List<LiveLessonTable>) db.query(tableName,new HashMap<>());
    }
}
