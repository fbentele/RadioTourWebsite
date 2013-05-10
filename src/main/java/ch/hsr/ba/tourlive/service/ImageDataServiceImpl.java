package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.dao.ImageDataDAO;
import ch.hsr.ba.tourlive.model.Device;
import ch.hsr.ba.tourlive.model.ImageData;
import ch.hsr.ba.tourlive.model.Stage;

@Service
public class ImageDataServiceImpl implements ImageDataService {
	@Autowired
	private ImageDataDAO imageDataDao;

	@Transactional
	public void save(ImageData imageData) {
		imageDataDao.save(imageData);
	}

	@Transactional
	public void update(ImageData imageData) {
		imageDataDao.update(imageData);
	}

	@Transactional
	public void delete(Long imageDataId) {
		imageDataDao.delete(imageDataId);
	}

	@Transactional
	public List<ImageData> getAllImageDataByDevice(Device device) {
		return imageDataDao.getAllImageDataByDevice(device);
	}

	@Transactional
	public ImageData getMostRecentByDevice(Device device) {
		return imageDataDao.getMostRecentByDevice(device);
	}

	@Transactional
	public List<ImageData> getMostRecentByStage(Stage stage, Long limit) {
		return imageDataDao.getMostRecentByStage(stage, limit);
	}

	@Transactional
	public ImageData getById(Long id) {
		return imageDataDao.getById(id);
	}

	@Transactional
	public List<ImageData> getMostRecentByStage(Stage stage) {
		return imageDataDao.getMostRecentByStage(stage);
	}

	@Transactional
	public List<ImageData> getAllByStage(Stage stage) {
		return imageDataDao.getAllByStage(stage);
	}

}
