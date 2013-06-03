package ch.hsr.ba.tourlive.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
	private static final String TIME_FORMAT_PATTERN = "HH:mm:ss";
	private static final String TIME_SHORT_PATTERN = "mm:ss";
	private static final String DATE_TIME_FORMAT_PATTERN = "dd.MM.yyyy - HH:mm:ss";
	private static final String DATE_TIME_SHORT_FORMAT_PATTERN = "dd.MM.yyyy - HH:mm";
	private static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";
	private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);

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
			LOG.info("Wrong input format");
			return 0L;
		}
	}

	/**
	 * 
	 * @param timestamp
	 * @return returns a String of the timestamp in the format
	 *         {@value #TIME_FORMAT_PATTERN}
	 */
	public static String toTimeFormat(Long timestamp) {
		try {
			if (timestamp == -1)
				return "";
			Date date = new Date(timestamp);
			SimpleDateFormat s = new SimpleDateFormat(TIME_FORMAT_PATTERN);
			return s.format(date);
		} catch (NullPointerException e) {
			return "";
		}
	}

	/**
	 * 
	 * @param timestamp
	 * @return returns a String of the timestamp in the format
	 *         {@value #TIME_SHORT_PATTERN}
	 */
	public static String toShortTimeFormat(Long timestamp) {
		try {
			if (timestamp == -1)
				return "";
			Date date = new Date(timestamp);
			SimpleDateFormat s = new SimpleDateFormat(TIME_SHORT_PATTERN);
			return s.format(date);
		} catch (NullPointerException e) {
			LOG.info("wrong input formar");
			return "";
		}
	}

	/**
	 * @param javaDate
	 * @return returns a nicely formatted birthday
	 */
	public static String toBirthday(Date date) {
		SimpleDateFormat s = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		return s.format(date);
	}

	/**
	 * @param a
	 *            full time string in the format
	 *            {@value #DATE_TIME_FORMAT_PATTERN}
	 * @return
	 */
	public static Long fullDateToTimestamp(String fullDate) {
		try {
			Date d = new SimpleDateFormat(DATE_TIME_FORMAT_PATTERN).parse(fullDate);
			return d.getTime();
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 
	 * @param the
	 *            timestamp to be converted
	 * @return a formatted datetim string for the timestamp
	 *         {@value #DATE_TIME_FORMAT_PATTERN}
	 */
	public static String timestampToFullDate(Long timestamp) {
		SimpleDateFormat date = new SimpleDateFormat(DATE_TIME_FORMAT_PATTERN);
		try {
			Date d = new Date(timestamp);
			return date.format(d);
		} catch (NullPointerException e) {
			return "";
		}
	}

	/**
	 * 
	 * @param the
	 *            timestamp to be converted
	 * @return a formatted datetim string for the timestamp
	 *         {@value #DATE_TIME_SHORT_FORMAT_PATTERN}
	 */
	public static String timestampToShortDate(Long timestamp) {
		SimpleDateFormat date = new SimpleDateFormat(DATE_TIME_SHORT_FORMAT_PATTERN);
		try {
			Date d = new Date(timestamp);
			return date.format(d);
		} catch (NullPointerException e) {
			return "";
		}
	}

	/**
	 * @param a
	 *            full time string in the format
	 *            {@value #DATE_TIME_SHORT_FORMAT_PATTERN}
	 * @return
	 */
	public static Long shortDateToTimestamp(String fullDate) {
		try {
			Date d = new SimpleDateFormat(DATE_TIME_SHORT_FORMAT_PATTERN).parse(fullDate);
			return d.getTime();
		} catch (ParseException e) {
			LOG.info("Wrong input Format");
			return 0L;
		} catch (NullPointerException e) {
			LOG.info("No input string");
			return 0L;
		}
	}
}
