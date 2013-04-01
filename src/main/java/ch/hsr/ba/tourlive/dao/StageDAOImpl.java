package ch.hsr.ba.tourlive.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.model.Race;
import ch.hsr.ba.tourlive.model.Stage;

@Repository
public class StageDAOImpl implements StageDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void save(Stage stage) {
		sessionFactory.getCurrentSession().save(stage);
	}

	public void update(Stage stage) {
		sessionFactory.getCurrentSession().update(stage);
	}

	public void delete(Long id) {
		Stage st = (Stage) sessionFactory.getCurrentSession().load(Stage.class,
				id);
		if (null != st) {
			sessionFactory.getCurrentSession().delete(st);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Stage> getAllStages() {
		return sessionFactory.getCurrentSession().createCriteria(Stage.class)
				.list();
	}

	public Stage getStageById(Long id) {
		return (Stage) sessionFactory.getCurrentSession().get(Stage.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stage> getAllByRace(Race race) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Stage.class);
		crit.add(Restrictions.eq("race", race));
		return crit.list();

		// if (race != null) {
		// return sessionFactory.getCurrentSession()
		// .createQuery("from Stage as stage where stage.raceId=?")
		// .setLong(0, race.getRaceId()).list();
		// } else {
		// return null;
		// }
	}

	public List<Stage> doit(Race race) {

		return null;
	}
}
