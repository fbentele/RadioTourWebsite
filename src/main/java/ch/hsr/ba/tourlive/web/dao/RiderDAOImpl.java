/**
 * RiderDAOImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.rider.Rider;

/**
 * The Class RiderDAOImpl.
 */
@Repository
public class RiderDAOImpl implements RiderDAO {

	/** The session factory. */
	@Autowired
	SessionFactory sessionFactory;
	private final static Logger LOG = LoggerFactory.getLogger(RiderDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.RiderDAO#save(ch.hsr.ba.tourlive.web.model
	 * .rider.Rider)
	 */
	public void save(Rider rider) {
		sessionFactory.getCurrentSession().save(rider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.RiderDAO#update(ch.hsr.ba.tourlive.web.model
	 * .rider.Rider)
	 */
	public void update(Rider rider) {
		sessionFactory.getCurrentSession().update(rider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.RiderDAO#delete(java.lang.Long)
	 */
	public void delete(Long id) {
		Rider rider = (Rider) sessionFactory.getCurrentSession().load(Rider.class, id);
		if (rider != null) {
			sessionFactory.getCurrentSession().delete(rider);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.RiderDAO#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Rider> getAll() {
		return (List<Rider>) sessionFactory.getCurrentSession().createCriteria(Rider.class).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.RiderDAO#getRiderById(java.lang.Long)
	 */
	public Rider getRiderById(Long id) {
		return (Rider) sessionFactory.getCurrentSession().load(Rider.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.RiderDAO#getAllByStage(ch.hsr.ba.tourlive.
	 * web.model.Stage)
	 */
	@SuppressWarnings("unchecked")
	public List<Rider> getAllByStage(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Rider.class);
		crit.add(Restrictions.eq("stage", stage));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Rider>) crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.RiderDAO#getRiderByStartNrForStage(ch.hsr.
	 * ba.tourlive.web.model.Stage, java.lang.Integer)
	 */
	public Rider getRiderByStartNrForStage(Stage stage, Integer startNr) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Rider.class);
		crit.add(Restrictions.eq("stage", stage));
		crit.add(Restrictions.eq("startNr", startNr));
		try {
			return (Rider) crit.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			LOG.info("no Rider found");
		}
		return null;
	}
}
