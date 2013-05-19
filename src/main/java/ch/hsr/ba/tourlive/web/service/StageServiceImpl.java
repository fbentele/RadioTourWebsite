package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.StageDAO;
import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Race;
import ch.hsr.ba.tourlive.web.model.Stage;

@Service
public class StageServiceImpl implements StageService {
	@Autowired
	private StageDAO stagedao;

	@Transactional
	public void save(Stage stage) {
		stagedao.save(stage);
	}

	@Transactional
	public void update(Stage stage) {
		stagedao.update(stage);
	}

	@Transactional
	public void delete(Long id) {
		stagedao.delete(id);
	}

	@Transactional
	public List<Stage> getAll() {
		return stagedao.getAllStages();
	}

	@Transactional
	public List<Stage> getAllVisibleStages() {
		return stagedao.getAllVisibleStages();
	}

	@Transactional
	public List<Stage> getAllVisibleByRace(Race race) {
		return stagedao.getAllVisibleByRace(race);
	}

	@Transactional
	public Stage getStageById(Long id) {
		return stagedao.getStageById(id);
	}

	@Transactional
	public List<Stage> getAllByRace(Race race) {
		return stagedao.getAllByRace(race);
	}

	@Transactional
	public Stage getStageBySlug(String slug) {
		return stagedao.getStageBySlug(slug);
	}

	@Transactional
	public Stage getMostRecentStageForDevice(Device device) {
		return stagedao.getMostRecentStageForDevice(device);
	}

	@Transactional
	public List<Stage> getAllStagesForDevice(Device device) {
		return stagedao.getAllStagesForDevice(device);
	}

	@Transactional
	public Float getTotalRaceDistance(Race race, Boolean completed) {
		return stagedao.getTotalRaceDistance(race, completed);
	}
}