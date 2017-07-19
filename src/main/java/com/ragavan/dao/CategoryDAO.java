package com.ragavan.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.ragavan.model.Article;
import com.ragavan.model.Category;

public interface CategoryDAO {

	int save(Category category);

	int update(Category category);

	int delete(int id);

	List<Category> list();

	List<Category> listCategory();

	List<Category> listByUserId(int userId);

	int functionGetCategoryId(String name, int userIdVar);

	void insertCategory(Article article, Category category) throws DuplicateKeyException;

	List<Article> viewByCategory(String nam);

}