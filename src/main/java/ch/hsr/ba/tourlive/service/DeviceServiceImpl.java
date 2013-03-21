package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.dao.DeviceDAO;
import ch.hsr.ba.tourlive.model.Device;

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
	public void delete(Long id) {
		device.delete(id);
	}

	@Transactional
	public List<Device> getAll() {
		return device.getAll();
	}
}