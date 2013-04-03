package ch.hsr.ba.tourlive.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ch.hsr.ba.tourlive.model.PositionData;
import ch.hsr.ba.tourlive.model.ValueContainer;
import ch.hsr.ba.tourlive.service.PositionDataService;
import ch.hsr.ba.tourlive.service.RaceService;
import ch.hsr.ba.tourlive.service.ValueContainerService;

@Controller
public class ApiController {
	@Autowired
	ApplicationContext context;
	@Autowired
	PositionDataService positionDataService;
	@Autowired
	ValueContainerService valueContainerService;
	@Autowired
	RaceService raceService;

	private static final Logger log = LoggerFactory
			.getLogger(ApiController.class);

	@RequestMapping(value = "/api/positiondata", method = RequestMethod.POST)
	@ResponseBody
	public void api(@RequestBody final PositionData request) {
		positionDataService.save(request);
	}

	@RequestMapping(value = "/api/valuecontainer", method = RequestMethod.POST)
	@ResponseBody
	public void values(@RequestBody final ValueContainer request) {
		valueContainerService.save(request);
	}

	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public String showdata(Locale locale, Model model) {
		model.addAttribute("valueContainer", valueContainerService.getAll());
		model.addAttribute("positions", positionDataService.getAll());
		model.addAttribute("current", positionDataService.getAll().get(0));
		model.addAttribute("navbarapi", "active");
		model.addAttribute("races", raceService.getAll());
		return "api";
	}

	@RequestMapping(value = "/api/image", method = RequestMethod.POST, headers = { "content-type=multipart/form-data" })
	public void uploadImage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("image") CommonsMultipartFile image,
			@RequestParam("stamp") String filename) {
		InputStream is = null;
		if (filename.isEmpty() || filename == null) {
			filename = "tempimage";
		}
		try {
			is = image.getInputStream();
			BufferedImage sourceImage = ImageIO.read(is);
			ImageIO.write(sourceImage, "png", new File("/tmp/images/"
					+ filename));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// implement handler here
		}
	}
}

//
// {
// "timestamp": 13646926,
// "device": {
// "deviceId": "devideid unique",
// "username": ""
// },
// "netData": {
// "technology": "3",
// "mncmcc": "22801",
// "locationArea": 909,
// "packetLoss": 0,
// "rtt": 0,
// "signalStrength": 0,
// "cellNumber": 689828,
// "upstream": 0
// },
// "positionData": {
// "direction": 0,
// "speed": 0,
// "altitude": 0,
// "latitude": 47.3235338,
// "longitude": 8.817226,
// "incline": 9,
// "gpsSendInterval": 0
// },
// "stageData": {
// "stageTime": "01:00:00",
// "distance": 0,
// "stageAltitude": 0,
// "averageSpeed": 0
// }
// }