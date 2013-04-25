package ch.hsr.ba.tourlive.view;

import java.util.ArrayList;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.hsr.ba.tourlive.service.DeviceService;
import ch.hsr.ba.tourlive.service.RaceService;
import ch.hsr.ba.tourlive.viewmodel.MenuItem;

@Controller
public class AdminDeviceController {
	@Autowired
	DeviceService deviceService;
	@Autowired
	RaceService raceService;
	Logger log = LoggerFactory.getLogger(AdminDeviceController.class);

	@RequestMapping(value = "/admin/device", method = RequestMethod.GET)
	public String manageDevice(Locale locale, Model model) {
		model.addAttribute("devices", deviceService.getAll());
		model.addAttribute("menuitems", makeMenu());
		model.addAttribute("races", raceService.getAllVisible());
		return "admin/manageDevice";
	}

	@RequestMapping(value = "/admin/device/delete/{deviceId}")
	public String deleteDevice(@PathVariable("deviceId") String deviceId, Model model)
			throws Exception {
		// deviceService.delete(deviceId);
		log.error("If device is being deleted what happens to valuecontainer?");

		if (1 == Integer.valueOf("1"))
			throw new Exception();
		return "hoi";
	}

	private ArrayList<MenuItem> makeMenu() {
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		menu.add(new MenuItem("Rennen", "/admin/race"));
		menu.add(new MenuItem("Geräte", "/admin/device"));
		return menu;
	}
}
