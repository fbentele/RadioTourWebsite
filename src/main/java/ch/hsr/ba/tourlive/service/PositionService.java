package ch.hsr.ba.tourlive.service;

import java.util.List;

import ch.hsr.ba.tourlive.model.Position;

public interface PositionService {
	public void save(Position position);

	public void update(Position position);

	public void delete(Long id);

	public List<Position> getAllPositions();
}
