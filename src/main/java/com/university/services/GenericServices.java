package com.university.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.repository.GenericRepository;

@Service
@Transactional
public abstract class GenericServices<T, ID> {

	private final GenericRepository<T, ID> repository;

	@Autowired
	public GenericServices(GenericRepository<T, ID> repository) {
		super();
		this.repository = repository;
	}

	public void save(T entity) {

		repository.save(entity);
	}

	public void saveAll(List<T> lst) {

		repository.saveAll(lst);
	}

	public List<T> findAll() {

		return repository.findAll();
	}

	public T findById(ID entityId) {
		try {
			return repository.findById(entityId).get();
		} catch (Exception e) {
			return null;
		}
	}

	public void deleteAll() {
		repository.deleteAll();
	}

	public boolean updateById(T entity, ID entityId) {
		Optional<T> optional = repository.findById(entityId);

		if (optional.isPresent()) {
			repository.save(entity);
			return true;
		}
		return false;
	}

	public void deleteById(ID entityId) {
		repository.deleteById(entityId);

	}

	public void delete(T entity) {
		repository.delete(entity);

	}

}
