package com.ecolink.core.common.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	/**
	 * Etc
	 */
	INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "E-001", "잘못된 요청입니다."),

	/**
	 * 아바타 관련 오류
	 */
	AVATAR_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "A-001", "주어진 식별자로 아바타를 찾을 수 없습니다."),

	/**
	 * 인증 관련 오류
	 */
	INVALID_PROVIDER(HttpStatus.BAD_REQUEST, "OA-001", "유효하지 않은 provider입니다."),
	EMAIL_IS_REGISTER_WITH_ANOTHER_PROVIDER(HttpStatus.BAD_REQUEST, "OA-002", "같은 이메일이 다른 소셜 로그인 플랫폼으로 가입되어 있습니다."),

	/**
	 * 닉네임 관련 오류
	 */
	FAIL_TO_FIND_UNIQUE_NICKNAME(HttpStatus.INTERNAL_SERVER_ERROR, "N-001", "유일한 닉네임을 찾는데 실패했습니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

}
