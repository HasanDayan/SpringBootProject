package com.hasandayan.dto;

import com.hasandayan.model.Article;

public class ArticleDTO {

    private Long articleId;
    private String title;
    private String category;
    private PersonDTO author;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public PersonDTO getAuthor() {
        return author;
    }

    public void setAuthor(PersonDTO author) {
        this.author = author;
    }

    public Article toObject() {
        Article article = new Article();

        article.setArticleId(this.articleId);
        article.setTitle(this.title);
        article.setCategory(this.category);
        article.setAuthor(this.author.toObject());

        return article;
    }
}
