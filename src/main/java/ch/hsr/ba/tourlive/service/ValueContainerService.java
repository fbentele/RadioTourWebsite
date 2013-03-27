package ch.hsr.ba.tourlive.service;

import java.util.List;

import ch.hsr.ba.tourlive.model.ValueContainer;

public interface ValueContainerService {

	public void save(ValueContainer devicedata);

	public void update(ValueContainer devicedata);

	public void delete(Long id);

	public List<ValueContainer> getAll();
}
