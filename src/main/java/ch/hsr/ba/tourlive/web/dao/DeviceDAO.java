/**
 * DeviceDAO.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Interface DeviceDAO.
 */
public interface DeviceDAO {

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
	 * Gets all.
	 * 
	 * @return all
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
	 * Gets all not already assigned to.
	 * 
	 * @param stage
	 *            the stage
	 * @return all not already assigned to
	 */
	public List<Device> getAllNotAlreadyAssignedTo(Stage stage);
}
