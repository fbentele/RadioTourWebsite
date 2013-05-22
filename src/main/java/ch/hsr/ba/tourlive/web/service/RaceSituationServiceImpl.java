/**
 * RaceSituationServiceImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.RaceSituationDAO;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.rider.RaceSituation;

/**
 * The Class RaceSituationServiceImpl.
 */
@Service
public class RaceSituationServiceImpl implements RaceSituationService {

	/** The race situation dao. */
	@Autowired
	RaceSituationDAO raceSituationDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RaceSituationService#save(ch.hsr.ba.tourlive
	 * .web.model.rider.RaceSituation)
	 */
	@Transactional
	public void save(RaceSituation raceSituation) {
		raceSituationDao.save(raceSituation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RaceSituationService#update(ch.hsr.ba.
	 * tourlive.web.model.rider.RaceSituation)
	 */
	@Transactional
	public void update(RaceSituation raceSituation) {
		raceSituationDao.update(raceSituation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RaceSituationService#delete(java.lang.
	 * Long)
	 */
	@Transactional
	public void delete(Long id) {
		raceSituationDao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.RaceSituationService#getAll()
	 */
	@Transactional
	public List<RaceSituation> getAll() {
		return raceSituationDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RaceSituationService#getRaceSituationById
	 * (java.lang.Long)
	 */
	@Transactional
	public RaceSituation getRaceSituationById(Long id) {
		return raceSituationDao.getRaceSituationById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RaceSituationService#getRaceSituationByStage
	 * (ch.hsr.ba.tourlive.web.model.Stage)
	 */
	@Transactional
	public List<RaceSituation> getRaceSituationByStage(Stage stage) {
		return raceSituationDao.getAllByStage(stage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RaceSituationService#getLatestByStage(
	 * ch.hsr.ba.tourlive.web.model.Stage)
	 */
	@Transactional
	public RaceSituation getLatestByStage(Stage stage) {
		return raceSituationDao.getLatestByStage(stage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RaceSituationService#getLatestByStage(
	 * ch.hsr.ba.tourlive.web.model.Stage, java.lang.Long)
	 */
	@Transactional
	public RaceSituation getLatestByStage(Stage stage, Long limit) {
		return raceSituationDao.getLatestByStage(stage, limit);
	}

}
