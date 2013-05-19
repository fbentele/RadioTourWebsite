package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.DeviceDAO;
import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Stage;

@Service
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	private DeviceDAO device;

	@Transactional
	public void save(Device dev) {
		device.save(dev);
	}

	@Transactional
	public void update(Device dev) {
		device.update(dev);
	}

	@Transactional
	public void delete(String id) {
		device.delete(id);
	}

	@Transactional
	public Device getDeviceById(String deviceId) {
		return device.getDeviceById(deviceId);
	}

	@Transactional
	public List<Device> getAll() {
		return device.getAll();
	}

	@Transactional
	public List<Device> getAllNotAlreadyAssignedTo(Stage stage) {
		return device.getAllNotAlreadyAssignedTo(stage);
	}
}