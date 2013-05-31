/**
 * RaceServiceImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.RaceDAO;
import ch.hsr.ba.tourlive.web.model.Race;

/**
 * The Class RaceServiceImpl.
 */
@Service
public class RaceServiceImpl implements RaceService {
	@Autowired
	private RaceDAO racedao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RaceService#save(ch.hsr.ba.tourlive.web
	 * .model.Race)
	 */
	@Transactional
	public Long save(Race race) {
		return racedao.save(race);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RaceService#update(ch.hsr.ba.tourlive.
	 * web.model.Race)
	 */
	@Transactional
	public void update(Race race) {
		racedao.update(race);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.RaceService#delete(java.lang.Long)
	 */
	@Transactional
	public void delete(Long id) {
		racedao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.RaceService#getAll()
	 */
	@Transactional
	public List<Race> getAll() {
		return racedao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.RaceService#getAllVisible()
	 */
	@Transactional
	public List<Race> getAllVisible() {
		return racedao.getAllVisible();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RaceService#getRaceById(java.lang.Long)
	 */
	@Transactional
	public Race getRaceById(Long id) {
		return racedao.getRaceById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.RaceService#getRaceBySlug(java.lang.String
	 * )
	 */
	@Transactional
	public Race getRaceBySlug(String slug) {
		return racedao.getRaceBySlug(slug);
	}
}
