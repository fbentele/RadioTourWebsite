package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.dao.StageDAO;
import ch.hsr.ba.tourlive.model.Race;
import ch.hsr.ba.tourlive.model.Stage;

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
	public List<Stage> getAllByRace(Race race) {
		return stagedao.getAllByRace(race);
	}
}