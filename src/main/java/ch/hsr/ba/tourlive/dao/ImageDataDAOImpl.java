package ch.hsr.ba.tourlive.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.model.Device;
import ch.hsr.ba.tourlive.model.ImageData;

@Repository
public class ImageDataDAOImpl implements ImageDataDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void save(ImageData imageData) {
		sessionFactory.getCurrentSession().save(imageData);
	}

	public void update(ImageData imageData) {
		sessionFactory.getCurrentSession().update(imageData);
	}

	public void delete(Long imageDataId) {
		sessionFactory.getCurrentSession().delete(
				sessionFactory.getCurrentSession().get(ImageData.class,
						imageDataId));
	}

	public ImageData getById(Long imageDataId) {
		return (ImageData) sessionFactory.getCurrentSession().get(
				ImageData.class, imageDataId);
	}

	@SuppressWarnings("unchecked")
	public List<ImageData> getAllImageDataByDevice(Device device) {
		return sessionFactory.getCurrentSession()
				.createCriteria(ImageData.class).list();
	}

	public ImageData getMostRecentByDevice(Device device) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				ImageData.class);
		try {
			return (ImageData) crit.add(Restrictions.eq("device", device))
					.addOrder(Order.desc("timestamp")).list().get(0);
		} catch (NullPointerException e) {
			return null;
		}
	}
}
