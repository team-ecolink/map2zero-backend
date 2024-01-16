package com.ecolink.core.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResponse<T> {
	private final Integer status;
	private final String message;
	private final T data;

	private ApiResponse(final Integer status, final String message) {
		this.status = status;
		this.message = message;
		this.data = null;
	}

	public static <T> ApiResponse<T> of(Integer status, String message, T data) {
		return new ApiResponse<>(status, message, data);
	}
}