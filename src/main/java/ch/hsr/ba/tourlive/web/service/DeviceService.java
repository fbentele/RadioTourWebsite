package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Device;

public interface DeviceService {
	public void save(Device device);

	public void update(Device device);

	public void delete(String id);

	public List<Device> getAll();

	public Device getDeviceById(String deviceId);
}
