package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.hsr.ba.tourlive.model.DeviceData;

@Service
public class DeviceDataServiceImpl implements DeviceDataService {

	@Override
	public void save(DeviceData devicedata) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(DeviceData devicedata) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DeviceData> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	// @Autowired
	// private DeviceDataDAO devicedata;
	//
	// @Transactional
	// public void save(DeviceData devdat) {
	// devicedata.save(devdat);
	// }
	//
	// @Transactional
	// public void update(DeviceData devdat) {
	// devicedata.update(devdat);
	// }
	//
	// @Transactional
	// public void delete(Long id) {
	// devicedata.delete(id);
	// }
	//
	// @Transactional
	// public List<DeviceData> getAll() {
	// return devicedata.getAll();
	// }
}
