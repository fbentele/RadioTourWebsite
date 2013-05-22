/**
 * ImageDataDAO.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.ImageData;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Interface ImageDataDAO.
 */
public interface ImageDataDAO {

	/**
	 * Saves the Object provided.
	 * 
	 * @param imageData
	 *            the image data
	 */
	public void save(ImageData imageData);

	/**
	 * Updates the object provided.
	 * 
	 * @param imageData
	 *            the image data
	 */
	public void update(ImageData imageData);

	/**
	 * Deletes the object with the ID.
	 * 
	 * @param imageDataId
	 *            the image data id
	 */
	public void delete(Long imageDataId);

	/**
	 * Gets all limited.
	 * 
	 * @return all limited
	 */
	public List<ImageData> getAllLimited();

	/**
	 * Deletes the object with the ID all from device.
	 * 
	 * @param device
	 *            the device
	 */
	public void deleteAllFromDevice(Device device);

	/**
	 * Gets all image data by device.
	 * 
	 * @param device
	 *            the device
	 * @return all image data by device
	 */
	public List<ImageData> getAllImageDataByDevice(Device device);

	/**
	 * Gets the most recent by device.
	 * 
	 * @param device
	 *            the device
	 * @return the most recent by device
	 */
	public ImageData getMostRecentByDevice(Device device);

	/**
	 * Gets the most recent by stage.
	 * 
	 * @param stage
	 *            the stage
	 * @return the most recent by stage
	 */
	public List<ImageData> getMostRecentByStage(Stage stage);

	/**
	 * Gets the most recent by stage.
	 * 
	 * @param stage
	 *            the stage
	 * @param time
	 *            the time
	 * @return the most recent by stage
	 */
	public List<ImageData> getMostRecentByStage(Stage stage, Long time);

	/**
	 * Gets by id.
	 * 
	 * @param id
	 *            the id
	 * @return by id
	 */
	public ImageData getById(Long id);

	/**
	 * Gets all by stage.
	 * 
	 * @param stage
	 *            the stage
	 * @return all by stage
	 */
	public List<ImageData> getAllByStage(Stage stage);
}
