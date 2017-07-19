package com.ragavan.model;

public class Comment {
	private int id;
	private Article articleId;
	private User userId;
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
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	private String commentText;
}
