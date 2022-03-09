package com.hasandayan.controller;

import java.util.List;

import com.hasandayan.dto.PersonDTO;
import com.hasandayan.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hasandayan.log.ProjectLog;
import com.hasandayan.model.Person;

@Controller
public class PersonController {

	@Autowired
	private PersonService personService;

	private static final ProjectLog logger = ProjectLog.getInstance();

	@ResponseBody
	@GetMapping("listPersons")
	public String listPersons() {

		try {
			List<Person> persons = personService.findAll();
			logger.writeLog("info", persons.toString());
			return persons.toString();
		} catch (Exception e) {
			logger.writeLog("error", e.toString());
		}

		return null;
	}
	
	@GetMapping("newAuthor")
	public String newAuthor(Model model) {
		model.addAttribute("person", new Person());
		return "personform";
	}
	
	@GetMapping("editAuthor")
	public String editAuthor(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("person", personService.findById(id.longValue()));
		return "personform";
	}

	@PostMapping("savePerson")
	public String savePerson(PersonDTO person) {

		try {
			logger.writeLog("info", "1. Alanlar utf-8 e gore cevrilir ");
			convertFieldsToUtf8(person);

			personService.save(person.toObject());
		} catch (Exception e) {
			logger.writeLog("error", e.toString());
			
			if (person.getId() == null) {
				return "redirect:/newPerson";
			} else {
				return "redirect:/editPerson?id=" + person.getId();
			}
		}

		return "redirect:/home";
	}

	public void convertFieldsToUtf8(PersonDTO person) {

		person.setName(getNormalizedValue(person.getName()));
		person.setCountry(getNormalizedValue(person.getCountry()));
	}

	public String getNormalizedValue(String value) {

		return value.replace("Ã§", "ç")
				.replace("Ã", "Ç")
				.replace("Ä", "ğ")
				.replace("Ä", "Ğ")
				.replace("Ä±", "ı")
				.replace("Ã¶", "ö")
				.replace("Ã", "Ö")
				.replace("Å", "ş")
				.replace("Å", "Ş")
				.replace("Ã¼", "ü")
				.replace("Ã", "Ü")
				.replace("Ä°", "İ");
	}

}
