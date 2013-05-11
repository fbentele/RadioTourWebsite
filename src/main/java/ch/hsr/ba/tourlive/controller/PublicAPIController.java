package ch.hsr.ba.tourlive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hsr.ba.tourlive.model.ImageData;
import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.model.ValueContainer;
import ch.hsr.ba.tourlive.model.VideoData;
import ch.hsr.ba.tourlive.service.ImageDataService;
import ch.hsr.ba.tourlive.service.StageService;
import ch.hsr.ba.tourlive.service.ValueContainerService;
import ch.hsr.ba.tourlive.service.VideoDataService;

@Controller
public class PublicAPIController {
	@Autowired
	ValueContainerService valueContainerService;
	@Autowired
	StageService stageService;
	@Autowired
	ImageDataService imageDataService;
	@Autowired
	VideoDataService videoDataService;

	@ResponseBody
	@RequestMapping(value = "/public/stages", method = RequestMethod.GET)
	public List<Stage> allStages() {
		return stageService.getAll();
	}

	@ResponseBody
	@RequestMapping(value = "/public/stage/{stageId}/valuecontainer", method = RequestMethod.GET)
	public List<ValueContainer> getValueContainersForStage(@PathVariable("stageId") Long stageId) {
		Stage stage = stageService.getStageById(stageId);
		if (stage != null) {
			return valueContainerService.getAllForStageByDistance(stage);
		} else {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "public/stage/{stageId}/imagedata", method = RequestMethod.GET)
	public List<ImageData> getImageDataForStage(@PathVariable("stageId") Long stageId) {
		Stage stage = stageService.getStageById(stageId);
		if (stage != null) {
			return imageDataService.getAllByStage(stage);
		} else {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "public/stage/{stageId}/videodata", method = RequestMethod.GET)
	public List<VideoData> getVideoDataForStage(@PathVariable("stageId") Long stageId) {
		Stage stage = stageService.getStageById(stageId);
		if (stage != null) {
			return videoDataService.getAllByStage(stage);
		} else {
			return null;
		}
	}
}