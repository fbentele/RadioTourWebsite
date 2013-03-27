package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.dao.ValueContainerDAO;
import ch.hsr.ba.tourlive.model.ValueContainer;

@Service
public class ValueContainerServiceImpl implements ValueContainerService {
	@Autowired
	ValueContainerDAO valueDao;

	@Transactional
	public void save(ValueContainer valueContainer) {
		valueDao.save(valueContainer);
	}

	@Transactional
	public void update(ValueContainer valueContainer) {
		valueDao.update(valueContainer);
	}

	@Transactional
	public void delete(Long id) {
		valueDao.delete(id);
	}

	@Transactional
	public List<ValueContainer> getAll() {
		return valueDao.getAll();
	}

}
