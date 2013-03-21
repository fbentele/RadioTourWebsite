package ch.hsr.ba.tourlive.dao;

import java.util.List;

import ch.hsr.ba.tourlive.model.PositionData;

public interface PositionDataDAO {
	public void save(PositionData position);

	public void update(PositionData position);

	public void delete(Long id);

	public List<PositionData> getAll();
}