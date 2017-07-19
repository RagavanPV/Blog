package com.ragavan.validator;

import static com.ragavan.util.ValidationUtil.isInvalidInteger;

import com.ragavan.exception.ValidationException;
import com.ragavan.model.Rating;

public class RatingValidator {
	public void validateSave(Rating rating) throws ValidationException {
		isInvalidInteger(rating.getArticleId().getId(), "Invalid Article Id");
		isInvalidInteger(rating.getUserId().getId(), "Invalid User Id");
		isInvalidInteger(rating.getRating(), "Invalid Rating");
	}

	public void validateUpdate(Rating rating) throws ValidationException {
		isInvalidInteger(rating.getArticleId().getId(), "Invalid Article Id");
		isInvalidInteger(rating.getUserId().getId(), "Invalid User Id");
		isInvalidInteger(rating.getRating(), "Invalid Rating");
		isInvalidInteger(rating.getLike(), "Invalid Like");
	}

	public void validateDelete(int id) throws ValidationException {
		isInvalidInteger(id, "Invalid id");
	}
}
