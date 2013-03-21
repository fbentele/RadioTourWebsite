package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.dao.PositionDataDAO;
import ch.hsr.ba.tourlive.model.PositionData;

@Service
public class PositionDataServiceImpl implements PositionDataService {
	@Autowired
	private PositionDataDAO positiondao;

	@Transactional
	public void save(PositionData position) {
		positiondao.save(position);
	}

	@Transactional
	public void update(PositionData position) {
		positiondao.update(position);
	}

	@Transactional
	public void delete(Long id) {
		positiondao.delete(id);
	}

	@Transactional
	public List<PositionData> getAll() {
		return positiondao.getAll();
	}
}