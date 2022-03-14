package com.hasandayan.service.impl;

import java.io.Serializable;

import com.hasandayan.service.ArticleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hasandayan.model.Article;
import com.hasandayan.repository.ArticleRepository;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	private final ArticleRepository articleRepository;

	public ArticleServiceImpl(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	public JpaRepository<Article, Serializable> getRepository() {
		return articleRepository;
	}

}
