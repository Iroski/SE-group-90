package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

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
    private Long lessonTime;
    private Integer status;
    private Boolean isCustomized;
    private String specificExercise;
    private Long createTime;
}
