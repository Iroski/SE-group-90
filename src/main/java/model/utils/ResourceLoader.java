package model.utils;

import model.dao.base.DataHouse;
import model.entity.StaticVideo;
import model.entity.Video;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ：Yubo Wang
 * @date ：2021-04-08 11:39
 * @description：Load the resources from specific dir
 * @modified By：
 * @version:
 */
public class ResourceLoader {
    public static void staticVideoLoader(String fileDir) {
        DataHouse dataHouse = DataHouse.getInstance();
        File file = new File(fileDir);
        File[] videoFiles = file.listFiles();
        for (File videoFile : videoFiles) {
            if (videoFile.isFile()) {
                StaticVideo staticVideo = new StaticVideo(videoFile.getPath());
                HashMap<String, String> queryArgs = new HashMap<>();
                queryArgs.put("filePath", staticVideo.getFilePath());
                List<?> staticVideos = dataHouse.query("StaticVideo", queryArgs);
                if (staticVideos.size() > 0) {
                    continue;
                } else {
                    staticVideo = (StaticVideo) dataHouse.insert("StaticVideo", staticVideo);
                    Video video = new Video(staticVideo.getId(), 0L, new ArrayList<>());
                    dataHouse.insert("Video", video);
                }
            }
        }
    }
}
