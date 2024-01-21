package com.ecolink.core.store.dto;

import com.ecolink.core.store.domain.StoreTag;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreTagDto {

	@Schema(description = "시설물 아이콘 아이디", example = "1")
	Long id;
	@Schema(description = "시설물 아이콘 이름", example = "반려동물 동반")
	String name;

	public StoreTagDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public static StoreTagDto of(StoreTag storeTag) {
		return new StoreTagDto(storeTag.getId(), storeTag.getTag().getName());
	}
}
