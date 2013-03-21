package ch.hsr.ba.tourlive.dao;

import java.util.List;

import ch.hsr.ba.tourlive.model.DeviceData;

public interface DeviceDataDAO {
	public void save(DeviceData devicedata);

	public void update(DeviceData devicedata);

	public void delete(Long id);

	public List<DeviceData> getAll();
}
