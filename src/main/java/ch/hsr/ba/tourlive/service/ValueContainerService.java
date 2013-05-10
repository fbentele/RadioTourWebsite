package ch.hsr.ba.tourlive.service;

import java.util.HashMap;
import java.util.List;

import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.model.ValueContainer;

public interface ValueContainerService {

	public void save(ValueContainer devicedata);

	public void update(ValueContainer devicedata);

	public void delete(Long id);

	public List<ValueContainer> getAll();

	public List<ValueContainer> getAllValueContainerForStage(Stage stage);

	public List<ValueContainer> getAllForStageByDistance(Stage stage);

	public List<ValueContainer> getAllForStageByDistance(Stage stage, Long limit);

	public ValueContainer getFirstByStage(Stage stage);

	public ValueContainer getFirstByStage(Stage stage, Long limit);

	public List<ValueContainer> getLatestForDeviceByStage(Stage stage);

	public List<ValueContainer> getLatestForDeviceByStage(Stage stage, Long limit);

	public List<ValueContainer> getForStageByDistanceLimitedTo(Stage stage, Long limit);

	public HashMap<Long, Integer> getDeficiteToLeaderForStage(Stage stage);

	public HashMap<Long, Integer> getDeficiteToLeaderForStage(Stage stage, Long limit);

}
