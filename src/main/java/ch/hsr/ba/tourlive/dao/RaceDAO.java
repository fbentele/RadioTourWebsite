package ch.hsr.ba.tourlive.dao;

import java.util.List;

import ch.hsr.ba.tourlive.model.Race;

public interface RaceDAO {
	public void save(Race race);

	public void update(Race race);

	public void delete(Long id);

	public List<Race> getAll();

	public Race getActualTds();
}