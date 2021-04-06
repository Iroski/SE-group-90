package model.dao.base;

import model.dao.entity.Video;

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
    private DataBase db;
    private String tableName;

    public VideoDao() {
        db = DataBase.getInstance();
        tableName = "Video";

    }

    public List<Video> getAllVideos(){
        return (List<Video>)db.query(tableName,new HashMap<>());
    }

    public void updateVideo(Video video){
        db.update(tableName,video.getId(),video);
    }


}
