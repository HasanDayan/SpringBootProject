package com.hasandayan.controller;

import java.util.List;

import com.hasandayan.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hasandayan.model.Article;

@Controller
public class HomeController {

	private final ArticleService articleService;

	public HomeController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@GetMapping({ "", "/" })
	public String mainPage() {
		return "redirect:/home";
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}

	@GetMapping("home")
	public String home(Model model) {
		List<Article> results = articleService.findAll();
		model.addAttribute("articles", results);
		return "home";
	}
}
