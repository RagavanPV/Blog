package com.ragavan.dto;

public class ArticleCategoryDTO {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArticleDTO getArticleId() {
		return articleId;
	}
	public void setArticleId(ArticleDTO articleId) {
		this.articleId = articleId;
	}
	public CategoryDTO getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(CategoryDTO categoryId) {
		this.categoryId = categoryId;
	}
	private int id;
	private ArticleDTO articleId;
	private CategoryDTO categoryId;
}
