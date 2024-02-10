package com.ecolink.core.store.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MapQueryRequest {

	@Schema(description = "경도", example = "126.9960")
	@Positive
	@NotNull
	private final double x;

	@Schema(description = "위도", example = "37.5601")
	@Positive
	@NotNull
	private final double y;

	public MapQueryRequest(double x, double y) {
		this.x = x;
		this.y = y;
	}

}
