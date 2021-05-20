package model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class LiveLessonTable extends DataItem {
    private String username;
    private List<LiveLesson> lessonList;

    public LiveLessonTable(String username, List<LiveLesson> lessonList) {
        this.username = username;
        this.lessonList = lessonList;
    }

    public LiveLessonTable(Long id,String username, List<LiveLesson> lessonList) {
        super(id);
        this.username = username;
        this.lessonList = lessonList;
    }
}
