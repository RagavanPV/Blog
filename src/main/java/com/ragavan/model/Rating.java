package com.ragavan.model;

public class Rating {
	private int id;
	private Article articleId;
	private User userId;
	private int rating;
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
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
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
	private int like;
}
