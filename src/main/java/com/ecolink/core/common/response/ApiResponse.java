package com.ecolink.core.common.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

	private final T data;

	private final Integer status;

	private final String message;

	private ApiResponse(T data, Integer status, String message) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static <T> ApiResponse<T> ok(T data) {
		return new ApiResponse<>(data, 200, "OK");
	}

	public static <T> ApiResponse<T> created(T data) {
		return new ApiResponse<>(data, 201, "Created");
	}
}
