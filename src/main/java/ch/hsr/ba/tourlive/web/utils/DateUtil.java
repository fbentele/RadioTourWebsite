package ch.hsr.ba.tourlive.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("unused")
public class DateUtil {
	private static final String TIME_FORMAT_PATTERN = "HH:mm:ss";
	private static final String TIME_SHORT_PATTERN = "mm:ss";
	private static final String DATE_TIME_FORMAT_PATTERN = "dd.mm.yyyy - HH:mm:ss";
	private static final String DATE_FORMAT_PATTERN = "dd.mm.yyyy";

	/**
	 * 
	 * @return returns the date in the format HH:mm:ss
	 */
	public static String timeNow() {
		Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat(TIME_FORMAT_PATTERN);
		return s.format(d);
	}

	/**
	 * 
	 * @param time
	 *            as Stringformat {@value #TIME_FORMAT_PATTERN}
	 * @return returns a Java Date representation of param time
	 */
	public static Date toDate(String time) {
		try {
			Date date = new SimpleDateFormat(TIME_FORMAT_PATTERN).parse(time);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param time
	 *            as Stringformat {@value #TIME_FORMAT_PATTERN}
	 * @return returns Long timestamp representation
	 */
	public static Long timeToTimestamp(String time) {
		try {
			Date date = new SimpleDateFormat(TIME_FORMAT_PATTERN).parse(time);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param timestamp
	 * @return returns a String of the timestamp in the format
	 *         {@value #TIME_FORMAT_PATTERN}
	 */
	public static String toTimeFormat(Long timestamp) {
		Date date = new Date(timestamp);
		SimpleDateFormat s = new SimpleDateFormat(TIME_FORMAT_PATTERN);
		return s.format(date);
	}

	/**
	 * 
	 * @param timestamp
	 * @return returns a String of the timestamp in the format
	 *         {@value #TIME_SHORT_PATTERN}
	 */
	public static String toShortTimeFormat(Long timestamp) {
		Date date = new Date(timestamp);
		SimpleDateFormat s = new SimpleDateFormat(TIME_SHORT_PATTERN);
		return s.format(date);
	}

	/**
	 * @param javaDate
	 * @return returns a nicely formatted birthday
	 */
	public static String toBirthday(Date date) {
		SimpleDateFormat s = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		return s.format(date);
	}

}
