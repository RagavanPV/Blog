package com.ragavan.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ragavan.dao.ArticleCategoryDAO;
import com.ragavan.exception.ServiceException;
import com.ragavan.exception.ValidationException;
import com.ragavan.model.ArticleCategory;
import com.ragavan.service.ArticleCategoryService;
import com.ragavan.validator.ArticleCategoryValidator;
@Service
public class ArticleCategoryServiceImplementation implements ArticleCategoryService {
	@Autowired
	ArticleCategoryDAO dao;
	ArticleCategoryValidator articleCategoryValidator=new ArticleCategoryValidator();

	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleCategoryService#saveService(com.ragavan.model.ArticleCategory)
	 */
	@Override
	public int saveService(ArticleCategory articleCategory) throws ServiceException {
		try {
			articleCategoryValidator.validateSave(articleCategory);
			return dao.save(articleCategory);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleCategoryService#updateService(com.ragavan.model.ArticleCategory)
	 */
	@Override
	public int updateService(ArticleCategory articleCategory) throws ServiceException {
		try {
			articleCategoryValidator.validateUpdate(articleCategory);
			return dao.update(articleCategory);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleCategoryService#deleteService(int)
	 */
	@Override
	public int deleteService(int id) throws ServiceException {
		try {
			articleCategoryValidator.validateDelete(id);
			return dao.delete(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleCategoryService#listService()
	 */
	@Override
	public List<ArticleCategory> listService() {
		return dao.list();
	}
}
