/**
 * StageDataDAOImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.web.model.StageData;

/**
 * The Class StageDataDAOImpl.
 */
@Repository
public class StageDataDAOImpl implements StageDataDAO {

	/** The session factory. */
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.StageDataDAO#save(ch.hsr.ba.tourlive.web.model
	 * .StageData)
	 */
	@Override
	public Long save(StageData stagedata) {
		return (Long) sessionFactory.getCurrentSession().save(stagedata);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.StageDataDAO#update(ch.hsr.ba.tourlive.web
	 * .model.StageData)
	 */
	@Override
	public void update(StageData stagedata) {
		sessionFactory.getCurrentSession().update(stagedata);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.StageDataDAO#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		StageData stagedata = (StageData) sessionFactory.getCurrentSession().load(StageData.class,
				id);
		if (null != stagedata) {
			sessionFactory.getCurrentSession().delete(stagedata);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.StageDataDAO#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<StageData> getAll() {
		return (List<StageData>) sessionFactory.getCurrentSession().createCriteria(StageData.class)
				.list();
	}

}
