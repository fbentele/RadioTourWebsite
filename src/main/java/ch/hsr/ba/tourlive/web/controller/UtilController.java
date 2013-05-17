package ch.hsr.ba.tourlive.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hsr.ba.tourlive.web.service.RiderService;
import ch.hsr.ba.tourlive.web.service.StageService;
import ch.hsr.ba.tourlive.web.utils.SlugGenerator;

@Controller
public class UtilController {
	@Autowired
	RiderService riderService;
	@Autowired
	StageService stageService;

	@RequestMapping(value = "/utils/slug", method = RequestMethod.POST)
	@ResponseBody
	public String giveSlug(@RequestParam("toSlug") String toSlug) {
		return SlugGenerator.makeASlug(toSlug);
	}
}
