/**
 * ImageDataService.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.ImageData;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Interface ImageDataService.
 */
public interface ImageDataService {

	/**
	 * Saves the object provided.
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
	 * Deletes ImageData object from the device. This method should be called
	 * before a device is being deleted.
	 * 
	 * @param device
	 *            the device
	 */
	public void deleteAllFromDevice(Device device);

	/**
	 * Gets a limited number of ImageData (develop mode only).
	 * 
	 * @return the limited number of ImageData
	 */
	public List<ImageData> getAllLimited();

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
	 * @param limit
	 *            the limit
	 * @return the most recent by stage
	 */
	public List<ImageData> getMostRecentByStage(Stage stage, Long limit);

	/**
	 * Gets one by id.
	 * 
	 * @param id
	 * 
	 * @return the ImageData with the ID
	 */
	public ImageData getById(Long id);

	/**
	 * Gets all by stage.
	 * 
	 * @param stage
	 * 
	 * @return all by stage
	 */
	public List<ImageData> getAllByStage(Stage stage);
}