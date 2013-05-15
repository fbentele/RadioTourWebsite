package ch.hsr.ba.tourlive.utils.importer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.hsr.ba.tourlive.model.rider.Rider;

public class RiderImporter {
	private final static int startNr = 0;
	private final static int name = 1;
	private final static int team = 2;
	private final static int teamShort = 3;
	private final static int country = 4;
	private final static int timeOff = 5;
	private final static int timeRue = 6;
	private final static int timeVir = 7;
	private final static int uci = 8;
	private final static int neo = 9;

	public Rider convertTo(String[] strings) {
		final Rider rider = new Rider();
		rider.setStartNr(Integer.valueOf(strings[startNr]));
		rider.setBirthday(getBirthday(strings[uci]));
		rider.setCountry(strings[country]);
		rider.setName(strings[name]);
		rider.setTeam(strings[team]);
		rider.setTeamShort(strings[teamShort]);
		rider.setTimeOff(strings[timeOff]);
		rider.setTimeRueck(strings[timeRue]);
		rider.setTimeVirt(strings[timeVir]);
		rider.setNeo(!strings[neo].equals("0"));
		return rider;
	}

	// private String getCountry(String string) {
	// return string.substring(0, 3);
	// }

	private Date getBirthday(String string) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		Date temp;
		try {
			temp = formater.parse(string.substring(3));
		} catch (ParseException e) {
			temp = null;
		}
		return temp;
	}
}
