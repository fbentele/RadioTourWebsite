package ch.hsr.ba.tourlive.web.viewmodel;

public class Page {
	private String title;
	private String link;

	public Page(String title, String link) {
		this.title = title;
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
