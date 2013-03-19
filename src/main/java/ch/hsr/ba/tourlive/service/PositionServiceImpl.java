package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.dao.PositionDAO;
import ch.hsr.ba.tourlive.model.Position;

@Service
public class PositionServiceImpl implements PositionService {
	@Autowired
	private PositionDAO positiondao;

	@Transactional
	public void save(Position position) {
		positiondao.save(position);
	}

	@Transactional
	public void update(Position position) {
		positiondao.update(position);
	}

	@Transactional
	public void delete(Long id) {
		positiondao.delete(id);
	}

	@Transactional
	public List<Position> getAllPositions() {
		return positiondao.getAllPosition();
	}

}
