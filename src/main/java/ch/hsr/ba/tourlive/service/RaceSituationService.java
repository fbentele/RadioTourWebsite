package ch.hsr.ba.tourlive.service;

import java.util.List;

import ch.hsr.ba.tourlive.model.Race;
import ch.hsr.ba.tourlive.model.RaceSituation;
import ch.hsr.ba.tourlive.model.Stage;

public interface RaceSituationService {
	public void save(RaceSituation raceSituation);

	public void update(RaceSituation raceSituation);

	public void delete(Long id);

	public List<RaceSituation> getAll();

	public Race getRaceSituationById(Long id);

	public List<RaceSituation> getRaceSituationByStage(Stage stage);

}
