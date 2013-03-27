package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.dao.DeviceDataDAO;
import ch.hsr.ba.tourlive.model.DeviceData;

@Service
public class DeviceDataServiceImpl implements DeviceDataService {
	@Autowired
	DeviceDataDAO deviceDataDao;

	@Transactional
	public void save(DeviceData devicedata) {
		deviceDataDao.save(devicedata);
	}

	@Transactional
	public void update(DeviceData devicedata) {
		deviceDataDao.update(devicedata);
	}

	@Transactional
	public void delete(Long id) {
		deviceDataDao.delete(id);
	}

	@Transactional
	public List<DeviceData> getAll() {
		return deviceDataDao.getAll();
	}

}
