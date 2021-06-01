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

    private static String filePathAdaptor(String filePath) {
        String separator = "/|\\\\";
        if (filePath == null)
            return "";
        String[] splitedFilePath = filePath.split(separator);
        StringBuilder newPath = new StringBuilder();
        for (int i = 0; i < splitedFilePath.length; ++i) {
            if (i == 0) {
                newPath = new StringBuilder(splitedFilePath[i]);
            } else {
                newPath.append(File.separator).append(splitedFilePath[i]);
            }
        }
        return newPath.toString();
    }

    private static String cutFilePath(String filePath) {
        String separator = "/|\\\\";
        String[] splitedFilePath = filePath.split(separator);
        int len = splitedFilePath.length;
        StringBuilder path = new StringBuilder();
        int fileParentDirIdx = 3;
        for (int i = fileParentDirIdx; i < len; ++i) {
            if (i == fileParentDirIdx) {
                path = new StringBuilder(splitedFilePath[i]);
            }
            else {
                path.append(File.separator).append(splitedFilePath[i]);
            }
        }
        return path.toString();
    }

    public static void staticVideoLoader(String fileDir) {
        DataHouse dataHouse = DataHouse.getInstance();
        File file = new File(fileDir);
        File[] videoFiles = file.listFiles();
        List<Video> videos = (List<Video>)dataHouse.query("Video", new HashMap<>());
        HashSet<String> videoPaths = new HashSet<>();
        for (Video video: videos) {
            String filePath = video.getStaticVideo().getFilePath();
            String coverPath = video.getStaticVideo().getCoverPath();
            video.getStaticVideo().setFilePath(filePathAdaptor(filePath));
            video.getStaticVideo().setCoverPath(filePathAdaptor(coverPath));
            videoPaths.add(filePath);
            dataHouse.update("Video", video);
        }
        for (File videoFile : videoFiles) {
            if (videoFile.isFile()) {
                String videoPath = cutFilePath(videoFile.getPath());
                if (videoPaths.contains(videoPath)) {
                    continue;
                }
                StaticVideo staticVideo = new StaticVideo(videoPath);
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
            String filePath = filePathAdaptor(image.getFilePath());
            image.setFilePath(filePath);
            dataHouse.update("StaticImage", image);
            imagePaths.add(filePath);
        }
        for (File profilePhotoFile : profilePhotoFiles) {
            if (profilePhotoFile.isFile()) {
                String profilePhotoFilePath = cutFilePath(profilePhotoFile.getPath());
                if (imagePaths.contains(profilePhotoFilePath)) {
                    continue;
                }
                StaticImage staticImage = new StaticImage(profilePhotoFilePath, profilePhotoFile.getName(), ImageType.PROFILEPHOTO);
                dataHouse.insert("StaticImage", staticImage);
            }
        }
    }
}
