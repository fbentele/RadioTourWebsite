package ch.hsr.ba.tourlive.dao;

import java.util.List;

import ch.hsr.ba.tourlive.model.NetData;

public interface NetDataDAO {
	public void save(NetData netdata);

	public void update(NetData netdata);

	public void delete(Long id);

	public List<NetData> getAll();
}
