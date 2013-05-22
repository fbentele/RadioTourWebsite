/**
 * RiderDAO.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.rider.Rider;

/**
 * The Interface RiderDAO.
 */
public interface RiderDAO {

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
	 * Gets the rider by id.
	 * 
	 * @param id
	 *            the id
	 * @return the rider by id
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
