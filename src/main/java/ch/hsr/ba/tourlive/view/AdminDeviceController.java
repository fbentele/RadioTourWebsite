package ch.hsr.ba.tourlive.view;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.hsr.ba.tourlive.service.DeviceService;
import ch.hsr.ba.tourlive.viewmodel.MenuItem;

@Controller
public class AdminDeviceController {
	@Autowired
	DeviceService deviceService;

	@RequestMapping(value = "admin/device", method = RequestMethod.GET)
	public String manageRace(Locale locale, Model model) {
		model.addAttribute("devices", deviceService.getAll());
		model.addAttribute("menuitems", makeMenu());
		return "admin/manageDevice";
	}

	private ArrayList<MenuItem> makeMenu() {
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		menu.add(new MenuItem("Rennen", "/admin/race"));
		menu.add(new MenuItem("Geräte", "/admin/device"));
		return menu;
	}
}
