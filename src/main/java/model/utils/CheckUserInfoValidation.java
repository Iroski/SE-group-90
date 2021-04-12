package model.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：YanBo Zhang
 */
public class CheckUserInfoValidation {
    public static boolean checkEmail(String mail){
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        Pattern p   =   Pattern.compile(regex);
        Matcher m   =   p.matcher(mail);
        return m.find();
    }

    public static boolean checkMobile(String mobile) {
        return Pattern.matches("((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))", mobile);
    }

    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean isFloat(String str){
        if (!Character.isDigit(str.charAt(0))||!Character.isDigit(str.charAt(str.length() - 1))){
            return false;
        }
        Character temp;
        for (int i = 1;i<str.length();i++){
            if (!Character.isDigit(str.charAt(i))){
                temp = str.charAt(i);
                if(temp.toString().equals(".")){
                    for(int j=i+1;j<str.length();j++){
                        if (!Character.isDigit(str.charAt(j))){
                            return false;
                        }
                    }
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }
}