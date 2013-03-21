package ch.hsr.ba.tourlive.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.model.Device;

@Repository
public class DeviceDAOImpl implements DeviceDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void save(Device device) {
		sessionFactory.getCurrentSession().save(device);
	}

	public void update(Device device) {
		sessionFactory.getCurrentSession().update(device);

	}

	public void delete(Long id) {
		Device device = (Device) sessionFactory.getCurrentSession().load(
				Device.class, id);
		if (null != device) {
			sessionFactory.getCurrentSession().delete(device);
		}
	}

	public List<Device> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Device.class)
				.list();
	}
}
