package ch.hsr.ba.tourlive.model;

import java.util.ArrayList;

public class PageModel {
	public String browsertitle;
	public String pagetitle;
	public ArrayList<String> menuitems;
	public ArrayList<String> navbar;

	public String getBrowsertitle() {
		return browsertitle;
	}

	public void setBrowsertitle(String browsertitle) {
		this.browsertitle = browsertitle;
	}

	public String getPagetitle() {
		return pagetitle;
	}

	public void setPagetitle(String pagetitle) {
		this.pagetitle = pagetitle;
	}

	public ArrayList<String> getMenuitems() {
		return menuitems;
	}

	public void setMenuitems(ArrayList<String> menuitems) {
		this.menuitems = menuitems;
	}

	public ArrayList<String> getNavbar() {
		return navbar;
	}

	public void setNavbar(ArrayList<String> navbar) {
		this.navbar = navbar;
	}

	@Override
	public String toString() {
		return "PageModel [browsertitle=" + browsertitle + ", pagetitle="
				+ pagetitle + ", menuitems=" + menuitems + ", navbar=" + navbar
				+ "]";
	}

}
