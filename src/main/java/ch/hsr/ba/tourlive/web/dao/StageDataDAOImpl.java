package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.web.model.StageData;

@Repository
public class StageDataDAOImpl implements StageDataDAO {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(StageData stagedata) {
		sessionFactory.getCurrentSession().save(stagedata);
	}

	@Override
	public void update(StageData stagedata) {
		sessionFactory.getCurrentSession().update(stagedata);
	}

	@Override
	public void delete(Long id) {
		StageData stagedata = (StageData) sessionFactory.getCurrentSession().load(StageData.class,
				id);
		if (null != stagedata) {
			sessionFactory.getCurrentSession().delete(stagedata);
		}
	}

	@SuppressWarnings("unchecked")
	public List<StageData> getAll() {
		return (List<StageData>) sessionFactory.getCurrentSession().createCriteria(StageData.class)
				.list();
	}

}
