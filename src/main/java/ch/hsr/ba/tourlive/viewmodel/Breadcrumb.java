package ch.hsr.ba.tourlive.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class Breadcrumb {
	private List<Page> parentPages = new ArrayList<Page>();
	private Page activepage;

	public Breadcrumb(String path) {
		List<Page> parent = new ArrayList<Page>();
		String[] items = path.split("/");
		for (String string : items) {
			if (string.length() > 0) {
				char[] stringArray = string.toCharArray();
				stringArray[0] = Character.toUpperCase(stringArray[0]);
				parent.add(new Page(new String(stringArray), "/" + string));
			}
		}
		if (parent.size() > 1) {
			this.activepage = parent.remove(parent.size() - 1);
			this.parentPages = parent;
		} else if (parent.size() == 1) {
			this.activepage = parent.get(0);
		}
	}

	public Breadcrumb(List<Page> parentPages, Page activepage) {
		this.parentPages = parentPages;
		this.activepage = activepage;
	}

	public List<Page> getParentPages() {
		return parentPages;
	}

	public void setParentPages(List<Page> parentPages) {
		this.parentPages = parentPages;
	}

	public Page getActivepage() {
		return activepage;
	}

	public void setActivepage(Page activepage) {
		this.activepage = activepage;
	}

}
