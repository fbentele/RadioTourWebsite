package ch.hsr.ba.tourlive.service;

import java.util.List;

import ch.hsr.ba.tourlive.model.Stage;

public interface StageService {
	public void save(Stage stage);

	public void update(Stage stage);

	public void delete(Long id);

	public List<Stage> getAllStages();
}
