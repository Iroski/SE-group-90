package model.utils;

import model.dao.base.DataHouse;
import model.entity.StaticImage;
import model.entity.StaticVideo;
import model.entity.Video;
import model.enumPackage.ImageType;

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

    private static boolean isFileExist(File file, HashSet<String> paths) {
        // TODO: 不同平台路径适配状况
        String filePath = file.getPath();
        String separator = "/|\\\\";
        String[] splitedFilePath = filePath.split(separator);
        int len = splitedFilePath.length;
        Path path = Paths.get(splitedFilePath[len-3], splitedFilePath[len-2], splitedFilePath[len-1]);
        if (paths.contains(path.toString()))
            return true;
        return false;
    }
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
                if (isFileExist(videoFile, videoPaths))
                    continue;
                StaticVideo staticVideo = new StaticVideo(videoFile.getPath());
                Video video = new Video(staticVideo, 0L, new ArrayList<>(), new AtomicBoolean(false));
                dataHouse.insert("Video", video);
            }
        }
    }

    public static void staticDefaultProfilePhotoLoader(String fileDir) {
        DataHouse dataHouse = DataHouse.getInstance();
        File file = new File(fileDir);
        File[] profilePhotoFiles = file.listFiles();
        List<StaticImage> staticImages = (List<StaticImage>)dataHouse.query("StaticImage", new HashMap<>());
        HashSet<String> imagePaths = new HashSet<>();
        for (StaticImage image : staticImages) {
            imagePaths.add(image.getFilePath());
        }
        for (File profilePhotoFile : profilePhotoFiles) {
            if (profilePhotoFile.isFile()) {
                if (isFileExist(profilePhotoFile, imagePaths))
                    continue;
                StaticImage staticImage = new StaticImage(profilePhotoFile.getPath(), profilePhotoFile.getName(), ImageType.PROFILEPHOTO);
                dataHouse.insert("StaticImage", staticImage);
            }
        }
    }
}
