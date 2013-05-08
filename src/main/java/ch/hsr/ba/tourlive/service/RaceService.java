package ch.hsr.ba.tourlive.service;

import java.util.List;

import ch.hsr.ba.tourlive.model.Race;

public interface RaceService {
	public void save(Race race);

	public void update(Race race);

	public void delete(Long id);

	public List<Race> getAll();

	public List<Race> getAllVisible();

	public Race getRaceById(Long id);

	public Race getRaceBySlug(String slug);
}
