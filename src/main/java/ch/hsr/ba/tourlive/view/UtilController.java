package ch.hsr.ba.tourlive.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hsr.ba.tourlive.utils.SlugGenerator;

@Controller
public class UtilController {

	@RequestMapping(value = "/utils/slug", method = RequestMethod.POST)
	@ResponseBody
	public String giveSlug(@RequestParam("toSlug") String toSlug) {
		return SlugGenerator.makeASlug(toSlug);
	}
}
