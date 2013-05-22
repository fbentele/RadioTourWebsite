/**
 * VideoDataDAO.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.VideoData;

/**
 * The Interface VideoDataDAO.
 */
public interface VideoDataDAO {

	/**
	 * Saves the Object provided.
	 * 
	 * @param VideoData
	 *            the video data
	 */
	public void save(VideoData VideoData);

	/**
	 * Updates the object provided.
	 * 
	 * @param VideoData
	 *            the video data
	 */
	public void update(VideoData VideoData);

	/**
	 * Deletes the object with the ID.
	 * 
	 * @param VideoDataId
	 *            the video data id
	 */
	public void delete(Long VideoDataId);

	/**
	 * Deletes the object with the ID all from device.
	 * 
	 * @param device
	 *            the device
	 */
	public void deleteAllFromDevice(Device device);

	/**
	 * Gets all limited .
	 * 
	 * @return all limited
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
	 * Get by id.
	 * 
	 * @param id
	 *            the id
	 * @return by id
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
	 * Gets the next for device.
	 * 
	 * @param device
	 *            the device
	 * @param afterId
	 *            the after id
	 * @return the next for device
	 */
	public VideoData getNextForDevice(Device device, Long afterId);

}
