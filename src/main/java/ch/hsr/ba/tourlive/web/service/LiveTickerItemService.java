/**
 * LiveTickerItemService.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.LiveTickerItem;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Interface LiveTickerItemService.
 */
public interface LiveTickerItemService {

	/**
	 * Saves the Object provided.
	 * 
	 * @param lti
	 *            the LiveTickerItem to be saved
	 */
	public void save(LiveTickerItem lti);

	/**
	 * Updates the object provided.
	 * 
	 * @param lti
	 *            the LiveTickerItem to be updated
	 */
	public void update(LiveTickerItem lti);

	/**
	 * Deletes the object with the ID.
	 * 
	 * @param id
	 *            the LiveTickerItemID to be deleted
	 */
	public void delete(Long id);

	/**
	 * Get one Object by its Id
	 * 
	 * @param the
	 *            id of the object
	 * @return lti returns one Item with ID
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
	 *            the limit as number
	 * @return all by stage limited to
	 */
	public List<LiveTickerItem> getAllByStageLimitedTo(Stage stage, int limit);

	/**
	 * Gets all by stage limited to.
	 * 
	 * @param stage
	 *            the stage
	 * @param limit
	 *            the limit
	 * @param untilTime
	 *            the until time
	 * @return all by stage limited to time
	 */
	public List<LiveTickerItem> getAllByStageLimitedTo(Stage stage, int limit, Long untilTime);

}
