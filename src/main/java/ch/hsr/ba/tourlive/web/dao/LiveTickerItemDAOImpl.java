/**
 * LiveTickerItemDAOImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.web.model.LiveTickerItem;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Class LiveTickerItemDAOImpl.
 */
@Repository
public class LiveTickerItemDAOImpl implements LiveTickerItemDAO {
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.LiveTickerItemDAO#save(ch.hsr.ba.tourlive.
	 * web.model.LiveTickerItem)
	 */
	public Long save(LiveTickerItem lti) {
		return (Long) sessionFactory.getCurrentSession().save(lti);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.LiveTickerItemDAO#update(ch.hsr.ba.tourlive
	 * .web.model.LiveTickerItem)
	 */
	public void update(LiveTickerItem lti) {
		sessionFactory.getCurrentSession().update(lti);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.LiveTickerItemDAO#delete(java.lang.Long)
	 */
	public void delete(Long id) {
		LiveTickerItem lti = (LiveTickerItem) sessionFactory.getCurrentSession().load(
				LiveTickerItem.class, id);
		if (lti != null)
			sessionFactory.getCurrentSession().delete(lti);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.LiveTickerItemDAO#getById(java.lang.Long)
	 */
	public LiveTickerItem getById(Long id) {
		return (LiveTickerItem) sessionFactory.getCurrentSession().get(LiveTickerItem.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.LiveTickerItemDAO#getAllByStage(ch.hsr.ba.
	 * tourlive.web.model.Stage)
	 */
	@SuppressWarnings("unchecked")
	public List<LiveTickerItem> getAllByStage(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(LiveTickerItem.class);
		crit.add(Restrictions.eq("stage", stage));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<LiveTickerItem>) crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.LiveTickerItemDAO#getAllByStageLimitedTo(ch
	 * .hsr.ba.tourlive.web.model.Stage, int, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<LiveTickerItem> getAllByStageLimitedTo(Stage stage, Long timelimit) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(LiveTickerItem.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.add(Restrictions.eq("stage", stage));
		crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(), timelimit));
		crit.addOrder(Order.desc("timestamp"));
		return (List<LiveTickerItem>) crit.list();
	}
}
