package ch.hsr.ba.tourlive.view;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.hsr.ba.tourlive.model.PositionData;
import ch.hsr.ba.tourlive.service.PositionDataService;

@Controller
public class ApiController {
	@Autowired
	ApplicationContext context;
	@Autowired
	PositionDataService positionDataService;

	// test json
	// {"timestamp":11929112,"speed":12.5,"direction":5,"incline":9,"longitude":31.111,"latitude":43.9999,"altitude":50}

	private static final Logger logger = LoggerFactory
			.getLogger(ApiController.class);

	@RequestMapping(value = "/api/positiondata", method = RequestMethod.POST)
	public void api(@RequestBody final PositionData request) {
		positionDataService.save(request);
	}

	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public String showdata(Locale locale, Model model) {
		model.addAttribute("positions", positionDataService.getAll());
		return "api";
	}
}
