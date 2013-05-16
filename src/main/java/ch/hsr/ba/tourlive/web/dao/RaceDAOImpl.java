package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.web.model.Race;

@Repository
public class RaceDAOImpl implements RaceDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void save(Race race) {
		sessionFactory.getCurrentSession().save(race);
	}

	public void update(Race race) {
		sessionFactory.getCurrentSession().update(race);
	}

	public void delete(Long id) {
		Race race = (Race) sessionFactory.getCurrentSession().get(Race.class, id);
		if (race != null) {
			sessionFactory.getCurrentSession().delete(race);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Race> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Race.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Race> getAllVisible() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Race.class);
		crit.add(Restrictions.eq("visible", true));
		return (List<Race>) crit.list();
	}

	public Race getRaceById(Long id) {
		return (Race) sessionFactory.getCurrentSession().get(Race.class, id);
	}

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
