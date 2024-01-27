package com.ecolink.core.bookmark.dto.response;

import com.ecolink.core.store.domain.Store;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record StoreInfoDto(
	@Schema(description = "매장 ID", example = "1")
	Long id,
	@Schema(description = "매장 이름", example = "에코 상점")
	String name
) {

	public static StoreInfoDto of(Store store) {
		return new StoreInfoDto(
			store.getId(),
			store.getName()
		);
	}
}