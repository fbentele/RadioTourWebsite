package ch.hsr.ba.tourlive.dao;

import java.util.List;

import ch.hsr.ba.tourlive.model.Position;

public interface PositionDAO {
	public void save(Position position);

	public void update(Position position);

	public void delete(Long id);

	public List<Position> getAllPosition();

}