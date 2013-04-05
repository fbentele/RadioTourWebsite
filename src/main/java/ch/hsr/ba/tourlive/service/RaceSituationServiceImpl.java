package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ch.hsr.ba.tourlive.dao.RaceSituationDAO;
import ch.hsr.ba.tourlive.model.RaceSituation;
import ch.hsr.ba.tourlive.model.Stage;

public class RaceSituationServiceImpl implements RaceSituationService {
	@Autowired
	RaceSituationDAO raceSituationDao;

	@Override
	public void save(RaceSituation raceSituation) {
		raceSituationDao.save(raceSituation);
	}

	@Override
	public void update(RaceSituation raceSituation) {
		raceSituationDao.update(raceSituation);
	}

	@Override
	public void delete(Long id) {
		raceSituationDao.delete(id);
	}

	@Override
	public List<RaceSituation> getAll() {
		return raceSituationDao.getAll();
	}

	@Override
	public RaceSituation getRaceSituationById(Long id) {
		return raceSituationDao.getRaceSituationById(id);
	}

	@Override
	public List<RaceSituation> getRaceSituationByStage(Stage stage) {
		return raceSituationDao.getAllByStage(stage);
	}

}
