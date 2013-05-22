/**
 * NetDataService.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.NetData;

/**
 * The Interface NetDataService.
 */
public interface NetDataService {

	/**
	 * Saves the object provided.
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
	 * Gets all NetData objects.
	 * 
	 * @return all NetData Objects
	 */
	public List<NetData> getAll();
}
