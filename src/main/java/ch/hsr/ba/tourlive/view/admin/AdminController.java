package ch.hsr.ba.tourlive.view.admin;

import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hsr.ba.tourlive.model.Race;
import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.service.DeviceService;
import ch.hsr.ba.tourlive.service.LiveTickerItemService;
import ch.hsr.ba.tourlive.service.RaceService;
import ch.hsr.ba.tourlive.service.StageService;
import ch.hsr.ba.tourlive.viewmodel.Breadcrumb;
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
	@Autowired
	private LiveTickerItemService ltiService;
	@Value("${config.api.imagePath}")
	private String imagePath;
	@Value("${config.dev.hostname}")
	private String hostname;
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Model model) {
		model.addAttribute("menuitems", MenuItem.makeAdminMenu());
		model.addAttribute("races", raceService.getAll());
		model.addAttribute("breadcrumb", new Breadcrumb("/admin"));
		return "admin/admin";
	}

	@RequestMapping(value = "/admin/race", method = RequestMethod.GET)
	public String manageRace(Locale locale, Model model) {
		model.addAttribute("menuitems", MenuItem.makeAdminMenu());
		model.addAttribute("races", raceService.getAll());
		model.addAttribute("breadcrumb", new Breadcrumb("/admin/race"));
		model.addAttribute("race", new Race());
		return "admin/manageRace";
	}

	@RequestMapping(value = "/admin/race/add", method = RequestMethod.POST)
	public String newRace(@Valid @ModelAttribute("race") Race race, BindingResult binding,
			@RequestParam(value = "visible", defaultValue = "") String visible, Locale locale,
			Model model) {
		if (binding.hasErrors()) {
			model.addAttribute("race", race);
			model.addAttribute("menuitems", MenuItem.makeAdminMenu());
			model.addAttribute("races", raceService.getAll());
			model.addAttribute("breadcrumb", new Breadcrumb("/admin/race"));
			model.addAttribute("showhidden", true);
			return "admin/manageRace";
		} else {
			if (visible.contains("true"))
				race.setVisible(true);
			raceService.save(race);
			return "redirect:/admin/race";
		}
	}

	@RequestMapping(value = "/admin/race/edit/{raceId}", method = RequestMethod.GET)
	public String editRace(@PathVariable("raceId") Long raceId,
			@RequestParam(value = "visible", defaultValue = "") String visible, Model model) {

		model.addAttribute("menuitems", MenuItem.makeAdminMenu());
		Race race = raceService.getRaceById(raceId);
		model.addAttribute("race", race);
		model.addAttribute("stage", new Stage());
		model.addAttribute("stages", stageService.getAllByRace(race));
		model.addAttribute("races", raceService.getAllVisible());
		model.addAttribute("breadcrumb", new Breadcrumb("/admin/race/" + raceId));
		return "admin/editRace";
	}

	@RequestMapping(value = "/admin/race/edit/{raceId}", method = RequestMethod.POST)
	public String editedRace(@PathVariable("raceId") Long raceId,
			@ModelAttribute("race") Race race,
			@RequestParam(value = "visible", defaultValue = "") String visible, Model model) {
		if (visible.contains("true"))
			race.setVisible(true);
		raceService.update(race);
		model.addAttribute("menuitems", MenuItem.makeAdminMenu());
		model.addAttribute("race", raceService.getRaceById(raceId));
		return "redirect:/admin/race";
	}

	@RequestMapping(value = "/admin/race/delete/{raceId}", method = RequestMethod.GET)
	public String removeRace(@PathVariable("raceId") Long raceId, Model model) {
		raceService.delete(raceId);
		model.addAttribute("menuitems", MenuItem.makeAdminMenu());
		model.addAttribute("races", raceService.getAllVisible());
		model.addAttribute("breadcrumb", new Breadcrumb("/admin/race"));
		return "forward:/admin/race";
	}
}
