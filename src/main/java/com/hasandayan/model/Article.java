package com.hasandayan.model;

import com.hasandayan.dto.ArticleDTO;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sb_article")
public class Article implements Serializable {

	private Long articleId;
	private String title;
	private String category;
	private Person author;

	@Id
	@SequenceGenerator(name = "SEQ_ARTICLE_GEN", sequenceName = "SEQ_ARTICLE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ARTICLE_GEN")
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author_id", referencedColumnName = "id")
	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public ArticleDTO toDTO() {
		ArticleDTO dto = new ArticleDTO();

		dto.setArticleId(this.getArticleId());
		dto.setTitle(this.getTitle());
		dto.setCategory(this.getCategory());
		dto.setAuthor(this.getAuthor().toDTO());

		return dto;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", title=" + title + ", category=" + category + ", author=" + author
				+ "]";
	}
}