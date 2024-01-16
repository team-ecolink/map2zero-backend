package com.ecolink.core.common.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
	private final Integer status;
	private final String message;
	private final T data;

	private ApiResponse(Integer status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static <T> ApiResponse<T> of(Integer status, String message, T data) {
		return new ApiResponse<>(status, message, data);
	}
}
