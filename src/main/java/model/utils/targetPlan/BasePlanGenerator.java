package model.utils.targetPlan;

import lombok.Data;

import java.util.Random;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/5/7 18:15
 * @description:
 * @modifiedBy By:
 * @version :
 */
@Data
public class BasePlanGenerator {
    int pushUpsMax=3;
    int pushUpsTimes=3;
    int sitUpsMax=3;
    int sitUpsTimes=3;
    int crunchyMax=3;
    int crunchyTimes=3;
    int squatMax=3;
    int squatTimes=3;
    int openCloseJumpMax=3;
    int openCloseJumpTimes=3;
    int bobbyJumpMax=3;
    int bobbyJumpTime=3;
    int highLegUpMax=3;
    int highLegUpTimes=3;
    int benchPressMax=3;
    int benchPressTimes=3;
    int verticalPushUpsMax=3;
    int verticalPushUpsTimes=3;
    int actNum=9;
    int lastIndex=-1;


    public String generatePlan(Integer height,Double weight,String target){
        double BMI;
        if(height==null||weight==null)
            BMI=22;
        else
            BMI=(double)((weight/(height/100))/(height/100));
        System.out.println(BMI);
        return getPlans(BMI,target);
    }

    protected String getPlans(Double BMI,String target){
        int times=3;
        if(BMI>25){
            times=5;
        }else if(BMI>23){
            times=4;
        }
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("target: ").append(target).append(System.lineSeparator());
        stringBuilder.append("this lecture's plan: ").append(System.lineSeparator());

        for(int i=0;i<times;++i){
            stringBuilder.append(i+1);
            stringBuilder.append(" round: ").append(System.lineSeparator());
            int randNum=new Random().nextInt(5)+3;
            for(int j=0;j<randNum;++j){
                int randAct= new Random().nextInt(actNum);
                while(randAct==lastIndex)
                    randAct= new Random().nextInt(actNum);
                lastIndex=randAct;

                int actNum=0;
                int actTimes=0;
                String act="";
                switch (randAct){
                    case 0:
                        act="push-ups";
                        actNum=new Random().nextInt(pushUpsMax)+1;
                        actTimes=pushUpsTimes;
                        break;
                    case 1:
                        act="sit-ups";
                        actNum=new Random().nextInt(sitUpsMax)+1;
                        actTimes=sitUpsTimes;
                        break;
                    case 2:
                        act="crunchy";
                        actNum=new Random().nextInt(crunchyMax)+1;
                        actTimes=crunchyTimes;
                        break;
                    case 3:
                        act="squat";
                        actNum=new Random().nextInt(squatMax)+1;
                        actTimes=squatTimes;
                        break;
                    case 4:
                        act="open close jump";
                        actNum=new Random().nextInt(openCloseJumpMax)+1;
                        actTimes=openCloseJumpTimes;
                        break;
                    case 5:
                        act="bobby jump";
                        actNum=new Random().nextInt(bobbyJumpMax)+1;
                        actTimes=bobbyJumpTime;
                        break;
                    case 6:
                        act="high leg-up";
                        actNum=new Random().nextInt(pushUpsMax)+1;
                        actTimes=highLegUpTimes;
                        break;
                    case 7:
                        act="bench press";
                        actNum=new Random().nextInt(benchPressMax)+1;
                        actTimes=benchPressTimes;
                        break;
                    case 8:
                        act="vertical push-ups";
                        actNum=new Random().nextInt(verticalPushUpsMax)+1;
                        actTimes=verticalPushUpsTimes;
                        break;
                }
                if(actTimes==0){
                    --j;
                }else{
                    actTimes=new Random().nextInt(actTimes)+1;
                    stringBuilder.append(act);
                    stringBuilder.append(" *");
                    stringBuilder.append(actNum);
                    stringBuilder.append("0 , ");
                    stringBuilder.append(actTimes);
                    stringBuilder.append(" times").append(System.lineSeparator());
                }
            }
            stringBuilder.append("rest for 30 sec").append(System.lineSeparator());
            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }
}
