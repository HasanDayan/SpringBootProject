package com.hasandayan.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hasandayan.model.Person;

public interface PersonRepository extends JpaRepository<Person, Serializable> {

}
