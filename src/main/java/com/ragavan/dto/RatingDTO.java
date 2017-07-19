package com.ragavan.dto;

public class RatingDTO {
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	private int id;
	private ArticleDTO articleId;
	private int userId;
	private int rating;
	private int like;
}
