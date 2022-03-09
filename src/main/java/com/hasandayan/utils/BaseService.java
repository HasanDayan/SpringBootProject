package com.hasandayan.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseService<T> {

	public default T save(T t) {
		return this.getRepo().save(t);
	}

	public default List<T> findAll() {
		return this.getRepo().findAll();
	}

	public default Optional<T> findById(Serializable id) {
		return this.getRepo().findById(id);
	}

	public default void delete(Serializable id) {
		getRepo().deleteById(id);
	}

	public JpaRepository<T, Serializable> getRepository();

	public default JpaRepository<T, Serializable> getRepo() {
		return getRepository();
	}

}
