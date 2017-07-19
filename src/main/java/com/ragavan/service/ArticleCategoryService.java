package com.ragavan.service;

import java.util.List;

import com.ragavan.exception.ServiceException;
import com.ragavan.model.ArticleCategory;

public interface ArticleCategoryService {

	int saveService(ArticleCategory articleCategory) throws ServiceException;

	int updateService(ArticleCategory articleCategory) throws ServiceException;

	int deleteService(int id) throws ServiceException;

	List<ArticleCategory> listService();

}