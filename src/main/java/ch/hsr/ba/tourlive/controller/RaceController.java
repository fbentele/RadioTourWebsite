package ch.hsr.ba.tourlive.controller;

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
import ch.hsr.ba.tourlive.service.LiveTickerItemService;
import ch.hsr.ba.tourlive.service.MarchTableService;
import ch.hsr.ba.tourlive.service.RaceService;
import ch.hsr.ba.tourlive.service.RaceSituationService;
import ch.hsr.ba.tourlive.service.RiderService;
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
	@Autowired
	private RaceSituationService raceSituationService;
	@Autowired
	private RiderService riderService;
	@Autowired
	private MarchTableService marchTableService;
	@Autowired
	private LiveTickerItemService liveTickerItemService;
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
		if (actualRace != null) {
			model.addAttribute("race", actualRace);
			model.addAttribute("navbarrace", "active");
			model.addAttribute("races", raceService.getAllVisible());
			model.addAttribute("menuitems", makeMenu(stageService.getAllVisibleByRace(actualRace)));
			model.addAttribute("breadcrumb", new Breadcrumb("/race/" + raceSlug));
			return "actualrace";
		} else {
			return "redirect:/race";
		}
	}

	@RequestMapping(value = "/race/{raceSlug}/stage", method = RequestMethod.GET)
	public String sRace(@PathVariable("raceSlug") String raceSlug) {
		return "redirect:/race/" + raceSlug;
	}

	@RequestMapping(value = "/race/{raceSlug}/stage/{stageSlug}", method = RequestMethod.GET)
	public String stage(@PathVariable("stageSlug") String stageSlug,
			@PathVariable("raceSlug") String raceSlug, Model model) {
		Stage stage = stageService.getStageBySlug(stageSlug);
		if (stage != null) {
			List<ValueContainer> valueContainers = valueContainerService
					.getAllValueContainerForStage(stage);
			model.addAttribute("races", raceService.getAllVisible());
			model.addAttribute("limit", System.currentTimeMillis());
			model.addAttribute("menuitems", MenuItem.makeStageNavi());
			model.addAttribute("stage", stage);
			model.addAttribute("navbarrace", "active");
			model.addAttribute("valuecontainers", valueContainers);
			model.addAttribute("riders", riderService.getAllByStage(stage));
			model.addAttribute("images", imageDataService.getMostRecentByStage(stage));
			model.addAttribute("videos", videoDataService.getMostRecentByStage(stage));
			model.addAttribute("liveTickerItems",
					liveTickerItemService.getAllByStageLimitedTo(stage, 10));
			try {
				model.addAttribute("devices", stage.getDevices());
			} catch (Exception e) {
				// no devices for this stage
			}
			model.addAttribute("deficitetimes",
					valueContainerService.getDeficiteToLeaderForStage(stage));
			model.addAttribute("hostname", hostname);
			model.addAttribute("latest", valueContainerService.getLatestForDeviceByStage(stage));
			model.addAttribute("distances", valueContainerService.getAllForStageByDistance(stage));
			model.addAttribute("marchtable", marchTableService.getAllByStage(stage));
			model.addAttribute("first", valueContainerService.getFirstByStage(stage));
			model.addAttribute("breadcrumb", new Breadcrumb("/race/" + raceSlug + "/stage/"
					+ stageSlug));
			model.addAttribute("situation", raceSituationService.getLatestByStage(stage));
		}
		return "actualstage";
	}

	@RequestMapping(value = "/race/{raceSlug}/stage/{stageSlug}/{untilTime}", method = RequestMethod.GET)
	public String stageForTime(@PathVariable("stageSlug") String stageSlug,
			@PathVariable("raceSlug") String raceSlug, @PathVariable("untilTime") Long untilTime,
			Model model) {
		Stage stage = stageService.getStageBySlug(stageSlug);
		if (stage != null) {
			List<ValueContainer> valueContainers = valueContainerService
					.getForStageByDistanceLimitedTo(stage, untilTime);
			model.addAttribute("limit", untilTime);
			model.addAttribute("races", raceService.getAllVisible());
			model.addAttribute("menuitems", MenuItem.makeStageNavi());
			model.addAttribute("raceSlug", raceSlug);
			model.addAttribute("stage", stage);
			model.addAttribute("navbarrace", "active");
			model.addAttribute("valuecontainers", valueContainers);
			model.addAttribute("riders", riderService.getAllByStage(stage));
			model.addAttribute("images", imageDataService.getMostRecentByStage(stage, untilTime));
			model.addAttribute("videos",
					videoDataService.getMostRecentByStageLimitedTo(stage, untilTime));
			model.addAttribute("liveTickerItems",
					liveTickerItemService.getAllByStageLimitedTo(stage, 10));
			try {
				model.addAttribute("devices", stage.getDevices());
			} catch (Exception e) {
				// no devices for this stage
			}
			model.addAttribute("deficitetimes",
					valueContainerService.getDeficiteToLeaderForStage(stage, untilTime));
			model.addAttribute("hostname", hostname);
			model.addAttribute("latest",
					valueContainerService.getLatestForDeviceByStage(stage, untilTime));
			model.addAttribute("distances",
					valueContainerService.getAllForStageByDistance(stage, untilTime));
			model.addAttribute("marchtable", marchTableService.getAllByStage(stage));
			model.addAttribute("first", valueContainerService.getFirstByStage(stage, untilTime));
			model.addAttribute("breadcrumb", new Breadcrumb("/race/" + raceSlug + "/stage/"
					+ stageSlug));
			model.addAttribute("situation", raceSituationService.getLatestByStage(stage, untilTime));
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