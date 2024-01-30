package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;

public class DuplicatedNicknameException extends GeneralException {
	public DuplicatedNicknameException(ErrorCode errorCode) {
		super(errorCode);
	}
}
