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

	private static final Logger log = LoggerFactory
			.getLogger(AdminController.class);

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Model model) {
		model.addAttribute("menuitems", makeMenu());
		return "admin";
	}

	@RequestMapping(value = "admin/race/add", method = RequestMethod.POST)
	public String newRace(@ModelAttribute("race") Race race,
			SessionStatus status) {
		raceService.save(race);
		status.setComplete();
		log.info("!!!!!!!" + race.getDescription());
		return "addRace";
	}

	@RequestMapping(value = "admin/race", method = RequestMethod.GET)
	public String addRace(Locale locale, Model model) {
		model.addAttribute("menuitems", makeMenu());
		return "addRace";
	}

	@RequestMapping(value = "/admin/stage", method = RequestMethod.GET)
	public String addStage(Locale locale, Model model) {
		model.addAttribute("menuitems", makeMenu());
		return "addStage";
	}

	@RequestMapping(value = "admin/stage/add", method = RequestMethod.POST)
	public String newStage(@ModelAttribute("stage") Stage stage,
			SessionStatus status) {
		stageService.save(stage);
		status.setComplete();
		return "admin";
	}

	@RequestMapping(value = "/admin/rider", method = RequestMethod.GET)
	public String addRider(Locale locale, Model model) {
		model.addAttribute("menuitems", makeMenu());
		return "addRider";
	}

	private ArrayList<String> makeMenu() {
		ArrayList<String> items = new ArrayList<String>();
		items.add("/admin/race");
		items.add("/admin/stage");
		items.add("/admin/rider");
		return items;
	}
}
