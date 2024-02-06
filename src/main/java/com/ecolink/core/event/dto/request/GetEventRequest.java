package com.ecolink.core.event.dto.request;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetEventRequest {

	@Parameter(description = "검색을 시작할 커서의 id(포함) 최초 조회시에는 입력하지 말아주세요")
	@Nullable
	@Positive
	private final Long cursor;

	@Parameter(description = "검색 사이즈")
	@NotNull
	@Range(min = 1, max = 30)
	private final Integer size;

	public GetEventRequest(@Nullable Long cursor, Integer size) {
		this.cursor = cursor;
		this.size = size;
	}
}
