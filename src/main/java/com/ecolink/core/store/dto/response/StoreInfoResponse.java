package com.ecolink.core.store.dto.response;

import java.util.List;

import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.domain.StoreTag;
import com.ecolink.core.store.dto.AddressDto;
import com.ecolink.core.store.dto.OperatingHourDto;
import com.ecolink.core.store.dto.StoreTagDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreInfoResponse {

	@Schema(description = "연락처", example = "02-111-1111")
	String contact;
	@Schema(description = "매장 주소")
	AddressDto address;
	@Schema(description = "매장 영업 시간")
	List<OperatingHourDto> operatingHours;
	@Schema(description = "시설물 태그(아이콘)")
	List<StoreTagDto> storeTags;

	public static StoreInfoResponse of(Store store) {
		return StoreInfoResponse.builder()
			.contact(store.getContact())
			.address(AddressDto.of(store))
			.operatingHours(store.getStoreOperatingHour().stream()
				.map(OperatingHourDto::of).toList())
			.storeTags(store.getStoreTags().stream()
				.map(StoreTagDto::of).toList())
			.build();
	}
}
