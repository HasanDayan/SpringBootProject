package com.hasandayan.dto;

import com.hasandayan.model.Person;

public class PersonDTO {

    private Long id;
    private String name;
    private String country;

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

    public Person toObject() {
        Person person = new Person();

        person.setId(this.id);
        person.setName(this.name);
        person.setCountry(this.country);

        return person;
    }
}
