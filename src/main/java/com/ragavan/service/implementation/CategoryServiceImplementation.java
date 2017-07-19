package com.ragavan.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.ragavan.dao.CategoryDAO;
import com.ragavan.exception.ServiceException;
import com.ragavan.exception.ValidationException;
import com.ragavan.model.Article;
import com.ragavan.model.Category;
import com.ragavan.service.CategoryService;
import com.ragavan.validator.CategoryValidator;

@Service
public class CategoryServiceImplementation implements CategoryService {
	@Autowired
	CategoryDAO dao;
	CategoryValidator categoryValidator=new CategoryValidator();

	/* (non-Javadoc)
	 * @see com.ragavan.service.CategoryService#saveService(com.ragavan.model.Category)
	 */
	@Override
	public int saveService(Category category) throws ServiceException {
		try {
			categoryValidator.validateSave(category);
			return dao.save(category);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.CategoryService#updateService(com.ragavan.model.Category)
	 */
	@Override
	public int updateService(Category category) throws ServiceException {
		try {
			categoryValidator.validateUpdate(category);
			return dao.update(category);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.CategoryService#deleteService(int)
	 */
	@Override
	public int deleteService(int id) throws ServiceException {
		try {
			categoryValidator.validateDelete(id);
			return dao.delete(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.CategoryService#listService()
	 */
	@Override
	public List<Category> listService() {
		return dao.list();
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.CategoryService#listCategoryService()
	 */
	@Override
	public List<Category> listCategoryService() {
		return dao.listCategory();
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.CategoryService#listByUserIdService(int)
	 */
	@Override
	public List<Category> listByUserIdService(int userId) {
		return dao.listByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.CategoryService#viewByCategoryService(java.lang.String)
	 */
	@Override
	public List<Article> viewByCategoryService(String categoryName) {
		return dao.viewByCategory(categoryName);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.CategoryService#insertCategory(com.ragavan.model.Article, com.ragavan.model.Category)
	 */
	@Override
	public void insertCategory(Article article, Category category) throws ServiceException {
		try {
			categoryValidator.validateSave(category);
			dao.insertCategory(article, category);
		} catch (ValidationException | DuplicateKeyException e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}
}
