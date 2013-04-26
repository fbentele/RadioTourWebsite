package ch.hsr.ba.tourlive.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.model.Device;
import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.model.VideoData;

@Repository
public class VideoDataDAOImpl implements VideoDataDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void save(VideoData videoData) {
		sessionFactory.getCurrentSession().save(videoData);
	}

	public void update(VideoData videoData) {
		sessionFactory.getCurrentSession().update(videoData);
	}

	public void delete(Long videoDataId) {
		VideoData vid = (VideoData) sessionFactory.getCurrentSession().load(VideoData.class,
				videoDataId);
		if (null != vid) {
			sessionFactory.getCurrentSession().delete(vid);
		}
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
			return null;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public List<VideoData> getMostRecentByStage(Stage stage) {
		List<VideoData> videoData = new ArrayList<VideoData>();
		for (Device dev : stage.getDevices()) {
			VideoData img = this.getMostRecentByDevice(dev);

			if (img != null) {
				if (stage.getStarttimeAsTimestamp() < img.getRealTimestamp()
						&& img.getRealTimestamp() < stage.getEndtimeAsTimestamp())
					videoData.add(this.getMostRecentByDevice(dev));
			}
		}
		return videoData;
	}

	public VideoData getById(Long id) {
		return (VideoData) sessionFactory.getCurrentSession().get(VideoData.class, id);
	}

}
