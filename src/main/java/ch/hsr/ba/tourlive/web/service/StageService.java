package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Race;
import ch.hsr.ba.tourlive.web.model.Stage;

public interface StageService {
	public void save(Stage stage);

	public void update(Stage stage);

	public void delete(Long id);

	public List<Stage> getAll();

	public Stage getStageById(Long id);

	public List<Stage> getAllByRace(Race race);

	public List<Stage> getAllVisibleStages();

	public List<Stage> getAllVisibleByRace(Race race);

	public Stage getStageBySlug(String slug);

	public Stage getMostRecentStageForDevice(Device device);

	public Float getTotalRaceDistance(Race race, Boolean completed);
}
