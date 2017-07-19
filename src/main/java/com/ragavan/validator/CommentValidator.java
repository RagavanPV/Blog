package com.ragavan.validator;

import static com.ragavan.util.ValidationUtil.*;

import com.ragavan.exception.ValidationException;
import com.ragavan.model.Comment;

public class CommentValidator {
	public void validateSave(Comment comment) throws ValidationException {
		isInvalidInteger(comment.getArticleId().getId(), "Invalid article id");
		isInvalidInteger(comment.getUserId().getId(), "Invalid user id");
		isInvalidString(comment.getCommentText(), "Invalid comment");
	}

	public void validateUpdate(Comment comment) throws ValidationException {
		isInvalidInteger(comment.getArticleId().getId(), "Invalid article id");
		isInvalidInteger(comment.getUserId().getId(), "Invalid user id");
		isInvalidString(comment.getCommentText(), "Invalid comment");
	}

	public void validateDelete(int id) throws ValidationException {
		isInvalidInteger(id, "Invalid id");
	}
}
