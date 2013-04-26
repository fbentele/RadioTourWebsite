package ch.hsr.ba.tourlive.dao;

import java.util.List;

import ch.hsr.ba.tourlive.model.Device;
import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.model.VideoData;

public interface VideoDataDAO {
	public void save(VideoData VideoData);

	public void update(VideoData VideoData);

	public void delete(Long VideoDataId);

	public List<VideoData> getAllVideoDataByDevice(Device device);

	public VideoData getMostRecentByDevice(Device device);

	public List<VideoData> getMostRecentByStage(Stage stage);

	public VideoData getById(Long id);
}
