/**
 * RaceDAOImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.web.model.Race;

/**
 * The Class RaceDAOImpl.
 */
@Repository
public class RaceDAOImpl implements RaceDAO {

	/** The session factory. */
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.RaceDAO#save(ch.hsr.ba.tourlive.web.model.
	 * Race)
	 */
	public void save(Race race) {
		sessionFactory.getCurrentSession().save(race);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.RaceDAO#update(ch.hsr.ba.tourlive.web.model
	 * .Race)
	 */
	public void update(Race race) {
		sessionFactory.getCurrentSession().update(race);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.RaceDAO#delete(java.lang.Long)
	 */
	public void delete(Long id) {
		Race race = (Race) sessionFactory.getCurrentSession().get(Race.class, id);
		if (race != null) {
			sessionFactory.getCurrentSession().delete(race);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.RaceDAO#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Race> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Race.class).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.RaceDAO#getAllVisible()
	 */
	@SuppressWarnings("unchecked")
	public List<Race> getAllVisible() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Race.class);
		crit.add(Restrictions.eq("visible", true));
		return (List<Race>) crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.RaceDAO#getRaceById(java.lang.Long)
	 */
	public Race getRaceById(Long id) {
		return (Race) sessionFactory.getCurrentSession().get(Race.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.RaceDAO#getRaceBySlug(java.lang.String)
	 */
	public Race getRaceBySlug(String slug) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Race.class);
		crit.add(Restrictions.eq("raceSlug", slug));
		try {
			return (Race) crit.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			// no race available
			return null;
		}
	}
}
