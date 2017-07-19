package com.ragavan.dto;

public class CommentDTO {
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
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	private int id;
	private ArticleDTO articleId;
	private int userId;
	private String commentText;
}
