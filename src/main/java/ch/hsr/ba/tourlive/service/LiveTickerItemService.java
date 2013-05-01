package ch.hsr.ba.tourlive.service;

import java.util.List;

import ch.hsr.ba.tourlive.model.LiveTickerItem;
import ch.hsr.ba.tourlive.model.Stage;

public interface LiveTickerItemService {
	public void save(LiveTickerItem lti);

	public void update(LiveTickerItem lti);

	public void delete(Long id);

	public List<LiveTickerItem> getAllByStage(Stage stage);

	public List<LiveTickerItem> getAllByStageLimitedTo(Stage stage, int limit);

}
