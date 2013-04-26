package ch.hsr.ba.tourlive.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.model.PositionData;

@Repository
public class PositionDataDAOImpl implements PositionDataDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void save(PositionData position) {
		sessionFactory.getCurrentSession().save(position);
	}

	public void update(PositionData position) {
		sessionFactory.getCurrentSession().update(position);

	}

	public void delete(Long id) {
		PositionData pos = (PositionData) sessionFactory.getCurrentSession().load(
				PositionData.class, id);
		if (null != pos) {
			sessionFactory.getCurrentSession().delete(pos);
		}
	}

	@SuppressWarnings(value = "unchecked")
	public List<PositionData> getAll() {
		return (List<PositionData>) sessionFactory.getCurrentSession()
				.createCriteria(PositionData.class, "Position")
				.addOrder(Order.desc("Position.positionid")).list();
	}
}
