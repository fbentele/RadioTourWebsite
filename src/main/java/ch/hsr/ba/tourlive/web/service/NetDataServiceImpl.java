/**
 * NetDataServiceImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.NetDataDAO;
import ch.hsr.ba.tourlive.web.model.NetData;

/**
 * The Class NetDataServiceImpl.
 */
@Service
public class NetDataServiceImpl implements NetDataService {
	@Autowired
	private NetDataDAO netdatadao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.NetDataService#save(ch.hsr.ba.tourlive
	 * .web.model.NetData)
	 */
	@Transactional
	public void save(NetData netdat) {
		netdatadao.save(netdat);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.NetDataService#update(ch.hsr.ba.tourlive
	 * .web.model.NetData)
	 */
	@Transactional
	public void update(NetData netdat) {
		netdatadao.update(netdat);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.NetDataService#delete(java.lang.Long)
	 */
	@Transactional
	public void delete(Long id) {
		netdatadao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hsr.ba.tourlive.web.service.NetDataService#getAll()
	 */
	@Transactional
	public List<NetData> getAll() {
		return netdatadao.getAll();
	}
}
