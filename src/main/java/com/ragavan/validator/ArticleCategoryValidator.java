package com.ragavan.validator;

import static com.ragavan.util.ValidationUtil.isInvalidInteger;

import com.ragavan.exception.ValidationException;
import com.ragavan.model.ArticleCategory;

public class ArticleCategoryValidator {
	public void validateSave(ArticleCategory articleCategory) throws ValidationException {
		isInvalidInteger(articleCategory.getArticleId().getId(), "Invalid Article Id");
		isInvalidInteger(articleCategory.getCategoryId().getId(), "Invalid Category Id");
	}

	public void validateUpdate(ArticleCategory articleCategory) throws ValidationException {
		isInvalidInteger(articleCategory.getArticleId().getId(), "Invalid Article Id");
		isInvalidInteger(articleCategory.getCategoryId().getId(), "Invalid Category Id");
		isInvalidInteger(articleCategory.getId(), "Invalid id");
	}

	public void validateDelete(int id) throws ValidationException {
		isInvalidInteger(id, "Invalid id");
	}
}
