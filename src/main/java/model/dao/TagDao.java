package model.dao;

import model.dao.base.DataHouse;
import model.entity.Tag;

import java.util.HashMap;
import java.util.List;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/6 18:47
 * @description:
 * @modifiedBy By:
 */
public class TagDao {
    private DataHouse db;
    private String tableName;

    public TagDao() {
        db = DataHouse.getInstance();
        tableName = "Tag";
    }

    public void saveTag(Tag tag){
        db.insert(tableName,tag);
    }

    public List<Tag> getAllTag(){
        return (List<Tag>)db.query(tableName,new HashMap<>());
    }

    public void updateTag(Tag tag){
        db.update(tableName,tag);
    }
}
