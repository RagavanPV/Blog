package com.ragavan.dao;

import java.util.List;

import com.ragavan.model.Rating;

public interface RatingDAO {

	int save(Rating rating);

	int update(Rating rating);

	int delete(int id);

	List<Rating> list();

}