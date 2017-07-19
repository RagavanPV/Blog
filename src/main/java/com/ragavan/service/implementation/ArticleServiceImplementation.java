package com.ragavan.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.ragavan.dao.ArticleCategoryDAO;
import com.ragavan.dao.ArticleDAO;
import com.ragavan.dao.implementation.ArticleCategoryDAOImplementation;
import com.ragavan.exception.ServiceException;
import com.ragavan.exception.ValidationException;
import com.ragavan.model.Article;
import com.ragavan.model.User;
import com.ragavan.service.ArticleService;
import com.ragavan.validator.ArticleValidator;
@Service
public class ArticleServiceImplementation implements ArticleService {
	@Autowired
	ArticleDAO dao;
	ArticleValidator articleValidator=new ArticleValidator();

	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleService#saveService(com.ragavan.model.Article)
	 */
	@Override
	public int saveService(Article article) throws ServiceException {
		try {
			articleValidator.validateSave(article);
			return dao.save(article);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleService#updateService(com.ragavan.model.Article, java.lang.String)
	 */
	@Override
	public int updateService(Article article, String title) throws ServiceException {
		try {
			articleValidator.validateUpdate(article, title);
			return dao.update(article, title);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleService#updateByIdService(com.ragavan.model.Article)
	 */
	@Override
	public int updateByIdService(Article article) throws ServiceException {
		try {
			articleValidator.validateUpdateById(article);
			return dao.updateById(article);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleService#getUserIdByArticleId(int)
	 */
	@Override
	public String getUserIdByArticleId(int articleId) {
		return dao.getUserNameByArticleId(articleId);

	}
	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleService#getEmailByArticleId(int)
	 */
	@Override
	public String getEmailByArticleId(int articleId) {
		return dao.functionGetEmailIdByArticleId(articleId);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleService#deleteService(int)
	 */
	@Override
	public int deleteService(int id) throws ServiceException {
		try {
			articleValidator.validateDelete(id);
			return dao.delete(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleService#listService()
	 */
	@Override
	public List<Article> listService() {
		return dao.list();
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleService#listByUserService(int)
	 */
	@Override
	public List<Article> listByUserService(int userId) throws ServiceException {
		try {
			articleValidator.validateListByUser(userId);
			return dao.listByUser(userId);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleService#listOtherUserService(int)
	 */
	@Override
	public List<Article> listOtherUserService(int userId) throws ServiceException {
		try {
			articleValidator.validateListByUser(userId);
			return dao.listOtherUser(userId);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleService#publishArticleService(com.ragavan.model.Article, com.ragavan.model.User)
	 */
	@Override
	public boolean publishArticleService(Article article, User user) throws ServiceException {
		try {
			articleValidator.validateSave(article);
			return dao.publishArticle(article, user);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		} catch (DuplicateKeyException d) {
			throw new ServiceException(d.getMessage(), d);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleService#updateArticleService(com.ragavan.model.Article, com.ragavan.model.User, java.lang.String)
	 */
	@Override
	public boolean updateArticleService(Article article, User user, String title) throws ServiceException {
		try {
			articleValidator.validateUpdate(article, title);
			return dao.updateArticle(article, user, title);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.ArticleService#deleteArticleService(com.ragavan.model.Article)
	 */
	@Override
	public boolean deleteArticleService(Article article) throws ServiceException {
		try {
			int articleId = article.getId();
			articleValidator.validateDelete(articleId);
			ArticleCategoryDAO articleCategoryDAO = new ArticleCategoryDAOImplementation();
			articleCategoryDAO.delete(articleId);
			return dao.deleteArticle(article);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
