package ch.hsr.ba.tourlive.view;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import ch.hsr.ba.tourlive.model.Race;
import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.service.RaceService;
import ch.hsr.ba.tourlive.service.StageService;

@Controller
public class AdminController {
	@Autowired
	ApplicationContext context;
	@Autowired
	StageService stageService;
	@Autowired
	RaceService raceService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Model model) {
		model.addAttribute("menuitems", makeMenu());
		return "admin/admin";
	}

	@RequestMapping(value = "admin/race", method = RequestMethod.GET)
	public String manageRace(Locale locale, Model model) {
		model.addAttribute("menuitems", makeMenu());
		model.addAttribute("races", raceService.getAll());
		return "admin/manageRace";
	}

	@RequestMapping(value = "admin/race/add", method = RequestMethod.POST)
	public String newRace(@ModelAttribute("race") Race race,
			SessionStatus status, Model model) {
		raceService.save(race);
		status.setComplete();
		return "redirect:/admin/race";
	}

	@RequestMapping(value = "admin/race/edit/{raceId}", method = RequestMethod.GET)
	public String editRace(@PathVariable("raceId") Long raceId, Model model) {
		model.addAttribute("menuitems", makeMenu());
		model.addAttribute("race", raceService.getRaceById(raceId));
		return "admin/editRace";
	}

	@RequestMapping(value = "admin/race/edit/{raceId}", method = RequestMethod.POST)
	public String editedRace(@PathVariable("raceId") Long raceId,
			@ModelAttribute("race") Race race, Model model) {
		model.addAttribute("menuitems", makeMenu());
		raceService.update(race);
		model.addAttribute("race", raceService.getRaceById(raceId));
		return "redirect:/admin/race";
	}

	@RequestMapping(value = "admin/race/delete/{raceId}", method = RequestMethod.GET)
	public String removeRace(@PathVariable("raceId") Long raceId, Model model) {
		raceService.delete(raceId);
		model.addAttribute("menuitems", makeMenu());
		return "forward:/admin/race";
	}

	@RequestMapping(value = "/admin/stage", method = RequestMethod.GET)
	public String manageStage(Locale locale, Model model) {
		model.addAttribute("menuitems", makeMenu());
		return "forward:/admin/stage";
	}

	@RequestMapping(value = "admin/race/{raceId}/stage/add", method = RequestMethod.POST)
	public String newStage(@ModelAttribute("stage") Stage stage,
			SessionStatus status) {
		stageService.save(stage);
		status.setComplete();
		return "forward:/admin/race";
	}

	@RequestMapping(value = "/admin/race/{raceId}/rider", method = RequestMethod.GET)
	public String addRider(Locale locale, Model model) {
		model.addAttribute("menuitems", makeMenu());
		return "admin/manageRider";
	}

	private HashMap<String, String> makeMenu() {
		HashMap<String, String> dings = new HashMap<String, String>();
		dings.put("Rennen", "/admin/race");
		dings.put("Etappen", "/admin/stage");
		dings.put("Fahrer", "/admin/rider");
		return dings;
	}
}
