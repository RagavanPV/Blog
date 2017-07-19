package com.ragavan.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ragavan.dao.RatingDAO;
import com.ragavan.exception.ServiceException;
import com.ragavan.exception.ValidationException;
import com.ragavan.model.Rating;
import com.ragavan.service.RatingService;
import com.ragavan.validator.RatingValidator;
@Service
public class RatingServiceImplementation implements RatingService {
	@Autowired
	RatingDAO dao;
	RatingValidator ratingValidator=new RatingValidator();

	/* (non-Javadoc)
	 * @see com.ragavan.service.RatingService#saveService(com.ragavan.model.Rating)
	 */
	@Override
	public int saveService(Rating rating) throws ServiceException {
		try {
			ratingValidator.validateSave(rating);
			return dao.save(rating);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.RatingService#updateService(com.ragavan.model.Rating)
	 */
	@Override
	public int updateService(Rating rating) throws ServiceException {
		try {
			ratingValidator.validateUpdate(rating);
			return dao.update(rating);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.RatingService#deleteService(int)
	 */
	@Override
	public int deleteService(int id) throws ServiceException {
		try {
			ratingValidator.validateDelete(id);
			return dao.delete(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.RatingService#listService()
	 */
	@Override
	public List<Rating> listService() {
		return dao.list();
	}
}
