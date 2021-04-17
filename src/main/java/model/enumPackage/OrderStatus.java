package model.enumPackage;

import lombok.AllArgsConstructor;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/13 22:19
 * @description:
 * @modifiedBy By:
 */
@AllArgsConstructor
public enum OrderStatus {
    NOT_PAYED(0,"the order is just book with out payment"),
    IS_PAYED(1,"the order is payed"),
    IS_CANCELED(2,"the order is canceled");
    int code;
    String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
