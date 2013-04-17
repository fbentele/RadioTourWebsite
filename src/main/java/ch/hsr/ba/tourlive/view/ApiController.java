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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ch.hsr.ba.tourlive.model.ImageData;
import ch.hsr.ba.tourlive.model.PositionData;
import ch.hsr.ba.tourlive.model.RaceSituation;
import ch.hsr.ba.tourlive.model.ValueContainer;
import ch.hsr.ba.tourlive.service.DeviceService;
import ch.hsr.ba.tourlive.service.ImageDataService;
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
	@Autowired
	ImageDataService imageDataService;
	@Autowired
	DeviceService deviceService;

	@Value("${config.api.imagePath}")
	private String imagePath;

	private static final Logger log = LoggerFactory
			.getLogger(ApiController.class);

	@RequestMapping(value = "/api/positiondata", method = RequestMethod.POST)
	@ResponseBody
	public void api(@RequestBody final PositionData request) {
		positionDataService.save(request);
	}

	@RequestMapping(value = "/api/valuecontainer", method = RequestMethod.POST)
	@ResponseBody
	public void valueContainer(@RequestBody final ValueContainer request) {
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
			@RequestParam("timestamp") Long timestamp,
			@RequestParam("deviceId") String deviceId) {

		InputStream is = null;
		if (deviceId.isEmpty() || deviceId == null)
			deviceId = "tempimage";

		try {
			is = image.getInputStream();
			BufferedImage sourceImage = ImageIO.read(is);

			File theImage = new File(imagePath + deviceId);

			if (!theImage.exists()) {
				boolean result = theImage.mkdir();
				if (result) {
					log.info("images folder created");
				}
			}
			String imagefilename = deviceId + timestamp + ".png";
			ImageIO.write(sourceImage, "png", new File(theImage, imagefilename));
			imageDataService.save(new ImageData(timestamp, deviceService
					.getDeviceById(deviceId), deviceId + "/" + imagefilename));
		} catch (IOException e) {
			// catch exception
		} finally {
			// implement handler here
		}
	}

	@RequestMapping(value = "/api/racesituation", method = RequestMethod.POST)
	public void raceSituation(@RequestBody final RaceSituation raceSituation) {
		log.info("RaceSituation received");

	}
}

//
// {
// "timestamp": 13646926,
// "device": {
// "deviceId": "iJlekqJleNapiWnxqPejslq",
// "username": "Spitze"
// },
// "netData": {
// "technology": "3",
// "mncmcc": "22801",
// "locationArea": 909,
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
// "transferInterval": 0
// },
// "stageData": {
// "stageTime": "01:00:00",
// "distance": 0,
// "stageUpAltitude": 0,
// "averageSpeed": 0
// }
// }

// curl -F "deviceId=testdevice" -F "image=@CydiaIcon.png" -F
// "timestamp=1111" http://localhost:8080/api/image