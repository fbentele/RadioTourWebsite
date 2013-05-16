package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.LiveTickerItem;
import ch.hsr.ba.tourlive.web.model.Stage;

public interface LiveTickerItemService {
	public void save(LiveTickerItem lti);

	public void update(LiveTickerItem lti);

	public void delete(Long id);

	public List<LiveTickerItem> getAllByStage(Stage stage);

	public List<LiveTickerItem> getAllByStageLimitedTo(Stage stage, int limit);

	public List<LiveTickerItem> getAllByStageLimitedTo(Stage stage, int limit, Long untilTime);

}
