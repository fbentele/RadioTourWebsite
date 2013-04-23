package ch.hsr.ba.tourlive.view;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ch.hsr.ba.tourlive.model.Race;
import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.service.DeviceService;
import ch.hsr.ba.tourlive.service.RaceService;
import ch.hsr.ba.tourlive.service.StageService;
import ch.hsr.ba.tourlive.utils.SafeImageUtil;
import ch.hsr.ba.tourlive.viewmodel.MenuItem;

@Controller
public class AdminController {
	@Autowired
	private ApplicationContext context;
	@Autowired
	private StageService stageService;
	@Autowired
	private RaceService raceService;
	@Autowired
	private DeviceService deviceService;
	@Value("${config.api.imagePath}")
	private String imagePath;
	@Value("${config.dev.hostname}")
	private String hostname;
	private Logger log = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Model model) {
		model.addAttribute("menuitems", makeMenu());
		model.addAttribute("races", raceService.getAll());
		return "admin/admin";
	}

	@RequestMapping(value = "/admin/race", method = RequestMethod.GET)
	public String manageRace(Locale locale, Model model) {
		model.addAttribute("menuitems", makeMenu());
		model.addAttribute("races", raceService.getAll());
		return "admin/manageRace";
	}

	@RequestMapping(value = "/admin/race/add", method = RequestMethod.POST)
	public String newRace(@ModelAttribute("race") Race race,
			SessionStatus status, Model model) {
		raceService.save(race);
		status.setComplete();
		return "redirect:/admin/race";
	}

	@RequestMapping(value = "/admin/race/edit/{raceId}", method = RequestMethod.GET)
	public String editRace(@PathVariable("raceId") Long raceId, Model model) {
		model.addAttribute("menuitems", makeMenu());
		Race race = raceService.getRaceById(raceId);
		model.addAttribute("race", race);
		model.addAttribute("stages", stageService.getAllByRace(race));
		model.addAttribute("races", raceService.getAll());
		return "admin/editRace";
	}

	@RequestMapping(value = "/admin/race/edit/{raceId}", method = RequestMethod.POST)
	public String editedRace(@PathVariable("raceId") Long raceId,
			@ModelAttribute("race") Race race, Model model) {
		model.addAttribute("menuitems", makeMenu());
		raceService.update(race);
		model.addAttribute("race", raceService.getRaceById(raceId));
		return "redirect:/admin/race";
	}

	@RequestMapping(value = "/admin/race/delete/{raceId}", method = RequestMethod.GET)
	public String removeRace(@PathVariable("raceId") Long raceId, Model model) {
		raceService.delete(raceId);
		model.addAttribute("menuitems", makeMenu());
		model.addAttribute("races", raceService.getAll());

		return "forward:/admin/race";
	}

	@RequestMapping(value = "/admin/stage", method = RequestMethod.GET)
	public String manageStage(Locale locale, Model model) {
		model.addAttribute("menuitems", makeMenu());
		model.addAttribute("races", raceService.getAll());

		return "forward:/admin/stage";
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/add", method = RequestMethod.POST)
	public String addStage(
			@PathVariable("raceId") Long raceId,
			@RequestParam("stageName") String stageName,
			@RequestParam("stageDescription") String stageDescription,
			@RequestParam("stageSlug") String stageSlug,
			@RequestParam("starttime") String starttime,
			@RequestParam("endtime") String endtime,
			@RequestParam(value = "bannerImageFile", defaultValue = "") CommonsMultipartFile bannerimage,
			@RequestParam(value = "stageProfileFile", defaultValue = "") CommonsMultipartFile stageProfileImage) {
		Stage stage = new Stage();
		stage.setStageName(stageName);
		stage.setStageDescription(stageDescription);
		stage.setStageSlug(stageSlug);
		stage.setRace(raceService.getRaceById(raceId));
		stage.setStarttime(starttime);
		stage.setEndtime(endtime);
		stageService.save(stage);
		// creates id, needed to save image properly
		stage = stageService.getStageBySlug(stageSlug);

		String rel = "stage" + stage.getStageId();

		stage.setBannerImage(SafeImageUtil.safe(bannerimage, imagePath, rel,
				"banner.png"));
		stage.setStageProfileImage(SafeImageUtil.safe(stageProfileImage,
				imagePath, rel, "banner.png"));
		stageService.update(stage);
		return "redirect:/admin/race/edit/" + raceId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/edit/{stageId}", method = RequestMethod.GET)
	public String editStage(@PathVariable("stageId") Long stageId,
			@PathVariable("raceId") Long raceId, Model model) {
		model.addAttribute("stage", stageService.getStageById(stageId));
		model.addAttribute("race", raceService.getRaceById(raceId));
		model.addAttribute("menuitems", makeMenu());
		model.addAttribute("races", raceService.getAll());
		model.addAttribute("devices", deviceService.getAll());
		return "admin/editStage";
	}

	/*
	 * This method handles each request parameter separately because of the
	 * specially handled time formates and muliple images not saved in the stage
	 * model but to disk and a only as path string to the model
	 */
	@RequestMapping(value = "/admin/race/{raceId}/stage/edit/{stageId}", method = RequestMethod.POST)
	public String updateStage(
			@PathVariable("raceId") Long raceId,
			@RequestParam("stageId") Long stageId,
			@RequestParam("stageName") String stageName,
			@RequestParam("stageDescription") String stageDescription,
			@RequestParam("stageSlug") String stageSlug,
			@RequestParam("starttime") String starttime,
			@RequestParam("distance") float stageDistance,
			@RequestParam("endtime") String endtime,
			@RequestParam(value = "bannerImageFile", defaultValue = "") CommonsMultipartFile bannerimage,
			@RequestParam(value = "stageProfileFile", defaultValue = "") CommonsMultipartFile stageProfileImage) {
		Stage stage = stageService.getStageById(stageId);
		stage.setStageName(stageName);
		stage.setStageDescription(stageDescription);
		stage.setStageSlug(stageSlug);
		stage.setStarttime(starttime);
		stage.setDistance(stageDistance);
		stage.setEndtime(endtime);

		String rel = "stage" + stage.getStageId();

		stage.setBannerImage(SafeImageUtil.safe(bannerimage, imagePath, rel,
				"banner.png"));
		stage.setStageProfileImage(SafeImageUtil.safe(stageProfileImage,
				imagePath, rel, "stageProfile.png"));
		stageService.update(stage);

		return "redirect:/admin/race/edit/" + raceId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/delete/{stageId}", method = RequestMethod.GET)
	public String deleteStage(@PathVariable("stageId") Long stageId,
			@PathVariable("raceId") Long raceId) {
		stageService.delete(stageId);
		return "redirect:/admin/race/edit/" + raceId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/device/add", method = RequestMethod.POST)
	public String editDevice(@PathVariable("stageId") Long stageId,
			@PathVariable("raceId") Long raceId, HttpServletRequest request) {
		Stage stage = stageService.getStageById(stageId);
		String[] a = request.getParameterValues("device");
		for (String deviceId : a) {
			stage.addDevice(deviceService.getDeviceById(deviceId));
		}
		stageService.update(stage);
		return "redirect:/admin/race/" + raceId + "/stage/edit/" + stageId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/rider", method = RequestMethod.GET)
	public String addRider(@PathVariable("raceId") Long raceId, Locale locale,
			Model model) {
		model.addAttribute("menuitems", makeMenu());
		model.addAttribute("races", raceService.getAll());
		return "admin/manageRider";
	}

	private ArrayList<MenuItem> makeMenu() {
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		menu.add(new MenuItem("Rennen", "/admin/race"));
		menu.add(new MenuItem("Geräte", "/admin/device"));
		return menu;
	}
}
