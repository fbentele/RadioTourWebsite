package ch.hsr.ba.tourlive.service;

import java.util.List;

import ch.hsr.ba.tourlive.model.Device;
import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.model.VideoData;

public interface VideoDataService {
	public void save(VideoData videoData);

	public void update(VideoData videoData);

	public void delete(Long videoDataId);

	public List<VideoData> getAllVideoDataByDevice(Device device);

	public VideoData getMostRecentByDevice(Device device);

	public List<VideoData> getMostRecentByStage(Stage stage);

	public List<VideoData> getMostRecentByStageLimitedTo(Stage stage, Long limit);

	public VideoData getById(Long id);

	public List<VideoData> getAllByStage(Stage stage);

}