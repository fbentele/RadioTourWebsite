package ch.hsr.ba.tourlive.view;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.hsr.ba.tourlive.service.PositionDataService;
import ch.hsr.ba.tourlive.service.RaceService;
import ch.hsr.ba.tourlive.service.StageService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	ApplicationContext context;

	@Autowired
	StageService st;
	@Autowired
	PositionDataService positionDataService;
	@Autowired
	RaceService raceService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/*
	 * Default Controller for all Pages
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG,
				locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("navbarhome", "active");
		model.addAttribute("races", raceService.getAllVisible());
		return "home";
	}

	@RequestMapping(value = "/archive", method = RequestMethod.GET)
	public String archive(Locale locale, Model model) {
		logger.info("This is the archive Page");
		model.addAttribute("navbararchive", "active");
		model.addAttribute("races", raceService.getAllVisible());

		return "archive";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Locale locale, Model model) {
		logger.info("This is the about Page");
		model.addAttribute("navbarabout", "active");
		model.addAttribute("races", raceService.getAllVisible());

		return "about";
	}
}
