package com.ecolink.core.store.dto;

import com.ecolink.core.store.domain.StorePhoto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StorePhotoDto {
	@Schema(description = "사진 URL", example = "https://www.~~~.com")
	String url;
	@Schema(description = "사진 순서", example = "1")
	Integer givenOrder;

	public StorePhotoDto(String url, Integer givenOrder) {
		this.url = url;
		this.givenOrder = givenOrder;
	}

	public static StorePhotoDto of(StorePhoto storePhoto) {
		return new StorePhotoDto(storePhoto.getFile().getUrl(), storePhoto.getGivenOrder());
	}
}
