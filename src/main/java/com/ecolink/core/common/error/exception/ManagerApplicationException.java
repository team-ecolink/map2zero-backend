package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;

public class ManagerApplicationException extends GeneralException {
	public ManagerApplicationException(ErrorCode errorCode) {
		super(errorCode);
	}
}
