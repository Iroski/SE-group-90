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
    private StaticVideo staticVideo;
    private Long views;
    private List<String> tagsName;
    private AtomicBoolean isPremium;
    private String videoName;

    public Video(StaticVideo staticVideo, Long views, List<String> tagsName, AtomicBoolean isPremium) {
        this.staticVideo = staticVideo;
        this.views = views;
        this.tagsName = tagsName;
        this.isPremium = isPremium;
        this.videoName = staticVideo.getVideoName();
    }

    public Video(Long id, StaticVideo staticVideo, Long views, List<String> tagsName, AtomicBoolean isPremium) {
        super(id);
        this.staticVideo = staticVideo;
        this.views = views;
        this.tagsName = tagsName;
        this.isPremium = isPremium;
        this.videoName = staticVideo.getVideoName();
    }
}
