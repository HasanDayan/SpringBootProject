package com.hasandayan.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.hasandayan.model.Article;

public interface ArticleRepository extends CrudRepository<Article, Serializable> {

}
