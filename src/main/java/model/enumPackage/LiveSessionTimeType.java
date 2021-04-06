package model.enumPackage;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/6 0:28
 * @description:
 * @modifiedBy By:
 * @version :
 */
@AllArgsConstructor
public enum LiveSessionTimeType {
    NOT_START("notStart"),
    IS_END("isEnd");
    String description;


    public String getDescription() {
        return description;
    }
}
