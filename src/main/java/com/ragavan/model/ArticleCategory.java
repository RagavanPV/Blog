package com.ragavan.model;

public class ArticleCategory {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Article getArticleId() {
		return articleId;
	}
	public void setArticleId(Article articleId) {
		this.articleId = articleId;
	}
	public Category getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}
	private int id;
	private Article articleId;
	private Category categoryId;
}
