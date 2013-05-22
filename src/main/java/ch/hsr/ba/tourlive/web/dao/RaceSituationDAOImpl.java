/**
 * RaceSituationDAOImpl.java
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

import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.rider.RaceSituation;

/**
 * The Class RaceSituationDAOImpl.
 */
@Repository
public class RaceSituationDAOImpl implements RaceSituationDAO {

	/** The session factory. */
	@Autowired
	SessionFactory sessionFactory;
	private final static Logger LOG = LoggerFactory.getLogger(RaceSituationDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.RaceSituationDAO#save(ch.hsr.ba.tourlive.web
	 * .model.rider.RaceSituation)
	 */
	public void save(RaceSituation raceSituation) {
		sessionFactory.getCurrentSession().save(raceSituation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.RaceSituationDAO#update(ch.hsr.ba.tourlive
	 * .web.model.rider.RaceSituation)
	 */
	public void update(RaceSituation raceSituation) {
		sessionFactory.getCurrentSession().update(raceSituation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.RaceSituationDAO#delete(java.lang.Long)
	 */
	public void delete(Long id) {
		sessionFactory.getCurrentSession().delete(
				sessionFactory.getCurrentSession().get(RaceSituation.class, id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.RaceSituationDAO#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<RaceSituation> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(RaceSituation.class).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.RaceSituationDAO#getRaceSituationById(java
	 * .lang.Long)
	 */
	@Override
	public RaceSituation getRaceSituationById(Long id) {
		return (RaceSituation) sessionFactory.getCurrentSession().get(RaceSituation.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.RaceSituationDAO#getAllByStage(ch.hsr.ba.tourlive
	 * .web.model.Stage)
	 */
	@SuppressWarnings("unchecked")
	public List<RaceSituation> getAllByStage(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(RaceSituation.class);
		crit.add(Restrictions.eq("stage", stage));
		return (List<RaceSituation>) crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.RaceSituationDAO#getLatestByStage(ch.hsr.ba
	 * .tourlive.web.model.Stage)
	 */
	public RaceSituation getLatestByStage(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(RaceSituation.class);
		crit.add(Restrictions.eq("stage", stage));
		crit.addOrder(Order.desc("timestamp"));
		try {
			return (RaceSituation) crit.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			LOG.info("no RaceSituation found");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.RaceSituationDAO#getLatestByStage(ch.hsr.ba
	 * .tourlive.web.model.Stage, java.lang.Long)
	 */
	public RaceSituation getLatestByStage(Stage stage, Long limit) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(RaceSituation.class);
		crit.add(Restrictions.eq("stage", stage));
		crit.add(Restrictions.le("timestamp", limit));
		crit.addOrder(Order.desc("timestamp"));
		try {
			return (RaceSituation) crit.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			LOG.info("no RaceSituation found");
		}
		return null;
	}
}
