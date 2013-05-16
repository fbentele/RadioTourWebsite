package ch.hsr.ba.tourlive.web.utils.importer;

import ch.hsr.ba.tourlive.web.model.MarchTableItem;

public class MarchTableImporter {
	private final static int icon = 0;
	private final static int altitude = 1;
	private final static int distance = 2;
	private final static int distanceToGo = 3;
	private final static int settlement = 4;
	private final static int werbekolonne = 5;
	private final static int raceSlow = 6;
	private final static int raceMedium = 7;
	private final static int raceFast = 8;

	// private enum Icon {
	// achtung, kreisel, bahn, tunnel, verpflegung, rechts, links, bodenwelle,
	// info
	// }

	public MarchTableItem convertTo(String[] strings) {
		final MarchTableItem mti = new MarchTableItem();
		mti.setIcon(strings[icon]);
		mti.setAltitude(Integer.valueOf(strings[altitude]));
		mti.setDistance(Float.valueOf(strings[distance]));
		mti.setDistanceToGo(Float.valueOf(strings[distanceToGo]));
		mti.setSettlement(strings[settlement]);
		mti.setAdvertisingColumn(strings[werbekolonne]);
		mti.setRaceSlow(strings[raceSlow]);
		mti.setRaceMedium(strings[raceMedium]);
		mti.setRaceFast(strings[raceFast]);
		return mti;
	}
}
