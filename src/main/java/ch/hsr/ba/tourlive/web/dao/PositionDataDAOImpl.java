/**
 * PositionDataDAOImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.web.model.PositionData;

/**
 * The Class PositionDataDAOImpl.
 */
@Repository
public class PositionDataDAOImpl implements PositionDataDAO {

	/** The session factory. */
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.PositionDataDAO#save(ch.hsr.ba.tourlive.web
	 * .model.PositionData)
	 */
	public Long save(PositionData position) {
		return (Long) sessionFactory.getCurrentSession().save(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.PositionDataDAO#update(ch.hsr.ba.tourlive.
	 * web.model.PositionData)
	 */
	public void update(PositionData position) {
		sessionFactory.getCurrentSession().update(position);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.PositionDataDAO#delete(java.lang.Long)
	 */
	public void delete(Long id) {
		PositionData pos = (PositionData) sessionFactory.getCurrentSession().load(
				PositionData.class, id);
		if (null != pos) {
			sessionFactory.getCurrentSession().delete(pos);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.PositionDataDAO#getAll()
	 */
	@SuppressWarnings(value = "unchecked")
	public List<PositionData> getAll() {
		return (List<PositionData>) sessionFactory.getCurrentSession()
				.createCriteria(PositionData.class, "Position")
				.addOrder(Order.desc("Position.positionid")).list();
	}
}
