package ch.hsr.ba.tourlive.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.model.NetData;

@Repository
public class NetDataDAOImpl implements NetDataDAO {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(NetData netdata) {
		sessionFactory.getCurrentSession().save(netdata);
	}

	@Override
	public void update(NetData netdata) {
		sessionFactory.getCurrentSession().update(netdata);

	}

	@Override
	public void delete(Long id) {
		NetData netdata = (NetData) sessionFactory.getCurrentSession().load(NetData.class, id);
		if (null != netdata) {
			sessionFactory.getCurrentSession().delete(netdata);
		}
	}

	@SuppressWarnings("unchecked")
	public List<NetData> getAll() {
		return (List<NetData>) sessionFactory.getCurrentSession().createCriteria(NetData.class)
				.list();
	}
}
