/**
 * LiveTickerItemDAO.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.LiveTickerItem;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Interface LiveTickerItemDAO.
 */
public interface LiveTickerItemDAO {

	/**
	 * Saves the Object provided.
	 * 
	 * @param lti
	 *            the LiveTickerItem
	 */
	public void save(LiveTickerItem lti);

	/**
	 * Updates the object provided.
	 * 
	 * @param lti
	 *            the LiveTickerItem
	 */
	public void update(LiveTickerItem lti);

	/**
	 * Deletes the object with the ID.
	 * 
	 * @param id
	 *            the LiveTickerItem id
	 */
	public void delete(Long id);

	/**
	 * 
	 * @param id
	 * @return lti, returns the Object with ID
	 */
	public LiveTickerItem getById(Long id);

	/**
	 * Gets all by stage.
	 * 
	 * @param stage
	 *            the stage
	 * @return all by stage
	 */
	public List<LiveTickerItem> getAllByStage(Stage stage);

	/**
	 * Gets all by stage limited to.
	 * 
	 * @param stage
	 *            the stage
	 * @param limit
	 *            the limit
	 * @param timelimit
	 *            the timelimit
	 * @return all by stage limited to
	 */
	public List<LiveTickerItem> getAllByStageLimitedTo(Stage stage, Long timelimit);
}
