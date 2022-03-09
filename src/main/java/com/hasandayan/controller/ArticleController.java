package com.hasandayan.controller;

import java.util.List;

import com.hasandayan.dto.ArticleDTO;
import com.hasandayan.service.ArticleService;
import com.hasandayan.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hasandayan.log.ProjectLog;
import com.hasandayan.model.Article;
import com.hasandayan.model.Person;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private PersonService personService;

	private static final ProjectLog logger = ProjectLog.getInstance();

	@ResponseBody
	@GetMapping("listArticles")
	public String listArticles() {

		try {
			List<Article> articles = articleService.findAll();
			logger.writeLog("info", articles.toString());
			return articles.toString();
		} catch (Exception e) {
			logger.writeLog("error", e.toString());
		}

		return null;
	}

	@GetMapping("newArticle")
	public String newArticle(Model model) {
		model.addAttribute("article", new ArticleDTO());
		model.addAttribute("authors", personService.findAll());
		return "articleform";
	}

	@GetMapping("editArticle")
	public String newArticle(@RequestParam("id") Integer id, Model model) {
		Article article = articleService.findById(id.longValue()).orElseThrow(() -> new RuntimeException("Article not found"));
		model.addAttribute("article", article.toDTO());
		model.addAttribute("authors", personService.findAll());
		return "articleform";
	}

	@PostMapping("saveArticle")
	public String saveArticle(ArticleDTO article) {

		try {
			logger.writeLog("info", "1. Alanlar utf-8 e gore cevrilir ");
			convertFieldsToUtf8(article);
			
			Long authorId = article.getAuthor().getId();
			Person author = personService.findById(authorId).orElseThrow(() -> new RuntimeException("Person not found"));
			article.setAuthor(author.toDTO());

			articleService.save(article.toObject());
		} catch (Exception e) {
			logger.writeLog("error", e.toString());

			if (article.getArticleId() == null) {
				return "redirect:/newArticle";
			} else {
				return "redirect:/editArticle?id=" + article.getArticleId();
			}
		}

		return "redirect:/home";
	}

	private void convertFieldsToUtf8(ArticleDTO article) {

		article.setTitle(getNormalizedValue(article.getTitle()));
		article.setCategory(getNormalizedValue(article.getCategory()));
	}

	private String getNormalizedValue(String value) {

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
