package com.ecolink.core.common.error.exception;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.GeneralException;
import com.ecolink.core.user.constant.UserType;

import lombok.Getter;

@Getter
public class DuplicatedEmailException extends GeneralException {

	private final UserType requestedType;
	private final UserType existingType;

	public DuplicatedEmailException(ErrorCode errorCode, UserType requestedType, UserType existingType) {
		super(errorCode);
		this.requestedType = requestedType;
		this.existingType = existingType;
	}

}
