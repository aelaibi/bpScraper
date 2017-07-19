package ma.ael.bank.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Numbers
{
  public static Double toDouble(String text)
  {
    try
    {
      NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
      Number number = format.parse(text);
      return Double.valueOf(number.doubleValue());
    } catch (ParseException e) {}
    return null;
  }
}