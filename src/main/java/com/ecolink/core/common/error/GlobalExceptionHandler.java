package com.ecolink.core.common.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecolink.core.common.error.exception.DuplicatedEmailException;
import com.ecolink.core.common.response.ErrorResponse;
import com.ecolink.core.user.constant.UserType;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse<Object>> handleException(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(ErrorResponse.error("500", e.getMessage()));
	}

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<ErrorResponse<Object>> handleRuntimeException(RuntimeException e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(ErrorResponse.error("500", e.getMessage()));
	}

	/**
	 * 객체, 파라미터 데이터 값이 유효하지 않은 경우
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse<String>> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();

		BindingResult bindingResult = e.getBindingResult();
		for (FieldError er : bindingResult.getFieldErrors()) {
			if (errors.containsKey(er.getField())) {
				errors.put(er.getField(), errors.get(er.getField()) + " " + er.getDefaultMessage());
			} else {
				errors.put(er.getField(), er.getDefaultMessage());
			}
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ErrorResponse.error(errors, ErrorCode.INVALID_TYPE_VALUE.getCode(),
				ErrorCode.INVALID_TYPE_VALUE.getMessage()));
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

	/**
	 * 소셜 로그인시 다른 프로바이더에서 해당 이메일로 가입했을 경우 발생하는 예외
	 */
	@ExceptionHandler(value = DuplicatedEmailException.class)
	public ResponseEntity<ErrorResponse<UserType>> handleDuplicatedEmailException(DuplicatedEmailException e) {
		ErrorCode errorCode = e.getErrorCode();
		Map<String, UserType> data = Map.of("requestedType", e.getRequestedType(), "existingType", e.getExistingType());

		return ResponseEntity.status(errorCode.getHttpStatus())
			.body(ErrorResponse.error(data, errorCode.getCode(), errorCode.getMessage()));
	}

}

