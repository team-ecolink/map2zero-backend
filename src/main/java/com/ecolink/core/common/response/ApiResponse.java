package com.ecolink.core.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

	@Schema(description = "요청한 응답에 대한 데이터")
	private final T data;

	@Schema(description = "HTTP 상태 코드", example = "200")
	private final Integer status;

	@Schema(description = "요청 관련 메세지", example = "OK")
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
