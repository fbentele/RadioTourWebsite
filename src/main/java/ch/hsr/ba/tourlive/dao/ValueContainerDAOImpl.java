package ch.hsr.ba.tourlive.dao;

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
	@Autowired
	SessionFactory sessionFactory;
	Logger log = LoggerFactory.getLogger(ValueContainerDAOImpl.class);

	@Override
	public void save(ValueContainer valuecontainer) {
		sessionFactory.getCurrentSession().save(valuecontainer);
	}

	@Override
	public void update(ValueContainer valuecontainer) {
		sessionFactory.getCurrentSession().update(valuecontainer);
	}

	@Override
	public void delete(Long id) {
		ValueContainer valuecontainer = (ValueContainer) sessionFactory.getCurrentSession().load(
				ValueContainer.class, id);
		if (null != valuecontainer) {
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
		if (!stage.getDevices().isEmpty()) {
			Disjunction d = Restrictions.or();
			for (Device device : stage.getDevices()) {
				d.add(Restrictions.eq("device", device));
			}
			crit.add(d);
		}
		crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(),
				stage.getEndtimeAsTimestamp()));
		crit.addOrder(Order.desc("timestamp"));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<ValueContainer> getAllForStageByDistance(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
		if (!stage.getDevices().isEmpty()) {
			Disjunction d = Restrictions.or();
			for (Device device : stage.getDevices()) {
				d.add(Restrictions.eq("device", device));
			}
			crit.add(d);
		}
		crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(),
				stage.getEndtimeAsTimestamp()));
		Criteria stageCriteria = crit.createCriteria("stageData");
		stageCriteria.addOrder(Order.asc("distance"));
		return (List<ValueContainer>) stageCriteria.list();
	}

	public ValueContainer getFirstByStage(Stage stage) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ValueContainer.class);
		if (!stage.getDevices().isEmpty()) {
			Disjunction d = Restrictions.or();
			for (Device device : stage.getDevices()) {
				d.add(Restrictions.eq("device", device));
			}
			crit.add(d);
		}
		crit.add(Restrictions.between("timestamp", stage.getStarttimeAsTimestamp(),
				stage.getEndtimeAsTimestamp()));
		try {
			Criteria stageCriteria = crit.createCriteria("stageData");
			return (ValueContainer) stageCriteria.addOrder(Order.desc("distance")).list().get(0);

		} catch (Exception e) {
			// TODO: do stuff here, if list is empty, cannot acces element 0
			return null;
		}

	}
}
