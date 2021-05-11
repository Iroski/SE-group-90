package model.service.premium;

import lombok.Data;
import model.entity.Account;
import model.service.AccountService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/5/7 15:13
 * @description:
 * @modifiedBy By:
 */
@Data
public abstract class BasePremium {
    int type = 0;
    long duration = 0;
    int freeLesson = 0;
    BigDecimal bargain = new BigDecimal("1.0");

    public Account setPremium(Account account, int num) {
        long curTime = account.getPremiumEndTime();
        account.setFreeLiveLessonNum(account.getFreeLiveLessonNum() + freeLesson * num);
        if (curTime < System.currentTimeMillis() / 1000) {
            account.setPremiumEndTime(System.currentTimeMillis() / 1000 + num * duration);
            account.setPremiumLevel(type);
        } else {
            account.setPremiumEndTime(curTime + num * duration);
            account.getNotStartPremium().put((curTime), type);
        }
        return account;
    }
}
