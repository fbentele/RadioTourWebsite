/**
 * StageDataDAO.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.StageData;

/**
 * The Interface StageDataDAO.
 */
public interface StageDataDAO {

	/**
	 * Saves the Object provided.
	 * 
	 * @param stagedata
	 *            the stagedata
	 */
	public void save(StageData stagedata);

	/**
	 * Updates the object provided.
	 * 
	 * @param stagedata
	 *            the stagedata
	 */
	public void update(StageData stagedata);

	/**
	 * Deletes the object with the ID.
	 * 
	 * @param id
	 *            the id
	 */
	public void delete(Long id);

	/**
	 * Gets all StageData.
	 * 
	 * @return all StageData
	 */
	public List<StageData> getAll();
}
