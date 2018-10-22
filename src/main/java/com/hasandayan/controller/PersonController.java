package com.hasandayan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hasandayan.log.ProjectLog;
import com.hasandayan.model.Article;
import com.hasandayan.model.Person;
import com.hasandayan.service.PersonService;

@Controller
public class PersonController {

	@Autowired
	private PersonService personService;

	ProjectLog logger = ProjectLog.getInstance();

	@ResponseBody
	@RequestMapping(value = "listPersons", method = RequestMethod.GET)
	private String listPersons() {
		List<Person> persons = new ArrayList<>();

		try {
			persons = (List<Person>) personService.findAll();
			logger.writeLog("info", persons.toString());
			return persons.toString();
		} catch (Exception e) {
			logger.writeLog("error", e.toString());
		}

		return null;
	}
	
	@RequestMapping(value = "newAuthor", method = RequestMethod.GET)
	private String newAuthor(ModelMap model) {
		model.addAttribute("person", new Person());
		return "personform";
	}
	
	@RequestMapping(value = "editAuthor", method = RequestMethod.GET)
	private String editAuthor(@RequestParam("id") Integer id, ModelMap model) {
		model.addAttribute("person", personService.findById(id.longValue()));
		return "personform";
	}

	@RequestMapping(value = "savePerson", method = RequestMethod.POST)
	private String savePerson(Person person, ModelMap model, HttpSession session) {

		try {

			logger.writeLog("info", "1. Alanlar utf-8 e gore cevrilir ");
			person = convertFiledsToUtf8(person);

			personService.save(person);
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

	public Person convertFiledsToUtf8(Person person) {

		person.setName(getNormalizedValue(person.getName()));
		person.setCountry(getNormalizedValue(person.getCountry()));

		return person;
	}

	public String getNormalizedValue(String value) {

		return value.replaceAll("Ã§", "ç").replaceAll("Ã", "Ç").replaceAll("Ä", "ğ").replaceAll("Ä", "Ğ")
				.replaceAll("Ä±", "ı").replaceAll("I", "I").replaceAll("Ã¶", "ö").replaceAll("Ã", "Ö")
				.replaceAll("Å", "ş").replaceAll("Å", "Ş").replaceAll("Ã¼", "ü").replaceAll("Ã", "Ü")
				.replaceAll("Ä°", "İ");
	}

}
