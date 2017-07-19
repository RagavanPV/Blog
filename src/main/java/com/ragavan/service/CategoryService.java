package com.ragavan.service;

import java.util.List;

import com.ragavan.exception.ServiceException;
import com.ragavan.model.Article;
import com.ragavan.model.Category;

public interface CategoryService {

	int saveService(Category category) throws ServiceException;

	int updateService(Category category) throws ServiceException;

	int deleteService(int id) throws ServiceException;

	List<Category> listService();

	List<Category> listCategoryService();

	List<Category> listByUserIdService(int userId);

	List<Article> viewByCategoryService(String categoryName);

	void insertCategory(Article article, Category category) throws ServiceException;

}