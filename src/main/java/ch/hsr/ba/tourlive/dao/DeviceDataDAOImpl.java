package ch.hsr.ba.tourlive.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.model.DeviceData;

@Repository
public class DeviceDataDAOImpl implements DeviceDataDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void save(DeviceData devicedata) {
		sessionFactory.getCurrentSession().save(devicedata);
	}

	public void update(DeviceData devicedata) {
		sessionFactory.getCurrentSession().update(devicedata);
	}

	public void delete(Long id) {
		DeviceData devdat = (DeviceData) sessionFactory.getCurrentSession()
				.load(DeviceData.class, id);
		if (null != devdat) {
			sessionFactory.getCurrentSession().delete(devdat);
		}
	}

	public List<DeviceData> getAll() {
		return sessionFactory.getCurrentSession()
				.createCriteria(DeviceData.class).list();
	}

}
