package ch.hsr.ba.tourlive.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

import javax.imageio.ImageIO;
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
import ch.hsr.ba.tourlive.viewmodel.MenuItem;

@Controller
public class AdminController {
	@Autowired
	ApplicationContext context;
	@Autowired
	StageService stageService;
	@Autowired
	RaceService raceService;
	@Autowired
	DeviceService deviceService;
	@Value("${config.api.imagePath}")
	private String imagePath;
	@Value("${config.dev.hostname}")
	private String hostname;

	Logger log = LoggerFactory.getLogger(AdminController.class);

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
	public String addStage(@RequestParam("stageName") String stageName,
			@RequestParam("stageDescription") String stageDescription,
			@RequestParam("stageSlug") String stageSlug,
			@RequestParam("starttime") String starttime,
			@RequestParam("endtime") String endtime,
			@PathVariable("raceId") Long raceId,
			@RequestParam("bannerImageFile") CommonsMultipartFile image) {
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
		InputStream is = null;
		try {
			is = image.getInputStream();
			BufferedImage sourceImage = ImageIO.read(is);
			File theImage = new File(imagePath + "stage" + stage.getStageId());
			if (!theImage.exists()) {
				boolean result = theImage.mkdir();
				if (result) {
					log.info("images folder created");
				}
			}
			String imagefilename = "bannerImage.png";
			ImageIO.write(sourceImage, "png", new File(theImage, imagefilename));
		} catch (IOException e) {
			// catch exception
		} finally {
			// implement handler here
		}
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
		String imagelocation = hostname + "stage" + stageId
				+ "/bannerImage.png";
		File f = new File(imagelocation);
		log.error(imagelocation + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		String s = f.exists() ? imagelocation
				: "http://www.placehold.it/300x50/EFEFEF/AAAAAA&text=kein+Bild";
		model.addAttribute("bannerImage", s);
		return "admin/editStage";
	}

	@RequestMapping(value = "/admin/race/{raceId}/stage/edit/{stageId}", method = RequestMethod.POST)
	public String updateStage(
			@RequestParam("stageId") Long stageId,
			@RequestParam("stageName") String stageName,
			@RequestParam("stageDescription") String stageDescription,
			@RequestParam("stageSlug") String stageSlug,
			@RequestParam("starttime") String starttime,
			@RequestParam("endtime") String endtime,
			@PathVariable("raceId") Long raceId,
			@RequestParam(value = "bannerImageFile", defaultValue = "") CommonsMultipartFile image) {
		Stage stage = stageService.getStageById(stageId);
		stage.setStageName(stageName);
		stage.setStageDescription(stageDescription);
		stage.setStageSlug(stageSlug);
		stage.setStarttime(starttime);
		stage.setEndtime(endtime);
		stageService.save(stage);
		InputStream is = null;
		try {
			is = image.getInputStream();
			BufferedImage sourceImage = ImageIO.read(is);
			File theImage = new File(imagePath + "stage" + stage.getStageId());
			if (!theImage.exists()) {
				boolean result = theImage.mkdir();
				if (result) {
					log.info("images folder created");
				}
			}
			String imagefilename = "bannerImage.png";
			ImageIO.write(sourceImage, "png", new File(theImage, imagefilename));
		} catch (IOException e) {
			// catch exception
		} catch (IllegalArgumentException e) {
			// no image specified
		} finally {
			// implement handler here
		}

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
