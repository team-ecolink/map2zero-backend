package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;
public class BookmarkNotFoundException extends GeneralException {

	public BookmarkNotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}
}
