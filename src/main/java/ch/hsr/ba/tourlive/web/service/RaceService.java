/**
 * RaceService.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Race;

/**
 * The Interface RaceService.
 */
public interface RaceService {

	/**
	 * Saves the Object provided.
	 * 
	 * @param race
	 *            the race
	 */
	public Long save(Race race);

	/**
	 * Updates the object provided.
	 * 
	 * @param race
	 *            the race
	 */
	public void update(Race race);

	/**
	 * Deletes the object with the ID.
	 * 
	 * @param id
	 *            the id
	 */
	public void delete(Long id);

	/**
	 * Gets all Races.
	 * 
	 * @return all Races
	 */
	public List<Race> getAll();

	/**
	 * Gets all visible.
	 * 
	 * @return all visible Races
	 */
	public List<Race> getAllVisible();

	/**
	 * Gets the race with the id.
	 * 
	 * @param id
	 *            the id
	 * @return the race with id
	 */
	public Race getRaceById(Long id);

	/**
	 * Gets the race by slug.
	 * 
	 * @param slug
	 *            the slug
	 * @return the race by slug
	 */
	public Race getRaceBySlug(String slug);
}
