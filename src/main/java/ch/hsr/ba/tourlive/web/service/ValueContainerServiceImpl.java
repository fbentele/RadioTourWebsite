/**
 * ValueContainerServiceImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
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

/**
 * The Class ValueContainerServiceImpl.
 */
@Service
public class ValueContainerServiceImpl implements ValueContainerService {

	/** The value dao. */
	@Autowired
	ValueContainerDAO valueDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.ValueContainerService#save(ch.hsr.ba.tourlive
	 * .web.model.ValueContainer)
	 */
	@Override
	@Transactional
	public Long save(ValueContainer valueContainer) {
		return valueDao.save(valueContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.ValueContainerService#update(ch.hsr.ba
	 * .tourlive.web.model.ValueContainer)
	 */
	@Override
	@Transactional
	public void update(ValueContainer valueContainer) {
		valueDao.update(valueContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.ValueContainerService#delete(java.lang
	 * .Long)
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		valueDao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.ValueContainerService#deleteAllFromDevice
	 * (ch.hsr.ba.tourlive.web.model.Device)
	 */
	@Override
	@Transactional
	public void deleteAllFromDevice(Device device) {
		valueDao.deleteAllFromDevice(device);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.ValueContainerService#getAllValueContainers
	 * ()
	 */
	@Override
	@Transactional
	public List<ValueContainer> getAllValueContainers() {
		return valueDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.ValueContainerService#
	 * getAllValueContainerForStage(ch.hsr.ba.tourlive.web.model.Stage)
	 */
	@Override
	@Transactional
	public List<ValueContainer> getAllValueContainerForStage(Stage stage) {
		return valueDao.getAllValueContainerForStage(stage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.ValueContainerService#getAllForStageByDistance
	 * (ch.hsr.ba.tourlive.web.model.Stage)
	 */
	@Override
	@Transactional
	public List<ValueContainer> getAllForStageByDistance(Stage stage) {
		return valueDao.getAllForStageByDistance(stage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.ValueContainerService#getAllForStageByDistance
	 * (ch.hsr.ba.tourlive.web.model.Stage, java.lang.Long)
	 */
	@Override
	@Transactional
	public List<ValueContainer> getAllForStageByDistance(Stage stage, Long limit) {
		return valueDao.getAllForStageByDistance(stage, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.ValueContainerService#
	 * getLatestForDeviceByStage(ch.hsr.ba.tourlive.web.model.Stage)
	 */
	@Override
	@Transactional
	public List<ValueContainer> getLatestForDeviceByStage(Stage stage) {
		return valueDao.getLatestForDeviceByStage(stage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.ValueContainerService#
	 * getLatestForDeviceByStage(ch.hsr.ba.tourlive.web.model.Stage,
	 * java.lang.Long)
	 */
	@Override
	@Transactional
	public List<ValueContainer> getLatestForDeviceByStage(Stage stage, Long limit) {
		return valueDao.getLatestForDeviceByStage(stage, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.ValueContainerService#
	 * getFirstForStageByDistanceLimitedToRaceKm
	 * (ch.hsr.ba.tourlive.web.model.Stage, java.lang.Float)
	 */
	@Override
	@Transactional
	public ValueContainer getFirstForStageByDistanceLimitedToRaceKm(Stage stage, Float raceKm) {
		return valueDao.getFirstForStageByDistanceLimitedToRaceKm(stage, raceKm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.ValueContainerService#
	 * getDeficiteToLeaderForStage(ch.hsr.ba.tourlive.web.model.Stage)
	 */
	@Override
	@Transactional
	public HashMap<Long, Integer> getDeficiteToLeaderForStage(Stage stage) {
		return valueDao.getDeficiteToLeaderForStage(stage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.ValueContainerService#
	 * getDeficiteToLeaderForStage(ch.hsr.ba.tourlive.web.model.Stage,
	 * java.lang.Long)
	 */
	@Override
	@Transactional
	public HashMap<Long, Integer> getDeficiteToLeaderForStage(Stage stage, Long limit) {
		return valueDao.getDeficiteToLeaderForStage(stage, limit);
	}
}
