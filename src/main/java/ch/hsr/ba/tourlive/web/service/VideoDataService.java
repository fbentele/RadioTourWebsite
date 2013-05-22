/**
 * VideoDataService.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.VideoData;

/**
 * The Interface VideoDataService.
 */
public interface VideoDataService {

	/**
	 * Saves the Object provided.
	 * 
	 * @param videoData
	 *            the video data
	 */
	public void save(VideoData videoData);

	/**
	 * Updates the object provided.
	 * 
	 * @param videoData
	 *            the video data
	 */
	public void update(VideoData videoData);

	/**
	 * Deletes the object with the ID.
	 * 
	 * @param videoDataId
	 *            the video data id
	 */
	public void delete(Long videoDataId);

	/**
	 * Deletes all VideoData from device.
	 * 
	 * @param device
	 *            the device
	 */
	public void deleteAllFromDevice(Device device);

	/**
	 * Gets all limited.
	 * 
	 * @return the all limited
	 */
	public List<VideoData> getAllLimited();

	/**
	 * Gets all video data by device.
	 * 
	 * @param device
	 *            the device
	 * @return all video data by device
	 */
	public List<VideoData> getAllVideoDataByDevice(Device device);

	/**
	 * Gets the most recent by device.
	 * 
	 * @param device
	 *            the device
	 * @return the most recent by device
	 */
	public VideoData getMostRecentByDevice(Device device);

	/**
	 * Gets the most recent by stage.
	 * 
	 * @param stage
	 *            the stage
	 * @return the most recent by stage
	 */
	public List<VideoData> getMostRecentByStage(Stage stage);

	/**
	 * Gets the most recent by stage limited to.
	 * 
	 * @param stage
	 *            the stage
	 * @param limit
	 *            the limit
	 * @return the most recent by stage limited to
	 */
	public List<VideoData> getMostRecentByStageLimitedTo(Stage stage, Long limit);

	/**
	 * Gets VideoData Object by id.
	 * 
	 * @param id
	 *            the id
	 * @return VideoData Object
	 */
	public VideoData getById(Long id);

	/**
	 * Gets all by stage.
	 * 
	 * @param stage
	 *            the stage
	 * @return all by stage
	 */
	public List<VideoData> getAllByStage(Stage stage);

	/**
	 * Gets the next video for device.
	 * 
	 * @param device
	 *            the device
	 * @param afterId
	 *            the after id
	 * @return the next video for device
	 */
	public VideoData getNextForDevice(Device device, Long afterId);
}