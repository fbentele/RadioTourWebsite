package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hsr.ba.tourlive.dao.ValueContainerDAO;
import ch.hsr.ba.tourlive.model.ValueContainer;

@Service
public class ValueContainerServiceImpl implements ValueContainerService {
	@Autowired
	ValueContainerDAO valueDao;

	@Override
	public void save(ValueContainer valueContainer) {
		valueDao.save(valueContainer);
	}

	@Override
	public void update(ValueContainer valueContainer) {
		valueDao.update(valueContainer);
	}

	@Override
	public void delete(Long id) {
		valueDao.delete(id);
	}

	@Override
	public List<ValueContainer> getAll() {
		return valueDao.getAll();
	}

}
