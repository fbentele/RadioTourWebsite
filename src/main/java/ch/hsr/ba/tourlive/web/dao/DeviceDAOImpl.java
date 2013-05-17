package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.web.model.Device;

@Repository
public class DeviceDAOImpl implements DeviceDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(Device device) {
		sessionFactory.getCurrentSession().save(device);
	}

	public void update(Device device) {
		sessionFactory.getCurrentSession().update(device);
	}

	public void delete(String id) {
		Device device = (Device) sessionFactory.getCurrentSession().load(Device.class, id);
		if (null != device) {
			sessionFactory.getCurrentSession().delete(device);
		}
	}

	public Device getDeviceById(String deviceId) {
		return (Device) sessionFactory.getCurrentSession().get(Device.class, deviceId);
	}

	@SuppressWarnings("unchecked")
	public List<Device> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Device.class).list();
	}

}