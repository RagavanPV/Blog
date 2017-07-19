package com.ragavan.dao;

import java.util.List;

import com.ragavan.model.ArticleCategory;

public interface ArticleCategoryDAO {

	int save(ArticleCategory articleCategory);

	int update(ArticleCategory articleCategory);

	int delete(int id);

	List<ArticleCategory> list();

	List<ArticleCategory> listByCategory(int id);

}