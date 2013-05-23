/**
 * PositionDataDAO.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.PositionData;

/**
 * The Interface PositionDataDAO.
 */
public interface PositionDataDAO {

	/**
	 * Saves the Object provided.
	 * 
	 * @param position
	 *            the position
	 */
	public Long save(PositionData position);

	/**
	 * Updates the object provided.
	 * 
	 * @param position
	 *            the position
	 */
	public void update(PositionData position);

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
	public List<PositionData> getAll();
}