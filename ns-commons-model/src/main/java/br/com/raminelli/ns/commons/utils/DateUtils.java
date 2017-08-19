package br.com.raminelli.ns.commons.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils {

	private static Calendar calendar = GregorianCalendar.getInstance(new Locale("pt_BR"));
    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public static boolean dateBeforeEquals(Date date1, Date date2) {
		return differenceInDays(date1, date2) <= 0;
	}
	
	public static boolean dateEquals(Date date1, Date date2) {
		return differenceInDays(date1, date2) == 0;
	}
	
	public static Date addDays(Date date, int days) {
		calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
	}
	
	public static Date parse(String date) {
		try {
			return df.parse(date);
		} catch (ParseException e) {
			return new Date();
		}
	}
	
	public static String format(Date date) {
		return df.format(date);		
	}
	
	public static int differenceInDays(Date date1, Date date2) {
		calendar.setTime(date1);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 0);
		Date dt1 = calendar.getTime();
		
		calendar.setTime(date2);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 0);
		Date dt2 = calendar.getTime();
		
        long milis = dt2.getTime() - dt1.getTime();
        return (int) Math.round(milis / 86400000);
    }
}
