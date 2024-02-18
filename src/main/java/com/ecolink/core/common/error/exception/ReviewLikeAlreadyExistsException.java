package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;
public class ReviewLikeAlreadyExistsException extends GeneralException {

	public ReviewLikeAlreadyExistsException(ErrorCode errorCode) {
		super(errorCode);
	}
}
