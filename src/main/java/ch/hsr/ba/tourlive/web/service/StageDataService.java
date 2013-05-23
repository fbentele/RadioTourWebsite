package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.StageData;

/**
 * The Interface StageDataService.
 */
public interface StageDataService {

	/**
	 * Saves the Object provided.
	 * 
	 * @param stagedata
	 *            the stagedata
	 */
	public Long save(StageData stagedata);

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