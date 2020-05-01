package ma.ael.bank.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;







public class DateHelper
{
  public static Date yesterdayDate()
  {
    return add2Date(new Date(), -1);
  }

  public static String yesterday() throws ConvertException
  {
    return toString(yesterdayDate());
  }

  public static Date add2Date(Date date, int days) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(5, days);
    return cal.getTime();
  }

  public static String toString(Date date, String format) throws ConvertException
  {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    try
    {
      return sdf.format(date);
    } catch (Exception e) {
      throw new ConvertException("impossible de formatter cette date " + date);
    }
  }

  public static String toString(Date date) throws ConvertException {
    return toString(date, "dd/MM/yyyy");
  }

  public static Date toDate(String text) throws ConvertException {
    String format = "dd/MM/yy";
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    try
    {
      return sdf.parse(text);
    } catch (ParseException e) {
      throw new ConvertException("impossible de parser cette date " + text);
    }
  }
}