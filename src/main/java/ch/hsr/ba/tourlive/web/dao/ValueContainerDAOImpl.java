/**
 * ValueContainerDAOImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.dao;

import java.util.ArrayList;
import java.util.Collections;
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
import ch.hsr.ba.tourlive.web.model.ValueContainer;
import ch.hsr.ba.tourlive.web.utils.SortValueContainer;

/**
 * The Class ValueContainerDAOImpl.
 */
@Repository
public class ValueContainerDAOImpl implements ValueContainerDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private final static Logger LOG = LoggerFactory.getLogger(ValueContainerDAOImpl.class);
	private static final int LIMIT_RESULTS_TO = 1000;
	private static final float DISTANCE_ACCURACY = 0.5f;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ValueContainerDAO#save(ch.hsr.ba.tourlive.
	 * web.model.ValueContainer)
	 */
	@Override
	public Long save(ValueContainer valuecontainer) {
		return (Long) sessionFactory.getCurrentSession().save(valuecontainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ValueContainerDAO#update(ch.hsr.ba.tourlive
	 * .web.model.ValueContainer)
	 */
	@Override
	public void update(ValueContainer valuecontainer) {
		sessionFactory.getCurrentSession().update(valuecontainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.ValueContainerDAO#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		ValueContainer valuecontainer = (ValueContainer) sessionFactory.getCurrentSession().load(
				ValueContainer.class, id);
		if (valuecontainer != null) {
			sessionFactory.getCurrentSession().delete(valuecontainer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ValueContainerDAO#deleteAllFromDevice(ch.hsr
	 * .ba.tourlive.web.model.Device)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void deleteAllFromDevice(Device device) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
		crit.add(Restrictions.eq("device", device));
		for (ValueContainer v : (List<ValueContainer>) crit.list()) {
			sessionFactory.getCurrentSession().delete(v);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.ValueContainerDAO#getAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ValueContainer> getAll() {
		return sessionFactory.getCurrentSession()
				.createCriteria(ValueContainer.class, "ValueContainer")
				.addOrder(Order.desc("ValueContainer.valueContainerId")).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ValueContainerDAO#getAllValueContainerForStage
	 * (ch.hsr.ba.tourlive.web.model.Stage)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ValueContainer> getLatest1000ValueContainerForStage(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
		if (stage != null && !stage.getDevices().isEmpty()) {
			Disjunction d = Restrictions.or();
			for (Device device : stage.getDevices()) {
				d.add(Restrictions.eq("device", device));
			}
			crit.add(d);
			crit.add(Restrictions.between("timestamp", stage.getCorrectedStarttimeAsTimestamp(),
					stage.getEndtimeAsTimestamp()));
			crit.addOrder(Order.desc("timestamp"));
			crit.setMaxResults(LIMIT_RESULTS_TO);
			return crit.list();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ValueContainerDAO#getAllForStageByDistance
	 * (ch.hsr.ba.tourlive.web.model.Stage)
	 */
	@Override
	public List<ValueContainer> getAllForStageByDistance(Stage stage) {
		return getAllForStageByDistance(stage, stage.getEndtimeAsTimestamp());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ValueContainerDAO#getAllForStageByDistance
	 * (ch.hsr.ba.tourlive.web.model.Stage, java.lang.Long)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ValueContainer> getAllForStageByDistance(Stage stage, Long limit) {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
			Disjunction d = Restrictions.or();
			for (Device device : stage.getDevices()) {
				d.add(Restrictions.eq("device", device));
			}
			crit.add(d);
			crit.add(Restrictions.between("timestamp", stage.getCorrectedStarttimeAsTimestamp(),
					limit + 1));
			crit.addOrder(Order.desc("timestamp"));
			return (List<ValueContainer>) crit.list();
		} catch (NullPointerException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.dao.ValueContainerDAO#
	 * getFirstForStageByDistanceLimitedToRaceKm
	 * (ch.hsr.ba.tourlive.web.model.Stage, java.lang.Float)
	 */
	@Override
	public ValueContainer getFirstForStageByDistanceLimitedToRaceKm(Stage stage, Float raceKm) {
		List<ValueContainer> temp = new ArrayList<ValueContainer>();
		if (stage != null && !stage.getDevices().isEmpty()) {
			for (Device device : stage.getDevices()) {
				Criteria crit = sessionFactory.getCurrentSession().createCriteria(
						ValueContainer.class);
				crit.add(Restrictions.eq("device", device));
				crit.add(Restrictions.between("timestamp",
						stage.getCorrectedStarttimeAsTimestamp(), stage.getEndtimeAsTimestamp()));
				Criteria stageCriteria = crit.createCriteria("stageData");
				stageCriteria.add(Restrictions.between("distance", raceKm - DISTANCE_ACCURACY,
						raceKm));
				stageCriteria.addOrder(Order.desc("distance"));
				try {
					temp.add((ValueContainer) stageCriteria.list().get(0));
				} catch (IndexOutOfBoundsException e) {
					LOG.info("No ValueContainer found");
				}
			}
		}
		try {
			Collections.sort(temp, new SortValueContainer());
			return temp.get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ValueContainerDAO#getLatestForDeviceByStage
	 * (ch.hsr.ba.tourlive.web.model.Stage)
	 */
	@Override
	public List<ValueContainer> getLatestForDeviceByStage(Stage stage) {
		return getLatestForDeviceByStage(stage, stage.getEndtimeAsTimestamp());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ValueContainerDAO#getLatestForDeviceByStage
	 * (ch.hsr.ba.tourlive.web.model.Stage, java.lang.Long)
	 */
	@Override
	public List<ValueContainer> getLatestForDeviceByStage(Stage stage, Long limit) {
		List<ValueContainer> list = new ArrayList<ValueContainer>();
		if (stage != null && !stage.getDevices().isEmpty()) {
			for (Device device : stage.getDevices()) {
				Criteria crit = sessionFactory.getCurrentSession().createCriteria(
						ValueContainer.class);
				crit.add(Restrictions.eq("device", device));
				crit.add(Restrictions.between("timestamp",
						stage.getCorrectedStarttimeAsTimestamp(), limit));
				Criteria stageCriteria = crit.createCriteria("stageData");
				stageCriteria.addOrder(Order.desc("distance"));
				try {
					list.add((ValueContainer) stageCriteria.list().get(0));
				} catch (IndexOutOfBoundsException e) {
					LOG.info("No ValueContainer found");
				}
			}
		}
		Collections.sort(list, new SortValueContainer());
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ValueContainerDAO#getMostRecentForEachDevice
	 * (java.util.List)
	 */
	public List<ValueContainer> getMostRecentForEachDevice(List<Device> devices) {
		List<ValueContainer> list = new ArrayList<ValueContainer>();
		for (Device device : devices) {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
			crit.add(Restrictions.eq("device", device));
			crit.addOrder(Order.desc("timestamp"));
			try {
				list.add((ValueContainer) crit.list().get(0));
			} catch (IndexOutOfBoundsException e) {
				LOG.info("No ValueContainer found");
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ValueContainerDAO#getMostRecentForDevice(ch
	 * .hsr.ba.tourlive.web.model.Device)
	 */
	public ValueContainer getMostRecentForDevice(Device d) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
		crit.add(Restrictions.eq("device", d));
		crit.addOrder(Order.desc("timestamp"));
		try {
			return (ValueContainer) crit.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			LOG.info("No position known for this device");
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.dao.ValueContainerDAO#getDeficiteToLeaderForStage
	 * (ch.hsr.ba.tourlive.web.model.Stage)
	 */
	@Override
	public void calculateDeficiteToLeaderForStage(Stage stage) {
		if (stage.getDevices().size() >= 2) {
			for (ValueContainer v : getAllForStageByDistance(stage)) {
				if (v.getDeficiteTimeAsTimestamp() != null)
					continue;
				Criteria crit = sessionFactory.getCurrentSession().createCriteria(
						ValueContainer.class);
				Disjunction d = Restrictions.or();
				for (Device device : stage.getDevices()) {
					if (!device.getDeviceId().equals(v.getDevice().getDeviceId()))
						d.add(Restrictions.eq("device", device));
				}
				crit.add(d);
				crit.add(Restrictions.between("timestamp",
						stage.getCorrectedStarttimeAsTimestamp(), stage.getEndtimeAsTimestamp()));
				crit.addOrder(Order.desc("timestamp"));
				Criteria stageCriteria = crit.createCriteria("stageData");
				stageCriteria.add(Restrictions.between("distance", v.getStageData().getDistance(),
						v.getStageData().getDistance() - DISTANCE_ACCURACY));
				try {
					ValueContainer val = (ValueContainer) stageCriteria.list().get(0);
					if (val.getTimestamp() < v.getTimestamp()
							&& val.getStageData().getDistance() + 1 >= v.getStageData()
									.getDistance()) {
						v.setDeficiteTime(v.getTimestamp() - val.getTimestamp());
					} else {
						// Is Leader, no deficitetime == -1
						v.setDeficiteTime(-1L);
					}
				} catch (IndexOutOfBoundsException e) {
					v.setDeficiteTime(-1L);
				}
				update(v);
			}
		}
	}

	public Long getHighestDeficiteForStage(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
		crit.add(Restrictions.between("timestamp", stage.getCorrectedStarttimeAsTimestamp(),
				stage.getEndtimeAsTimestamp()));
		Disjunction d = Restrictions.or();
		for (Device device : stage.getDevices()) {
			d.add(Restrictions.eq("device", device));
		}
		crit.add(d);
		crit.addOrder(Order.desc("deficiteTime"));
		try {
			return ((ValueContainer) crit.list().get(0)).getDeficiteTimeAsTimestamp();
		} catch (IndexOutOfBoundsException e) {
			return 1000L;
		}
	}
}
