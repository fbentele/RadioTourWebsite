package ch.hsr.ba.tourlive.web.utils;

import java.util.Comparator;

import ch.hsr.ba.tourlive.web.model.ValueContainer;

public class SortValueContainer implements Comparator<ValueContainer> {

	@Override
	public int compare(ValueContainer vc1, ValueContainer vc2) {
		return (int) (vc1.getTimestamp() - vc2.getTimestamp());
	}
}
