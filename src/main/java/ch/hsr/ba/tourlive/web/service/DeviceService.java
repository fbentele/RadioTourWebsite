/**
 * DeviceService.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Interface DeviceService.
 */
public interface DeviceService {

	/**
	 * Saves the Object provided.
	 * 
	 * @param device
	 *            the device
	 */
	public void save(Device device);

	/**
	 * Updates the object provided.
	 * 
	 * @param device
	 *            the device
	 */
	public void update(Device device);

	/**
	 * Deletes the object with the ID.
	 * 
	 * @param id
	 *            the id
	 */
	public void delete(String id);

	/**
	 * Gets all the Objects available, this might be a very large list, use with
	 * cautious.
	 * 
	 * @return the all
	 */
	public List<Device> getAll();

	/**
	 * Gets the device by id.
	 * 
	 * @param deviceId
	 *            the device id
	 * @return the device by id
	 */
	public Device getDeviceById(String deviceId);

	/**
	 * Gets the devices which are not already assigned to the stage.
	 * 
	 * @param stage
	 *            the stage
	 * @return all not already assigned to
	 */
	public List<Device> getAllNotAlreadyAssignedTo(Stage stage);
}
