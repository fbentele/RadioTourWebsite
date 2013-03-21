package ch.hsr.ba.tourlive.service;

import java.util.List;

import ch.hsr.ba.tourlive.model.NetData;

public interface NetDataService {
	public void save(NetData netdata);

	public void update(NetData netdata);

	public void delete(Long id);

	public List<NetData> getAll();
}
