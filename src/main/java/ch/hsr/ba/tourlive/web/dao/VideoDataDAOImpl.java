/**
 * VideoDataDAOImpl.java
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
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.VideoData;
import ch.hsr.ba.tourlive.web.utils.FileUtil;

/**
 * The Class VideoDataDAOImpl.
 */
@Repository
public class VideoDataDAOImpl implements VideoDataDAO {

	/** The session factory. */
	@Autowired
	SessionFactory sessionFactory;
	@Value("${config.api.imagePath}")
	private String filePath;
	private final static Logger LOG = LoggerFactory.getLogger(VideoDataDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.VideoDataDAO#save(ch.hsr.ba.tourlive.web.model
	 * .VideoData)
	 */
	public void save(VideoData videoData) {
		sessionFactory.getCurrentSession().save(videoData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.VideoDataDAO#update(ch.hsr.ba.tourlive.web
	 * .model.VideoData)
	 */
	public void update(VideoData videoData) {
		sessionFactory.getCurrentSession().update(videoData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.VideoDataDAO#delete(java.lang.Long)
	 */
	public void delete(Long videoDataId) {
		VideoData vid = (VideoData) sessionFactory.getCurrentSession().load(VideoData.class,
				videoDataId);
		if (vid != null) {
			FileUtil.deleteFile(filePath + vid.getVideoLocation() + ".mp4");
			FileUtil.deleteFile(filePath + vid.getVideoLocation() + ".ogg");
			sessionFactory.getCurrentSession().delete(vid);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.VideoDataDAO#deleteAllFromDevice(ch.hsr.ba
	 * .tourlive.web.model.Device)
	 */
	@SuppressWarnings("unchecked")
	public void deleteAllFromDevice(Device device) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(VideoData.class);
		crit.add(Restrictions.eq("device", device));
		for (VideoData v : (List<VideoData>) crit.list()) {
			FileUtil.deleteFile(filePath + v.getVideoLocation() + ".mp4");
			FileUtil.deleteFile(filePath + v.getVideoLocation() + ".ogg");
			sessionFactory.getCurrentSession().delete(v);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.VideoDataDAO#getAllLimited()
	 */
	@SuppressWarnings("unchecked")
	public List<VideoData> getAllLimited() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(VideoData.class);
		crit.setMaxResults(1000);
		crit.addOrder(Order.desc("timestamp"));
		return (List<VideoData>) crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.VideoDataDAO#getAllVideoDataByDevice(ch.hsr
	 * .ba.tourlive.web.model.Device)
	 */
	@SuppressWarnings("unchecked")
	public List<VideoData> getAllVideoDataByDevice(Device device) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(VideoData.class);
		crit.add(Restrictions.eq("device", device));
		return crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.VideoDataDAO#getMostRecentByDevice(ch.hsr.
	 * ba.tourlive.web.model.Device)
	 */
	public VideoData getMostRecentByDevice(Device device) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(VideoData.class);
		try {
			return (VideoData) crit.add(Restrictions.eq("device", device))
					.addOrder(Order.desc("timestamp")).list().get(0);
		} catch (NullPointerException e) {
			LOG.error("device not set");
		} catch (IndexOutOfBoundsException e) {
			LOG.info("not VideoData found");
		}
		return null;
	}

	/**
	 * Gets the next for device.
	 * 
	 * @param device
	 *            the device
	 * @param afterId
	 *            the after id
	 * @return Returns the next available Video after the one with the ID
	 *         afterId
	 */
	public VideoData getNextForDevice(Device device, Long afterId) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(VideoData.class);
		try {
			crit.add(Restrictions.eq("device", device));
			crit.add(Restrictions.gt("videoDataId", afterId));
			return (VideoData) crit.addOrder(Order.asc("videoDataId")).list().get(0);
		} catch (NullPointerException e) {
			LOG.error("device not set");
		} catch (IndexOutOfBoundsException e) {
			LOG.info("not VideoData found");
		}
		return null;
	}

	/**
	 * Gets the most recent by device.
	 * 
	 * @param device
	 *            the device
	 * @param limit
	 *            the limit
	 * @return the most recent by device
	 */
	public VideoData getMostRecentByDevice(Device device, Long limit) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(VideoData.class);
		try {
			crit.add(Restrictions.eq("device", device));
			crit.add(Restrictions.le("timestamp", limit));
			crit.addOrder(Order.desc("timestamp"));
			return (VideoData) crit.list().get(0);
		} catch (NullPointerException e) {
			LOG.error("device not set");
		} catch (IndexOutOfBoundsException e) {
			LOG.info("not VideoData found");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.VideoDataDAO#getMostRecentByStage(ch.hsr.ba
	 * .tourlive.web.model.Stage)
	 */
	public List<VideoData> getMostRecentByStage(Stage stage) {
		List<VideoData> videoData = new ArrayList<VideoData>();
		try {
			for (Device dev : stage.getDevices()) {
				VideoData vid = getMostRecentByDevice(dev);
				if (vid != null && stage.getStarttimeAsTimestamp() < vid.getRealTimestamp()
						&& vid.getRealTimestamp() < stage.getEndtimeAsTimestamp())
					videoData.add(getMostRecentByDevice(dev));
			}
			return videoData;
		} catch (NullPointerException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.VideoDataDAO#getMostRecentByStageLimitedTo
	 * (ch.hsr.ba.tourlive.web.model.Stage, java.lang.Long)
	 */
	public List<VideoData> getMostRecentByStageLimitedTo(Stage stage, Long limit) {
		List<VideoData> videoData = new ArrayList<VideoData>();
		if (stage != null) {
			for (Device dev : stage.getDevices()) {
				VideoData vid = getMostRecentByDevice(dev, limit);
				if (vid != null && stage.getStarttimeAsTimestamp() < vid.getRealTimestamp()
						&& vid.getRealTimestamp() < stage.getEndtimeAsTimestamp())
					videoData.add(getMostRecentByDevice(dev, limit));
			}
			return videoData;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.VideoDataDAO#getById(java.lang.Long)
	 */
	public VideoData getById(Long id) {
		return (VideoData) sessionFactory.getCurrentSession().get(VideoData.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.VideoDataDAO#getAllByStage(ch.hsr.ba.tourlive
	 * .web.model.Stage)
	 */
	@SuppressWarnings("unchecked")
	public List<VideoData> getAllByStage(Stage stage) {
		if (stage != null) {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(VideoData.class);
			Disjunction d = Restrictions.or();
			for (Device device : stage.getDevices()) {
				d.add(Restrictions.eq("device", device));
			}
			crit.add(d);
			crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(),
					stage.getEndtimeAsTimestamp()));
			return (List<VideoData>) crit.list();
		}
		return null;
	}

}
