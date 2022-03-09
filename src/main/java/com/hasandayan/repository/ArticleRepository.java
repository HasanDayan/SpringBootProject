package com.hasandayan.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hasandayan.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Serializable> {

}
