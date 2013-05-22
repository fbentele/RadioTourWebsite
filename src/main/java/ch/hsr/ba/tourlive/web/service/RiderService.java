/**
 * RiderService.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.rider.Rider;

/**
 * The Interface RiderService.
 */
public interface RiderService {

	/**
	 * Saves the Object provided.
	 * 
	 * @param rider
	 *            the rider
	 */
	public void save(Rider rider);

	/**
	 * Updates the object provided.
	 * 
	 * @param rider
	 *            the rider
	 */
	public void update(Rider rider);

	/**
	 * Deletes the object with the ID.
	 * 
	 * @param id
	 *            the id
	 */
	public void delete(Long id);

	/**
	 * Gets all Riders.
	 * 
	 * @return all Riders
	 */
	public List<Rider> getAll();

	/**
	 * Gets the rider with the id.
	 * 
	 * @param id
	 *            the id
	 * @return the rider with the id
	 */
	public Rider getRiderById(Long id);

	/**
	 * Gets all Riders by stage.
	 * 
	 * @param stage
	 *            the stage
	 * @return all Riders by stage
	 */
	public List<Rider> getAllByStage(Stage stage);

	/**
	 * Gets the rider by start nr for stage.
	 * 
	 * @param stage
	 *            the stage
	 * @param startNr
	 *            the start nr
	 * @return the rider by start nr for stage
	 */
	public Rider getRiderByStartNrForStage(Stage stage, Integer startNr);

}