package ch.hsr.ba.tourlive.web.service;

import java.util.HashMap;
import java.util.List;

import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.ValueContainer;

public interface ValueContainerService {

	public void save(ValueContainer devicedata);

	public void update(ValueContainer devicedata);

	public void delete(Long id);

	public void deleteAllFromDevice(Device device);

	public List<ValueContainer> getAllValueContainers();

	public List<ValueContainer> getAllValueContainerForStage(Stage stage);

	public List<ValueContainer> getAllForStageByDistance(Stage stage);

	public List<ValueContainer> getAllForStageByDistance(Stage stage, Long limit);

	public List<ValueContainer> getLatestForDeviceByStage(Stage stage);

	public List<ValueContainer> getLatestForDeviceByStage(Stage stage, Long limit);

	public HashMap<Long, Integer> getDeficiteToLeaderForStage(Stage stage);

	public HashMap<Long, Integer> getDeficiteToLeaderForStage(Stage stage, Long limit);
}
