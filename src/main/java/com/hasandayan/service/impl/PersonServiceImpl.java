package com.hasandayan.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hasandayan.model.Person;
import com.hasandayan.repository.PersonRepository;
import com.hasandayan.service.PersonService;
import com.hasandayan.utils.AbstractServiceImpl;

@Service
@Transactional
public class PersonServiceImpl extends AbstractServiceImpl<Person> implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public CrudRepository<Person, Serializable> getRepository() {
		return personRepository;
	}

}
