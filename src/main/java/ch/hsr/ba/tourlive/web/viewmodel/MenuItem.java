package ch.hsr.ba.tourlive.web.viewmodel;

import java.util.ArrayList;
import java.util.List;

import ch.hsr.ba.tourlive.web.model.Stage;

public class MenuItem {
	private String menutitle;
	private String urlpath;

	public MenuItem(String title, String url) {
		this.menutitle = title;
		this.urlpath = url;
	}

	public String getMenutitle() {
		return menutitle;
	}

	public void setMenutitle(String menutitle) {
		this.menutitle = menutitle;
	}

	public String getUrlpath() {
		return urlpath;
	}

	public void setUrlpath(String urlpath) {
		this.urlpath = urlpath;
	}

	public static List<MenuItem> makeStageNavi() {
		List<MenuItem> navi = new ArrayList<MenuItem>();
		navi.add(new MenuItem("Top", "#top"));
		navi.add(new MenuItem("Streckenprofil", "#streckenprofil"));
		navi.add(new MenuItem("Abstand", "#abstand"));
		navi.add(new MenuItem("Livebilder", "#livebilder"));
		navi.add(new MenuItem("Karte", "#karte"));
		navi.add(new MenuItem("Live Ticker", "#liveticker"));
		navi.add(new MenuItem("Rennsituation", "#rennsituation"));
		navi.add(new MenuItem("Rangliste", "#rangliste"));
		navi.add(new MenuItem("Marschtabelle", "#marschtabelle"));
		return navi;
	}

	public static List<MenuItem> makeMenu(List<Stage> stages) {
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		for (Stage stage : stages) {
			menu.add(new MenuItem(stage.getStageName(), "/race/" + stage.getRace().getRaceSlug()
					+ "/stage/" + stage.getStageSlug()));
		}
		return menu;
	}
}