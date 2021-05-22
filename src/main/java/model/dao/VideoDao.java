package model.dao;

import model.dao.base.DataHouse;
import model.entity.Coach;
import model.entity.Video;

import java.util.HashMap;
import java.util.List;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/6 16:52
 * @description:
 * @modifiedBy By:
 */
public class VideoDao {
    private DataHouse db;
    private String tableName;

    public VideoDao() {
        db = DataHouse.getInstance();
        tableName = "Video";

    }

    public List<Video> getAllVideos(){
        return (List<Video>)db.query(tableName,new HashMap<>());
    }

    public List<Video> blurSearch(String attName, String value) {
        String queryArg = attName + "`" + value;
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("HAS", queryArg);
        return (List<Video>) db.query(tableName, arguments);
    }

    public void updateVideo(Video video){
        db.update(tableName,video);
    }
    public void saveVideo(Video video){
        db.insert(tableName,video);
    }


}
