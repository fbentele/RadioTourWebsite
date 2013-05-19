package ch.hsr.ba.tourlive.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hsr.ba.tourlive.web.model.Race;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.ValueContainer;
import ch.hsr.ba.tourlive.web.model.VideoData;
import ch.hsr.ba.tourlive.web.service.DeviceService;
import ch.hsr.ba.tourlive.web.service.ImageDataService;
import ch.hsr.ba.tourlive.web.service.LiveTickerItemService;
import ch.hsr.ba.tourlive.web.service.MarchTableService;
import ch.hsr.ba.tourlive.web.service.RaceService;
import ch.hsr.ba.tourlive.web.service.RaceSituationService;
import ch.hsr.ba.tourlive.web.service.RiderService;
import ch.hsr.ba.tourlive.web.service.StageService;
import ch.hsr.ba.tourlive.web.service.ValueContainerService;
import ch.hsr.ba.tourlive.web.service.VideoDataService;
import ch.hsr.ba.tourlive.web.viewmodel.Breadcrumb;
import ch.hsr.ba.tourlive.web.viewmodel.MenuItem;

@Controller
public class RaceController {
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
	private DeviceService deviceService;
	@Autowired
	private RiderService riderService;
	@Autowired
	private MarchTableService marchTableService;
	@Autowired
	private LiveTickerItemService liveTickerItemService;
	@Value("${config.dev.hostname}")
	private String hostname;

	private final static Logger LOG = LoggerFactory.getLogger(RaceController.class);

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
			model.addAttribute("menutitle", "etappen");
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
			model.addAttribute("limit", stage.getCompleted() ? stage.getEndtimeAsTimestamp()
					: System.currentTimeMillis());
			model.addAttribute("races", raceService.getAllVisible());
			// model.addAttribute("menuitems", MenuItem.makeStageNavi());
			model.addAttribute("stagemenu", true);
			model.addAttribute("stage", stage);
			model.addAttribute("navbarrace", "active");
			model.addAttribute("menutitle", stage.getStageName());
			model.addAttribute("valuecontainers", valueContainers);
			model.addAttribute("riders", riderService.getAllByStage(stage));
			model.addAttribute("images", imageDataService.getMostRecentByStage(stage));
			model.addAttribute("videos", videoDataService.getMostRecentByStage(stage));
			model.addAttribute("liveTickerItems",
					liveTickerItemService.getAllByStageLimitedTo(stage, 10));
			try {
				model.addAttribute("devices", stage.getDevices());
				if (stage.getDevices().size() > 1)
					model.addAttribute("deficitetimes",
							valueContainerService.getDeficiteToLeaderForStage(stage));
			} catch (Exception e) {
				LOG.info("no devices for this stage");
			}
			model.addAttribute("hostname", hostname);
			model.addAttribute("latest", valueContainerService.getLatestForDeviceByStage(stage));
			model.addAttribute("distances", valueContainerService.getAllForStageByDistance(stage));
			model.addAttribute("marchtable", marchTableService.getAllByStage(stage));
			try {
				model.addAttribute("first", valueContainers.get(0));
			} catch (NullPointerException e) {
				LOG.info("no ValueContainers for the stage " + stage.getStageName());
			} catch (IndexOutOfBoundsException e) {
				LOG.info("no ValueContainers for the stage " + stage.getStageName());
			}
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
			List<ValueContainer> valueContainers = valueContainerService.getAllForStageByDistance(
					stage, untilTime);
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
					liveTickerItemService.getAllByStageLimitedTo(stage, 10, untilTime));
			try {
				model.addAttribute("devices", stage.getDevices());
				if (stage.getDevices().size() > 1)
					model.addAttribute("deficitetimes",
							valueContainerService.getDeficiteToLeaderForStage(stage, untilTime));
			} catch (Exception e) {
				LOG.info("no devices for this stage");
			}
			model.addAttribute("hostname", hostname);
			model.addAttribute("latest",
					valueContainerService.getLatestForDeviceByStage(stage, untilTime));
			model.addAttribute("distances",
					valueContainerService.getAllForStageByDistance(stage, untilTime));
			model.addAttribute("marchtable", marchTableService.getAllByStage(stage));
			try {
				model.addAttribute("first", valueContainers.get(0));
			} catch (NullPointerException e) {
				LOG.info("no ValueContainers for the stage " + stage.getStageName());
			} catch (IndexOutOfBoundsException e) {
				LOG.info("no ValueContainers for the stage " + stage.getStageName());
			}
			model.addAttribute("breadcrumb", new Breadcrumb("/race/" + raceSlug + "/stage/"
					+ stageSlug));
			model.addAttribute("situation", raceSituationService.getLatestByStage(stage, untilTime));
		}
		return "actualstage";
	}

	@ResponseBody
	@RequestMapping(value = "/race/{raceSlug}/stage/{stageSlug}/nextvideo", method = RequestMethod.POST)
	public VideoData getNextVideoForDevice(@PathVariable("stageSlug") String stageSlug,
			@PathVariable("raceSlug") String raceSlug, @RequestParam("deviceId") String deviceId,
			@RequestParam("afterId") Long afterId, Model model) {
		LOG.info("video Requested");
		return videoDataService.getNextForDevice(deviceService.getDeviceById(deviceId), afterId);
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