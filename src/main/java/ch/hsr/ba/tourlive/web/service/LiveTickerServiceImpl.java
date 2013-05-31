/**
 * LiveTickerServiceImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.LiveTickerItemDAO;
import ch.hsr.ba.tourlive.web.model.LiveTickerItem;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Class LiveTickerServiceImpl.
 */
@Service
public class LiveTickerServiceImpl implements LiveTickerItemService {

	@Autowired
	private LiveTickerItemDAO ltidao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.LiveTickerItemService#save(ch.hsr.ba.tourlive
	 * .web.model.LiveTickerItem)
	 */
	@Transactional
	public Long save(LiveTickerItem lti) {
		return ltidao.save(lti);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.LiveTickerItemService#update(ch.hsr.ba
	 * .tourlive.web.model.LiveTickerItem)
	 */
	@Transactional
	public void update(LiveTickerItem lti) {
		ltidao.update(lti);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.LiveTickerItemService#delete(java.lang
	 * .Long)
	 */
	@Transactional
	public void delete(Long id) {
		ltidao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.LiveTickerItemService#getById(java.lang
	 * .Long)
	 */
	@Transactional
	public LiveTickerItem getById(Long id) {
		return ltidao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.LiveTickerItemService#getAllByStage(ch
	 * .hsr.ba.tourlive.web.model.Stage)
	 */
	@Transactional
	public List<LiveTickerItem> getAllByStage(Stage stage) {
		return ltidao.getAllByStage(stage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.LiveTickerItemService#getAllByStageLimitedTo
	 * (ch.hsr.ba.tourlive.web.model.Stage, int, java.lang.Long)
	 */
	@Transactional
	public List<LiveTickerItem> getAllByStageLimitedTo(Stage stage, Long untilTime) {
		return ltidao.getAllByStageLimitedTo(stage, untilTime);
	}
}
