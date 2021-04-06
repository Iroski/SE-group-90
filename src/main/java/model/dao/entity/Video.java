package model.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.dao.base.DataItem;

import java.util.List;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/6 16:43
 * @description:
 * @modifiedBy By:
 * @version :
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Video extends DataItem {
    private String videoName;
    private String author;
    private long length;
    private long views;
    private List<String> tagsName;
    private String videoPath;
    private String coverPath;
}
