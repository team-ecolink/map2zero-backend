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

	public static ErrorResponse error(String code, String message) {
		return error(code, message);
	}

	public static <T>ErrorResponse error(String code, String message, T data) {
		return ErrorResponse.builder()
			.code(code)
			.data(data)
			.message(message)
			.build();
	}

}
