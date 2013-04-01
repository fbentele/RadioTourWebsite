package ch.hsr.ba.tourlive.view;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.hsr.ba.tourlive.service.DeviceService;

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

	private HashMap<String, String> makeMenu() {
		HashMap<String, String> dings = new HashMap<String, String>();
		dings.put("Rennen", "/admin/race");
		dings.put("Geräte", "/admin/device");
		return dings;
	}
}
