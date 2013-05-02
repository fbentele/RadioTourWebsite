package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.dao.MarchTableItemDAO;
import ch.hsr.ba.tourlive.model.MarchTableItem;
import ch.hsr.ba.tourlive.model.Stage;

@Service
public class MarchTableItemServiceImpl implements MarchTableService {
	@Autowired
	private MarchTableItemDAO mtidao;

	@Transactional
	public void save(MarchTableItem mti) {
		mtidao.save(mti);
	}

	@Transactional
	public void update(MarchTableItem mti) {
		mtidao.update(mti);
	}

	@Transactional
	public void delete(Long id) {
		mtidao.delete(id);
	}

	@Transactional
	public List<MarchTableItem> getAllByStage(Stage stage) {
		return mtidao.getAllByStage(stage);
	}

}
