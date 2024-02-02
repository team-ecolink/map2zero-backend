package com.ecolink.core.event.dto.request;

import com.ecolink.core.event.constant.EventStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EventListRequest {

	@Parameter(description = "이벤트 진행 상황", example = "ACTIVE")
	private final EventStatus status;

	public EventListRequest(EventStatus status) {
		this.status = status;
	}
}
