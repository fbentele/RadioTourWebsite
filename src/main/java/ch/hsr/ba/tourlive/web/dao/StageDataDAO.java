package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.StageData;

public interface StageDataDAO {
	public void save(StageData stagedata);

	public void update(StageData stagedata);

	public void delete(Long id);

	public List<StageData> getAll();
}
