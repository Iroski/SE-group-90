package model.enumPackage;

import lombok.AllArgsConstructor;
import model.utils.target.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/5/7 17:54
 * @description:
 * @modifiedBy By:
 */
@AllArgsConstructor
public enum TargetType {
    FAT_REDUCTION("Reduce fat",new FatReduction()),
    ABDOMINAL_MUSCLES("Abdominal muscles",new AbdominalMuscles()),
    PECTORALIS("Pectoralis",new Pectoralis()),
    LEG("Legs",new Leg()),
    OTHER("Other",new BasePlanGenerator());

    String description;
    BasePlanGenerator basePlanGenerator;

    public String getDescription() {
        return description;
    }

    public static List<String> getAllDescription(){
        ArrayList<String> list=new ArrayList<>();
        for(TargetType e : TargetType.values()){
            list.add(e.getDescription());
        }
        return list;
    }

    public BasePlanGenerator getBasePlanGenerator() {
        return basePlanGenerator;
    }

    public static BasePlanGenerator getBasePlanGeneratorByDesc(String description) {
        for(TargetType e: TargetType.values()){
            if(e.getDescription().equals(description))
                return e.getBasePlanGenerator();
        }
        return new BasePlanGenerator();
    }
}
