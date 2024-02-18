package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;
public class ReviewLikeNotFoundException extends GeneralException {

	public ReviewLikeNotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}
}
