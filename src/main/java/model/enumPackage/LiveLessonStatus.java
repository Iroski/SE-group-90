package model.enumPackage;

import lombok.AllArgsConstructor;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/5 23:34
 * @description:
 * @modifiedBy By:
 */
@AllArgsConstructor
public enum LiveLessonStatus {
    NOT_PAYED(0,"the lesson is just book with out payment","notStart"),
    IS_PAYED(1,"the lesson is payed but not start","notStart"),
    IS_FINISH(2,"the lesson is finish successfully","isEnd"),
    IS_CANCELED(3,"the lesson is canceled","isEnd");
    int code;
    String description;
    String type;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getType(){
        return type;
    }
}
