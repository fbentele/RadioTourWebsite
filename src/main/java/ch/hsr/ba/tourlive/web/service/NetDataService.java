package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import ch.hsr.ba.tourlive.web.model.NetData;

public interface NetDataService {
	public void save(NetData netdata);

	public void update(NetData netdata);

	public void delete(Long id);

	public List<NetData> getAll();
}
