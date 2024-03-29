/**
 * ImageDataServiceImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.ImageDataDAO;
import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.ImageData;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Class ImageDataServiceImpl.
 */
@Service
public class ImageDataServiceImpl implements ImageDataService {
	@Autowired
	private ImageDataDAO imageDataDao;

	@Override
	@Transactional
	public void save(ImageData imageData) {
		imageDataDao.save(imageData);
	}

	@Override
	@Transactional
	public void update(ImageData imageData) {
		imageDataDao.update(imageData);
	}

	@Override
	@Transactional
	public void delete(Long imageDataId) {
		imageDataDao.delete(imageDataId);
	}

	@Override
	@Transactional
	public void deleteAllFromDevice(Device device) {
		imageDataDao.deleteAllFromDevice(device);
	}

	@Override
	@Transactional
	public List<ImageData> getAllLimited() {
		return imageDataDao.getAllLimited();
	}

	@Override
	@Transactional
	public List<ImageData> getAllImageDataByDevice(Device device) {
		return imageDataDao.getAllImageDataByDevice(device);
	}

	@Override
	@Transactional
	public ImageData getMostRecentByDevice(Device device) {
		return imageDataDao.getMostRecentByDevice(device);
	}

	@Override
	@Transactional
	public List<ImageData> getMostRecentByStage(Stage stage, Long limit) {
		return imageDataDao.getMostRecentByStage(stage, limit);
	}

	@Override
	@Transactional
	public ImageData getById(Long id) {
		return imageDataDao.getById(id);
	}

	@Override
	@Transactional
	public List<ImageData> getMostRecentByStage(Stage stage) {
		return imageDataDao.getMostRecentByStage(stage);
	}

	@Override
	@Transactional
	public List<ImageData> getAllByStage(Stage stage) {
		return imageDataDao.getAllByStage(stage);
	}

}
