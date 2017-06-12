package ma.ael.bank.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by ael on 30/09/2015.
 */
public class Numbers {
    public static Double toDouble(String text) {
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            Number number = format.parse(text);
            return number.doubleValue();
        } catch (ParseException e) {
            return null;
        }


    }
}
