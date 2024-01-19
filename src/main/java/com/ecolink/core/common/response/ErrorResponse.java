package com.ecolink.core.common.response;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ErrorResponse<T> {

	@Schema(description = "에러가 발생한 데이터")
	private final Map<String, T> data;

	@Schema(description = "에러 코드 [{알파벳}-{숫자}] 형태", example = "E-001")
	private final String code;

	@Schema(description = "에러 메세지", example = "잘못된 요청입니다.")
	private final String message;

	private ErrorResponse(Map<String, T> data, String code, String message) {
		this.data = data;
		this.code = code;
		this.message = message;
	}

	public static ErrorResponse<Object> error(String code, String message) {
		return error(Map.of(), code, message);
	}

	public static <T> ErrorResponse<T> error(Map<String, T> data, String code, String message) {
		return new ErrorResponse<>(data, code, message);
	}

}
