package ch.hsr.ba.tourlive.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
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
		if (vid != null) {
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

	public VideoData getMostRecentByDevice(Device device, Long limit) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(VideoData.class);
		try {
			crit.add(Restrictions.eq("device", device));
			crit.add(Restrictions.le("timestamp", limit));
			crit.addOrder(Order.desc("timestamp"));
			return (VideoData) crit.list().get(0);
		} catch (NullPointerException e) {
			return null;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public List<VideoData> getMostRecentByStage(Stage stage) {
		List<VideoData> videoData = new ArrayList<VideoData>();
		if (stage != null) {
			for (Device dev : stage.getDevices()) {
				VideoData img = getMostRecentByDevice(dev);

				if (img != null) {
					if (stage.getStarttimeAsTimestamp() < img.getRealTimestamp()
							&& img.getRealTimestamp() < stage.getEndtimeAsTimestamp())
						videoData.add(getMostRecentByDevice(dev));
				}
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
				if (vid != null) {
					if (stage.getStarttimeAsTimestamp() < vid.getRealTimestamp()
							&& vid.getRealTimestamp() < stage.getEndtimeAsTimestamp())
						videoData.add(getMostRecentByDevice(dev, limit));
				}
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
