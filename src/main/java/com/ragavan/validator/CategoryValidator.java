package com.ragavan.validator;

import static com.ragavan.util.ValidationUtil.*;

import com.ragavan.exception.ValidationException;
import com.ragavan.model.Category;

public class CategoryValidator {
	public void validateSave(Category category) throws ValidationException {
		isInvalidString(category.getName(), "Invalid name");
		isInvalidInteger(category.getUserId().getId(), "Invalid user id");
	}

	public void validateUpdate(Category category) throws ValidationException {
		isInvalidString(category.getName(), "Invalid name");
		isInvalidInteger(category.getUserId().getId(), "Invalid user id");
	}

	public void validateDelete(int id) throws ValidationException {
		isInvalidInteger(id, "Invalid id");
	}
}
