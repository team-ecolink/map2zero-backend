package com.ecolink.core.common.response;

import java.util.Map;

import lombok.Getter;

@Getter
public class ErrorResponse<T> {

	private final T data;

	private final String code;

	private final String message;

	private ErrorResponse(T data, String code, String message) {
		this.data = data;
		this.code = code;
		this.message = message;
	}

	public static ErrorResponse<Object> error(String code, String message) {
		return error(Map.of(), code, message);
	}

	public static <T> ErrorResponse<T> error(T data, String code, String message) {
		return new ErrorResponse<>(data, code, message);
	}

}
