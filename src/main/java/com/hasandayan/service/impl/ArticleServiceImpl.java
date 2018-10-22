package com.hasandayan.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hasandayan.model.Article;
import com.hasandayan.repository.ArticleRepository;
import com.hasandayan.service.ArticleService;
import com.hasandayan.utils.AbstractServiceImpl;

@Service
@Transactional
public class ArticleServiceImpl extends AbstractServiceImpl<Article> implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public CrudRepository<Article, Serializable> getRepository() {
		return articleRepository;
	}

}
