package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;

public class PhotoLimitExceededException extends GeneralException {
	public PhotoLimitExceededException(ErrorCode errorCode) {
		super(errorCode);
	}
}
