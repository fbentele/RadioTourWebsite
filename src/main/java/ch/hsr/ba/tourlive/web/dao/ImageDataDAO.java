package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.ImageData;
import ch.hsr.ba.tourlive.web.model.Stage;

public interface ImageDataDAO {
	public void save(ImageData imageData);

	public void update(ImageData imageData);

	public void delete(Long imageDataId);

	public List<ImageData> getAllLimited();

	public void deleteAllFromDevice(Device device);

	public List<ImageData> getAllImageDataByDevice(Device device);

	public ImageData getMostRecentByDevice(Device device);

	public List<ImageData> getMostRecentByStage(Stage stage);

	public List<ImageData> getMostRecentByStage(Stage stage, Long time);

	public ImageData getById(Long id);

	public List<ImageData> getAllByStage(Stage stage);
}
