package model.service.premium;

import lombok.Data;
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
@Data
public class SeasonPremium  extends BasePremium {
    public SeasonPremium() {
        super.type=2;
        super.duration=75168000;
        super.freeLesson=1;
        super.bargain=new BigDecimal("0.8");
    }

}
