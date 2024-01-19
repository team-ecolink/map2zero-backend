package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;

public class EntityNotFoundException extends GeneralException {

	public EntityNotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}

}
