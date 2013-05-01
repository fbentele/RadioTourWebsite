package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.dao.LiveTickerItemDAO;
import ch.hsr.ba.tourlive.model.LiveTickerItem;
import ch.hsr.ba.tourlive.model.Stage;

@Service
public class LiveTickerServiceImpl implements LiveTickerItemService {

	@Autowired
	private LiveTickerItemDAO ltidao;

	@Transactional
	public void save(LiveTickerItem lti) {
		ltidao.save(lti);
	}

	@Transactional
	public void update(LiveTickerItem lti) {
		ltidao.update(lti);
	}

	@Transactional
	public void delete(Long id) {
		ltidao.delete(id);
	}

	@Transactional
	public List<LiveTickerItem> getAllByStage(Stage stage) {
		return ltidao.getAllByStage(stage);
	}

	@Transactional
	public List<LiveTickerItem> getAllByStageLimitedTo(Stage stage, int limit) {
		return ltidao.getAllByStageLimitedTo(stage, limit);
	}

}
