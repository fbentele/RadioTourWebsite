package ch.hsr.ba.tourlive.web.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.ValueContainerDAO;
import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.ValueContainer;

@Service
public class ValueContainerServiceImpl implements ValueContainerService {
	@Autowired
	ValueContainerDAO valueDao;

	@Transactional
	public void save(ValueContainer valueContainer) {
		valueDao.save(valueContainer);
	}

	@Transactional
	public void update(ValueContainer valueContainer) {
		valueDao.update(valueContainer);
	}

	@Transactional
	public void delete(Long id) {
		valueDao.delete(id);
	}

	@Transactional
	public void deleteAllFromDevice(Device device) {
		valueDao.deleteAllFromDevice(device);
	}

	@Transactional
	public List<ValueContainer> getAllValueContainers() {
		return valueDao.getAll();
	}

	@Transactional
	public List<ValueContainer> getAllValueContainerForStage(Stage stage) {
		return valueDao.getAllValueContainerForStage(stage);
	}

	@Transactional
	public List<ValueContainer> getAllForStageByDistance(Stage stage) {
		return valueDao.getAllForStageByDistance(stage);
	}

	@Transactional
	public List<ValueContainer> getAllForStageByDistance(Stage stage, Long limit) {
		return valueDao.getAllForStageByDistance(stage, limit);
	}

	@Transactional
	public List<ValueContainer> getLatestForDeviceByStage(Stage stage) {
		return valueDao.getLatestForDeviceByStage(stage);
	}

	@Transactional
	public List<ValueContainer> getLatestForDeviceByStage(Stage stage, Long limit) {
		return valueDao.getLatestForDeviceByStage(stage, limit);
	}

	@Transactional
	public ValueContainer getFirstForStageByDistanceLimitedToRaceKm(Stage stage, Float raceKm) {
		return valueDao.getFirstForStageByDistanceLimitedToRaceKm(stage, raceKm);
	}

	@Transactional
	public HashMap<Long, Integer> getDeficiteToLeaderForStage(Stage stage) {
		return valueDao.getDeficiteToLeaderForStage(stage);
	}

	@Transactional
	public HashMap<Long, Integer> getDeficiteToLeaderForStage(Stage stage, Long limit) {
		return valueDao.getDeficiteToLeaderForStage(stage, limit);
	}
}
