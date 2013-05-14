package ch.hsr.ba.tourlive.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ch.hsr.ba.tourlive.model.Device;
import ch.hsr.ba.tourlive.model.ImageData;
import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.model.ValueContainer;
import ch.hsr.ba.tourlive.model.VideoData;
import ch.hsr.ba.tourlive.model.rider.RaceSituation;
import ch.hsr.ba.tourlive.model.rider.Rider;
import ch.hsr.ba.tourlive.service.DeviceService;
import ch.hsr.ba.tourlive.service.ImageDataService;
import ch.hsr.ba.tourlive.service.PositionDataService;
import ch.hsr.ba.tourlive.service.RaceService;
import ch.hsr.ba.tourlive.service.RaceSituationService;
import ch.hsr.ba.tourlive.service.StageService;
import ch.hsr.ba.tourlive.service.ValueContainerService;
import ch.hsr.ba.tourlive.service.VideoDataService;

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
	StageService stageService;
	@Autowired
	ImageDataService imageDataService;
	@Autowired
	DeviceService deviceService;
	@Autowired
	VideoDataService videoDataService;
	@Autowired
	RaceSituationService raceSituationService;

	@Value("${config.api.imagePath}")
	private String imagePath;

	private static final Logger log = LoggerFactory.getLogger(ApiController.class);

	@RequestMapping(value = "/api/valuecontainer", method = RequestMethod.POST)
	@ResponseBody
	public void valueContainer(@RequestBody final ValueContainer request) {
		// not loosing device information on every request, refactoring
		// needed...
		try {
			Device rec = request.getDevice();
			Device d = deviceService.getDeviceById(rec.getDeviceId());
			d.setUsername(rec.getUsername());
			request.setDevice(d);
		} catch (NullPointerException e) {

		}
		valueContainerService.save(request);
	}

	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public String showdata(Locale locale, Model model) {
		model.addAttribute("valueContainer", valueContainerService.getAll());
		model.addAttribute("positions", positionDataService.getAll());
		model.addAttribute("current", positionDataService.getAll().get(0));
		model.addAttribute("navbarapi", "active");
		model.addAttribute("races", raceService.getAllVisible());
		return "api";
	}

	@RequestMapping(value = "/api/image", method = RequestMethod.POST, headers = { "content-type=multipart/form-data" })
	public void uploadImage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("image") CommonsMultipartFile image,
			@RequestParam("timestamp") Long timestamp, @RequestParam("deviceId") String deviceId) {

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
			imageDataService.save(new ImageData(timestamp, deviceService.getDeviceById(deviceId),
					deviceId + "/" + imagefilename));
		} catch (IOException e) {
		} finally {
			// implement handler here
		}
	}

	@RequestMapping(value = "/api/video", method = RequestMethod.POST, headers = { "content-type=multipart/form-data" })
	public void uploadVideoSequence(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("video") CommonsMultipartFile video,
			@RequestParam("timestamp") Long timestamp, @RequestParam("deviceId") String deviceId) {

		String videoFilename = timestamp + "_" + video.getOriginalFilename();
		File filePath = new File(imagePath + deviceId);
		if (!filePath.exists()) {
			filePath.mkdir();
		}
		File dest = new File(filePath, videoFilename);
		try {
			video.transferTo(dest);
			Device device = deviceService.getDeviceById(deviceId);
			if (device == null) {
				device = new Device(deviceId);
			}
			videoDataService.save(new VideoData(timestamp, deviceService.getDeviceById(deviceId),
					deviceId + "/" + videoFilename));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

	@RequestMapping(value = "/api/racesituation/stage/{stageId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public void raceSituation(@PathVariable("stageId") Long stageId,
			@RequestBody RaceSituation rawSituation) {
		rawSituation.setStage(stageService.getStageById(stageId));
		raceSituationService.save(rawSituation);
	}

	@ResponseBody
	@RequestMapping(value = "/api/getstageinfo/{deviceId}", method = RequestMethod.GET, produces = "application/json")
	public Map<String, Object> getStageInfo(@PathVariable("deviceId") String deviceId) {
		Device device = deviceService.getDeviceById(deviceId);
		if (device != null) {
			Stage stage = stageService.getMostRecentStageForDevice(device);
			if (stage != null) {
				Map<String, Object> val = new HashMap<String, Object>();
				val.put("stageName", stage.getStageName());
				val.put("raceName", stage.getRace().getRaceName());
				val.put("currentRaceTotalDistance",
						stageService.getTotalRaceDistance(stage.getRace(), false));
				val.put("currentStageTotalDistance", stage.getDistance());
				val.put("completedStagesDistance",
						stageService.getTotalRaceDistance(stage.getRace(), true));
				return val;
			}
		}

		return null;
		// stageName
		// raceName
		// currentRaceTotalDistance //sum of all stages.distance together
		// currentStageTotalDistance
		// completedStagesDistance // all passed stages.distance together
	}

	@RequestMapping(value = "/api/stage/{stageId}/rider/add", method = RequestMethod.POST)
	public void addRider(@PathVariable("stageId") Long stageId, @RequestBody final Rider rider) {
		// Stage stage = stageService.getStageById(stageId);
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
//
// curl -F "deviceId=testdevice" -F "image=@CydiaIcon.png" -F "timestamp=1111"
// http://localhost:8080/api/image
// curl -F "deviceId=testdevice" -F "video=@testvid.m4v" -F
// "timestamp=111111111" http://tlng.cnlab.ch/api/video