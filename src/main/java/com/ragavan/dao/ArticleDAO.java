package com.ragavan.dao;

import java.util.List;

import com.ragavan.model.Article;
import com.ragavan.model.User;

public interface ArticleDAO {

	int save(Article article);

	int update(Article article, String title);

	int updateById(Article article);

	String getUserNameByArticleId(int articleId);

	int delete(int id);

	List<Article> list();

	List<Article> listByUser(int userId);

	List<Article> listOtherUser(int userId);

	int functionGetArticleId(String name, int userIdVar);

	String functionGetEmailIdByArticleId(int articleIdVar);

	boolean publishArticle(Article article, User user);

	boolean updateArticle(Article article, User user, String title);

	boolean deleteArticle(Article article);

}