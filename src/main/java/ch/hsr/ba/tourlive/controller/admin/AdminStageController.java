package ch.hsr.ba.tourlive.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ch.hsr.ba.tourlive.model.LiveTickerItem;
import ch.hsr.ba.tourlive.model.MarchTableItem;
import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.model.rider.Rider;
import ch.hsr.ba.tourlive.service.DeviceService;
import ch.hsr.ba.tourlive.service.LiveTickerItemService;
import ch.hsr.ba.tourlive.service.MarchTableService;
import ch.hsr.ba.tourlive.service.RaceService;
import ch.hsr.ba.tourlive.service.RiderService;
import ch.hsr.ba.tourlive.service.StageService;
import ch.hsr.ba.tourlive.utils.DateUtil;
import ch.hsr.ba.tourlive.utils.FileUtil;
import ch.hsr.ba.tourlive.utils.importer.CSVReader;
import ch.hsr.ba.tourlive.utils.importer.MarchTableImporter;
import ch.hsr.ba.tourlive.utils.importer.RiderImporter;
import ch.hsr.ba.tourlive.viewmodel.Breadcrumb;

@Controller
public class AdminStageController {
	@Autowired
	private StageService stageService;
	@Autowired
	private RaceService raceService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private LiveTickerItemService ltiService;
	@Autowired
	private RiderService riderService;
	@Autowired
	private MarchTableService mtiService;
	@Value("${config.api.imagePath}")
	private String filePath;
	@Value("${config.dev.hostname}")
	private String hostname;
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(AdminStageController.class);

	@RequestMapping(value = "/admin/race/{raceId}/stage/add", method = RequestMethod.POST)
	public String addStage(
			@PathVariable("raceId") Long raceId,
			@RequestParam("stageName") String stageName,
			@RequestParam("stageDescription") String stageDescription,
			@RequestParam("stageSlug") String stageSlug,
			@RequestParam("starttime") String starttime,
			@RequestParam("endtime") String endtime,
			@RequestParam("distance") Float distance,
			@RequestParam("adCode") String adCode,
			@RequestParam(value = "visible", defaultValue = "") String visible,
			@RequestParam(value = "bannerImageFile", defaultValue = "") CommonsMultipartFile bannerimage,
			@RequestParam(value = "stageProfileFile", defaultValue = "") CommonsMultipartFile stageProfileImage,
			Model model) {
		Stage stage = new Stage();
		stage.setStageName(stageName);
		stage.setStageDescription(stageDescription);
		stage.setStageSlug(stageSlug);
		stage.setRace(raceService.getRaceById(raceId));
		stage.setStarttime(starttime);
		stage.setEndtime(endtime);
		stage.setDistance(distance);
		stage.setAdCode(adCode);
		if (visible.contains("true"))
			stage.setVisible(true);
		stageService.save(stage);
		// creates id, needed to save image properly
		stage = stageService.getStageBySlug(stageSlug);
		String rel = "stage" + stage.getStageId();
		stage.setBannerImage(FileUtil.safePng(bannerimage, filePath, rel, "banner.png"));
		stage.setStageProfileImage(FileUtil.safePng(stageProfileImage, filePath, rel,
				"stageProfile.png"));
		stageService.update(stage);
		return "redirect:/admin/race/edit/" + raceId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/edit/{stageId}", method = RequestMethod.GET)
	public String editStage(@PathVariable("stageId") Long stageId,
			@PathVariable("raceId") Long raceId, Model model) {
		Stage stage = stageService.getStageById(stageId);
		model.addAttribute("stage", stage);
		model.addAttribute("hostname", hostname);
		model.addAttribute("race", raceService.getRaceById(raceId));
		model.addAttribute("adminmenu", "true");
		model.addAttribute("races", raceService.getAllVisible());
		model.addAttribute("devices", deviceService.getAll());
		model.addAttribute("riders", riderService.getAllByStage(stage));
		model.addAttribute("marchTable", mtiService.getAllByStage(stage));
		model.addAttribute("breadcrumb", new Breadcrumb("/admin/race/" + raceId + "/stage/"
				+ stageId));
		return "admin/editStage";
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}", method = RequestMethod.GET)
	public String showStage(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId) {
		return "redirect:/admin/race/" + raceId + "/stage/edit/" + stageId;
	}

	/**
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
			@RequestParam("adCode") String adCode,
			@RequestParam(value = "visible", defaultValue = "") String visible,
			@RequestParam(value = "bannerImageFile", defaultValue = "") CommonsMultipartFile bannerimage,
			@RequestParam(value = "stageProfileFile", defaultValue = "") CommonsMultipartFile stageProfileImage) {
		Stage stage = stageService.getStageById(stageId);
		stage.setStageName(stageName);
		stage.setStageDescription(stageDescription);
		stage.setStageSlug(stageSlug);
		stage.setStarttime(starttime);
		stage.setDistance(stageDistance);
		stage.setEndtime(endtime);
		stage.setAdCode(adCode);
		stage.setVisible(false);
		if (visible.contains("true"))
			stage.setVisible(true);
		String rel = "stage" + stage.getStageId();
		stage.setBannerImage(FileUtil.safePng(bannerimage, filePath, rel, "banner.png"));
		stage.setStageProfileImage(FileUtil.safePng(stageProfileImage, filePath, rel,
				"stageProfile.png"));
		stageService.update(stage);
		return "redirect:/admin/race/edit/" + raceId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/delete/{stageId}", method = RequestMethod.GET)
	public String deleteStage(@PathVariable("stageId") Long stageId,
			@PathVariable("raceId") Long raceId) {
		stageService.delete(stageId);
		return "redirect:/admin/race/edit/" + raceId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/profileimage/delete", method = RequestMethod.GET)
	public String deleteProfileImage(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId) {
		Stage stage = stageService.getStageById(stageId);
		if (stage != null) {
			FileUtil.deleteFile(filePath + stage.getStageProfileImage());
			stage.setStageProfileImage(null);
			stageService.update(stage);
		}
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/bannerimage/delete", method = RequestMethod.GET)
	public String deleteBannerImage(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId) {
		Stage stage = stageService.getStageById(stageId);
		if (stage != null) {
			FileUtil.deleteFile(filePath + stage.getBannerImage());
			stage.setBannerImage(null);
			stageService.update(stage);
		}
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/device/add", method = RequestMethod.POST)
	public String editDevice(@PathVariable("stageId") Long stageId,
			@PathVariable("raceId") Long raceId, HttpServletRequest request) {
		Stage stage = stageService.getStageById(stageId);
		String[] deviceIds = request.getParameterValues("device");
		for (String deviceId : deviceIds) {
			stage.addDevice(deviceService.getDeviceById(deviceId));
		}
		stageService.update(stage);
		return "redirect:/admin/race/" + raceId + "/stage/edit/" + stageId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/device/{deviceId}/remove", method = RequestMethod.GET)
	public String removeDevice(@PathVariable("stageId") Long stageId,
			@PathVariable("raceId") Long raceId, @PathVariable("deviceId") String deviceId,
			HttpServletRequest request) {
		Stage stage = stageService.getStageById(stageId);
		stage.removeDevice(deviceService.getDeviceById(deviceId));
		stageService.update(stage);

		return "redirect:/admin/race/" + raceId + "/stage/edit/" + stageId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/liveticker", method = RequestMethod.GET)
	public String showLiveTicker(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId, Model model) {
		Stage stage = stageService.getStageById(stageId);
		model.addAttribute("race", raceService.getRaceById(raceId));
		model.addAttribute("stage", stage);
		model.addAttribute("liveTickerItems", ltiService.getAllByStage(stage));
		model.addAttribute("now", DateUtil.timeNow());
		model.addAttribute("breadcrumb", new Breadcrumb("/admin/race/" + raceId + "/stage/"
				+ stageId));
		model.addAttribute("adminmenu", "true");
		model.addAttribute("races", raceService.getAll());
		return "admin/liveticker";
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/liveticker/add", method = RequestMethod.POST)
	public String addLiveTickerItem(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId, @RequestParam("timestamp") String timestamp,
			@RequestParam("news") String news) {
		LiveTickerItem lti = new LiveTickerItem();
		lti.setTimestamp(timestamp);
		lti.setNews(news);
		lti.setStage(stageService.getStageById(stageId));
		ltiService.save(lti);
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId + "/liveticker";
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/liveticker/delete/{ltiId}", method = RequestMethod.GET)
	public String removeLTI(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId, @PathVariable("ltiId") Long ltiId) {
		ltiService.delete(ltiId);
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId + "/liveticker";
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/rider/import", method = RequestMethod.POST)
	public String importRider(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId,
			@RequestParam(value = "riderCsv", defaultValue = "") CommonsMultipartFile riderCsv) {
		File csvFile = FileUtil.safeCsv(riderCsv, filePath, "stage" + stageId);
		Stage stage = stageService.getStageById(stageId);
		CSVReader reader;
		RiderImporter importer = new RiderImporter();

		try {
			reader = new CSVReader(new FileInputStream(csvFile));
			for (String[] riderAsString : reader.readFile()) {
				Rider r = importer.convertTo(riderAsString);
				r.setStage(stage);
				riderService.save(r);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/rider/delete/{riderId}", method = RequestMethod.GET)
	public String deleteRider(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId, @PathVariable("riderId") Long riderId) {
		riderService.delete(riderId);
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/marchtable/import", method = RequestMethod.POST)
	public String importMarchTable(
			@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId,
			@RequestParam(value = "marchTableCsv", defaultValue = "") CommonsMultipartFile marchTableCsv) {
		File csvFile = FileUtil.safeCsv(marchTableCsv, filePath, "stage" + stageId);
		Stage stage = stageService.getStageById(stageId);
		CSVReader reader;
		MarchTableImporter importer = new MarchTableImporter();
		try {
			reader = new CSVReader(new FileInputStream(csvFile));
			for (String[] mtiAsString : reader.readFile()) {
				MarchTableItem mti = importer.convertTo(mtiAsString);
				mti.setStage(stage);
				mtiService.save(mti);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId;
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/marchtable/delete/{mtiId}", method = RequestMethod.GET)
	public String deleteMarchTableItem(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId, @PathVariable("mtiId") Long mtiId) {
		mtiService.delete(mtiId);
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId;
	}

}
