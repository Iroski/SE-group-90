package model.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：YanBo Zhang
 */
public class CheckUserInfoValidation {
    public  static boolean checkEmail(String mail){
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        Pattern p   =   Pattern.compile(regex);
        Matcher m   =   p.matcher(mail);
        return m.find();
    }

    public static boolean checkMobile(String mobile) {
        return Pattern.matches("((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))", mobile);
    }
}