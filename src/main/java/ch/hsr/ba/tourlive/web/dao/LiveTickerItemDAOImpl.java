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

@Repository
public class LiveTickerItemDAOImpl implements LiveTickerItemDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(LiveTickerItem lti) {
		sessionFactory.getCurrentSession().save(lti);
	}

	public void update(LiveTickerItem lti) {
		sessionFactory.getCurrentSession().update(lti);
	}

	public void delete(Long id) {
		LiveTickerItem lti = (LiveTickerItem) sessionFactory.getCurrentSession().load(
				LiveTickerItem.class, id);
		if (lti != null)
			sessionFactory.getCurrentSession().delete(lti);
	}

	@SuppressWarnings("unchecked")
	public List<LiveTickerItem> getAllByStage(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(LiveTickerItem.class);
		crit.add(Restrictions.eq("stage", stage));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<LiveTickerItem>) crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<LiveTickerItem> getAllByStageLimitedTo(Stage stage, int limit) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(LiveTickerItem.class);
		crit.add(Restrictions.eq("stage", stage));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.desc("timestamp"));
		crit.setMaxResults(limit);
		return (List<LiveTickerItem>) crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<LiveTickerItem> getAllByStageLimitedTo(Stage stage, int limit, Long timelimit) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(LiveTickerItem.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.add(Restrictions.eq("stage", stage));
		crit.add(Restrictions.le("timestamp", timelimit));
		crit.addOrder(Order.desc("timestamp"));
		crit.setMaxResults(limit);
		return (List<LiveTickerItem>) crit.list();
	}

}
