package ch.hsr.ba.tourlive.view;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.service.RaceService;
import ch.hsr.ba.tourlive.service.StageService;

@Controller
public class RaceController {
	@Autowired
	ApplicationContext context;
	@Autowired
	StageService stageService;
	@Autowired
	RaceService raceService;

	@RequestMapping(value = "/race/stages", method = RequestMethod.POST)
	@ResponseBody
	public void api(@RequestBody final Stage request) {
		stageService.save(request);
	}

	@RequestMapping(value = "/race", method = RequestMethod.GET)
	public String race(Locale locale, Model model) {
		model.addAttribute("races", raceService.getAll());
		model.addAttribute("navbarrace", "active");
		return "race";
	}

	@RequestMapping(value = "/race/{raceId}", method = RequestMethod.GET)
	public String customRace(@PathVariable("raceId") Long raceId,
			Locale locale, Model model) {
		model.addAttribute("race", raceService.getRaceById(raceId));
		model.addAttribute("navbarrace", "active");
		model.addAttribute("races", raceService.getAll());
		return "customrace";
	}

	@RequestMapping(value = "/race/stage/{int}", method = RequestMethod.GET)
	public String stage(Locale locale, Model model) {
		model.addAttribute("races", raceService.getAll());
		model.addAttribute("stages", stageService.getAll());
		model.addAttribute("navbarrace", "active");
		return "race";
	}
}