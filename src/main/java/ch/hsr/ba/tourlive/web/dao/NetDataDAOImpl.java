/**
 * NetDataDAOImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.web.model.NetData;

/**
 * The Class NetDataDAOImpl.
 */
@Repository
public class NetDataDAOImpl implements NetDataDAO {

	/** The session factory. */
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.NetDataDAO#save(ch.hsr.ba.tourlive.web.model
	 * .NetData)
	 */
	@Override
	public void save(NetData netdata) {
		sessionFactory.getCurrentSession().save(netdata);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.NetDataDAO#update(ch.hsr.ba.tourlive.web.model
	 * .NetData)
	 */
	@Override
	public void update(NetData netdata) {
		sessionFactory.getCurrentSession().update(netdata);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.NetDataDAO#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		NetData netdata = (NetData) sessionFactory.getCurrentSession().load(NetData.class, id);
		if (null != netdata) {
			sessionFactory.getCurrentSession().delete(netdata);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.NetDataDAO#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<NetData> getAll() {
		return (List<NetData>) sessionFactory.getCurrentSession().createCriteria(NetData.class)
				.list();
	}
}
