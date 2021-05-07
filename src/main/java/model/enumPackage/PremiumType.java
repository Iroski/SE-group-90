package model.enumPackage;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import model.service.premium.*;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/13 21:29
 * @description:
 * @modifiedBy By:
 */
@AllArgsConstructor
public enum PremiumType {
    NOT_PREMIUM(0,"just a ordinary user",new NoPremium()),
    MONTH_PREMIUM(1,"this is membership which reuse once by a month", new MonthPremium()),
    SEASON_PREMIUM(2,"this is membership which reuse once by a season", new SeasonPremium()),
    YEAR_PREMIUM(3,"this is a membership which reuse for a whole next year",new YearPremium());
    int type;
    String description;
    BasePremium premium;

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public BasePremium getPremium(){
        return premium;
    }

    public static BasePremium getPremiumByType(int type){
        for(PremiumType e: PremiumType.values()){
            if(e.getType()==type)
                return e.getPremium();
        }
        return NOT_PREMIUM.getPremium();
    }
}
