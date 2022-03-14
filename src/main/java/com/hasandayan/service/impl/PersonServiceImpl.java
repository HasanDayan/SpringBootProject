package com.hasandayan.service.impl;

import java.io.Serializable;

import com.hasandayan.service.PersonService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hasandayan.model.Person;
import com.hasandayan.repository.PersonRepository;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepository;

	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public JpaRepository<Person, Serializable> getRepository() {
		return personRepository;
	}

}
