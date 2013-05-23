package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.StageDataDAO;
import ch.hsr.ba.tourlive.web.model.StageData;

@Service
public class StageDataServiceImpl implements StageDataService {
	@Autowired
	StageDataDAO stDao;

	@Transactional
	public Long save(StageData stagedata) {
		return stDao.save(stagedata);
	}

	@Transactional
	public void update(StageData stagedata) {
		stDao.update(stagedata);
	}

	@Transactional
	public void delete(Long id) {
		stDao.delete(id);
	}

	@Transactional
	public List<StageData> getAll() {
		return stDao.getAll();
	}
}
