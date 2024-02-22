package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;

public class ForbiddenException extends GeneralException {
	public ForbiddenException(ErrorCode errorCode) {
		super(errorCode);
	}
}
