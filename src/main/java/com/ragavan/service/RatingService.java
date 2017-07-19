package com.ragavan.service;

import java.util.List;

import com.ragavan.exception.ServiceException;
import com.ragavan.model.Rating;

public interface RatingService {

	int saveService(Rating rating) throws ServiceException;

	int updateService(Rating rating) throws ServiceException;

	int deleteService(int id) throws ServiceException;

	List<Rating> listService();

}