package com.ecolink.core.common.error;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse<T> {

	private final T data;
	private final String code;
	private final String message;

	@Builder
	public ErrorResponse(T data, String code, String message) {
		this.data = data;
		this.code = code;
		this.message = message;
	}
}
