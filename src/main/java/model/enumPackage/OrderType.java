package model.enumPackage;

import lombok.AllArgsConstructor;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/14 16:44
 * @description:
 * @modifiedBy By:
 */
@AllArgsConstructor
public enum OrderType {
    PREMIUM_ORDER(0),
    LIVELESSON_ORDER(1);
    int code;

    public int getCode(){
        return this.code;
    }
}
