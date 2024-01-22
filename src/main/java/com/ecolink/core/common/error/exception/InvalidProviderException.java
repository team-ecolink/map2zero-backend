package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;

public class InvalidProviderException extends GeneralException {
	public InvalidProviderException(ErrorCode errorCode) {
		super(errorCode);
	}
}
