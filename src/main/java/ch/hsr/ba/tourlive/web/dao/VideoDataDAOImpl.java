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
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.VideoData;

@Repository
public class VideoDataDAOImpl implements VideoDataDAO {
	@Autowired
	SessionFactory sessionFactory;
	private final static Logger LOG = LoggerFactory.getLogger(VideoDataDAOImpl.class);

	public void save(VideoData videoData) {
		sessionFactory.getCurrentSession().save(videoData);
	}

	public void update(VideoData videoData) {
		sessionFactory.getCurrentSession().update(videoData);
	}

	public void delete(Long videoDataId) {
		VideoData vid = (VideoData) sessionFactory.getCurrentSession().load(VideoData.class,
				videoDataId);
		if (vid != null) {
			sessionFactory.getCurrentSession().delete(vid);
		}
	}

	@SuppressWarnings("unchecked")
	public List<VideoData> getAllLimited() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(VideoData.class);
		crit.setMaxResults(1000);
		crit.addOrder(Order.desc("timestamp"));
		return (List<VideoData>) crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<VideoData> getAllVideoDataByDevice(Device device) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(VideoData.class);
		crit.add(Restrictions.eq("device", device));
		return crit.list();
	}

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
	 * 
	 * @param device
	 * @param afterId
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

	public List<VideoData> getMostRecentByStage(Stage stage) {
		List<VideoData> videoData = new ArrayList<VideoData>();
		if (stage != null) {
			for (Device dev : stage.getDevices()) {
				VideoData vid = getMostRecentByDevice(dev);
				if (vid != null && stage.getStarttimeAsTimestamp() < vid.getRealTimestamp()
						&& vid.getRealTimestamp() < stage.getEndtimeAsTimestamp())
					videoData.add(getMostRecentByDevice(dev));
			}
			return videoData;
		}
		return null;
	}

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

	public VideoData getById(Long id) {
		return (VideoData) sessionFactory.getCurrentSession().get(VideoData.class, id);
	}

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
