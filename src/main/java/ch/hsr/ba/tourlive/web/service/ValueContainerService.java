/**
 * ValueContainerService.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.ValueContainer;

/**
 * The Interface ValueContainerService.
 */
public interface ValueContainerService {

	/**
	 * Save the ValueContainer.
	 * 
	 * @param valueContainer
	 *            the ValueContainer to be saved
	 */
	public Long save(ValueContainer valueContainer);

	/**
	 * Update the ValueContainer.
	 * 
	 * @param valueContainer
	 *            the ValueContainer to be updated
	 */
	public void update(ValueContainer valueContainer);

	/**
	 * Delete the ValueContainer with the ID.
	 * 
	 * @param id
	 *            the ID of the ValueContainer to be deleted
	 */
	public void delete(Long id);

	/**
	 * Delete all ValueContainers from a specific device. This method should be
	 * called, before a device is being deleted.
	 * 
	 * @param device
	 *            all ValueContainer from this device will be deleted
	 */
	public void deleteAllFromDevice(Device device);

	/**
	 * Gets the all value containers. <b>!!! Attention !!</b> very expensive
	 * method, returns all ValueContainer which might be a very large list.
	 * 
	 * @return all ValueContainers
	 */
	public List<ValueContainer> getAllValueContainers();

	/**
	 * Gets all ValueContainers for a stage.
	 * 
	 * @param stage
	 *            the stage
	 * @return a List of ValueContainer assigned to a stage
	 */
	public List<ValueContainer> getLatest1000ValueContainerForStage(Stage stage);

	/**
	 * Gets all for stage ordered by distance (farest first).
	 * 
	 * @param stage
	 *            the stage
	 * @return all ValueContainers for stage ordered by distance
	 */
	public List<ValueContainer> getAllForStageByDistance(Stage stage);

	/**
	 * Gets all for stage by distance limited to.
	 * 
	 * @param stage
	 *            the stage
	 * @param limit
	 *            the limit
	 * @return all ValueContainers for stage ordered by distance limited to
	 *         limit
	 */
	public List<ValueContainer> getAllForStageByDistance(Stage stage, Long limit);

	/**
	 * Gets the latest (youngest by time) ValueContainer for each device assigne
	 * to the stage.
	 * 
	 * @param stage
	 *            the stage for which the ValueContainers applie to
	 * @return the latest for each device by stage
	 */
	public List<ValueContainer> getLatestForDeviceByStage(Stage stage);

	/**
	 * Gets the latest (youngest by time) ValueContainer for each device assigne
	 * to the stage.
	 * 
	 * @param stage
	 *            the stage for which the ValueContainers applie to
	 * @param limit
	 *            the time limit
	 * @return the latest for each device by stage limited to limit
	 */
	public List<ValueContainer> getLatestForDeviceByStage(Stage stage, Long limit);

	/**
	 * Gets the first for stage by distance limited to race km. This is to
	 * evaluate the time / raceKm relation
	 * 
	 * @param stage
	 *            the stage
	 * @param raceKm
	 *            the race km
	 * @return the first for stage by distance limited to race km
	 */
	public ValueContainer getFirstForStageByDistanceLimitedToRaceKm(Stage stage, Float raceKm);

	/**
	 * Gets the deficite to the current leader of the stage.
	 * <b>!!!Attention!!!</b> very expensive Method, should be cached in a
	 * temporary table.
	 * 
	 * @param stage
	 *            the stage
	 * @return a HashMap with the ID of the Valuecontainer and the deficite as
	 *         time in seconds
	 */
	public void getDeficiteToLeaderForStage(Stage stage);

	/**
	 * Get all latest position of a device, for admin purpose only
	 * 
	 * @param a
	 *            list of devices
	 * @return returns a list of ValueContainers
	 */
	public List<ValueContainer> getMostRecentForEachDevice(List<Device> devices);

}
