package ch.hsr.ba.tourlive.dao;

import java.util.List;

import ch.hsr.ba.tourlive.model.Race;
import ch.hsr.ba.tourlive.model.Stage;

public interface StageDAO {
	public void save(Stage stage);

	public void update(Stage stage);

	public void delete(Long id);

	public List<Stage> getAllStages();

	public Stage getStageById(Long id);

	public List<Stage> getAllByRace(Race race);

	public Stage getStageBySlug(String slug);
}
