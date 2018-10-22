package com.hasandayan.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.hasandayan.model.Article;
import com.hasandayan.service.ArticleService;

@Controller
public class HomeController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	private String mainPage() {
		return "redirect:/home";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	private String login(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	private String home(ModelMap model) {
		List<Article> results = (List<Article>) articleService.findAll();
		model.addAttribute("articles", results);
		return "home";
	}

}
