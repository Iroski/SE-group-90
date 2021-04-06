package utils;

import model.utils.CheckUserInfoValidation;
import model.utils.Encryption;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/6 13:47
 * @description:
 * @modifiedBy By:
 * @version :
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class DateUtilsTest {
    @Test
    public void test01(){
        System.out.println(Encryption.getMD5Str("123456"));
    }
}
