/**
 * MarchTableItemDAOImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.web.model.MarchTableItem;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Class MarchTableItemDAOImpl.
 */
@Repository
public class MarchTableItemDAOImpl implements MarchTableItemDAO {

	/** The session factory. */
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.MarchTableItemDAO#save(ch.hsr.ba.tourlive.
	 * web.model.MarchTableItem)
	 */
	public void save(MarchTableItem mti) {
		sessionFactory.getCurrentSession().save(mti);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.MarchTableItemDAO#update(ch.hsr.ba.tourlive
	 * .web.model.MarchTableItem)
	 */
	public void update(MarchTableItem mti) {
		sessionFactory.getCurrentSession().update(mti);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.MarchTableItemDAO#delete(java.lang.Long)
	 */
	public void delete(Long id) {
		MarchTableItem mti = (MarchTableItem) sessionFactory.getCurrentSession().load(
				MarchTableItem.class, id);
		if (mti != null) {
			sessionFactory.getCurrentSession().delete(mti);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.MarchTableItemDAO#getAllByStage(ch.hsr.ba.
	 * tourlive.web.model.Stage)
	 */
	@SuppressWarnings("unchecked")
	public List<MarchTableItem> getAllByStage(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(MarchTableItem.class);
		crit.add(Restrictions.eq("stage", stage));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<MarchTableItem>) crit.list();
	}
}
