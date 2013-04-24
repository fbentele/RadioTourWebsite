package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.dao.RaceDAO;
import ch.hsr.ba.tourlive.model.Race;

@Service
public class RaceServiceImpl implements RaceService {
	@Autowired
	private RaceDAO racedao;

	@Transactional
	public void save(Race race) {
		racedao.save(race);
	}

	@Transactional
	public void update(Race race) {
		racedao.update(race);
	}

	@Transactional
	public void delete(Long id) {
		racedao.delete(id);
	}

	@Transactional
	public List<Race> getAll() {
		return racedao.getAll();
	}

	@Transactional
	public List<Race> getAllVisible() {
		return racedao.getAllVisible();
	}

	@Transactional
	public Race getRaceById(Long id) {
		return racedao.getRaceById(id);
	}

	@Transactional
	public Race getActualTds() {
		return racedao.getActualTds();
	}

	@Transactional
	public Race getRaceBySlug(String slug) {
		return racedao.getRaceBySlug(slug);
	}
}
