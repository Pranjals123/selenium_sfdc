package sw_test.pageobjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by simon.wilby on 19/01/2017.
 */
public class RandomNameGenerator {

   public  static String getRandomNumber() {
        DateFormat dateFormat = new SimpleDateFormat("DDMMyyyy HHmmssSS");            /* Here you create object of the class DateFormat, and SPECIFY THE FORMAT (ddHHmmss). (Don`enter code here`t forget to import class Date(ctrl+shift+o  if you are using Eclipse)) */
        Date date = new Date();                                              /* Here you create object of the class Date. (Dont forget to import class Date. (Dont forget to import class Date(ctrl+shift+o  if you are using Eclipse)) */
        String randomNumber = dateFormat.format(date);                      /* Assign your current Date(something like 19184123) (ddHHmmSS) to a local variable(it requires a String) */
        return randomNumber;
    }

}
