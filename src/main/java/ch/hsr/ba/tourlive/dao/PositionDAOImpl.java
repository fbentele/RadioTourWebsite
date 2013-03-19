package ch.hsr.ba.tourlive.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hsr.ba.tourlive.model.Position;

@Repository
public class PositionDAOImpl implements PositionDAO {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(Position position) {
		sessionFactory.getCurrentSession().save(position);
	}

	@Override
	public void update(Position position) {
		sessionFactory.getCurrentSession().update(position);

	}

	@Override
	public void delete(Long id) {
		Position pos = (Position) sessionFactory.getCurrentSession().load(
				Position.class, id);
		if (null != pos) {
			sessionFactory.getCurrentSession().delete(pos);
		}
	}

	@Override
	public List<Position> getAllPosition() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Position.class).list();
	}
}
