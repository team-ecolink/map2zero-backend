package com.ecolink.core.bookmark.dto;

import java.util.List;

import com.ecolink.core.common.constant.Address;
import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.domain.StorePhoto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record StoreInfoDto(
	@Schema(description = "매장 ID", example = "1")
	Long id,
	@Schema(description = "매장 이름", example = "에코 상점")
	String name,
	@Schema(description = "매장 사진")
	List<ImageFile> photos,
	@Schema(description = "매장 주소")
	Address address

) {

	public static StoreInfoDto of(Store store) {
		return new StoreInfoDto(
			store.getId(),
			store.getName(),
			store.getStorePhotos().stream()
				.map(StorePhoto::getFile)
				.toList(),
			store.getAddress()
		);
	}
}