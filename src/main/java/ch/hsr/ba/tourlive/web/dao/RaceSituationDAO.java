/**
 * RaceSituationDAO.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.rider.RaceSituation;

/**
 * The Interface RaceSituationDAO.
 */
public interface RaceSituationDAO {

	/**
	 * Saves the Object provided.
	 * 
	 * @param raceSituation
	 *            the race situation
	 */
	public void save(RaceSituation raceSituation);

	/**
	 * Updates the object provided.
	 * 
	 * @param raceSituation
	 *            the race situation
	 */
	public void update(RaceSituation raceSituation);

	/**
	 * Deletes the object with the ID.
	 * 
	 * @param id
	 *            the id
	 */
	public void delete(Long id);

	/**
	 * Gets all.
	 * 
	 * @return all
	 */
	public List<RaceSituation> getAll();

	/**
	 * Gets the race situation by id.
	 * 
	 * @param id
	 *            the id
	 * @return the race situation by id
	 */
	public RaceSituation getRaceSituationById(Long id);

	/**
	 * Gets all by stage.
	 * 
	 * @param stage
	 *            the stage
	 * @return all by stage
	 */
	public List<RaceSituation> getAllByStage(Stage stage);

	/**
	 * Gets the latest by stage.
	 * 
	 * @param stage
	 *            the stage
	 * @return the latest by stage
	 */
	public RaceSituation getLatestByStage(Stage stage);

	/**
	 * Gets the latest by stage limited to.
	 * 
	 * @param stage
	 *            the stage
	 * @param limit
	 *            the limit
	 * @return the latest by stage limited to
	 */
	public RaceSituation getLatestByStage(Stage stage, Long limit);

}
