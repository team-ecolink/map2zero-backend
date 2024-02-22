package com.ecolink.core.manager.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ManagerApplicationRequest(
	@Schema(description = "입점할 매장 정보")
	StoreRegistrationRequest store
) {
}
