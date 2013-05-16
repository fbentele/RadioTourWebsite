package ch.hsr.ba.tourlive.web.dao;

import java.util.HashMap;
import java.util.List;

import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.ValueContainer;

public interface ValueContainerDAO {

	public void save(ValueContainer position);

	public void update(ValueContainer position);

	public void delete(Long id);

	public List<ValueContainer> getAll();

	public List<ValueContainer> getAllValueContainerForStage(Stage stage);

	public List<ValueContainer> getAllForStageByDistance(Stage stage);

	public List<ValueContainer> getAllForStageByDistance(Stage stage, Long limit);

	public List<ValueContainer> getLatestForDeviceByStage(Stage stage);

	public List<ValueContainer> getLatestForDeviceByStage(Stage stage, Long limit);

	public HashMap<Long, Integer> getDeficiteToLeaderForStage(Stage stage);

	public HashMap<Long, Integer> getDeficiteToLeaderForStage(Stage stage, Long limit);
}
