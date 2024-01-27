package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;

public class NicknameSelectFailException extends GeneralException {
	public NicknameSelectFailException(ErrorCode errorCode) {
		super(errorCode);
	}
}
