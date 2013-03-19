package ch.hsr.ba.tourlive.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
