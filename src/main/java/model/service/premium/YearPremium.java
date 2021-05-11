package model.service.premium;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import model.entity.Account;

import java.math.BigDecimal;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/5/7 15:13
 * @description:
 * @modifiedBy By:
 * @version :
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class YearPremium extends BasePremium {

    public YearPremium() {
        super.type=3;
        super.duration=300672000;
        super.freeLesson=12;
        super.bargain=new BigDecimal("0.7");
    }


}
