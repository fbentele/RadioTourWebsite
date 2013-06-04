/**
 * PublicAPIController.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hsr.ba.tourlive.web.model.ImageData;
import ch.hsr.ba.tourlive.web.model.MarchTableItem;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.ValueContainer;
import ch.hsr.ba.tourlive.web.model.VideoData;
import ch.hsr.ba.tourlive.web.model.rider.Rider;
import ch.hsr.ba.tourlive.web.service.ImageDataService;
import ch.hsr.ba.tourlive.web.service.MarchTableService;
import ch.hsr.ba.tourlive.web.service.RiderService;
import ch.hsr.ba.tourlive.web.service.StageService;
import ch.hsr.ba.tourlive.web.service.ValueContainerService;
import ch.hsr.ba.tourlive.web.service.VideoDataService;

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

	@Autowired
	MarchTableService mtiService;

	@Autowired
	RiderService riderService;

	/**
	 * Returns all public visible stages.
	 * 
	 * @return list of all visible Stages
	 */
	@ResponseBody
	@RequestMapping(value = "/public/stages", method = RequestMethod.GET)
	public List<Stage> allStages() {
		return stageService.getAllVisibleStages();
	}

	/**
	 * Gets the value containers for stage.
	 * 
	 * @param stageId
	 *            the stage id
	 * @return the value containers for stage
	 */
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

	/**
	 * Gets all image data objects for stage.
	 * 
	 * @param stageId
	 *            the stage id
	 * @return the image data for stage
	 */
	@ResponseBody
	@RequestMapping(value = "/public/stage/{stageId}/imagedata", method = RequestMethod.GET)
	public List<ImageData> getImageDataForStage(@PathVariable("stageId") Long stageId) {
		Stage stage = stageService.getStageById(stageId);
		if (stage != null) {
			return imageDataService.getAllByStage(stage);
		} else {
			return null;
		}
	}

	/**
	 * Gets all video data for stage.
	 * 
	 * @param stageId
	 *            the stage id
	 * @return the video data for stage
	 */
	@ResponseBody
	@RequestMapping(value = "/public/stage/{stageId}/videodata", method = RequestMethod.GET)
	public List<VideoData> getVideoDataForStage(@PathVariable("stageId") Long stageId) {
		Stage stage = stageService.getStageById(stageId);
		if (stage != null) {
			return videoDataService.getAllByStage(stage);
		} else {
			return null;
		}
	}

	/**
	 * Gets all MarchTableItems for stage.
	 * 
	 * @param stageId
	 *            the stage id
	 * @return the video data for stage
	 */
	@ResponseBody
	@RequestMapping(value = "/public/stage/{stageId}/marchtableitem", method = RequestMethod.GET)
	public List<MarchTableItem> getMarchTableItemsForStage(@PathVariable("stageId") Long stageId) {
		Stage stage = stageService.getStageById(stageId);
		if (stage != null) {
			return mtiService.getAllByStage(stage);
		} else {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/public/stage/{stageId}/riders", method = RequestMethod.GET)
	public List<Rider> getRiderForStage(@PathVariable("stageId") Long stageId) {
		Stage stage = stageService.getStageById(stageId);
		if (stage != null) {
			return riderService.getAllByStage(stage);
		} else {
			return null;
		}
	}
}
