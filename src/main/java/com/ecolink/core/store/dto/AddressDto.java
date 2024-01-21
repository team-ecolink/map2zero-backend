package com.ecolink.core.store.dto;

import com.ecolink.core.store.domain.Store;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressDto {

	@Schema(description = "시/도 주소(1차)", example = "서울특별시")
	String province;
	@Schema(description = "시/군/구 주소(2차)", example = "동작구")
	String city;
	@Schema(description = "도로명 주소", example = "서달로12나길 2 1층")
	String roadName;
	@Schema(description = "지번 주소", example = "흑석동 54-149 1층")
	String lotNumber;

	public static AddressDto of(Store store) {
		return AddressDto.builder()
			.province(store.getAddress().getProvince())
			.city(store.getAddress().getCity())
			.roadName(store.getAddress().getRoadName())
			.lotNumber(store.getAddress().getLotNumber())
			.build();
	}
}


