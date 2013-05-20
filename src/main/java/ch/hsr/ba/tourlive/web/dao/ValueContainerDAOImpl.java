package ch.hsr.ba.tourlive.web.dao;

import java.util.ArrayList;
import java.util.HashMap;
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

@Repository
public class ValueContainerDAOImpl implements ValueContainerDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private final static Logger LOG = LoggerFactory.getLogger(ValueContainerDAOImpl.class);
	private static final int LIMIT_RESULTS_TO = 1000;

	public void save(ValueContainer valuecontainer) {
		sessionFactory.getCurrentSession().save(valuecontainer);
	}

	public void update(ValueContainer valuecontainer) {
		sessionFactory.getCurrentSession().update(valuecontainer);
	}

	public void delete(Long id) {
		ValueContainer valuecontainer = (ValueContainer) sessionFactory.getCurrentSession().load(
				ValueContainer.class, id);
		if (valuecontainer != null) {
			sessionFactory.getCurrentSession().delete(valuecontainer);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteAllFromDevice(Device device) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
		crit.add(Restrictions.eq("device", device));
		for (ValueContainer v : (List<ValueContainer>) crit.list()) {
			sessionFactory.getCurrentSession().delete(v);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ValueContainer> getAll() {
		return sessionFactory.getCurrentSession()
				.createCriteria(ValueContainer.class, "ValueContainer")
				.addOrder(Order.desc("ValueContainer.valueContainerId")).setMaxResults(1000).list();
	}

	@SuppressWarnings("unchecked")
	public List<ValueContainer> getAllValueContainerForStage(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
		if (stage != null && !stage.getDevices().isEmpty()) {
			Disjunction d = Restrictions.or();
			for (Device device : stage.getDevices()) {
				d.add(Restrictions.eq("device", device));
			}
			crit.add(d);
			crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(),
					stage.getEndtimeAsTimestamp()));
			crit.addOrder(Order.desc("timestamp"));
			crit.setMaxResults(LIMIT_RESULTS_TO);
			return crit.list();
		}
		return null;
	}

	public List<ValueContainer> getAllForStageByDistance(Stage stage) {
		return getAllForStageByDistance(stage, stage.getEndtimeAsTimestamp());
	}

	@SuppressWarnings("unchecked")
	public List<ValueContainer> getAllForStageByDistance(Stage stage, Long limit) {
		if (stage != null && !stage.getDevices().isEmpty()) {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
			Disjunction d = Restrictions.or();
			for (Device device : stage.getDevices()) {
				d.add(Restrictions.eq("device", device));
			}
			crit.add(d);
			crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(), limit + 1));
			Criteria stageCriteria = crit.createCriteria("stageData");
			stageCriteria.addOrder(Order.asc("distance"));
			return (List<ValueContainer>) stageCriteria.list();
		}
		return null;
	}

	public ValueContainer getFirstForStageByDistanceLimitedToRaceKm(Stage stage, Float raceKm) {
		if (stage != null && !stage.getDevices().isEmpty()) {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
			Disjunction d = Restrictions.or();
			for (Device device : stage.getDevices()) {
				d.add(Restrictions.eq("device", device));
			}
			crit.add(d);
			crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(),
					stage.getEndtimeAsTimestamp()));
			Criteria stageCriteria = crit.createCriteria("stageData");
			stageCriteria.add(Restrictions.le("distance", raceKm));
			stageCriteria.addOrder(Order.desc("distance"));
			try {
				return (ValueContainer) stageCriteria.list().get(0);
			} catch (IndexOutOfBoundsException e) {
				LOG.info("No ValueContainer found");
			}
		}
		return null;
	}

	public List<ValueContainer> getLatestForDeviceByStage(Stage stage) {
		List<ValueContainer> list = new ArrayList<ValueContainer>();
		if (stage != null && !stage.getDevices().isEmpty()) {
			for (Device device : stage.getDevices()) {
				Criteria crit = sessionFactory.getCurrentSession().createCriteria(
						ValueContainer.class);
				crit.add(Restrictions.eq("device", device));
				crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(),
						stage.getEndtimeAsTimestamp()));
				Criteria stageCriteria = crit.createCriteria("stageData");
				stageCriteria.addOrder(Order.desc("distance"));
				try {
					list.add((ValueContainer) stageCriteria.list().get(0));
				} catch (IndexOutOfBoundsException e) {
					LOG.info("No ValueContainer found");
				}
			}
		}
		return list;
	}

	public List<ValueContainer> getLatestForDeviceByStage(Stage stage, Long limit) {
		List<ValueContainer> list = new ArrayList<ValueContainer>();
		if (stage != null && !stage.getDevices().isEmpty()) {
			for (Device device : stage.getDevices()) {
				Criteria crit = sessionFactory.getCurrentSession().createCriteria(
						ValueContainer.class);
				crit.add(Restrictions.eq("device", device));
				crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(), limit));
				Criteria stageCriteria = crit.createCriteria("stageData");
				stageCriteria.addOrder(Order.desc("distance"));
				try {
					list.add((ValueContainer) stageCriteria.list().get(0));
				} catch (IndexOutOfBoundsException e) {
					LOG.info("No ValueContainer found");
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @param stage
	 * @return a HashMap with the ID of the Valuecontainer and the deficite in
	 *         seconds
	 */
	public HashMap<Long, Integer> getDeficiteToLeaderForStage(Stage stage) {
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		if (stage.getDevices().size() >= 2) {
			for (ValueContainer v : getAllForStageByDistance(stage)) {
				Criteria crit = sessionFactory.getCurrentSession().createCriteria(
						ValueContainer.class);
				Disjunction d = Restrictions.or();
				for (Device device : stage.getDevices()) {
					d.add(Restrictions.eq("device", device));
				}
				crit.add(d);
				crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(),
						stage.getEndtimeAsTimestamp()));
				crit.addOrder(Order.asc("timestamp"));
				Criteria stageCriteria = crit.createCriteria("stageData");
				stageCriteria.add(Restrictions.le("distance", v.getStageData().getDistance()));
				try {
					ValueContainer val = (ValueContainer) stageCriteria.list().get(0);
					if (val.getTimestamp() < v.getTimestamp()
							&& val.getStageData().getDistance() + 0.5 >= v.getStageData()
									.getDistance()) {
						map.put(v.getValueContainerId(),
								(int) (v.getTimestamp() - val.getTimestamp()) / 1000);
					} else {
						map.put(v.getValueContainerId(), 0);
					}
				} catch (IndexOutOfBoundsException e) {
					map.put(v.getValueContainerId(), 0);
				}
			}
		}
		return map;
	}

	public HashMap<Long, Integer> getDeficiteToLeaderForStage(Stage stage, Long limit) {
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		for (ValueContainer v : getAllForStageByDistance(stage)) {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
			Disjunction d = Restrictions.or();
			for (Device device : stage.getDevices()) {
				d.add(Restrictions.eq("device", device));
			}
			crit.add(d);
			crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(), limit));
			crit.addOrder(Order.asc("timestamp"));
			Criteria stageCriteria = crit.createCriteria("stageData");
			stageCriteria.add(Restrictions.le("distance", v.getStageData().getDistance()));
			try {
				ValueContainer val = (ValueContainer) stageCriteria.list().get(0);
				if (val.getTimestamp() < v.getTimestamp()
						&& val.getStageData().getDistance() + 0.5 >= v.getStageData().getDistance()) {
					map.put(v.getValueContainerId(),
							(int) (v.getTimestamp() - val.getTimestamp()) / 1000);
				} else {
					map.put(v.getValueContainerId(), 0);
				}
			} catch (IndexOutOfBoundsException e) {
				map.put(v.getValueContainerId(), 0);
			}
		}
		return map;
	}
}
