package ch.hsr.ba.tourlive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hsr.ba.tourlive.dao.RiderDAO;
import ch.hsr.ba.tourlive.model.Stage;
import ch.hsr.ba.tourlive.model.rider.Rider;

@Service
public class RiderServiceImpl implements RiderService {

	@Autowired
	RiderDAO riderDao;

	@Transactional
	public void save(Rider rider) {
		riderDao.save(rider);
	}

	@Transactional
	public void update(Rider rider) {
		riderDao.update(rider);
	}

	@Transactional
	public void delete(Long id) {
		riderDao.delete(id);
	}

	@Transactional
	public List<Rider> getAll() {
		return riderDao.getAll();
	}

	@Transactional
	public Rider getRiderById(Long id) {
		return riderDao.getRiderById(id);
	}

	@Transactional
	public List<Rider> getAllbyStage(Stage stage) {
		return riderDao.getAllbyStage(stage);
	}

	@Transactional
	public List<Rider> getTopTenByStage(Stage stage) {
		return riderDao.getTopTenByStage(stage);
	}

}
