package ch.hsr.ba.tourlive.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.model.Race;

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
		Race race = (Race) sessionFactory.getCurrentSession().load(Race.class,
				id);
		if (race != null) {
			sessionFactory.getCurrentSession().delete(race);
		}
	}

	public List<Race> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Race.class)
				.list();
	}

	public Race getActualTds() {
		// TODO: return latest Tour de Suiss Race Object or null of none
		// available
		return null;
	}

}