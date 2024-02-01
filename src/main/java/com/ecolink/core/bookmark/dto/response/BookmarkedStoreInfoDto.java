package com.ecolink.core.bookmark.dto.response;

import java.util.List;

import com.ecolink.core.common.constant.Address;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.domain.StorePhoto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BookmarkedStoreInfoDto(
	@Schema(description = "매장 ID", example = "1")
	Long id,
	@Schema(description = "매장 이름", example = "에코 상점")
	String name,
	@Schema(description = "매장 사진", example = "내부 사진")
	List<StorePhoto> storePhotos,
	@Schema(description = "매장 주소", example = "서울시 서대문구")
	Address address

) {

	public static BookmarkedStoreInfoDto of(Store store) {
		return new BookmarkedStoreInfoDto(
			store.getId(),
			store.getName(),
			store.getStorePhotos(),
			store.getAddress()
		);
	}
}