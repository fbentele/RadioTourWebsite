package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.dao.VideoDataDAO;
import ch.hsr.ba.tourlive.model.Device;
import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.model.VideoData;

@Service
public class VideoDataServiceImpl implements VideoDataService {
	@Autowired
	VideoDataDAO vidDao;

	@Transactional
	public void save(VideoData videoData) {
		vidDao.save(videoData);
	}

	@Transactional
	public void update(VideoData videoData) {
		vidDao.update(videoData);
	}

	@Transactional
	public void delete(Long videoDataId) {
		vidDao.delete(videoDataId);
	}

	@Transactional
	public List<VideoData> getAllVideoDataByDevice(Device device) {
		return vidDao.getAllVideoDataByDevice(device);
	}

	@Transactional
	public VideoData getMostRecentByDevice(Device device) {
		return vidDao.getMostRecentByDevice(device);
	}

	@Transactional
	public List<VideoData> getMostRecentByStage(Stage stage) {
		return vidDao.getMostRecentByStage(stage);
	}

	@Transactional
	public List<VideoData> getMostRecentByStageLimitedTo(Stage stage, Long limit) {
		return vidDao.getMostRecentByStageLimitedTo(stage, limit);
	}

	@Transactional
	public VideoData getById(Long id) {
		return vidDao.getById(id);
	}

	@Transactional
	public List<VideoData> getAllByStage(Stage stage) {
		return vidDao.getAllByStage(stage);
	}

	@Transactional
	public VideoData getNextForDevice(Device device, Long afterId) {
		return vidDao.getNextForDevice(device, afterId);
	}
}
