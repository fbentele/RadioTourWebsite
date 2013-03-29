package ch.hsr.ba.tourlive.utils;

import java.util.Random;

public class Randomizer {
	private static final Random NUMBER = new Random();
	public int nextNumber;

	public int getNextNumber() {
		return Math.abs(NUMBER.nextInt());
	}
}
