package ch.hsr.ba.tourlive.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static final String TIME_FORMAT_PATTERN = "HH:mm:ss";

	/**
	 * 
	 * @return returns the date in the format HH:mm:ss
	 */
	public static String timeNow() {
		Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat(TIME_FORMAT_PATTERN);
		return s.format(d);
	}

	public static Date toDate(String time) {
		try {
			Date date = new SimpleDateFormat(TIME_FORMAT_PATTERN).parse(time);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Long toTimestamp(String time) {
		try {
			Date date = new SimpleDateFormat(TIME_FORMAT_PATTERN).parse(time);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
