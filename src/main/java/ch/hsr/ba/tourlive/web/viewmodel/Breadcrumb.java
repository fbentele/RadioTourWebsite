package ch.hsr.ba.tourlive.web.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class Breadcrumb {
	private List<Page> parentPages = new ArrayList<Page>();
	private Page activepage;
	@Autowired
	ReloadableResourceBundleMessageSource messageSource;

	public Breadcrumb(String path, String actualRaces) {
		List<Page> parent = new ArrayList<Page>();
		String[] items = path.split("/");
		String linkstring = "";
		for (String string : items) {
			if (string.length() > 0) {
				linkstring += string + "/";
				if (string.equals("race")) {
					parent.add(new Page(actualRaces, "/race"));
					continue;
				}
				if (string.equals("stage")) {
					continue;
				}
				char[] stringArray = string.toCharArray();
				stringArray[0] = Character.toUpperCase(stringArray[0]);
				boolean next = false;
				for (int i = 0; i < stringArray.length; i++) {
					if (next) {
						stringArray[i] = Character.toUpperCase(stringArray[i]);
						next = false;
					}
					if (stringArray[i] == '-') {
						stringArray[i] = ' ';
						next = true;
					}
				}
				parent.add(new Page(new String(stringArray), "/" + linkstring));
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
