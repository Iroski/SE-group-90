package model.service.premium;

import lombok.Data;
import model.entity.Account;

import java.math.BigDecimal;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/5/7 15:13
 * @description:
 * @modifiedBy By:
 */
@Data
public abstract class BasePremium {
    int type=0;
    long duration=0;
    int freeLesson=0;
    BigDecimal bargain=new BigDecimal("1.0");

    public Account setPremium(Account account, int num) {
        return null;
    }
}
