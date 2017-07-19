package com.ragavan.validator;

import static com.ragavan.util.ValidationUtil.isInvalidInteger;
import static com.ragavan.util.ValidationUtil.isInvalidString;

import com.ragavan.exception.ValidationException;
import com.ragavan.model.Role;

public class RoleValidator {
	public void validateSave(Role role) throws ValidationException {
		isInvalidString(role.getRoleName(), "Invalid name");
	}

	public void validateUpdate(Role role) throws ValidationException {
		isInvalidString(role.getRoleName(), "Invalid name");
		isInvalidInteger(role.getId(), "Invalid user id");
	}

	public void validateDelete(int id) throws ValidationException {
		isInvalidInteger(id, "Invalid id");
	}
}
