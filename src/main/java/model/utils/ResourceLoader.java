package model.utils;

import model.dao.base.DataHouse;
import model.entity.StaticVideo;
import model.entity.Video;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
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
        List<?> videos = dataHouse.query("Video", new HashMap<>());
        HashSet<String> videoPaths = new HashSet<>();
        for (Object video: videos) {
            videoPaths.add(((Video)video).getStaticVideo().getFilePath());
        }
        for (File videoFile : videoFiles) {
            if (videoFile.isFile()) {
                String filePath = videoFile.getPath();
                String separator = "/|\\\\";
                String[] splitedFilePath = filePath.split(separator);
                int len = splitedFilePath.length;
                Path path = Paths.get(splitedFilePath[len-3], splitedFilePath[len-2], splitedFilePath[len-1]);
                if (videoPaths.contains(path.toString()))
                    continue;
                StaticVideo staticVideo = new StaticVideo(videoFile.getPath());
                Video video = new Video(staticVideo, 0L, new ArrayList<>(), new AtomicBoolean(false));
                dataHouse.insert("Video", video);
            }
        }
    }
}
