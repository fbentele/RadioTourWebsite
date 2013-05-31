/**
 * StageDAO.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Race;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Interface StageDAO.
 */
public interface StageDAO {

	/**
	 * Saves the Object provided.
	 * 
	 * @param stage
	 *            the stage
	 */
	public Long save(Stage stage);

	/**
	 * Updates the object provided.
	 * 
	 * @param stage
	 *            the stage
	 */
	public void update(Stage stage);

	/**
	 * Deletes the object with the ID.
	 * 
	 * @param id
	 *            the id
	 */
	public void delete(Long id);

	/**
	 * Gets all stages.
	 * 
	 * @return all stages
	 */
	public List<Stage> getAllStages();

	/**
	 * Gets all visible stages.
	 * 
	 * @return all visible stages
	 */
	public List<Stage> getAllVisibleStages();

	/**
	 * Gets the stage by id.
	 * 
	 * @param id
	 *            the id
	 * @return the stage by id
	 */
	public Stage getStageById(Long id);

	/**
	 * Gets all by race.
	 * 
	 * @param race
	 *            the race
	 * @return all by race
	 */
	public List<Stage> getAllByRace(Race race);

	/**
	 * Gets all visible by race.
	 * 
	 * @param race
	 *            the race
	 * @return all visible by race
	 */
	public List<Stage> getAllVisibleByRace(Race race);

	/**
	 * Gets the stage by slug.
	 * 
	 * @param slug
	 *            the slug
	 * @return the stage by slug
	 */
	public Stage getStageBySlug(String slug);

	/**
	 * Get The most recent stage which is visible
	 * 
	 * @return the most recent visible stage
	 */
	public Stage getLatestVisible();

	/**
	 * Gets the most recent stage for device.
	 * 
	 * @param device
	 *            the device
	 * @return the most recent stage for device
	 */
	public Stage getMostRecentStageForDevice(Device device);

	/**
	 * Gets all stages for device.
	 * 
	 * @param device
	 *            the device
	 * @return all stages for device
	 */
	public List<Stage> getAllStagesForDevice(Device device);

	/**
	 * Gets the total race distance.
	 * 
	 * @param race
	 *            the race
	 * @param completed
	 *            the completed
	 * @return the total race distance
	 */
	public Float getTotalRaceDistance(Race race, Boolean completed);

	/**
	 * Gets all Stages older than one Year
	 * 
	 * @return all older (1y) races
	 */
	public List<Stage> getAllOlderThanAYear();
}
