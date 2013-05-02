package ch.hsr.ba.tourlive.utils.importer;

import ch.hsr.ba.tourlive.model.MarchTableItem;

public class MarchTableImporter {
	private final int icon = 0;
	private final int altitude = 1;
	private final int distance = 2;
	private final int distanceToGo = 3;
	private final int ortschaft = 4;
	private final int werbekolonne = 5;
	private final int raceSlow = 6;
	private final int raceMedium = 7;
	private final int raceFast = 8;

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
		mti.setOrtschaft(strings[ortschaft]);
		mti.setWerbekolonne(strings[werbekolonne]);
		mti.setRaceSlow(strings[raceSlow]);
		mti.setRaceMedium(strings[raceMedium]);
		mti.setRaceFast(strings[raceFast]);
		return mti;
	}
}
