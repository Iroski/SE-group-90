package model.utils;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/6 13:43
 * @description:
 * @modifiedBy By:
 * @version :
 */
public class DateUtils {

    public static Date timeStampToDate(long timeStamp){
        return new Date(timeStamp);
    }

    public static String timeStampToString(long timeStamp){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(timeStamp));
    }

    public static long dateToTimeStamp(Date date){
        return date.getTime();
    }

    public static long stringToTimeStamp(String time) throws NullPointerException {
         return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time, new ParsePosition(0)).getTime() ;
    }
}
