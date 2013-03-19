package ch.hsr.ba.tourlive.view;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.service.StageService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	ApplicationContext context;

	@Autowired
	StageService st;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		//testHibernate();

		return "home";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Locale locale, Model model) {
		logger.info("This is the about Page");
		return "about";
	}

	@RequestMapping(value="/stage", method = RequestMethod.GET)
	public String stage(Locale locale, Model model){
		model.addAttribute("stages", st.getAllStages());
		return "stage";
	}
	
	
	private void testHibernate() {
		System.out.println("#### Start Test");

		Stage stage = context.getBean(Stage.class);

		stage.setStageId(1L);
		stage.setRaceId(4L);
		stage.setStageName("Hallo");

		st.save(stage);

		List<Stage> stages = st.getAllStages();

		for (Stage s : stages) {
			System.out.println("Stage: " + s.getStageName());
		}

		System.out.println("#### End Test");
	}
}
