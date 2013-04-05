package ch.hsr.ba.tourlive.dao;

import java.util.List;

import ch.hsr.ba.tourlive.model.Device;
import ch.hsr.ba.tourlive.model.ImageData;

public interface ImageDataDAO {
	public void save(ImageData imageData);

	public void update(ImageData imageData);

	public void delete(Long imageDataId);

	public List<ImageData> getAllImageDataByDevice(Device device);

	public ImageData getMostRecentByDevice(Device device);

	public ImageData getById(Long id);
}
