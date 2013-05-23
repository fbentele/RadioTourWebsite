/**
 * PositionDataServiceImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.PositionDataDAO;
import ch.hsr.ba.tourlive.web.model.PositionData;

/**
 * The Class PositionDataServiceImpl.
 */
@Service
public class PositionDataServiceImpl implements PositionDataService {
	@Autowired
	private PositionDataDAO positiondao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.PositionDataService#save(ch.hsr.ba.tourlive
	 * .web.model.PositionData)
	 */
	@Transactional
	public Long save(PositionData position) {
		return positiondao.save(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.PositionDataService#update(ch.hsr.ba.tourlive
	 * .web.model.PositionData)
	 */
	@Transactional
	public void update(PositionData position) {
		positiondao.update(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.PositionDataService#delete(java.lang.Long)
	 */
	@Transactional
	public void delete(Long id) {
		positiondao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.PositionDataService#getAll()
	 */
	@Transactional
	public List<PositionData> getAll() {
		return positiondao.getAll();
	}
}