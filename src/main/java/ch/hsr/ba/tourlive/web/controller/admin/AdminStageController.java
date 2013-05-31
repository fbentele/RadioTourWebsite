/**
 * AdminStageController.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ch.hsr.ba.tourlive.web.model.LiveTickerItem;
import ch.hsr.ba.tourlive.web.model.MarchTableItem;
import ch.hsr.ba.tourlive.web.model.Race;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.enums.StageType;
import ch.hsr.ba.tourlive.web.model.rider.Rider;
import ch.hsr.ba.tourlive.web.service.DeviceService;
import ch.hsr.ba.tourlive.web.service.LiveTickerItemService;
import ch.hsr.ba.tourlive.web.service.MarchTableService;
import ch.hsr.ba.tourlive.web.service.RaceService;
import ch.hsr.ba.tourlive.web.service.RiderService;
import ch.hsr.ba.tourlive.web.service.StageService;
import ch.hsr.ba.tourlive.web.utils.DateUtil;
import ch.hsr.ba.tourlive.web.utils.FileUtil;
import ch.hsr.ba.tourlive.web.utils.importer.CSVReader;
import ch.hsr.ba.tourlive.web.utils.importer.MarchTableImporter;
import ch.hsr.ba.tourlive.web.utils.importer.RiderImporter;
import ch.hsr.ba.tourlive.web.viewmodel.Breadcrumb;
import ch.hsr.ba.tourlive.web.viewmodel.Page;

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
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;
	private final String FILENAME_STAGEBANNER = "stageBanner.png";
	private final String FILENAME_STAGEPROFILE = "stageProfile.png";
	private final static Logger LOG = LoggerFactory.getLogger(AdminStageController.class);

	/**
	 * Add a new Stage
	 */
	@RequestMapping(value = "/admin/race/{raceId}/stage/add", method = RequestMethod.POST)
	public String addStage(
			@Valid @ModelAttribute("stage") Stage unUsedStage,
			BindingResult binding,
			@PathVariable("raceId") Long raceId,
			@RequestParam(value = "visible", defaultValue = "") String visible,
			@RequestParam(value = "bannerImageFile", defaultValue = "") CommonsMultipartFile bannerimage,
			@RequestParam(value = "stageProfileFile", defaultValue = "") CommonsMultipartFile stageProfileImage,
			Model model, Locale locale) {
		// Form Validation
		if (binding.hasErrors()) {
			model.addAttribute("races", raceService.getAll());
			model.addAttribute(
					"breadcrumb",
					new Breadcrumb("/admin/race", messageSource.getMessage("label.race", null,
							locale)));
			Race race = raceService.getRaceById(raceId);
			model.addAttribute("race", race);
			model.addAttribute("stage", unUsedStage);
			model.addAttribute("showhidden", true);
			model.addAttribute("adminmenu", "true");
			return "admin/editRace";
		} else {
			unUsedStage.setRace(raceService.getRaceById(raceId));
			if (visible.contains("true"))
				unUsedStage.setVisible(true);
			stageService.save(unUsedStage);
			Stage stage = stageService.getStageBySlug(unUsedStage.getStageSlug());
			String rel = "stage" + stage.getStageId();
			stage.setBannerImage(FileUtil.safePng(bannerimage, filePath, rel, FILENAME_STAGEBANNER));
			stage.setStageProfileImage(FileUtil.safePng(stageProfileImage, filePath, rel,
					FILENAME_STAGEPROFILE));
			stageService.update(stage);
			return "redirect:/admin/race/edit/" + raceId;
		}
	}

	/**
	 * Show the Stage edit Site.
	 */
	@RequestMapping(value = "/admin/race/{raceId}/stage/edit/{stageId}", method = RequestMethod.GET)
	public String editStage(@PathVariable("stageId") Long stageId,
			@PathVariable("raceId") Long raceId, Model model, Locale locale) {
		Race race = raceService.getRaceById(raceId);
		Stage stage = stageService.getStageById(stageId);
		model.addAttribute("stage", stage);
		model.addAttribute("hostname", hostname);
		model.addAttribute("race", race);
		model.addAttribute("adminmenu", "true");
		model.addAttribute("races", raceService.getAllVisible());
		model.addAttribute("devices", deviceService.getAllNotAlreadyAssignedTo(stage));
		model.addAttribute("riders", riderService.getAllByStage(stage));
		model.addAttribute("marchTable", mtiService.getAllByStage(stage));
		model.addAttribute("stagetype", stage.getStagetype());

		List<Page> p = new ArrayList<Page>();
		p.add(new Page("Admin", "/admin"));
		p.add(new Page(messageSource.getMessage("label.race", null, locale), "/admin/race"));
		p.add(new Page(race.getRaceName(), "/admin/race/edit/" + raceId.toString()));
		Breadcrumb b = new Breadcrumb(p, new Page(stage.getStageName(), stageId.toString()));
		model.addAttribute("breadcrumb", b);
		return "admin/editStage";
	}

	/**
	 * Redirect to Edit Stage
	 */
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
			@RequestParam("stageType") String stageType,
			@RequestParam("offsettime") String offsettime,
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
		stage.setOffsettime(offsettime);
		stage.setStagetype(StageType.valueOf(stageType));
		if (visible.contains("true"))
			stage.setVisible(true);
		String rel = "stage" + stage.getStageId();
		if (!bannerimage.isEmpty())
			stage.setBannerImage(FileUtil.safePng(bannerimage, filePath, rel, "banner.png"));
		if (!stageProfileImage.isEmpty())
			stage.setStageProfileImage(FileUtil.safePng(stageProfileImage, filePath, rel,
					"stageProfile.png"));
		stageService.update(stage);
		return "redirect:/admin/race/edit/" + raceId;
	}

	/**
	 * Deletes the stage with the ID stage.
	 */
	@RequestMapping(value = "/admin/race/{raceId}/stage/delete/{stageId}", method = RequestMethod.GET)
	public String deleteStage(@PathVariable("stageId") Long stageId,
			@PathVariable("raceId") Long raceId) {
		stageService.delete(stageId);
		return "redirect:/admin/race/edit/" + raceId;
	}

	/**
	 * Deletes the profile image for the stage.
	 */
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

	/**
	 * Deletes the banner image.
	 */
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

	/**
	 * Assign device to all stages in a race.
	 */
	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/device/add", method = RequestMethod.POST)
	public String editDevice(@PathVariable("stageId") Long stageId,
			@PathVariable("raceId") Long raceId, HttpServletRequest request) {
		Stage stage = stageService.getStageById(stageId);
		try {
			for (String deviceId : request.getParameterValues("device")) {
				for (Stage s : stageService.getAllByRace(stage.getRace())) {
					s.addDevice(deviceService.getDeviceById(deviceId));
					stageService.update(s);
				}
			}
		} catch (NullPointerException e) {
			LOG.info("Device is either invalid or has no deviceId");
		}
		return "redirect:/admin/race/" + raceId + "/stage/edit/" + stageId;
	}

	/**
	 * Removes device from a stage.
	 */
	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/device/{deviceId}/remove", method = RequestMethod.GET)
	public String removeDevice(@PathVariable("stageId") Long stageId,
			@PathVariable("raceId") Long raceId, @PathVariable("deviceId") String deviceId,
			HttpServletRequest request) {
		Stage stage = stageService.getStageById(stageId);
		stage.removeDevice(deviceId);
		stageService.update(stage);
		return "redirect:/admin/race/" + raceId + "/stage/edit/" + stageId;
	}

	/**
	 * Show live ticker (edit) form.
	 */
	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/liveticker", method = RequestMethod.GET)
	public String showLiveTicker(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId, Model model) {
		return "forward:/admin/race/" + raceId + "/stage/" + stageId + "/liveticker/0";
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/liveticker/{ltiId}", method = RequestMethod.GET)
	public String editLiveTicker(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId, @PathVariable("ltiId") Long ltiId, Model model,
			Locale locale) {
		Race race = raceService.getRaceById(raceId);
		Stage stage = stageService.getStageById(stageId);
		model.addAttribute("hostname", hostname);
		model.addAttribute("race", race);
		model.addAttribute("stage", stage);
		model.addAttribute("liveTickerItems", ltiService.getAllByStage(stage));
		model.addAttribute("now", DateUtil.timeNow());
		model.addAttribute(
				"breadcrumb",
				new Breadcrumb("/admin/race/" + race.getRaceName() + "/stage/"
						+ stage.getStageName(), messageSource
						.getMessage("label.race", null, locale)));
		model.addAttribute("adminmenu", "true");
		model.addAttribute("races", raceService.getAll());
		model.addAttribute("lti", ltiId > 0 ? ltiService.getById(ltiId) : new LiveTickerItem());
		return "admin/liveticker";
	}

	/**
	 * Adds a new LiveTickerItem.
	 */
	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/liveticker/add", method = RequestMethod.POST)
	public String addLiveTickerItem(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId, @RequestParam("liveTickerId") Long ltiId,
			@RequestParam("timestamp") String timestamp, @RequestParam("news") String news) {
		LiveTickerItem lti = new LiveTickerItem();
		lti.setTimestamp(timestamp);
		lti.setNews(news);
		lti.setStage(stageService.getStageById(stageId));
		if (ltiId != null) {
			lti.setLiveTickerId(ltiId);
			ltiService.update(lti);
		} else {
			ltiService.save(lti);
		}
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId + "/liveticker";
	}

	/**
	 * Removes a LiveTickerItem.
	 */
	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/liveticker/delete/{ltiId}", method = RequestMethod.GET)
	public String removeLTI(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId, @PathVariable("ltiId") Long ltiId) {
		ltiService.delete(ltiId);
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId + "/liveticker";
	}

	/**
	 * Import rider.
	 */
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
			LOG.error("File not found");
		} catch (NullPointerException e) {
			LOG.error("No File uploaded");
		}
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId;
	}

	/**
	 * Delete Rider.
	 */
	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/rider/delete/{riderId}", method = RequestMethod.GET)
	public String deleteRider(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId, @PathVariable("riderId") Long riderId) {
		riderService.delete(riderId);
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId;
	}

	/**
	 * Deletes all riders in this stage.
	 */
	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/rider/delete/all", method = RequestMethod.GET)
	public String deleteAllRider(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId) {
		try {
			for (Rider r : riderService.getAllByStage(stageService.getStageById(stageId))) {
				riderService.delete(r.getRiderId());
			}
		} catch (Exception e) {
			LOG.error("either stage or rider in stage not found");
		}
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId;
	}

	/**
	 * Import march table.
	 */
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
			LOG.error("File not found");
		} catch (NullPointerException e) {
			LOG.error("No File uploaded");
		}
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId;
	}

	/**
	 * Deletes a MarchTableItem.
	 */
	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/marchtable/delete/{mtiId}", method = RequestMethod.GET)
	public String deleteMarchTableItem(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId, @PathVariable("mtiId") Long mtiId) {
		mtiService.delete(mtiId);
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId;
	}

	/**
	 * Deletes all MarchTableItems.
	 */
	@RequestMapping(value = "/admin/race/{raceId}/stage/{stageId}/marchtable/delete/all", method = RequestMethod.GET)
	public String deleteAllMarchTableItem(@PathVariable("raceId") Long raceId,
			@PathVariable("stageId") Long stageId) {
		try {
			for (MarchTableItem mti : mtiService.getAllByStage(stageService.getStageById(stageId))) {
				mtiService.delete(mti.getMarchTableItemId());
			}
		} catch (Exception e) {
			LOG.error("Either stage oder MarchTableItem not found");
		}
		return "redirect:/admin/race/" + raceId + "/stage/" + stageId;
	}

}
