package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;

public class InvalidProductPriceException extends GeneralException {

	public InvalidProductPriceException(ErrorCode errorCode) {
		super(errorCode);
	}

}
