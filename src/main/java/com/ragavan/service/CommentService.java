package com.ragavan.service;

import java.util.List;

import com.ragavan.exception.ServiceException;
import com.ragavan.model.Comment;

public interface CommentService {

	int saveService(Comment comment) throws ServiceException;

	int updateService(Comment comment) throws ServiceException;

	int deleteService(int id) throws ServiceException;

	List<Comment> listService();

	List<Comment> listByArticleIdService(int articleId);

}