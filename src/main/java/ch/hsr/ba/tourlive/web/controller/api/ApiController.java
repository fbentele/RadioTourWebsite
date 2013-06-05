/**
 * ApiController.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.controller.api;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.ImageData;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.ValueContainer;
import ch.hsr.ba.tourlive.web.model.VideoData;
import ch.hsr.ba.tourlive.web.model.rider.RaceSituation;
import ch.hsr.ba.tourlive.web.service.DeviceService;
import ch.hsr.ba.tourlive.web.service.ImageDataService;
import ch.hsr.ba.tourlive.web.service.PositionDataService;
import ch.hsr.ba.tourlive.web.service.RaceService;
import ch.hsr.ba.tourlive.web.service.RaceSituationService;
import ch.hsr.ba.tourlive.web.service.StageService;
import ch.hsr.ba.tourlive.web.service.ValueContainerService;
import ch.hsr.ba.tourlive.web.service.VideoDataService;
import ch.hsr.ba.tourlive.web.utils.FileUtil;

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
	private String mediaPath;

	@Value("${config.dev.hostname}")
	private String hostname;

	private static final Logger LOG = LoggerFactory.getLogger(ApiController.class);

	/**
	 * Receiving Value container form Device.
	 */
	@RequestMapping(value = "/api/valuecontainer", method = RequestMethod.POST)
	@ResponseBody
	public void valueContainer(@RequestBody final ValueContainer request) {
		// not loosing device information on every request, refactoring
		// needed...
		try {
			Device requestDevice = request.getDevice();
			Device d = deviceService.getDeviceById(requestDevice.getDeviceId());
			d.setUsername(requestDevice.getUsername());
			d.setPhoneNr(requestDevice.getPhoneNr());
			request.setDevice(d);
		} catch (NullPointerException e) {
			LOG.error("no deviceId found");
		}
		valueContainerService.save(request);
	}

	/**
	 * Developemode only! For Debugging what has been reveived from all devices.
	 */
	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public String showdata(Locale locale, Model model) {
		try {
			model.addAttribute("valueContainer", valueContainerService.getAllValueContainers());
			model.addAttribute("positions", positionDataService.getAll());
			model.addAttribute("current", positionDataService.getAll().get(0));
			model.addAttribute("navbarapi", "active");
			model.addAttribute("races", raceService.getAllVisible());
			model.addAttribute("videos", videoDataService.getAllLimited());
			model.addAttribute("images", imageDataService.getAllLimited());
			model.addAttribute("hostname", hostname);
		} catch (IndexOutOfBoundsException e) {
			LOG.error("no valuecontainers");
		}
		return "api";
	}

	/**
	 * Upload image.
	 */
	@RequestMapping(value = "/api/image", method = RequestMethod.POST, headers = { "content-type=multipart/form-data" })
	public void uploadImage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("image") CommonsMultipartFile image,
			@RequestParam("timestamp") Long timestamp, @RequestParam("deviceId") String deviceId) {
		Device device = deviceService.getDeviceById(deviceId);
		if (device == null) {
			device = new Device(deviceId);
			deviceService.save(device);
		}

		String temp = FileUtil.safePng(image, mediaPath, deviceId, deviceId + "_" + timestamp
				+ ".png");
		imageDataService
				.save(new ImageData(timestamp, deviceService.getDeviceById(deviceId), temp));
	}

	/**
	 * Upload video sequence.
	 */
	@RequestMapping(value = "/api/video", method = RequestMethod.POST, headers = { "content-type=multipart/form-data" })
	public void uploadVideoSequence(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("video") CommonsMultipartFile video,
			@RequestParam("timestamp") Long timestamp, @RequestParam("deviceId") String deviceId,
			@RequestParam Integer rotation) {
		String vidLocation = FileUtil.safeVideo(video, mediaPath, deviceId, deviceId + "_"
				+ timestamp);
		Device device = deviceService.getDeviceById(deviceId);
		if (device == null) {
			device = new Device(deviceId);
			deviceService.save(device);
		}
		videoDataService.save(new VideoData(timestamp, device, vidLocation, rotation));
	}

	/**
	 * Posting a Race situation.
	 */
	@ResponseBody
	@RequestMapping(value = "/api/racesituation/stage/{stageId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public void raceSituation(@PathVariable("stageId") Long stageId,
			@RequestBody RaceSituation rawSituation) {
		rawSituation.setStage(stageService.getStageById(stageId));
		raceSituationService.save(rawSituation);
	}

	/**
	 * Assembling Information for Devices to be displayed on a device.
	 * 
	 * @return A HashMap with stageName, raceName, currentRaceTotalDistance,
	 *         currentStageTotalDistance, completedStagesDistance
	 */
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
				val.put("currentStageStarttime", stage.getStarttimeAsTimestamp());
				val.put("currentStageEndtime", stage.getEndtimeAsTimestamp());
				return val;
			}
		}
		return null;
	}
}