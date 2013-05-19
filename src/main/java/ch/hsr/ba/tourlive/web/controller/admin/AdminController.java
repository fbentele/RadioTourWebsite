package ch.hsr.ba.tourlive.web.controller.admin;

import java.security.Principal;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hsr.ba.tourlive.web.model.Race;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.service.RaceService;
import ch.hsr.ba.tourlive.web.service.StageService;
import ch.hsr.ba.tourlive.web.viewmodel.Breadcrumb;

@Controller
public class AdminController {
	@Autowired
	private StageService stageService;
	@Autowired
	private RaceService raceService;
	private final static Logger LOG = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model, Principal principal, Locale locale) {
		model.addAttribute("races", raceService.getAll());
		model.addAttribute("menutitle", "Admin");
		model.addAttribute("adminmenu", "true");
		model.addAttribute("breadcrumb", new Breadcrumb("/admin"));
		if (principal != null)
			model.addAttribute("user", principal.getName());
		return "admin/admin";
	}

	@RequestMapping(value = "/admin/race", method = RequestMethod.GET)
	public String manageRace(Locale locale, Model model) {
		model.addAttribute("adminmenu", "true");
		model.addAttribute("races", raceService.getAll());
		model.addAttribute("breadcrumb", new Breadcrumb("/admin/race"));
		model.addAttribute("race", new Race());
		return "admin/manageRace";
	}

	@RequestMapping(value = "/admin/race/add", method = RequestMethod.POST)
	public String newRace(@Valid @ModelAttribute("race") Race race, BindingResult binding,
			@RequestParam(value = "visible", defaultValue = "") String visible, Locale locale,
			Model model) {
		if (binding.hasErrors()) {
			model.addAttribute("race", race);
			model.addAttribute("adminmenu", "true");
			model.addAttribute("races", raceService.getAll());
			model.addAttribute("breadcrumb", new Breadcrumb("/admin/race"));
			model.addAttribute("showhidden", true);
			return "admin/manageRace";
		} else {
			if (visible.contains("true"))
				race.setVisible(true);
			raceService.save(race);
			return "redirect:/admin/race";
		}
	}

	@RequestMapping(value = "/admin/race/edit/{raceId}", method = RequestMethod.GET)
	public String editRace(@PathVariable("raceId") Long raceId,
			@RequestParam(value = "visible", defaultValue = "") String visible, Locale locale,
			Model model) {
		model.addAttribute("adminmenu", "true");
		Race race = raceService.getRaceById(raceId);
		model.addAttribute("race", race);
		model.addAttribute("stage", new Stage());
		model.addAttribute("stages", stageService.getAllByRace(race));
		model.addAttribute("races", raceService.getAllVisible());
		model.addAttribute("breadcrumb", new Breadcrumb("/admin/race/" + raceId));
		return "admin/editRace";
	}

	@RequestMapping(value = "/admin/race/edit/{raceId}", method = RequestMethod.POST)
	public String editedRace(@PathVariable("raceId") Long raceId,
			@ModelAttribute("race") Race race,
			@RequestParam(value = "visible", defaultValue = "") String visible, Model model,
			Locale locale) {
		if (visible.contains("true"))
			race.setVisible(true);
		raceService.update(race);
		model.addAttribute("adminmenu", "true");
		model.addAttribute("race", raceService.getRaceById(raceId));
		return "redirect:/admin/race";
	}

	@RequestMapping(value = "/admin/race/delete/{raceId}", method = RequestMethod.GET)
	public String removeRace(@PathVariable("raceId") Long raceId, Locale locale, Model model) {
		try {
			for (Stage stage : stageService.getAllByRace(raceService.getRaceById(raceId))) {
				stageService.delete(stage.getStageId());
			}
		} catch (Exception e) {
			LOG.info("Race or Stage does not exist");
		}
		raceService.delete(raceId);
		model.addAttribute("adminmenu", "true");
		model.addAttribute("races", raceService.getAllVisible());
		model.addAttribute("breadcrumb", new Breadcrumb("/admin/race"));
		return "forward:/admin/race";
	}
}
