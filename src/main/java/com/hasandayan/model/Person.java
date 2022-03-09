package com.hasandayan.model;

import com.hasandayan.dto.PersonDTO;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sb_person")
public class Person implements Serializable {

	private Long id;
	private String name;
	private String country;

	@Id
	@SequenceGenerator(name = "SEQ_PERSON_GEN", sequenceName = "SEQ_PERSON", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERSON_GEN")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public PersonDTO toDTO() {
		PersonDTO dto = new PersonDTO();

		dto.setId(this.getId());
		dto.setName(this.getName());
		dto.setCountry(this.getCountry());

		return dto;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", country=" + country + "]";
	}

}
