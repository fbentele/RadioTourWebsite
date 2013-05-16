package ch.hsr.ba.tourlive.web.dao;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.rider.Rider;

public interface RiderDAO {
	public void save(Rider rider);

	public void update(Rider rider);

	public void delete(Long id);

	public List<Rider> getAll();

	public Rider getRiderById(Long id);

	public List<Rider> getAllByStage(Stage stage);

	public List<Rider> getTopTenByStage(Stage stage);

	public Rider getRiderByStartNrForStage(Stage stage, Integer startNr);

}
