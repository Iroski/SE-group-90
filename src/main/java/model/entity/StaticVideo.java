package model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import model.dao.base.StaticResources;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author ：Yubo Wang
 * @date ：2021-04-10 15:38
 * @description：
 * @modified By：
 * @version:
 */
@Data()
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StaticVideo extends StaticResources {

    private String videoName;
    private String type;
    private String author;
    private String coverPath;
    private String filePath;

    public StaticVideo(String videoName, String type, String author, String coverPath, String filePath) {
        this.videoName = videoName;
        this.type = type;
        this.author = author;
        this.coverPath = coverPath;
        this.filePath = filePath;
    }

    public StaticVideo(String filePath) {
        String separator = "/|\\\\";
        String[] splitedFilePath = filePath.split(separator);
        int len = splitedFilePath.length;
        Path path = Paths.get(splitedFilePath[len-3], splitedFilePath[len-2], splitedFilePath[len-1]);
        this.filePath = path.toString();
        parseName();
    }

    private void parseName() {
        File f = new File(filePath);
        videoName = f.getName();
        type = videoName.substring(videoName.lastIndexOf('.') + 1);
        videoName = videoName.substring(0, videoName.lastIndexOf('.'));
        author = videoName.substring(videoName.lastIndexOf('_') + 1);
        videoName = videoName.substring(0, videoName.lastIndexOf('_'));
    }
}
