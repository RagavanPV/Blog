package com.ragavan.service;

import java.util.List;

import com.ragavan.exception.ServiceException;
import com.ragavan.model.Article;
import com.ragavan.model.User;

public interface ArticleService {

	int saveService(Article article) throws ServiceException;

	int updateService(Article article, String title) throws ServiceException;

	int updateByIdService(Article article) throws ServiceException;

	String getUserIdByArticleId(int articleId);

	String getEmailByArticleId(int articleId);

	int deleteService(int id) throws ServiceException;

	List<Article> listService();

	List<Article> listByUserService(int userId) throws ServiceException;

	List<Article> listOtherUserService(int userId) throws ServiceException;

	boolean publishArticleService(Article article, User user) throws ServiceException;

	boolean updateArticleService(Article article, User user, String title) throws ServiceException;

	boolean deleteArticleService(Article article) throws ServiceException;

}