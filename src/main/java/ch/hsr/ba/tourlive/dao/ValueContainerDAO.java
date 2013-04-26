package ch.hsr.ba.tourlive.dao;

import java.util.List;

import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.model.ValueContainer;

public interface ValueContainerDAO {
	public void save(ValueContainer position);

	public void update(ValueContainer position);

	public void delete(Long id);

	public List<ValueContainer> getAll();

	public List<ValueContainer> getAllValueContainerForStage(Stage stage);

	public List<ValueContainer> getAllForStageByDistance(Stage stage);

	public ValueContainer getFirstByStage(Stage stage);

	public List<ValueContainer> getLatestForDeviceByStage(Stage stage);

	public List<ValueContainer> getForStageByDistanceLimitedTo(Stage stage, Long limit);

}
