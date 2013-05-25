/**
 * StageServiceImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.StageDAO;
import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Race;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Class StageServiceImpl.
 */
@Service
public class StageServiceImpl implements StageService {
	@Autowired
	private StageDAO stagedao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.StageService#save(ch.hsr.ba.tourlive.web
	 * .model.Stage)
	 */
	@Transactional
	public void save(Stage stage) {
		stagedao.save(stage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.StageService#update(ch.hsr.ba.tourlive
	 * .web.model.Stage)
	 */
	@Transactional
	public void update(Stage stage) {
		stagedao.update(stage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.StageService#delete(java.lang.Long)
	 */
	@Transactional
	public void delete(Long id) {
		stagedao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.StageService#getAll()
	 */
	@Transactional
	public List<Stage> getAll() {
		return stagedao.getAllStages();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.StageService#getAllVisibleStages()
	 */
	@Transactional
	public List<Stage> getAllVisibleStages() {
		return stagedao.getAllVisibleStages();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.StageService#getAllVisibleByRace(ch.hsr
	 * .ba.tourlive.web.model.Race)
	 */
	@Transactional
	public List<Stage> getAllVisibleByRace(Race race) {
		return stagedao.getAllVisibleByRace(race);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.StageService#getStageById(java.lang.Long)
	 */
	@Transactional
	public Stage getStageById(Long id) {
		return stagedao.getStageById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.StageService#getLatestPublic()
	 */
	@Transactional
	public Stage getLatestVisible() {
		return stagedao.getLatestVisible();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.StageService#getAllOlderThanAYear()
	 */
	@Transactional
	public List<Stage> getAllOlderThanAYear() {
		return stagedao.getAllOlderThanAYear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.StageService#getAllByRace(ch.hsr.ba.tourlive
	 * .web.model.Race)
	 */
	@Transactional
	public List<Stage> getAllByRace(Race race) {
		return stagedao.getAllByRace(race);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.StageService#getStageBySlug(java.lang.
	 * String)
	 */
	@Transactional
	public Stage getStageBySlug(String slug) {
		return stagedao.getStageBySlug(slug);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.StageService#getMostRecentStageForDevice
	 * (ch.hsr.ba.tourlive.web.model.Device)
	 */
	@Transactional
	public Stage getMostRecentStageForDevice(Device device) {
		return stagedao.getMostRecentStageForDevice(device);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.StageService#getAllStagesForDevice(ch.
	 * hsr.ba.tourlive.web.model.Device)
	 */
	@Transactional
	public List<Stage> getAllStagesForDevice(Device device) {
		return stagedao.getAllStagesForDevice(device);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.StageService#getTotalRaceDistance(ch.hsr
	 * .ba.tourlive.web.model.Race, java.lang.Boolean)
	 */
	@Transactional
	public Float getTotalRaceDistance(Race race, Boolean completed) {
		return stagedao.getTotalRaceDistance(race, completed);
	}
}