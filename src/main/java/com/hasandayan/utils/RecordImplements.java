package com.hasandayan.utils;

import java.io.Serializable;
import java.util.Optional;

public interface RecordImplements<T> {

	public T save(T t);

	public Iterable<T> findAll();

	public Optional<T> findById(Serializable id);

	public void delete(Serializable id);

}
