package com.ragavan.validator;

import com.ragavan.model.User;
import static com.ragavan.util.ValidationUtil.*;

import com.ragavan.exception.ValidationException;

public class UserValidator {
	private static final String INVALID_ID = "Invalid id";
	private static final String INVALID_EMAIL = "Invalid email";
	private static final String INVALID_USER_NAME = "Invalid User name";
	private static final String INVALID_PASSWORD = "Invalid Password";

	public void validateSave(User user) throws ValidationException {
		isInvalidString(user.getUserName(), INVALID_USER_NAME);
		isInvalidString(user.getPassword(), INVALID_PASSWORD);
		isInvalidString(user.getEmailId(), INVALID_EMAIL);
	}

	public void validateUpdate(User user) throws ValidationException {
		isInvalidString(user.getUserName(), INVALID_USER_NAME);
		isInvalidString(user.getPassword(), INVALID_PASSWORD);
		isInvalidString(user.getEmailId(), INVALID_EMAIL);
		isInvalidInteger(user.getId(), INVALID_ID);
	}

	public void validateDelete(int id) throws ValidationException {
		isInvalidInteger(id, INVALID_ID);
	}

	public void validateUpdateUsername(User user) throws ValidationException {
		isInvalidString(user.getUserName(), INVALID_USER_NAME);
		isInvalidString(user.getEmailId(), INVALID_EMAIL);
	}

	public void validateUpdatePassword(User user) throws ValidationException {
		isInvalidString(user.getPassword(), INVALID_PASSWORD);
		isInvalidString(user.getEmailId(), INVALID_EMAIL);
	}

	public void validateListOne(String name) throws ValidationException {
		isInvalidString(name, INVALID_USER_NAME);
	}

	public void validateFunctionLogin(User user) throws ValidationException {
		isInvalidString(user.getUserName(), INVALID_USER_NAME);
		isInvalidString(user.getPassword(), INVALID_PASSWORD);
	}

	public void validateGetRole(String name) throws ValidationException {
		isInvalidString(name, INVALID_USER_NAME);
	}

	public void validateGetUserId(String name) throws ValidationException {
		isInvalidString(name, INVALID_USER_NAME);
	}

}
