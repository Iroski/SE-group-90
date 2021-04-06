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
public class Tag extends DataItem {
    private String tagName;
    private List<Video> videoList;
}
