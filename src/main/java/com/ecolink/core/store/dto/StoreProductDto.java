package com.ecolink.core.store.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreProductDto {

	@Schema(description = "매장 상품 ID", example = "1")
	private final Long id;

	@Schema(description = "매장 상품 이름", example = "연필")
	private final String name;

	public StoreProductDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
