package ch.hsr.ba.tourlive.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Sample {

	@RequestMapping("/sample")
	public @ResponseBody String simple() {
		return "Hello world!";
	}
}

