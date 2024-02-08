package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;

public class InvalidProductNameException extends GeneralException {

	public InvalidProductNameException(ErrorCode errorCode) {
		super(errorCode);
	}

}
