package com.ragavan.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ragavan.dao.CommentDAO;
import com.ragavan.exception.ServiceException;
import com.ragavan.exception.ValidationException;
import com.ragavan.model.Comment;
import com.ragavan.service.CommentService;
import com.ragavan.validator.CommentValidator;

@Service
public class CommentServiceImplementation implements CommentService {
	@Autowired
	CommentDAO dao;
	CommentValidator commentValidator=new CommentValidator();

	/* (non-Javadoc)
	 * @see com.ragavan.service.CommentService#saveService(com.ragavan.model.Comment)
	 */
	@Override
	public int saveService(Comment comment) throws ServiceException {
		try {
			commentValidator.validateSave(comment);
			return dao.save(comment);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.CommentService#updateService(com.ragavan.model.Comment)
	 */
	@Override
	public int updateService(Comment comment) throws ServiceException {
		try {
			commentValidator.validateUpdate(comment);
			return dao.update(comment);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.CommentService#deleteService(int)
	 */
	@Override
	public int deleteService(int id) throws ServiceException {
		try {
			commentValidator.validateDelete(id);
			return dao.delete(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.CommentService#listService()
	 */
	@Override
	public List<Comment> listService() {
		return dao.list();
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.CommentService#listByArticleIdService(int)
	 */
	@Override
	public List<Comment> listByArticleIdService(int articleId) {
		return dao.listByArticleId(articleId);
	}
}
