package com.ecolink.core.store.dto.response;

import com.ecolink.core.common.constant.Address;
import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.domain.StorePhoto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreTrendResponse {

	@Schema(description = "매장 ID", example = "1")
	private final Long id;

	@Schema(description = "매장 이름", example = "지구샵 연남점")
	private final String name;

	@Schema(description = "매장 홍보 멘트", example = "지구를 지키기 위한 한 걸음")
	private final String summary;

	@Schema(description = "매장 평점", example = "4.8")
	private final double averageScore;

	@Schema(description = "매장 리뷰 수", example = "50")
	private final int reviewCnt;

	@Schema(description = "매장 주소")
	private final Address address;

	@Schema(description = "매장 대표 사진")
	private final ImageFile photo;

	public static StoreTrendResponse of(Store store, StorePhoto storePhoto) {
		return StoreTrendResponse.builder()
			.id(store.getId())
			.name(store.getName())
			.summary(store.getSummary())
			.averageScore(store.roundedAverageScore())
			.reviewCnt(store.getReviewCnt())
			.address(store.getAddress())
			.photo(storePhoto != null ? storePhoto.getFile() : null)
			.build();
	}
}
