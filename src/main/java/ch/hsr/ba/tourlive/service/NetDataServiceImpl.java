package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.dao.NetDataDAO;
import ch.hsr.ba.tourlive.model.NetData;

@Service
public class NetDataServiceImpl implements NetDataService {
	@Autowired
	private NetDataDAO netdatadao;

	@Transactional
	public void save(NetData netdat) {
		netdatadao.save(netdat);
	}

	@Transactional
	public void update(NetData netdat) {
		netdatadao.update(netdat);
	}

	@Transactional
	public void delete(Long id) {
		netdatadao.delete(id);
	}

	@Transactional
	public List<NetData> getAll() {
		return netdatadao.getAll();
	}
}
