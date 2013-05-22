/**
 * DeviceServiceImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.DeviceDAO;
import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Class DeviceServiceImpl.
 */
@Service
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	private DeviceDAO device;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.DeviceService#save(ch.hsr.ba.tourlive.
	 * web.model.Device)
	 */
	@Transactional
	public void save(Device dev) {
		device.save(dev);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.DeviceService#update(ch.hsr.ba.tourlive
	 * .web.model.Device)
	 */
	@Transactional
	public void update(Device dev) {
		device.update(dev);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.DeviceService#delete(java.lang.String)
	 */
	@Transactional
	public void delete(String id) {
		device.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.DeviceService#getDeviceById(java.lang.
	 * String)
	 */
	@Transactional
	public Device getDeviceById(String deviceId) {
		return device.getDeviceById(deviceId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.DeviceService#getAll()
	 */
	@Transactional
	public List<Device> getAll() {
		return device.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.DeviceService#getAllNotAlreadyAssignedTo
	 * (ch.hsr.ba.tourlive.web.model.Stage)
	 */
	@Transactional
	public List<Device> getAllNotAlreadyAssignedTo(Stage stage) {
		return device.getAllNotAlreadyAssignedTo(stage);
	}
}