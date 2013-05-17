package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.rider.RaceSituation;

public interface RaceSituationDAO {
	public void save(RaceSituation raceSituation);

	public void update(RaceSituation raceSituation);

	public void delete(Long id);

	public List<RaceSituation> getAll();

	public RaceSituation getRaceSituationById(Long id);

	public List<RaceSituation> getAllByStage(Stage stage);

	public RaceSituation getLatestByStage(Stage stage);

	public RaceSituation getLatestByStage(Stage stage, Long limit);

}