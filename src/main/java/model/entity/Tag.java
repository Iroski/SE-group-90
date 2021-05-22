package model.entity;

import lombok.*;
import model.dao.base.DataItem;

import java.util.List;

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
@NoArgsConstructor
public class Tag extends DataItem {
    private String tagName;
    private List<Video> videoList;

    public Tag(String tagName, List<Video> videoList) {
        this.tagName = tagName;
        this.videoList = videoList;
    }

    public Tag(@NonNull Long id, String tagName, List<Video> videoList) {
        super(id);
        this.tagName = tagName;
        this.videoList = videoList;
    }
}
