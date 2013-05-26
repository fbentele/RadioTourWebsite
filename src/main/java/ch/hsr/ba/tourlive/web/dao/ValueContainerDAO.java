/**
 * ValueContainerDAO.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.ValueContainer;

/**
 * The Interface ValueContainerDAO handels basic Database CRUD operations and
 * complexe get algorithms.
 */
public interface ValueContainerDAO {

	/**
	 * Saves a ValueContainer.
	 * 
	 * @param valueContainer
	 *            to be saved
	 * 
	 */
	public Long save(ValueContainer valueContainer);

	/**
	 * Updates the ValueContainer.
	 * 
	 * @param valueContainer
	 *            to be update
	 */
	public void update(ValueContainer valueContainer);

	/**
	 * Delete object by id.
	 * 
	 * @param id
	 *            the object with the id to be deleted
	 */
	public void delete(Long id);

	/**
	 * Delete all from device.
	 * 
	 * @param device
	 *            the device
	 */
	public void deleteAllFromDevice(Device device);

	/**
	 * Gets all ValueContainers (may be a very large list, use with cautious and
	 * in develop mode only).
	 * 
	 * @return allValueContainers
	 */
	public List<ValueContainer> getAll();

	/**
	 * Gets all valuecontainers for stage.
	 * 
	 * @param stage
	 *            the stage to filter
	 * @return all value container for stage
	 */
	public List<ValueContainer> getLatest1000ValueContainerForStage(Stage stage);

	/**
	 * Gets all valuecontainers for stage.
	 * 
	 * @param stage
	 *            the stage to filter
	 * @return all value container for stage ordered by distance (farest first)
	 */
	public List<ValueContainer> getAllForStageByDistance(Stage stage);

	/**
	 * Gets all valuecontainers for stage.
	 * 
	 * @param stage
	 *            the stage to filter
	 * @param limit
	 *            the time limit
	 * @return all value container for stage ordered by distance (farest first)
	 *         limited to limit (timestamp)
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
}
