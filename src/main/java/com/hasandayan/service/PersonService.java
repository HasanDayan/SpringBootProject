package com.hasandayan.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hasandayan.model.Person;
import com.hasandayan.repository.PersonRepository;
import com.hasandayan.utils.BaseService;

@Service
@Transactional
public class PersonService implements BaseService<Person> {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public JpaRepository<Person, Serializable> getRepository() {
		return personRepository;
	}

}
