package model.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import model.dao.base.DataItem;

import java.util.List;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/6 15:46
 * @description:
 * @modifiedBy By:
 * @version :
 */
@Data()
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class LiveLessonTable extends DataItem {
    private String username;
    private List<LiveLesson> lessonList;

}
