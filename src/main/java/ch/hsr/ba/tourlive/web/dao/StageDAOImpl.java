/**
 * StageDAOImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Race;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Class StageDAOImpl.
 */
@Repository
public class StageDAOImpl implements StageDAO {

	/** The session factory. */
	@Autowired
	SessionFactory sessionFactory;
	private final static Logger LOG = LoggerFactory.getLogger(StageDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.StageDAO#save(ch.hsr.ba.tourlive.web.model
	 * .Stage)
	 */
	public void save(Stage stage) {
		sessionFactory.getCurrentSession().saveOrUpdate(stage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.StageDAO#update(ch.hsr.ba.tourlive.web.model
	 * .Stage)
	 */
	public void update(Stage stage) {
		sessionFactory.getCurrentSession().update(stage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.StageDAO#delete(java.lang.Long)
	 */
	public void delete(Long id) {
		Stage st = (Stage) sessionFactory.getCurrentSession().load(Stage.class, id);
		if (null != st) {
			sessionFactory.getCurrentSession().delete(st);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.StageDAO#getAllStages()
	 */
	@SuppressWarnings("unchecked")
	public List<Stage> getAllStages() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Stage.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.StageDAO#getAllVisibleStages()
	 */
	@SuppressWarnings("unchecked")
	public List<Stage> getAllVisibleStages() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Stage.class);
		crit.add(Restrictions.eq("visible", true));
		return (List<Stage>) crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.StageDAO#getAllVisibleByRace(ch.hsr.ba.tourlive
	 * .web.model.Race)
	 */
	@SuppressWarnings("unchecked")
	public List<Stage> getAllVisibleByRace(Race race) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Stage.class);
		crit.add(Restrictions.eq("race", race));
		crit.add(Restrictions.eq("visible", true));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Stage>) crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.StageDAO#getStageById(java.lang.Long)
	 */
	public Stage getStageById(Long id) {
		return (Stage) sessionFactory.getCurrentSession().get(Stage.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.StageDAO#getAllByRace(ch.hsr.ba.tourlive.web
	 * .model.Race)
	 */
	@SuppressWarnings("unchecked")
	public List<Stage> getAllByRace(Race race) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Stage.class);
		crit.add(Restrictions.eq("race", race));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Stage>) crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.StageDAO#getStageBySlug(java.lang.String)
	 */
	public Stage getStageBySlug(String slug) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Stage.class);
		try {
			return (Stage) crit.add(Restrictions.eq("stageSlug", slug)).list().get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.StageDAO#getMostRecentStageForDevice(ch.hsr
	 * .ba.tourlive.web.model.Device)
	 */
	public Stage getMostRecentStageForDevice(Device device) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Stage.class);
		crit.createAlias("devices", "d").add(Restrictions.eq("d.deviceId", device.getDeviceId()));
		crit.addOrder(Order.desc("starttime"));
		try {
			return (Stage) crit.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			LOG.info("no Stage found");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.StageDAO#getTotalRaceDistance(ch.hsr.ba.tourlive
	 * .web.model.Race, java.lang.Boolean)
	 */
	@SuppressWarnings("unchecked")
	public Float getTotalRaceDistance(Race race, Boolean completed) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Stage.class);
		crit.add(Restrictions.eq("race", race));
		if (completed)
			crit.add(Restrictions.eq("completed", true));
		float f = 0;
		List<Stage> list = crit.list();
		for (Stage stage : list) {
			f += stage.getDistance();
		}
		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.StageDAO#getAllStagesForDevice(ch.hsr.ba.tourlive
	 * .web.model.Device)
	 */
	@SuppressWarnings("unchecked")
	public List<Stage> getAllStagesForDevice(Device device) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Stage.class);
		crit.createAlias("devices", "d").add(Restrictions.eq("d.deviceId", device.getDeviceId()));
		crit.addOrder(Order.asc("starttime"));
		return (List<Stage>) crit.list();
	}
}
