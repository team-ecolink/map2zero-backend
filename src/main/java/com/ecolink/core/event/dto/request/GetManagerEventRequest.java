package com.ecolink.core.event.dto.request;

import com.ecolink.core.event.constant.EventStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetManagerEventRequest extends GetEventRequest{

	@Parameter(description = "이벤트 상태")
	@NotNull
	private final EventStatus status;

	public GetManagerEventRequest(@Nullable Long cursor, Integer size, EventStatus status) {
		super(cursor, size);
		this.status = status;
	}
}
