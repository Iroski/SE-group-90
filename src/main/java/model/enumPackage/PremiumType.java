package model.enumPackage;

import lombok.AllArgsConstructor;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/13 21:29
 * @description:
 * @modifiedBy By:
 */
@AllArgsConstructor
public enum PremiumType {
    NOT_PREMIUM(0,"just a ordinary user"),
    MONTH_PREMIUM(1,"this is membership which reuse once by a month"),
    YEAR_PREMIUM(2,"this is a membership which reuse for a whole next year");
    int type;
    String description;

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
