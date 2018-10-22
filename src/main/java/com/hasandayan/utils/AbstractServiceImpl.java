package com.hasandayan.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public abstract class AbstractServiceImpl<T> {

	public T save(T t) {
		return getRepo().save(t);
	}

	public Iterable<T> findAll() {
		return getRepo().findAll();
	}

	public Optional<T> findById(Serializable id) {
		return getRepo().findById(id);
	}

	public void delete(Serializable id) {
		getRepo().deleteById(id);
	}

	public abstract CrudRepository<T, Serializable> getRepository();

	public CrudRepository<T, Serializable> getRepo() {
		return getRepository();
	}

}
