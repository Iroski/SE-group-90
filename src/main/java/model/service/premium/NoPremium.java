package model.service.premium;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.entity.Account;

import java.math.BigDecimal;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/5/7 16:10
 * @description:
 * @modifiedBy By:
 * @version :
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NoPremium extends BasePremium {

    public NoPremium() {
        super.duration=0;
        super.freeLesson=0;
        super.bargain=new BigDecimal("1.0");
    }

}
