/**
 * ImageDataDAOImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.ImageData;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.utils.FileUtil;

/**
 * The Class ImageDataDAOImpl.
 */
@Repository
public class ImageDataDAOImpl implements ImageDataDAO {

	/** The session factory. */
	@Autowired
	SessionFactory sessionFactory;
	@Value("${config.api.imagePath}")
	private String filePath;
	private final static Logger LOG = LoggerFactory.getLogger(ImageDataDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ImageDataDAO#save(ch.hsr.ba.tourlive.web.model
	 * .ImageData)
	 */
	public void save(ImageData imageData) {
		sessionFactory.getCurrentSession().save(imageData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ImageDataDAO#update(ch.hsr.ba.tourlive.web
	 * .model.ImageData)
	 */
	public void update(ImageData imageData) {
		sessionFactory.getCurrentSession().update(imageData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.ImageDataDAO#delete(java.lang.Long)
	 */
	public void delete(Long imageDataId) {
		ImageData img = (ImageData) sessionFactory.getCurrentSession().get(ImageData.class,
				imageDataId);
		if (imageDataId != null) {
			FileUtil.deleteFile(filePath + img.getImageLocation());
			sessionFactory.getCurrentSession().delete(img);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ImageDataDAO#deleteAllFromDevice(ch.hsr.ba
	 * .tourlive.web.model.Device)
	 */
	@SuppressWarnings("unchecked")
	public void deleteAllFromDevice(Device device) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ImageData.class);
		crit.add(Restrictions.eq("device", device));
		for (ImageData img : (List<ImageData>) crit.list()) {
			FileUtil.deleteFile(filePath + img.getImageLocation());
			sessionFactory.getCurrentSession().delete(img);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.ImageDataDAO#getById(java.lang.Long)
	 */
	public ImageData getById(Long imageDataId) {
		return (ImageData) sessionFactory.getCurrentSession().get(ImageData.class, imageDataId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.ImageDataDAO#getAllLimited()
	 */
	@SuppressWarnings("unchecked")
	public List<ImageData> getAllLimited() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ImageData.class);
		crit.setMaxResults(1000);
		crit.addOrder(Order.desc("timestamp"));
		return (List<ImageData>) crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ImageDataDAO#getAllImageDataByDevice(ch.hsr
	 * .ba.tourlive.web.model.Device)
	 */
	@SuppressWarnings("unchecked")
	public List<ImageData> getAllImageDataByDevice(Device device) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ImageData.class);
		if (device != null) {
			crit.add(Restrictions.eq("device", device));
			return crit.list();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ImageDataDAO#getMostRecentByDevice(ch.hsr.
	 * ba.tourlive.web.model.Device)
	 */
	public ImageData getMostRecentByDevice(Device device) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ImageData.class);
		if (device != null) {
			try {
				return (ImageData) crit.add(Restrictions.eq("device", device))
						.addOrder(Order.desc("timestamp")).list().get(0);
			} catch (NullPointerException e) {
				LOG.error("Device not set");
			} catch (IndexOutOfBoundsException e) {
				LOG.info("no ImageData found");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ImageDataDAO#getMostRecentByDeviceLimitedTo
	 * (ch.hsr.ba.tourlive.web.model.Stage, java.lang.Long)
	 */
	public ImageData getMostRecentByDeviceLimitedTo(Device device, Long limit) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ImageData.class);
		if (device != null) {
			try {
				crit.add(Restrictions.eq("device", device));
				crit.add(Restrictions.le("timestamp", limit));
				return (ImageData) crit.addOrder(Order.desc("timestamp")).list().get(0);
			} catch (NullPointerException e) {
				LOG.error("Device not set");
			} catch (IndexOutOfBoundsException e) {
				LOG.info("no ImageData found");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ImageDataDAO#getMostRecentByStage(ch.hsr.ba
	 * .tourlive.web.model.Stage)
	 */
	public List<ImageData> getMostRecentByStage(Stage stage) {
		List<ImageData> imageData = new ArrayList<ImageData>();
		if (stage != null) {
			for (Device dev : stage.getDevices()) {
				ImageData img = getMostRecentByDevice(dev);
				if (img != null && stage.getStarttimeAsTimestamp() < img.getRealTimestamp()
						&& img.getRealTimestamp() < stage.getEndtimeAsTimestamp())
					imageData.add(getMostRecentByDevice(dev));
			}
		}
		return imageData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ImageDataDAO#getMostRecentByStage(ch.hsr.ba
	 * .tourlive.web.model.Stage, java.lang.Long)
	 */
	public List<ImageData> getMostRecentByStage(Stage stage, Long limit) {
		List<ImageData> imageData = new ArrayList<ImageData>();
		if (stage != null) {
			for (Device dev : stage.getDevices()) {
				ImageData img = getMostRecentByDeviceLimitedTo(dev, limit);
				if (img != null && stage.getStarttimeAsTimestamp() < img.getRealTimestamp()
						&& img.getRealTimestamp() < stage.getEndtimeAsTimestamp())
					imageData.add(getMostRecentByDeviceLimitedTo(dev, limit));
			}
		}
		return imageData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ImageDataDAO#getAllByStage(ch.hsr.ba.tourlive
	 * .web.model.Stage)
	 */
	@SuppressWarnings("unchecked")
	public List<ImageData> getAllByStage(Stage stage) {
		if (stage != null) {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(ImageData.class);
			Disjunction d = Restrictions.or();
			for (Device device : stage.getDevices()) {
				d.add(Restrictions.eq("device", device));
			}
			crit.add(d);
			crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(),
					stage.getEndtimeAsTimestamp()));
			return (List<ImageData>) crit.list();
		}
		return null;
	}
}
