package com.hasandayan.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hasandayan.model.Article;
import com.hasandayan.repository.ArticleRepository;
import com.hasandayan.utils.BaseService;

@Service
@Transactional
public class ArticleService implements BaseService<Article> {

	private final ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	public JpaRepository<Article, Serializable> getRepository() {
		return articleRepository;
	}

}
