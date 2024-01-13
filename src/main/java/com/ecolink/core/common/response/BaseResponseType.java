package com.ecolink.core.common.response;
import org.springframework.http.HttpStatus;

public interface BaseResponseType {
	Integer getCode();
	String getMessage();
	HttpStatus getHttpStatus();
}