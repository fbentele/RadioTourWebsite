package ch.hsr.ba.tourlive.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.model.rider.RaceSituation;

@Repository
public class RaceSituationDAOImpl implements RaceSituationDAO {
	@Autowired
	SessionFactory sessionFactory;
	private final static Logger LOG = LoggerFactory.getLogger(RaceSituationDAOImpl.class);

	public void save(RaceSituation raceSituation) {
		sessionFactory.getCurrentSession().save(raceSituation);
	}

	public void update(RaceSituation raceSituation) {
		sessionFactory.getCurrentSession().update(raceSituation);
	}

	public void delete(Long id) {
		sessionFactory.getCurrentSession().delete(
				sessionFactory.getCurrentSession().get(RaceSituation.class, id));
	}

	@SuppressWarnings("unchecked")
	public List<RaceSituation> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(RaceSituation.class).list();
	}

	@Override
	public RaceSituation getRaceSituationById(Long id) {
		return (RaceSituation) sessionFactory.getCurrentSession().get(RaceSituation.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<RaceSituation> getAllByStage(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(RaceSituation.class);
		crit.add(Restrictions.eq("stage", stage));
		return (List<RaceSituation>) crit.list();
	}

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
