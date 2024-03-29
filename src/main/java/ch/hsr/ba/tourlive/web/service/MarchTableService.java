/**
 * MarchTableService.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.MarchTableItem;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Interface MarchTableService.
 */
public interface MarchTableService {

	/**
	 * Saves the Object provided.
	 * 
	 * @param mti
	 *            the mti
	 */
	public void save(MarchTableItem mti);

	/**
	 * Updates the object provided.
	 * 
	 * @param mti
	 *            the mti
	 */
	public void update(MarchTableItem mti);

	/**
	 * Deletes the object with the ID.
	 * 
	 * @param id
	 *            the id
	 */
	public void delete(Long id);

	/**
	 * Gets all items assigned to a stage.
	 * 
	 * @param stage
	 *            the stage
	 * @return all items by stage
	 */
	public List<MarchTableItem> getAllByStage(Stage stage);
}
