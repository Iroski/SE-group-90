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
    private Long staticVideoId;
    private Long views;
    private List<String> tagsName;

    public Video(Long staticVideoId, Long views, List<String> tagsName) {
        this.staticVideoId = staticVideoId;
        this.views = views;
        this.tagsName = tagsName;
    }

    public Video(Long id, Long staticVideoId, Long views, List<String> tagsName) {
        super(id);
        this.staticVideoId = staticVideoId;
        this.views = views;
        this.tagsName = tagsName;
    }
}
