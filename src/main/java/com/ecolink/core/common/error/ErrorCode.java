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
	 * 상점 관련 오류
	 */
	STORE_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "S-001", "주어진 식별자로 스토어를 찾을 수 없습니다."),
	/**
	 * 북마크 관련 오류
	 */
	BOOKMARK_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "B-001", "북마크가 이미 존재합니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
