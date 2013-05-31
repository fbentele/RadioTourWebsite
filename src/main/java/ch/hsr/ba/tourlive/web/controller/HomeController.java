package ch.hsr.ba.tourlive.web.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.service.PositionDataService;
import ch.hsr.ba.tourlive.web.service.RaceService;
import ch.hsr.ba.tourlive.web.service.StageService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	StageService st;
	@Autowired
	PositionDataService positionDataService;
	@Autowired
	RaceService raceService;
	@Autowired
	StageService stageService;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Default Home Controller
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Stage stage = stageService.getLatestVisible();
		try {
			return "forward:/race/" + stage.getRace().getRaceSlug() + " /stage/"
					+ stage.getStageSlug();
		} catch (NullPointerException e) {
			return "forward:/info";
		}
	}

	/**
	 * Controller for the archives Page
	 */
	@RequestMapping(value = "/archive", method = RequestMethod.GET)
	public String archive(Locale locale, Model model) {
		logger.info("This is the archive Page");
		model.addAttribute("navbararchive", "active");
		model.addAttribute("races", raceService.getAllVisible());
		model.addAttribute("stages", stageService.getAllOlderThanAYear());
		return "archive";
	}

	/**
	 * Controller for info Page
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(Locale locale, Model model) {
		model.addAttribute("races", raceService.getAllVisible());
		return "about";
	}

	/**
	 * Prepared a Login view, not in use yet
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("races", raceService.getAllVisible());
		return "admin/login";
	}
}
