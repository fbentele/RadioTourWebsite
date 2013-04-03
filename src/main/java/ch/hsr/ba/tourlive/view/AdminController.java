package ch.hsr.ba.tourlive.view;

import java.util.ArrayList;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import ch.hsr.ba.tourlive.model.Race;
import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.service.DeviceService;
import ch.hsr.ba.tourlive.service.RaceService;
import ch.hsr.ba.tourlive.service.StageService;
import ch.hsr.ba.tourlive.viewmodel.MenuItem;

@Controller
public class AdminController {
	@Autowired
	ApplicationContext context;
	@Autowired
	StageService stageService;
	@Autowired
	RaceService raceService;
	@Autowired
	DeviceService deviceService;

	Logger log = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Model model) {
		model.addAttribute("menuitems", makeMenu());
		model.addAttribute("races", raceService.getAll());
		return "admin/admin";
	}

	@RequestMapping(value = "/admin/race", method = RequestMethod.GET)
	public String manageRace(Locale locale, Model model) {
		model.addAttribute("menuitems", makeMenu());
		model.addAttribute("races", raceService.getAll());
		model.addAttribute("races", raceService.getAll());

		return "admin/manageRace";
	}

	@RequestMapping(value = "/admin/race/add", method = RequestMethod.POST)
	public String newRace(@ModelAttribute("race") Race race,
			SessionStatus status, Model model) {
		raceService.save(race);
		status.setComplete();
		return "redirect:/admin/race";
	}

	@RequestMapping(value = "/admin/race/edit/{raceId}", method = RequestMethod.GET)
	public String editRace(@PathVariable("raceId") Long raceId, Model model) {
		model.addAttribute("menuitems", makeMenu());
		Race race = raceService.getRaceById(raceId);
		model.addAttribute("race", race);
		model.addAttribute("stages", stageService.getAllByRace(race));
		model.addAttribute("races", raceService.getAll());

		return "admin/editRace";
	}

	@RequestMapping(value = "/admin/race/edit/{raceId}", method = RequestMethod.POST)
	public String editedRace(@PathVariable("raceId") Long raceId,
			@ModelAttribute("race") Race race, Model model) {
		model.addAttribute("menuitems", makeMenu());
		raceService.update(race);
		model.addAttribute("race", raceService.getRaceById(raceId));
		return "redirect:/admin/race";
	}

	@RequestMapping(value = "/admin/race/delete/{raceId}", method = RequestMethod.GET)
	public String removeRace(@PathVariable("raceId") Long raceId, Model model) {
		raceService.delete(raceId);
		model.addAttribute("menuitems", makeMenu());
		model.addAttribute("races", raceService.getAll());

		return "forward:/admin/race";
	}

	@RequestMapping(value = "/admin/stage", method = RequestMethod.GET)
	public String manageStage(Locale locale, Model model) {
		model.addAttribute("menuitems", makeMenu());
		model.addAttribute("races", raceService.getAll());

		return "forward:/admin/stage";
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/add", method = RequestMethod.POST)
	public String addStage(@ModelAttribute("stage") Stage stage,
			@RequestParam("starttime") String starttime,
			@RequestParam("endtime") String endtime,
			@PathVariable("raceId") Long raceId, SessionStatus status) {
		stage.setRace(raceService.getRaceById(raceId));
		stage.setStarttime(starttime);
		stage.setEndtime(endtime);
		stageService.save(stage);
		status.setComplete();
		return "redirect:/admin/race/edit/" + raceId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/edit/{stageId}", method = RequestMethod.GET)
	public String editStage(@PathVariable("stageId") Long stageId,
			@PathVariable("raceId") Long raceId, Model model) {
		model.addAttribute("stage", stageService.getStageById(stageId));
		model.addAttribute("race", raceService.getRaceById(raceId));
		model.addAttribute("menuitems", makeMenu());
		model.addAttribute("races", raceService.getAll());
		model.addAttribute("devices", deviceService.getAll());
		return "admin/editStage";
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/edit/{stageId}", method = RequestMethod.POST)
	public String updateStage(@ModelAttribute("stage") Stage stage,
			@RequestParam("starttime") String starttime,
			@RequestParam("endtime") String endtime,
			@PathVariable("raceId") Long raceId, Model model) {

		stage.setRace(raceService.getRaceById(raceId));
		stage.setEndtime(endtime);
		stage.setStarttime(starttime);
		stageService.update(stage);
		return "redirect:/admin/race/edit/" + raceId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/delete/{stageId}", method = RequestMethod.GET)
	public String deleteStage(@PathVariable("stageId") Long stageId,
			@PathVariable("raceId") Long raceId) {
		stageService.delete(stageId);
		return "redirect:/admin/race/edit/" + raceId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/device/add", method = RequestMethod.POST)
	public String editDevice(@PathVariable("stageId") Long stageId,
			@PathVariable("raceId") Long raceId,
			@ModelAttribute("device") String deviceId) {
		Stage stage = stageService.getStageById(stageId);
		stage.addDevice(deviceService.getDeviceById(deviceId));
		stageService.update(stage);
		return "redirect:/admin/race/" + raceId + "/stage/edit/" + stageId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/rider", method = RequestMethod.GET)
	public String addRider(@PathVariable("raceId") Long raceId, Locale locale,
			Model model) {
		model.addAttribute("menuitems", makeMenu());
		model.addAttribute("races", raceService.getAll());
		return "admin/manageRider";
	}

	private ArrayList<MenuItem> makeMenu() {
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		menu.add(new MenuItem("Rennen", "/admin/race"));
		menu.add(new MenuItem("Geräte", "/admin/device"));
		return menu;
	}
}
