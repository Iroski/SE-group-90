package model.service.premium;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import model.entity.Account;

import java.math.BigDecimal;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/5/7 15:12
 * @description:
 * @modifiedBy By:
 * @version :
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MonthPremium extends BasePremium {

    public MonthPremium() {
        super.type=1;
        super.duration=2505600;
        super.freeLesson=0;
        super.bargain=new BigDecimal("1.0");
    }

}
