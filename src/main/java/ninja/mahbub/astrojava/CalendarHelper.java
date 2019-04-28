/**
 * 
 */
package ninja.mahbub.astrojava;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Mahbub Zaman
 *
 */
public class CalendarHelper {

	final static String TIME_ZONE_UTC = "UTC";
	final static String TIME_ZONE_LOCAL = "LOCAL";

	public int getDayOfYear (Calendar userCalendar) {
		return userCalendar.get(Calendar.DAY_OF_YEAR);
	}

	public String getTodaysDate ()  {
		Date date = new Date();
		return new SimpleDateFormat("dd-MM-yyyy").format(date);
	}
	
	public Calendar getCalendarDate(String userDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendar = Calendar.getInstance();
		Date date = null;
		try {
			date = sdf.parse(userDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(date);
		return calendar;
	}
}