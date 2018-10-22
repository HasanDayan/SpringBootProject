package com.hasandayan.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.hasandayan.model.Person;

public interface PersonRepository extends CrudRepository<Person, Serializable> {

}
