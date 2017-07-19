package com.ragavan.dao;

import java.util.List;

import com.ragavan.model.Comment;

public interface CommentDAO {

	int save(Comment comment);

	int update(Comment comment);

	int delete(int id);

	List<Comment> list();

	List<Comment> listByArticleId(int articleId);

}