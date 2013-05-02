package ch.hsr.ba.tourlive.dao;

import java.util.List;

import ch.hsr.ba.tourlive.model.MarchTableItem;
import ch.hsr.ba.tourlive.model.Stage;

public interface MarchTableItemDAO {
	public void save(MarchTableItem mti);

	public void update(MarchTableItem mti);

	public void delete(Long id);

	public List<MarchTableItem> getAllByStage(Stage stage);

}
