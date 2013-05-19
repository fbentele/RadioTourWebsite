package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Stage;

@Repository
public class DeviceDAOImpl implements DeviceDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private final static Logger LOG = LoggerFactory.getLogger(DeviceDAOImpl.class);

	public void save(Device device) {
		sessionFactory.getCurrentSession().save(device);
	}

	public void update(Device device) {
		sessionFactory.getCurrentSession().update(device);
	}

	public void delete(String id) {
		try {
			Device device = (Device) sessionFactory.getCurrentSession().get(Device.class, id);
			if (null != device) {
				sessionFactory.getCurrentSession().delete(device);
			}
		} catch (DataIntegrityViolationException e) {
			LOG.info("could not be deleted becaus relation to other object exists");
		}
	}

	public Device getDeviceById(String deviceId) {
		return (Device) sessionFactory.getCurrentSession().get(Device.class, deviceId);
	}

	@SuppressWarnings("unchecked")
	public List<Device> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Device.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Device> getAllNotAlreadyAssignedTo(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Device.class);
		try {
			for (Device d : stage.getDevices()) {
				crit.add(Restrictions.ne("deviceId", d.getDeviceId()));
			}
		} catch (NullPointerException e) {
			LOG.info("No devices in stage: " + stage.getStageSlug());
		}
		return (List<Device>) crit.list();
	}
}
