package model.entity;

import lombok.*;
import model.dao.base.DataItem;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/6 16:43
 * @description:
 * @modifiedBy By:
 * @version :
 */
@Data()
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Video extends DataItem {
    private String videoName;
    private String author;
    private Long length;
    private Long views;
    private List<String> tagsName;
    private String videoPath;
    private String coverPath;
    private AtomicBoolean isPremium;

    public Video(@NonNull Long id, String videoName, String author, Long length, Long views, List<String> tagsName, String videoPath, String coverPath, AtomicBoolean isPremium) {
        super(id);
        this.videoName = videoName;
        this.author = author;
        this.length = length;
        this.views = views;
        this.tagsName = tagsName;
        this.videoPath = videoPath;
        this.coverPath = coverPath;
        this.isPremium = isPremium;
    }

    public Video(String videoName, String author, Long length, Long views, List<String> tagsName, String videoPath, String coverPath, AtomicBoolean isPremium) {
        this.videoName = videoName;
        this.author = author;
        this.length = length;
        this.views = views;
        this.tagsName = tagsName;
        this.videoPath = videoPath;
        this.coverPath = coverPath;
        this.isPremium = isPremium;
    }
}
