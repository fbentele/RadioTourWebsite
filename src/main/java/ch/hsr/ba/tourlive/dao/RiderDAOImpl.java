package ch.hsr.ba.tourlive.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.model.rider.Rider;

@Repository
public class RiderDAOImpl implements RiderDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void save(Rider rider) {
		sessionFactory.getCurrentSession().save(rider);
	}

	public void update(Rider rider) {
		sessionFactory.getCurrentSession().update(rider);
	}

	public void delete(Long id) {
		Rider rider = (Rider) sessionFactory.getCurrentSession().load(Rider.class, id);
		if (rider != null) {
			sessionFactory.getCurrentSession().delete(rider);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Rider> getAll() {
		return (List<Rider>) sessionFactory.getCurrentSession().createCriteria(Rider.class).list();
	}

	public Rider getRiderById(Long id) {
		return (Rider) sessionFactory.getCurrentSession().load(Rider.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Rider> getAllbyStage(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Rider.class);
		crit.add(Restrictions.eq("stage", stage));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return (List<Rider>) crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Rider> getTopTenByStage(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Rider.class);
		crit.add(Restrictions.eq("stage", stage));
		crit.addOrder(Order.asc("startNr"));
		crit.setFirstResult(0);
		crit.setMaxResults(10);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Rider>) crit.list();
	}

}
