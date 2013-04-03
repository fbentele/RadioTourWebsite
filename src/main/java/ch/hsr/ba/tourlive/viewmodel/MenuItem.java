package ch.hsr.ba.tourlive.viewmodel;

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
}
