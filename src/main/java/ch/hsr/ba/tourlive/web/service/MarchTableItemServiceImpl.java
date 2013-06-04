/**
 * MarchTableItemServiceImpl.java
 * 
 * @author Florian Bentele
 * @date 22.05.2013
 */
package ch.hsr.ba.tourlive.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.web.dao.MarchTableItemDAO;
import ch.hsr.ba.tourlive.web.model.MarchTableItem;
import ch.hsr.ba.tourlive.web.model.Stage;

/**
 * The Class MarchTableItemServiceImpl.
 */
@Service
public class MarchTableItemServiceImpl implements MarchTableService {
	@Autowired
	private MarchTableItemDAO mtidao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.MarchTableService#save(ch.hsr.ba.tourlive
	 * .web.model.MarchTableItem)
	 */
	@Transactional
	public void save(MarchTableItem mti) {
		mtidao.save(mti);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.MarchTableService#update(ch.hsr.ba.tourlive
	 * .web.model.MarchTableItem)
	 */
	@Transactional
	public void update(MarchTableItem mti) {
		mtidao.update(mti);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.MarchTableService#delete(java.lang.Long)
	 */
	@Transactional
	public void delete(Long id) {
		mtidao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hsr.ba.tourlive.web.service.MarchTableService#getAllByStage(ch.hsr
	 * .ba.tourlive.web.model.Stage)
	 */
	@Transactional
	public List<MarchTableItem> getAllByStage(Stage stage) {
		return mtidao.getAllByStage(stage);
	}
}
