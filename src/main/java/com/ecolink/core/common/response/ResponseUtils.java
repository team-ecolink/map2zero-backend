package com.ecolink.core.common.response;

import com.ecolink.core.common.error.ErrorResponse;

public class ResponseUtils {

	/**
	 * 에러
	 */

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
