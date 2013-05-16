package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.web.model.MarchTableItem;
import ch.hsr.ba.tourlive.web.model.Stage;

@Repository
public class MarchTableItemDAOImpl implements MarchTableItemDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void save(MarchTableItem mti) {
		sessionFactory.getCurrentSession().save(mti);
	}

	public void update(MarchTableItem mti) {
		sessionFactory.getCurrentSession().update(mti);
	}

	public void delete(Long id) {
		MarchTableItem mti = (MarchTableItem) sessionFactory.getCurrentSession().load(
				MarchTableItem.class, id);
		if (mti != null) {
			sessionFactory.getCurrentSession().delete(mti);
		}
	}

	@SuppressWarnings("unchecked")
	public List<MarchTableItem> getAllByStage(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(MarchTableItem.class);
		crit.add(Restrictions.eq("stage", stage));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<MarchTableItem>) crit.list();
	}
}
