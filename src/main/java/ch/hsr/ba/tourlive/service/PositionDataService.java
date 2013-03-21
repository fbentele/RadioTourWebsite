package ch.hsr.ba.tourlive.service;

import java.util.List;

import ch.hsr.ba.tourlive.model.PositionData;

public interface PositionDataService {
	public void save(PositionData position);

	public void update(PositionData position);

	public void delete(Long id);

	public List<PositionData> getAll();
}
