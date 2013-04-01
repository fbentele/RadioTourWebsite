package ch.hsr.ba.tourlive.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.model.ValueContainer;

@Repository
public class ValueContainerDAOImpl implements ValueContainerDAO {
	@Autowired
	SessionFactory sessionFactory;

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
		ValueContainer valuecontainer = (ValueContainer) sessionFactory
				.getCurrentSession().load(ValueContainer.class, id);
		if (null != valuecontainer) {
			sessionFactory.getCurrentSession().delete(valuecontainer);
		}
	}

	@Override
	public List<ValueContainer> getAll() {
		return sessionFactory.getCurrentSession()
				.createCriteria(ValueContainer.class, "ValueContainer")
				.addOrder(Order.desc("ValueContainer.valueContainerId")).list();
	}
}
