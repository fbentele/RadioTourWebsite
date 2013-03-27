package ch.hsr.ba.tourlive.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.hsr.ba.tourlive.service.RaceService;
import ch.hsr.ba.tourlive.service.StageService;

@Controller
public class TdsController {
	@Autowired
	StageService stageService;
	@Autowired
	RaceService raceService;

	@RequestMapping("/tds")
	public String tds(Model model) {
		model.addAttribute("tds", raceService.getActualTds());
		model.addAttribute("menuitems",
				stageService.getAllByRace(raceService.getActualTds()));
		return "tds";
	}
}
