package ch.hsr.ba.tourlive.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ch.hsr.ba.tourlive.model.RaceSituation;
import ch.hsr.ba.tourlive.model.Stage;

public class RaceSituationDAOImpl implements RaceSituationDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void save(RaceSituation raceSituation) {
		sessionFactory.getCurrentSession().save(raceSituation);
	}

	public void update(RaceSituation raceSituation) {
		sessionFactory.getCurrentSession().update(raceSituation);
	}

	public void delete(Long id) {
		sessionFactory.getCurrentSession()
				.delete(sessionFactory.getCurrentSession().get(
						RaceSituation.class, id));
	}

	@SuppressWarnings("unchecked")
	public List<RaceSituation> getAll() {
		return sessionFactory.getCurrentSession()
				.createCriteria(RaceSituation.class).list();
	}

	@Override
	public RaceSituation getRaceSituationById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RaceSituation> getAllByStage(Stage stage) {
		// TODO Auto-generated method stub
		return null;
	}

}
