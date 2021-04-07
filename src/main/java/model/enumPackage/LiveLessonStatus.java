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
    NOT_PAYED(0,"the lesson is just book with out payment"),
    IS_PAYED(1,"the lesson is payed but not start"),
    IS_FINISH(2,"the lesson is finish successfully"),
    IS_CANCELED(3,"the lesson is canceled");
    int code;
    String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
