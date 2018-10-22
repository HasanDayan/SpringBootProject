package com.hasandayan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hasandayan.log.ProjectLog;
import com.hasandayan.model.Article;
import com.hasandayan.model.Person;
import com.hasandayan.service.ArticleService;
import com.hasandayan.service.PersonService;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private PersonService personService;

	ProjectLog logger = ProjectLog.getInstance();

	@ResponseBody
	@RequestMapping(value = "listArticles", method = RequestMethod.GET)
	private String listArticles() {
		List<Article> articles = new ArrayList<>();

		try {
			articles = (List<Article>) articleService.findAll();
			logger.writeLog("info", articles.toString());
			return articles.toString();
		} catch (Exception e) {
			logger.writeLog("error", e.toString());
		}

		return null;
	}

	@RequestMapping(value = "newArticle", method = RequestMethod.GET)
	private String newArticle(ModelMap model) {
		model.addAttribute("article", new Article());
		model.addAttribute("authors", personService.findAll());
		return "articleform";
	}

	@RequestMapping(value = "editArticle", method = RequestMethod.GET)
	private String newArticle(@RequestParam("id") Integer id, ModelMap model) {
		model.addAttribute("article", articleService.findById(id.longValue()).get());
		return "articleform";
	}

	@RequestMapping(value = "saveArticle", method = RequestMethod.POST)
	private String saveArticle(Article article, ModelMap model) {

		try {

			logger.writeLog("info", "1. Alanlar utf-8 e gore cevrilir ");
			article = convertFiledsToUtf8(article);
			
			Long authorId = article.getAuthor().getId();
			Person author = personService.findById(authorId).get();
			article.setAuthor(author);
			

			articleService.save(article);
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

	public Article convertFiledsToUtf8(Article article) {

		article.setTitle(getNormalizedValue(article.getTitle()));
		article.setCategory(getNormalizedValue(article.getCategory()));

		return article;
	}

	public String getNormalizedValue(String value) {

		return value.replaceAll("Ã§", "ç").replaceAll("Ã", "Ç").replaceAll("Ä", "ğ").replaceAll("Ä", "Ğ")
				.replaceAll("Ä±", "ı").replaceAll("I", "I").replaceAll("Ã¶", "ö").replaceAll("Ã", "Ö")
				.replaceAll("Å", "ş").replaceAll("Å", "Ş").replaceAll("Ã¼", "ü").replaceAll("Ã", "Ü")
				.replaceAll("Ä°", "İ");
	}
}
