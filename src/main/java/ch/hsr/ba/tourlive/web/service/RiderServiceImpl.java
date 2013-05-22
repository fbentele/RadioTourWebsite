/**
 * RiderServiceImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.RiderDAO;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.rider.Rider;

/**
 * The Class RiderServiceImpl.
 */
@Service
public class RiderServiceImpl implements RiderService {

	/** The rider dao. */
	@Autowired
	RiderDAO riderDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RiderService#save(ch.hsr.ba.tourlive.web
	 * .model.rider.Rider)
	 */
	@Transactional
	public void save(Rider rider) {
		riderDao.save(rider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RiderService#update(ch.hsr.ba.tourlive
	 * .web.model.rider.Rider)
	 */
	@Transactional
	public void update(Rider rider) {
		riderDao.update(rider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.RiderService#delete(java.lang.Long)
	 */
	@Transactional
	public void delete(Long id) {
		riderDao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.RiderService#getAll()
	 */
	@Transactional
	public List<Rider> getAll() {
		return riderDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RiderService#getRiderById(java.lang.Long)
	 */
	@Transactional
	public Rider getRiderById(Long id) {
		return riderDao.getRiderById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RiderService#getAllByStage(ch.hsr.ba.tourlive
	 * .web.model.Stage)
	 */
	@Transactional
	public List<Rider> getAllByStage(Stage stage) {
		return riderDao.getAllByStage(stage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RiderService#getRiderByStartNrForStage
	 * (ch.hsr.ba.tourlive.web.model.Stage, java.lang.Integer)
	 */
	@Transactional
	public Rider getRiderByStartNrForStage(Stage stage, Integer startNr) {
		return riderDao.getRiderByStartNrForStage(stage, startNr);
	}
}
