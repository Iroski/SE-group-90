package model.enumPackage;

import lombok.AllArgsConstructor;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/6 19:16
 * @description:
 * @modifiedBy By:
 */
@AllArgsConstructor
public enum TagOperateType {
    ADD("add"),
    REMOVE("remove");
    String type;

    public String getType() {
        return type;
    }
}
