package ch.hsr.ba.tourlive.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.model.ValueContainer;

@Repository
public class ValueContainerDAOImpl implements ValueContainerDAO {
	@Autowired
	SessionFactory sessioFactory;

	@Override
	public void save(ValueContainer valuecontainer) {
		sessioFactory.getCurrentSession().save(valuecontainer);
	}

	@Override
	public void update(ValueContainer valuecontainer) {
		sessioFactory.getCurrentSession().update(valuecontainer);
	}

	@Override
	public void delete(Long id) {
		ValueContainer valuecontainer = (ValueContainer) sessioFactory
				.getCurrentSession().load(ValueContainer.class, id);
		if (null != valuecontainer) {
			sessioFactory.getCurrentSession().delete(valuecontainer);
		}
	}

	@Override
	public List<ValueContainer> getAll() {
		return sessioFactory.getCurrentSession()
				.createCriteria(ValueContainer.class).list();
	}

}
