package ch.hsr.ba.tourlive.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.hsr.ba.tourlive.model.Race;
import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.model.ValueContainer;
import ch.hsr.ba.tourlive.service.ImageDataService;
import ch.hsr.ba.tourlive.service.RaceService;
import ch.hsr.ba.tourlive.service.StageService;
import ch.hsr.ba.tourlive.service.ValueContainerService;
import ch.hsr.ba.tourlive.service.VideoDataService;
import ch.hsr.ba.tourlive.viewmodel.Breadcrumb;
import ch.hsr.ba.tourlive.viewmodel.MenuItem;

@Controller
public class RaceController {
	@Autowired
	private ApplicationContext context;
	@Autowired
	private StageService stageService;
	@Autowired
	private RaceService raceService;
	@Autowired
	private ValueContainerService valueContainerService;
	@Autowired
	private ImageDataService imageDataService;
	@Autowired
	private VideoDataService videoDataService;
	@Value("${config.dev.hostname}")
	private String hostname;

	Logger log = LoggerFactory.getLogger(RaceController.class);

	@RequestMapping(value = "/race", method = RequestMethod.GET)
	public String race(Locale locale, Model model) {
		model.addAttribute("races", raceService.getAllVisible());
		model.addAttribute("navbarrace", "active");
		model.addAttribute("breadcrumb", new Breadcrumb("/race"));
		return "race";
	}

	@RequestMapping(value = "/race/{raceSlug}", method = RequestMethod.GET)
	public String customRace(@PathVariable("raceSlug") String raceSlug, Locale locale, Model model) {
		Race actualRace = raceService.getRaceBySlug(raceSlug);
		model.addAttribute("race", actualRace);
		model.addAttribute("navbarrace", "active");
		model.addAttribute("races", raceService.getAllVisible());
		model.addAttribute("menuitems", makeMenu(stageService.getAllVisibleByRace(actualRace)));
		model.addAttribute("breadcrumb", new Breadcrumb("/race/" + raceSlug));
		return "actualrace";
	}

	@RequestMapping(value = "/race/{raceSlug}/stage", method = RequestMethod.GET)
	public String sRace(@PathVariable("raceSlug") String raceSlug) {
		return "redirect:/race/" + raceSlug;
	}

	@RequestMapping(value = "/race/{raceSlug}/stage/{stageSlug}", method = RequestMethod.GET)
	public String stage(@PathVariable("stageSlug") String stageSlug,
			@PathVariable("raceSlug") String raceSlug, Model model) {
		Stage stage = stageService.getStageBySlug(stageSlug);
		List<ValueContainer> valueContainers = valueContainerService
				.getAllValueContainerForStage(stage);
		model.addAttribute("races", raceService.getAllVisible());
		// model.addAttribute("menuitems",makeMenu(stageService.getAllVisibleByRace(raceService.getRaceBySlug(raceSlug))));
		model.addAttribute("menuitems", MenuItem.makeStageNavi());
		model.addAttribute("stage", stage);
		model.addAttribute("navbarrace", "active");
		model.addAttribute("valuecontainers", valueContainers);
		model.addAttribute("images", imageDataService.getMostRecentByStage(stage));
		model.addAttribute("videos", videoDataService.getMostRecentByStage(stage));
		log.info("_____________________" + videoDataService.getMostRecentByStage(stage).size());
		model.addAttribute("devices", stage.getDevices());
		model.addAttribute("hostname", hostname);
		model.addAttribute("latest", valueContainerService.getLatestForDeviceByStage(stage));
		model.addAttribute("distances", valueContainerService.getAllForStageByDistance(stage));
		model.addAttribute("first", valueContainerService.getFirstByStage(stage));
		model.addAttribute("breadcrumb",
				new Breadcrumb("/race/" + raceSlug + "/stage/" + stageSlug));
		try {
			model.addAttribute("current", valueContainers.get(0));
		} catch (IndexOutOfBoundsException e) {
			log.info("No ValueContainer for this Stage, so no Map available");
		}
		return "actualstage";
	}

	@RequestMapping(value = "/race/{raceSlug}/stage/{stageSlug}/{untilTime}", method = RequestMethod.GET)
	public String stageForTime(@PathVariable("stageSlug") String stageSlug,
			@PathVariable("raceSlug") String raceSlug, @PathVariable("untilTime") Long untilTime,
			Model model) {
		Stage stage = stageService.getStageBySlug(stageSlug);
		List<ValueContainer> valueContainers = valueContainerService
				.getForStageByDistanceLimitedTo(stage, untilTime);
		log.info("___________________number: " + valueContainers.size());
		model.addAttribute("races", raceService.getAllVisible());
		// model.addAttribute("menuitems",makeMenu(stageService.getAllVisibleByRace(raceService.getRaceBySlug(raceSlug))));
		model.addAttribute("menuitems", MenuItem.makeStageNavi());
		model.addAttribute("stage", stage);
		model.addAttribute("navbarrace", "active");
		model.addAttribute("valuecontainers", valueContainers);
		model.addAttribute("images", imageDataService.getMostRecentByStage(stage));
		model.addAttribute("devices", stage.getDevices());
		model.addAttribute("hostname", hostname);
		model.addAttribute("latest", valueContainerService.getLatestForDeviceByStage(stage));
		model.addAttribute("distances", valueContainerService.getAllForStageByDistance(stage));
		model.addAttribute("first", valueContainerService.getFirstByStage(stage));
		model.addAttribute("breadcrumb",
				new Breadcrumb("/race/" + raceSlug + "/stage/" + stageSlug));
		try {
			model.addAttribute("current", valueContainers.get(0));
		} catch (IndexOutOfBoundsException e) {
			log.info("No ValueContainer for this Stage, so no Map available");
		}
		return "actualstage";
	}

	private List<MenuItem> makeMenu(List<Stage> stages) {
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		for (Stage stage : stages) {
			menu.add(new MenuItem(stage.getStageName(), "/race/" + stage.getRace().getRaceSlug()
					+ "/stage/" + stage.getStageSlug()));
		}
		return menu;
	}

}