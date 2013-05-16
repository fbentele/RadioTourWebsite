package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.RaceSituationDAO;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.rider.RaceSituation;

@Service
public class RaceSituationServiceImpl implements RaceSituationService {
	@Autowired
	RaceSituationDAO raceSituationDao;

	@Transactional
	public void save(RaceSituation raceSituation) {
		raceSituationDao.save(raceSituation);
	}

	@Transactional
	public void update(RaceSituation raceSituation) {
		raceSituationDao.update(raceSituation);
	}

	@Transactional
	public void delete(Long id) {
		raceSituationDao.delete(id);
	}

	@Transactional
	public List<RaceSituation> getAll() {
		return raceSituationDao.getAll();
	}

	@Transactional
	public RaceSituation getRaceSituationById(Long id) {
		return raceSituationDao.getRaceSituationById(id);
	}

	@Transactional
	public List<RaceSituation> getRaceSituationByStage(Stage stage) {
		return raceSituationDao.getAllByStage(stage);
	}

	@Transactional
	public RaceSituation getLatestByStage(Stage stage) {
		return raceSituationDao.getLatestByStage(stage);
	}

	@Transactional
	public RaceSituation getLatestByStage(Stage stage, Long limit) {
		return raceSituationDao.getLatestByStage(stage, limit);
	}

}
