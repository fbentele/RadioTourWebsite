/**
 * NetDataDAO.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.NetData;

/**
 * The Interface NetDataDAO.
 */
public interface NetDataDAO {

	/**
	 * Saves the Object provided.
	 * 
	 * @param netdata
	 *            the netdata
	 */
	public void save(NetData netdata);

	/**
	 * Updates the object provided.
	 * 
	 * @param netdata
	 *            the netdata
	 */
	public void update(NetData netdata);

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
	public List<NetData> getAll();
}
