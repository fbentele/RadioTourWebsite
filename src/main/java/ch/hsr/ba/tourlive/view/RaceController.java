package ch.hsr.ba.tourlive.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.hsr.ba.tourlive.model.Race;
import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.service.RaceService;
import ch.hsr.ba.tourlive.service.StageService;
import ch.hsr.ba.tourlive.viewmodel.MenuItem;

@Controller
public class RaceController {
	@Autowired
	ApplicationContext context;
	@Autowired
	StageService stageService;
	@Autowired
	RaceService raceService;

	@RequestMapping(value = "/race", method = RequestMethod.GET)
	public String race(Locale locale, Model model) {
		model.addAttribute("races", raceService.getAll());
		model.addAttribute("navbarrace", "active");
		return "race";
	}

	@RequestMapping(value = "/race/{raceId}", method = RequestMethod.GET)
	public String customRace(@PathVariable("raceId") Long raceId,
			Locale locale, Model model) {
		Race actualRace = raceService.getRaceById(raceId);
		model.addAttribute("race", actualRace);
		model.addAttribute("navbarrace", "active");
		model.addAttribute("races", raceService.getAll());
		model.addAttribute("menuitems", makeMenu(actualRace));
		return "actualrace";
	}

	@RequestMapping(value = "/race/stage/{int}", method = RequestMethod.GET)
	public String stage(Locale locale, Model model) {
		model.addAttribute("races", raceService.getAll());
		model.addAttribute("stages", stageService.getAll());
		model.addAttribute("navbarrace", "active");
		return "race";
	}

	private List<MenuItem> makeMenu(Race race) {
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		for (Stage stage : stageService.getAllByRace(race)) {
			menu.add(new MenuItem(stage.getStageName(), "#"));
		}
		return menu;
	}
}