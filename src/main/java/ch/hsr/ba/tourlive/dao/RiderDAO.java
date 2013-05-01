package ch.hsr.ba.tourlive.dao;

import java.util.List;

import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.model.rider.Rider;

public interface RiderDAO {
	public void save(Rider rider);

	public void update(Rider rider);

	public void delete(Long id);

	public List<Rider> getAll();

	public Rider getRiderById(Long id);

	public List<Rider> getAllbyStage(Stage stage);

	public List<Rider> getTopTenByStage(Stage stage);
}
