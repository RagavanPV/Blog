package com.ragavan.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.ragavan.exception.ValidationException;

public class ValidationUtil {
	private ValidationUtil() {

	}

	public static boolean isValidString(String name) {
		return name == null || "".equals(name.trim());
	}

	public static void isInvalidString(String name, String message) throws ValidationException {
		if (name == null || "".equals(name.trim())) {
			throw new ValidationException(message);
		}
	}

	public static boolean isValidInteger(int num) {
		return num <= 0;
	}

	public static void isInvalidInteger(int num, String message) throws ValidationException {
		if (num <= 0) {
			throw new ValidationException(message);
		}
	}

	public static boolean isValidTime(LocalTime time) {
		return time == null;
	}

	public static void isInvalidTime(LocalTime time, String message) throws ValidationException {
		if (time == null) {
			throw new ValidationException(message);
		}
	}

	public static boolean isValidDateTime(LocalDateTime dateTime) {
		return dateTime == null;
	}

	public static void isInvalidDateTime(LocalDateTime dateTime, String message) throws ValidationException {
		if (dateTime == null) {
			throw new ValidationException(message);
		}
	}
}