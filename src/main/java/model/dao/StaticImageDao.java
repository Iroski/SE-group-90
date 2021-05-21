package model.dao;

import model.dao.base.DataHouse;
import model.entity.StaticImage;

import java.util.HashMap;
import java.util.List;

/**
 * @author ：Yubo Wang
 * @date ：2021-05-21 19:16
 * @description：
 * @modified By：
 * @version:
 */
public class StaticImageDao {
    String tableName;
    DataHouse db;

    public StaticImageDao(){
        tableName = "StaticImage";
        db = DataHouse.getInstance();
    }

    public List<StaticImage> getAllProfilePhoto(){
        HashMap<String, String> arg = new HashMap<>();
        arg.put("type", "PROFILEPHOTO");
        return (List<StaticImage>)db.query(tableName, arg);
    }
}
