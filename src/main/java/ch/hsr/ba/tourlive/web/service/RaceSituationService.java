/**
 * RaceSituationService.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.rider.RaceSituation;

/**
 * The Interface RaceSituationService.
 */
public interface RaceSituationService {

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
	 * Gets all RaceSituations.
	 * 
	 * @return all RaceSituations
	 */
	public List<RaceSituation> getAll();

	/**
	 * Gets the race situation by id.
	 * 
	 * @param id
	 *            the id
	 * @return the race situation with the id
	 */
	public RaceSituation getRaceSituationById(Long id);

	/**
	 * Gets all race situation by stage.
	 * 
	 * @param stage
	 *            the stage
	 * @return all race situation by stage
	 */
	public List<RaceSituation> getRaceSituationByStage(Stage stage);

	/**
	 * Gets the latest RaceSituation by stage.
	 * 
	 * @param stage
	 *            the stage
	 * @return the latest RaceSituation by stage
	 */
	public RaceSituation getLatestByStage(Stage stage);

	/**
	 * Gets the latest RaceSituation by stage limited to limit.
	 * 
	 * @param stage
	 *            the stage
	 * @param limit
	 *            the limit
	 * @return the latest RaceSituation by stage limited to timelimit
	 */
	public RaceSituation getLatestByStage(Stage stage, Long limit);

}
