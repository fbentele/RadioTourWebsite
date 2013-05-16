package ch.hsr.ba.tourlive.web.utils;

public class SlugGenerator {
	public static String makeASlug(String anything) {

		return anything.toLowerCase().replace(" ", "-").replace("ä", "ae")
				.replace("ü", "ue").replace("ö", "oe");
	}
}