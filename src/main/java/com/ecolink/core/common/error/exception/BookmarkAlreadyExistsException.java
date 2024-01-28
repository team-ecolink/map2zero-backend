package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;
public class BookmarkAlreadyExistsException extends GeneralException {

	public BookmarkAlreadyExistsException(ErrorCode errorCode) {
		super(errorCode);
	}
}
