package com.hasandayan.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseService<T> {

	default void save(T t) {
		this.getRepo().save(t);
	}

	default List<T> findAll() {
		return this.getRepo().findAll();
	}

	default Optional<T> findById(Serializable id) {
		return this.getRepo().findById(id);
	}

	JpaRepository<T, Serializable> getRepository();

	default JpaRepository<T, Serializable> getRepo() {
		return getRepository();
	}

}
