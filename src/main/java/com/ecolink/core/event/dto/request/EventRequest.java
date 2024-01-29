package com.ecolink.core.event.dto.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;

@Getter
public class EventRequest {

	@Parameter(description = "페이지 번호", example = "0")
	private final Integer pageNo;
	@Parameter(description = "페이지 사이즈", example = "5")
	private final Integer size;

	public EventRequest(Integer pageNo, Integer size) {
		this.pageNo = pageNo;
		this.size = size;
	}
}
