package ch.hsr.ba.tourlive.dao;

import java.util.List;

import ch.hsr.ba.tourlive.model.Device;

public interface DeviceDAO {
	public void save(Device device);

	public void update(Device device);

	public void delete(Long id);

	public List<Device> getAll();
}
