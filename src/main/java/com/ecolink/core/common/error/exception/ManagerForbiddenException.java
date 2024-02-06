package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;

public class ManagerForbiddenException extends GeneralException {
	public ManagerForbiddenException(ErrorCode errorCode) {
		super(errorCode);
	}
}
