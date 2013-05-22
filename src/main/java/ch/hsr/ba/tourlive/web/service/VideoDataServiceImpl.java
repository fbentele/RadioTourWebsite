/**
 * VideoDataServiceImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.VideoDataDAO;
import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.VideoData;

/**
 * The Class VideoDataServiceImpl.
 */
@Service
public class VideoDataServiceImpl implements VideoDataService {

	/** The vid dao. */
	@Autowired
	VideoDataDAO vidDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.VideoDataService#save(ch.hsr.ba.tourlive
	 * .web.model.VideoData)
	 */
	@Transactional
	public void save(VideoData videoData) {
		vidDao.save(videoData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.VideoDataService#update(ch.hsr.ba.tourlive
	 * .web.model.VideoData)
	 */
	@Transactional
	public void update(VideoData videoData) {
		vidDao.update(videoData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.VideoDataService#delete(java.lang.Long)
	 */
	@Transactional
	public void delete(Long videoDataId) {
		vidDao.delete(videoDataId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.VideoDataService#deleteAllFromDevice(ch
	 * .hsr.ba.tourlive.web.model.Device)
	 */
	@Transactional
	public void deleteAllFromDevice(Device device) {
		vidDao.deleteAllFromDevice(device);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.VideoDataService#getAllLimited()
	 */
	@Transactional
	public List<VideoData> getAllLimited() {
		return vidDao.getAllLimited();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.VideoDataService#getAllVideoDataByDevice
	 * (ch.hsr.ba.tourlive.web.model.Device)
	 */
	@Transactional
	public List<VideoData> getAllVideoDataByDevice(Device device) {
		return vidDao.getAllVideoDataByDevice(device);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.VideoDataService#getMostRecentByDevice
	 * (ch.hsr.ba.tourlive.web.model.Device)
	 */
	@Transactional
	public VideoData getMostRecentByDevice(Device device) {
		return vidDao.getMostRecentByDevice(device);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.VideoDataService#getMostRecentByStage(
	 * ch.hsr.ba.tourlive.web.model.Stage)
	 */
	@Transactional
	public List<VideoData> getMostRecentByStage(Stage stage) {
		return vidDao.getMostRecentByStage(stage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.VideoDataService#getMostRecentByStageLimitedTo
	 * (ch.hsr.ba.tourlive.web.model.Stage, java.lang.Long)
	 */
	@Transactional
	public List<VideoData> getMostRecentByStageLimitedTo(Stage stage, Long limit) {
		return vidDao.getMostRecentByStageLimitedTo(stage, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.VideoDataService#getById(java.lang.Long)
	 */
	@Transactional
	public VideoData getById(Long id) {
		return vidDao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.VideoDataService#getAllByStage(ch.hsr.
	 * ba.tourlive.web.model.Stage)
	 */
	@Transactional
	public List<VideoData> getAllByStage(Stage stage) {
		return vidDao.getAllByStage(stage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.VideoDataService#getNextForDevice(ch.hsr
	 * .ba.tourlive.web.model.Device, java.lang.Long)
	 */
	@Transactional
	public VideoData getNextForDevice(Device device, Long afterId) {
		return vidDao.getNextForDevice(device, afterId);
	}
}
