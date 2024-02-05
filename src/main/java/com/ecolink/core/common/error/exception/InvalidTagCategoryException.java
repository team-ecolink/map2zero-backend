package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;

public class InvalidTagCategoryException extends GeneralException {
	public InvalidTagCategoryException(ErrorCode errorCode) {
		super(errorCode);
	}
}
