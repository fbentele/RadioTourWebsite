package ch.hsr.ba.tourlive.dao;

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

import ch.hsr.ba.tourlive.model.Device;
import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.model.ValueContainer;

@Repository
public class ValueContainerDAOImpl implements ValueContainerDAO {
	private static final int LIMIT_RESULTS_TO = 1000;
	@Autowired
	SessionFactory sessionFactory;
	Logger log = LoggerFactory.getLogger(ValueContainerDAOImpl.class);

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
	public List<ValueContainer> getAll() {
		return sessionFactory.getCurrentSession()
				.createCriteria(ValueContainer.class, "ValueContainer")
				.addOrder(Order.desc("ValueContainer.valueContainerId")).list();
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

	@SuppressWarnings("unchecked")
	public List<ValueContainer> getAllForStageByDistance(Stage stage) {
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
			stageCriteria.addOrder(Order.asc("distance"));
			return (List<ValueContainer>) stageCriteria.list();
		}
		return null;
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
			crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(), limit));
			Criteria stageCriteria = crit.createCriteria("stageData");
			stageCriteria.addOrder(Order.asc("distance"));
			return (List<ValueContainer>) stageCriteria.list();
		}
		return null;
	}

	public ValueContainer getFirstByStage(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
		if (stage != null && !stage.getDevices().isEmpty()) {
			Disjunction d = Restrictions.or();
			for (Device device : stage.getDevices()) {
				d.add(Restrictions.eq("device", device));
			}
			crit.add(d);
			crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(),
					stage.getEndtimeAsTimestamp()));
			try {
				Criteria stageCriteria = crit.createCriteria("stageData");
				return (ValueContainer) stageCriteria.addOrder(Order.desc("distance")).list()
						.get(0);
			} catch (IndexOutOfBoundsException e) {
				// TODO: do stuff here, if list is empty, cannot acces element 0
			}
		}
		return null;
	}

	public ValueContainer getFirstByStage(Stage stage, Long limit) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
		if (stage != null && !stage.getDevices().isEmpty()) {
			Disjunction d = Restrictions.or();
			for (Device device : stage.getDevices()) {
				d.add(Restrictions.eq("device", device));
			}
			crit.add(d);
			crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(), limit));
			try {
				Criteria stageCriteria = crit.createCriteria("stageData");
				return (ValueContainer) stageCriteria.addOrder(Order.desc("distance")).list()
						.get(0);
			} catch (IndexOutOfBoundsException e) {
				// TODO: do stuff here, if list is empty, cannot acces element 0
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
					// no valuecontainer from device
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
					// no valuecontainer from device
				}
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ValueContainer> getForStageByDistanceLimitedTo(Stage stage, Long limit) {
		if (stage != null && !stage.getDevices().isEmpty()) {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
			Disjunction d = Restrictions.or();
			for (Device device : stage.getDevices()) {
				d.add(Restrictions.eq("device", device));
			}
			crit.add(d);
			crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(), limit));
			Criteria stageCriteria = crit.createCriteria("stageData");
			stageCriteria.addOrder(Order.asc("distance"));
			return (List<ValueContainer>) stageCriteria.list();
		}
		return null;
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
						log.info("__________ put " + v.getValueContainerId() + "____ and "
								+ (v.getTimestamp() - val.getTimestamp()));
					} else {
						map.put(v.getValueContainerId(), 0);
						log.info("__________ put zero");
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
					log.info("__________ put " + v.getValueContainerId() + "____ and "
							+ (v.getTimestamp() - val.getTimestamp()));
				} else {
					map.put(v.getValueContainerId(), 0);
					log.info("__________ put zero");
				}
			} catch (IndexOutOfBoundsException e) {
				map.put(v.getValueContainerId(), 0);
			}
		}
		return map;
	}
}
