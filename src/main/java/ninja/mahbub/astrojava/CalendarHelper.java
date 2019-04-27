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
	
	public Calendar calendar = Calendar.getInstance();
	
	public int getDayOfYear (Calendar userCalendar) {
		return userCalendar.get(Calendar.DAY_OF_YEAR); 
	}
	
	public int getDayOfYear () {
		return getDayOfYear (calendar); 
	}
	
	public Calendar getCalendarDate(String userDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
