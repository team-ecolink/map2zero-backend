package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;

public class TagCategoryUnmatchedException extends GeneralException {
	public TagCategoryUnmatchedException(ErrorCode errorCode) {
		super(errorCode);
	}
}
