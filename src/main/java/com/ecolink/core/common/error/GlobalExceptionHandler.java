package com.ecolink.core.common.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecolink.core.common.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 객체, 파라미터 데이터 값이 유효하지 않은 경우
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ErrorResponse<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();

		BindingResult bindingResult = e.getBindingResult();
		for (FieldError er : bindingResult.getFieldErrors()) {
			if (errors.containsKey(er.getField())) {
				errors.put(er.getField(), errors.get(er.getField()) + " " + er.getDefaultMessage());
			} else {
				errors.put(er.getField(), er.getDefaultMessage());
			}
		}

		return ErrorResponse.error(errors, ErrorCode.INVALID_TYPE_VALUE.getCode(),
			ErrorCode.INVALID_TYPE_VALUE.getMessage());
	}

	/**
	 * 커스텀 예외
	 */
	@ExceptionHandler(value = GeneralException.class)
	public ResponseEntity<ErrorResponse<Object>> handleCustomException(GeneralException e) {
		ErrorCode errorCode = e.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus())
			.body(ErrorResponse.error(errorCode.getCode(), errorCode.getMessage()));
	}
}

