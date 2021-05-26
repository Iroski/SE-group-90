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

    /**
     * create by: YanBo Zhang
     * description: The method to set premium for a account
     * Namely, you do not need to overwrite this if you wang to add a new premium level
     * Instead, you should rewrite a new generator for the new premium.
     * Only overwrite it when your new premium when you have some priority that the base not contain
     * create time: 2021/5/7 17:18
     * @Param: account
     * @Param: num
     * @return model.entity.Account
     */
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
