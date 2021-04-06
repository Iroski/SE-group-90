package model.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import model.dao.base.DataItem;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/5 23:31
 * @description:
 * @modifiedBy By:
 * @version :
 */

@Data
@AllArgsConstructor
public class LiveLesson {
    private String username;
    private String coachName;
    private long lessonTime;
    private int status;
    private long createTime;

}
