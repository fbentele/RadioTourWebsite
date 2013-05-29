package ch.hsr.ba.tourlive.web.controller.admin;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.service.DeviceService;
import ch.hsr.ba.tourlive.web.service.ImageDataService;
import ch.hsr.ba.tourlive.web.service.RaceService;
import ch.hsr.ba.tourlive.web.service.StageService;
import ch.hsr.ba.tourlive.web.service.ValueContainerService;
import ch.hsr.ba.tourlive.web.service.VideoDataService;
import ch.hsr.ba.tourlive.web.viewmodel.Breadcrumb;

@Controller
public class AdminDeviceController {
	@Autowired
	DeviceService deviceService;
	@Autowired
	RaceService raceService;
	@Autowired
	StageService stageService;
	@Autowired
	ValueContainerService valueContainerService;
	@Autowired
	ImageDataService imageDataService;
	@Autowired
	VideoDataService videoDataService;

	/**
	 * Show all Devices
	 */
	@RequestMapping(value = "/admin/device", method = RequestMethod.GET)
	public String manageDevice(Locale locale, Model model) {
		model.addAttribute("devices", deviceService.getAll());
		model.addAttribute("adminmenu", "true");
		model.addAttribute("races", raceService.getAllVisible());
		model.addAttribute("breadcrumb", new Breadcrumb("/admin/device", ""));
		model.addAttribute("positions",
				valueContainerService.getMostRecentForEachDevice(deviceService.getAll()));
		return "admin/manageDevice";
	}

	/**
	 * Completely Delete a Device
	 */
	@RequestMapping(value = "/admin/device/delete/{deviceId}", method = RequestMethod.GET)
	public String deleteDevice(@PathVariable("deviceId") String deviceId, Model model) {
		Device d = deviceService.getDeviceById(deviceId);
		for (Stage stage : stageService.getAllStagesForDevice(d)) {
			stage.removeDevice(d);
			stageService.update(stage);
		}
		imageDataService.deleteAllFromDevice(d);
		videoDataService.deleteAllFromDevice(d);
		valueContainerService.deleteAllFromDevice(d);
		deviceService.delete(deviceId);
		return "redirect:/admin/device";
	}

	/**
	 * Show Device Form
	 */
	@RequestMapping(value = "/admin/device/edit/{deviceId}", method = RequestMethod.GET)
	public String editDevice(@PathVariable("deviceId") String deviceId, Model model) {
		Device device = deviceService.getDeviceById(deviceId);
		model.addAttribute("adminmenu", "true");
		model.addAttribute("races", raceService.getAllVisible());
		model.addAttribute("device", device);
		model.addAttribute("breadcrumb", new Breadcrumb("/admin/device/" + deviceId, ""));
		model.addAttribute("stages", stageService.getAllStagesForDevice(device));
		return "admin/editDevice";
	}

	/**
	 * Edit Device values
	 */
	@RequestMapping(value = "/admin/device/edit/{deviceId}", method = RequestMethod.POST)
	public String saveDevice(@PathVariable("deviceId") String deviceId,
			@ModelAttribute("deice") Device device, Model model) {
		deviceService.update(device);
		return "redirect:/admin/device";
	}
}
